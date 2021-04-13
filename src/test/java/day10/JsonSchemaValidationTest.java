package day10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_utils.SpartanNoAuthBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class JsonSchemaValidationTest extends SpartanNoAuthBaseTest {

    @DisplayName("Check GET /spartans/{id} json schema")
    @Test
    public void test1SpartanJsonSchema(){

        given()
                .pathParam("id",100).
                when().
                get("/spartans/{id}").
                then()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("singleSpartanSchema.json"))
        ;
    }
    @DisplayName("Check GET /spartans json schema")
    @Test
    public void testAllSpartansJsonSchema(){


        when()
                .get("/spartans").
                then()
                .body(matchesJsonSchemaInClasspath("allSpartansSchema.json")  )
                //below is an example if our json file is not in resources but in somewhere else
                //in that case we need to put the full path into a file
                .body(matchesJsonSchema(new File("src/test/java/day10/allSpartansSchema.json")))
        ;
    }


    @DisplayName("Check GET /spartans/search json schema")
    @Test
    public void testSearchSpartansJsonSchema(){

        when().get("/spartans/search").
                then().
                body(matchesJsonSchemaInClasspath("searchSpartansSchema.json"));

    }
}
