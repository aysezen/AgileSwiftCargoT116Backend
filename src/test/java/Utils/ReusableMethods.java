package Utils;

import io.restassured.response.Response;

import static Hooks.API.spec;
import static Hooks.API.token;
import static io.restassured.RestAssured.given;
import static stepdefinitions.APISteps.fullPath;

public class ReusableMethods {

    static Response response;
    public static Response getRequest(){

        response = given()
                        .spec(spec)
                        .headers("Authorization","Bearer " + token)
                   .when()
                        .get(fullPath);

        return response;
    }

}
