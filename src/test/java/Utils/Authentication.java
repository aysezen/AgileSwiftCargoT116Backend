package Utils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

import static Hooks.API.spec;
import static io.restassured.RestAssured.given;

public class Authentication {

    public static String generateToken(){

        spec.pathParam("pp1","gettoken");

        HashMap<String,Object> reqBody = new HashMap<>();

        reqBody.put("email", "banuyurdakul.admin@agileswiftcargo.com");
        reqBody.put("password","123123123");

        Response response = given().spec(spec).contentType(ContentType.JSON).when().body(reqBody).post("/{pp1}");

        JsonPath respJP =response.jsonPath();

        String token = respJP.get("data.token");

        return token;

    }











}
