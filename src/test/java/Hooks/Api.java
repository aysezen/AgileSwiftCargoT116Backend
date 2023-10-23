package Hooks;

import Utils.ApiUtils;
import Utils.ConfigReader;
import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Api {
    public static String token;
    public static RequestSpecification spec;


    @Before(order=0)
    public void setUp(){

        spec=new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("base_url")).build();

    }

    @Before(order=1)
    public void beforeGenerateToken(){

        token= ApiUtils.generateToken();
    }
}
