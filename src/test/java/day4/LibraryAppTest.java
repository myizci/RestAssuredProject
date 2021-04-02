package day4;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import pojo.Spartan;
import spartan_util.SpartanUtil;
import test_utils.SpartanNoAuthBaseTest;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


@DisplayName("Library App Simple Test")
public class LibraryAppTest {


    @BeforeAll
    public static void init() {
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";

    }

    @AfterAll
    public static void cleanUp() {

        reset();
    }


    @DisplayName("test POST /login")
    @Test
    public void testLogin() {
        // librarian69@library
        // KNPXrm3S

        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S").
                when()
                .post("/login").
                then()
                .statusCode(200)
                .log().all()
                .body("token", is(not(blankOrNullString())))
        ;


    }


    @DisplayName("test the token")
    @Test
    public void testGetTokenDeCodeToken() {
        // first send request to POST /login extract token
        // then send request to POST /decode to verify email and other info

        String username="librarian69@library";
        String password = "KNPXrm3S";

        String myToken =
                given()
                        .log().all()
                        .contentType(ContentType.URLENC)
                        .formParam("email",username)
                        .formParam("password",password).
                when()
                        .post("/login").

                then()
                        .log().all()
                        .statusCode(200).
                extract()
                        .body().path("token"); // this is a String


        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("token",myToken).
        when()
                .post("/decode").
        then()
                .statusCode(200)
                .log().all()
                .body("email" ,is(username))
        ;

    }

    @DisplayName("Test GET /dashboard_status endpoint ")
    @Test
    public void testDashboardNumbers(){

        String username="librarian69@library";
        String password = "KNPXrm3S";

        String myToken =
                given()
                        .log().all()
                        .contentType(ContentType.URLENC)
                        .formParam("email",username)
                        .formParam("password",password).
                        when()
                        .post("/login")
                        .path("token");


        given()
                .header("x-library-token",myToken).
                //.contentType(ContentType.URLENC).

        when()
                .get("/dashboard_stats").
        then()
                .log().all()
                .statusCode(200)
                .body("book_count",is("2669"))
                .body("borrowed_books",is("778"))
                .body("users",is("8686"))

        ;

        // alternatively , extract JsonPath object after status code check
        // assert each numbers separately
        JsonPath jp =
                given()
                        .header("x-library-token",myToken).
                        when()
                        .get("/dashboard_stats").
                        then()
                        .statusCode(200).
                        extract()
                        .jsonPath() ;

        assertThat(jp.getInt("book_count"),is(2107));
        assertThat(jp.getInt("borrowed_books"),is(775));
        assertThat(jp.getInt("users"),is(8665));



    }





}