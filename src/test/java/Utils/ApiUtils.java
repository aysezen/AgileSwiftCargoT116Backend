package Utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static Hooks.Api.spec;
import static io.restassured.RestAssured.given;

public class ApiUtils  {
    static JSONObject reqBody;
    private static RequestSpecification spec;
    public static String generateToken(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
        spec.pathParam("pp1","gettoken");

        reqBody=new JSONObject();

        reqBody.put("email", ConfigReader.getProperty("email"));
        reqBody.put("password", ConfigReader.getProperty("password"));

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(reqBody.toString())
                .post("/{pp1}");

        JsonPath repJP=response.jsonPath();

        String token=repJP.getString("data.token");

        System.out.println("token = " + token);
        return token;
    }
}
