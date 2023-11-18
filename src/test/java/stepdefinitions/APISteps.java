package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

import static Hooks.API.spec;
import static io.restassured.RestAssured.given;

public class APISteps {

    HashMap<String,Object> reqBody;
    Response response;

    @Given("Token create etmek icin gerekli olan pathparametresi set edilir.")
    public void token_create_etmek_icin_gerekli_olan_pathparametresi_set_edilir() {

        spec.pathParam("pp1","gettoken");

    }
    @Then("getToken Post methodu icin gerekli olan ReqBody olusturulur")
    public void get_token_post_methodu_icin_gerekli_olan_req_body_olusturulur() {

        /*
        {
            "email": "banuyurdakul.admin@agileswiftcargo.com",
            "password":"123123123"
        }
         */

        reqBody = new HashMap<>();

        reqBody.put("email", "banuyurdakul.admin@agileswiftcargo.com");
        reqBody.put("password","123123123");

    }
    @Then("getToken icin gerekli olan Request gonderilir")
    public void get_token_icin_gerekli_olan_request_gonderilir() {

        response = given().spec(spec).contentType(ContentType.JSON).when().body(reqBody).post("/{pp1}");
        // response.prettyPeek();
    }
    @Then("GetToken requesti icin donen Response bodysinden Token degeri alinir")
    public void get_token_requesti_icin_donen_response_bodysinden_token_degeri_alinir() {

        JsonPath respJP =response.jsonPath();

        String token = respJP.get("data.token");

        System.out.println("token = " + token);
    }

}
