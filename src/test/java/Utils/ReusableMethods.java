package Utils;



import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Hooks.Api.spec;
import static io.restassured.RestAssured.given;
import static stepdefinitions.APISteps.fullPath;


public class ReusableMethods {

    public static Response response;
    public static Response getRequest(String token) {

     response = given()
                    .spec(spec)
                    .headers("Authorization", "Bearer " + token, "Accept", "application/json")
                .when()
                    .get(fullPath);

     return response;
    }

    public static Response postRequest(String token,Object reqBody) {

        response = given()
                    .spec(spec)
                    .headers("Authorization", "Bearer " + token, "Accept", "application/json")
                    .contentType(ContentType.JSON)
                .when()
                    .body(reqBody)
                    .post(fullPath);

        return response;
    }


}
