package day6;


import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_utils.HR_ORDS_API_BaseTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HR_ORDS_API_Practice extends HR_ORDS_API_BaseTest {

    // GET http://54.159.232.203:1000/ords/hr/api/regions
    // base_uri = http://54.159.232.203:1000
    //base_path = /ords/hr/api
    //resources = /regions



    @DisplayName("GET/ regions test")
    @Test
    public void testAllRegions() {

        //there is no authorization

        //get("/regions").prettyPeek();

        //get("/regions").prettyPeek() ;
        // log the request uri
        // send GET /regions request
        // log the response
        // test status code is 200
        // test the count is 4
        // also test the size of items json array is 4

        given()
                .log().uri().
                when()
                .get("/regions").
                then()
                .log().all()
                .statusCode(200)
                .body("count", equalTo(4))
                .body("items", hasSize(4));

    }

    @DisplayName("GET/ regions test day 1 style")
    @Test
    public void testAllRegions2() {

        Response response = given().log().uri().
                            when().get("/regions")
                            .prettyPeek()
                             ;

        assertThat(response.statusCode(),is(200));
        //verify count field is four
        assertThat(response.path("count"),is(4));
        assertThat(response.path("items"),hasSize(4));

        JsonPath jp= response.jsonPath();

        System.out.println("jp.getList(\"items\").size() = " + jp.getList("items").size());


    }

}
