package stepdefinitions;

import Utils.ConfigReader;
import Utils.ReusableMethods;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.Post_HubReqBody;

import static Hooks.Api.spec;
import static Hooks.Api.token;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class APISteps {
    public static String fullPath;
    Response response;
    Faker faker = new Faker();
    int id;
    String exceptionMesaji = "";
    public static Post_HubReqBody reqBody;

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


    @Given("Hub List endpointi icin gerekli olan path parametreleri set edilir.")
    public void hub_list_endpointi_icin_gerekli_olan_path_parametreleri_set_edilir() {

        spec.pathParams("ali","hub","veli", "list");
       // spec.pathParam("ali","hub").pathParam("veli", "list");


    }


    @Then("Hub List endpointine Get request gonderilir.")
    public void hubListEndpointineGetRequestGonderilir() {

        response = given().spec(spec).headers("Authorization","Bearer " + token).when().get("/{ali}/{veli}");

        response.prettyPrint();
    }

    @Then("Donen responsein Status Code'unun {int} ve Response Message bilgisinin Success oldugunu dogrular.")
    public void donenResponseinStatusCodeUnunVeResponseMessageBilgisininSuccessOldugunuDogrular(int arg0) {

        response.then().assertThat().statusCode(200).body("message",Matchers.equalTo("Success"));

    }

    @Given("Api {string} path parametreleri set edilir")
    public void apiPathParametreleriSetEdilir(String rawPaths) { // hub/1

        String[] paths = rawPaths.split("/"); // [ "hub" , "1" ]

        StringBuilder tempPath = new StringBuilder("/{");   //     /{

        for (int i = 0; i < paths.length; i++) {

            String key = "pp" + (i+1);    // pp1   pp2
            String value = paths[i].trim();   //  hub   1

            spec.pathParam(key,value);

            tempPath.append( key + "}/{" );  //   /{pp1}/{pp2}/{pp3}
        }

        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));

        fullPath = tempPath.toString();
    }

    @Then("Get request gonderilir.")
    public void getRequestGonderilir() {

        response = ReusableMethods.getRequest(token);

        response.prettyPrint();



    }

    @Then("Donen response status kodunun {int} oldugu dogrulanmali")
    public void donenResponseStatusKodununOlduguDogrulanmali(int statusCode) {

        response.then().assertThat().statusCode(statusCode);


    }

    @Then("invalid credentials ile Get request gonderilir")
    public void invalidCredentialsIleGetRequestGonderilir() {

        try{
            response = ReusableMethods.getRequest(ConfigReader.getProperty("gecersizToken"));
        }
        catch(Exception e){
            exceptionMesaji = e.getMessage();
        }
        System.out.println("exceptionMesaji = " + exceptionMesaji);
    }


    @Then("Donen response status kodunun {int} ve message bilgisinin de Unauthenticated. oldugu dogrulanmali")
    public void donenResponseStatusKodununVeMessageBilgisininDeUnauthenticatedOlduguDogrulanmali(int arg0) {

         assertTrue(exceptionMesaji.contains("status code: 401"));
    }

    @Then("Name {string}, phone {string}, address {string}, current balance {string}, status {int} bilgileri ile Post request gonderilir.")
    public void namePhoneAddressCurrentBalanceStatusBilgileriIlePostRequestGonderilir(String name, String phone, String address, String currentBalance, int status) {

         reqBody = new Post_HubReqBody(name,phone,address,currentBalance,status);

         response = ReusableMethods.postRequest(token,reqBody);

         response.prettyPrint();
    }

    @Then("Then Donen response status kodunun {int} ve message bilgisinin de {string} oldugu dogrulanmali.")
    public void thenDonenResponseStatusKodununVeMessageBilgisininDeOlduguDogrulanmali(int statusCode, String message) {
        /*
        {
            "success": true,
            "message": "Hub is added",
            "data": {
                "New Hub ID": 209
            }
        }
 */
        JSONObject inner = new JSONObject();

        inner.put("New Hub ID", 209);

        JSONObject expData = new JSONObject();

        expData.put("success", true);
        expData.put("message", "Hub is added");
        expData.put("data", inner);

        JsonPath respJP = response.jsonPath();

        assertEquals(expData.get("success"), respJP.get("success"));
        assertEquals(expData.get("message"), respJP.get("message"));
        assertEquals(200, response.getStatusCode());
       // assertEquals(expData.getJSONObject("data").get("New Hub ID"), respJP.get("data.New Hub ID"));


    }





}
