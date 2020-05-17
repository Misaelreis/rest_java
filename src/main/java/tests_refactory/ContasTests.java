package tests_refactory;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import core.BaseTest;
import org.junit.Test;
import utils.BarrigaUtils;

public class ContasTests extends BaseTest {

    @Test
    public void deveIncluirUmaContaComSucesso(){
        given()
                .body("{\"nome\":\"Conta inserida\"}")
                .when()
                .post("/contas")
                .then()
                .statusCode(201)
        ;
    }

    @Test
    public void deveAlterarUmaContaComSucesso(){
        Integer CONTA_ID = BarrigaUtils.getIdContaPeloNome("Conta para alterar");

        given()
                .body("{\"nome\":\"Conta alterada\"}")
                .pathParam("id", CONTA_ID)
                .when()
                .put("/contas/{id}")
                .then()
                .statusCode(200)
                .body("nome", is("Conta alterada"))
        ;
    }

    @Test
    public void naoDeveIncluirUmaContaComMesmoNome(){
        given()
                .body("{\"nome\":\"Conta mesmo nome\"}")
                .when()
                .post("/contas")
                .then()
                .body("error", is("Já existe uma conta com esse nome!"))
                .statusCode(400)
        ;
    }
}
