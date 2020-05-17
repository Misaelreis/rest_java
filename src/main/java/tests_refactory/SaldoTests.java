package tests_refactory;

import core.BaseTest;
import io.restassured.RestAssured;
import org.junit.Test;
import utils.BarrigaUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SaldoTests extends BaseTest {

    @Test
    public void deveCalcularSaldoContas(){
        Integer CONTA_ID = BarrigaUtils.getIdContaPeloNome("Conta para saldo");
        given()
                .when()
                .get("/saldo")
                .then()
                .statusCode(200)
                .body("find{it.conta_id == "+CONTA_ID+"}.saldo", is("534.00"))
        ;
    }
}
