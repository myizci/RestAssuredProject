package test_utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanNoAuthBaseTest {
    //http://54.159.232.203:8000/api/spartans

    @BeforeAll

    public static void init() {
        //this will set the part of URL at RestAssured
        RestAssured.baseURI = ConfigurationReader.getProperty("baseUri");
        //"http://54.159.232.203:8000";
        // RestAssured.port = 8000;
        RestAssured.basePath = ConfigurationReader.getProperty("basePath");
    }

    @AfterAll
    public static void cleanUp(){
        RestAssured.reset(); // resets all values back to original values
    }

}
