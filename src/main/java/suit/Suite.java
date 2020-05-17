package suit;

import core.BaseTest;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import tests_refactory.AuthTests;
import tests_refactory.ContasTests;
import tests_refactory.MovimentacoesTests;
import tests_refactory.SaldoTests;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
        ContasTests.class,
        MovimentacoesTests.class,
        SaldoTests.class,
        AuthTests.class
})
public class Suite extends BaseTest {
    @BeforeClass
    public static void login(){
        Map<String, String> login = new HashMap<String, String>();
        login.put("email", "zael.au@hotmail.com");
        login.put("senha", "test");

        String TOKEN = given()
                .body(login)
                .when()
                .post("/signin")
                .then()
                .statusCode(200)
                .extract().path("token");

        RestAssured.requestSpecification.header("Authorization", "JWT " + TOKEN);
        RestAssured.get("reset").then().statusCode(200);
    }
}
