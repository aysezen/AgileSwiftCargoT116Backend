package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;

import static Hooks.Api.spec;
import static Hooks.Api.token;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class APISteps {
    String fullPath;
    Response response;

    Faker faker = new Faker();


    @Given("Admin sets the parameters in the path {string}.")
    public void admin_sets_the_parameters_in_the_path(String rawPaths) {
        // https://qa.wonderworldcollege.com//api/visitorsPurposeList

        // api/visitorsPurposeList => rawPaths

        String[] paths = rawPaths.split("/");   //  ["api","visitorsPurposeList"]

        StringBuilder tempParam = new StringBuilder("/{");

        for (int i = 0; i < paths.length; i++) {
            String key = "pp" + (i + 1);
            String value = paths[i].trim();
            spec.pathParam(key, value);

            tempParam.append(key + "}/{");
        }
        tempParam.deleteCharAt(tempParam.lastIndexOf("{"));
        tempParam.deleteCharAt(tempParam.lastIndexOf("/"));

        fullPath = tempParam.toString();
    }
    @Then("send Get request for visitorsPurposeList")
    public void send_get_request_for_visitors_purpose_list() {
        response = given().spec(spec).headers("Authorization","Bearer " + token).when().get(fullPath);
       // response.prettyPrint();
    }
    @Then("verifies that the return response for the visitorsPurposeList Api succesfull")
    public void verifies_that_the_return_response_for_the_visitors_purpose_list_api_succesfull() {

        response.then().assertThat().statusCode(200).body("message", Matchers.equalTo("Success"));

    }

    @Then("send Get Request with invalid credentials to visitorsPurposeList endpoint")
    public void send_get_request_with_invalid_credentials_to_visitors_purpose_list_endpoint() {
        String sahteToken = faker.internet().password(31,32,true,true,true);
       // System.out.println("sahteToken = " + sahteToken);

        response = given().spec(spec)
                .headers("Authorization","Bearer " + sahteToken, "Accept","application/json" )
                .contentType(ContentType.JSON)
                .when().get(fullPath);
    }
    @Then("verifies that the return response for the visitorsPurposeList Api is not successfull")
    public void verifies_that_the_return_response_for_the_visitors_purpose_list_api_is_not_successfull() {

        response.then().assertThat().statusCode(403);

    }
/*
{
            "id": "2",
            "visitors_purpose": "purpose update",
            "description": "came for student visit",
            "created_at": "2023-01-18 06:07:12"
        }
 */
    @Then("Verifies in the Response body with id {string} , visitors_purpose {string}, description {string}, created_at {string},")
    public void verifies_in_the_response_body_with_id_visitors_purpose_description_created_at(String id, String visitors_purpose, String description, String created_at) {

        JsonPath resJP = response.jsonPath();

        assertEquals(id, resJP.get("lists[0].id"));
        assertEquals(visitors_purpose, resJP.get("lists[0].visitors_purpose"));
        assertEquals(created_at, resJP.get("lists[0].created_at"));

    }






}
