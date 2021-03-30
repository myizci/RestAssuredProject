package test_utils;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class LibraryAppBaseTest {

    @BeforeAll
    public static void init() {
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";

    }

    public static String getToken(String username, String password){

        return
                given()
                        .log().all()
                        .contentType(ContentType.URLENC)
                        .formParam("email",username)
                        .formParam("password",password).
                        when()
                        .post("/login")
                        .path("token");

    }






    @AfterAll
    public static void cleanUp() {

        reset();
    }

}
