package Utils;

import io.restassured.http.ContentType;
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

    public static Response postRequest(Object reqBody){

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer " + token)
                .when()
                .body(reqBody)
                .post(fullPath);

        return response;
    }
}
