package day11;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_utils.LibraryAppBaseTest;

import static io.restassured.RestAssured.*;

public class ReusableLibrarySpecTest extends LibraryAppBaseTest {

    @DisplayName("GET /dashboard_stats")
    @Test
    public void testDashboardStats() {

        given()
               .spec(librarianSpec)
                .when()
                .get("/dashboard_stats")
                .prettyPeek()
                .then()
                .statusCode(200)
        ;
    }

    @DisplayName("GET /get_all_users")
    @Test
    public void testGetAllUser(){
        // in class task
        // use the Reusable request specification
        // we build to send the request to get all users
        // build reusable Response specification
        //  status code of 200
        //  content type of json
        //  and log all
        // Add it to the BaseTest
        // try reusing it in both of the tests in this class

        given()
                .spec(librarianSpec)
                .when().get("/get_all_users").
                then()
              //  .spec(librarianResponseSpec); // this is coming from base class
                .spec(getDynamicResponseSpec(200)); // this is coming from method below

    }

    public static ResponseSpecification getDynamicResponseSpec(int statusCode){
        return expect().statusCode(statusCode);
    }
}