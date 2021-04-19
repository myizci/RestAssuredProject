package day11;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import spartan_util.SpartanUtil;
import test_utils.SpartanNoAuthBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_utils.SpartanWithAuthBaseTest;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class SpartanPostSchemaTest extends SpartanWithAuthBaseTest {

    @DisplayName("Test Jsson Schema for Post Response ")
    @Test
    public void testAdd1SpartanResponse(){
        Spartan bodyPOJO = SpartanUtil.getRandomSpartanPOJO();
JsonPath jp=
        given()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(bodyPOJO).
        when()
                .post("/spartans").jsonPath().prettyPeek()
        //then()
            //    .statusCode(201)
              //  .log().body()
                //.body(matchesJsonSchemaInClasspath("spartanPostJsonSchema.json"))
               // .body(matchesJsonSchema(new File("src/test/resources/spartanPostJsonSchema.json")))
        ;

int id= jp.getInt("data.id");
        System.out.println("id = " + id);
    }


}
