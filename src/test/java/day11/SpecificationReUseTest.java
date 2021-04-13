package day11;
import static io.restassured.RestAssured.*;

import day9.SpartanWithAuthTest;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.filter.log.LogDetail.ALL;
import static java.lang.Enum.valueOf;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class SpecificationReUseTest extends SpartanWithAuthTest {
    // use admin credential
    // expect json result from response
    // expect status code 200
    // content type header is json


    static  RequestSpecification requestSpec = given()
            .log().all()
            .auth().basic("admin","admin")
            .accept(ContentType.JSON);

    static ResponseSpecification responseSpec = expect()
            .statusCode(200)
            .contentType(ContentType.JSON).logDetail(LogDetail.ALL);
    ;

    @DisplayName("Admin Get/spartans and expect 200 and json")
    @Test
  public void testAdminGetAll(){

     given().spec(requestSpec). // repeating part
        when()
                .get("/spartans").
                then()
                .spec(responseSpec);

//
//        given()
//                .auth().basic("admin","admin")
//                .accept(ContentType.JSON).
//                when()
//                .get("/spartans").
//                then()
//                .statusCode(200)
//                .contentType(ContentType.JSON);

    }
    @DisplayName("Admin Get/spartans/{id} and expect 200 and json, expect id match")
    @Test
    public void testAdminGetOne(){
        given()
                .pathParam("id",1)
                .spec(requestSpec);

                when()
                .get("/spartans/{id}").
                then()

               .spec(responseSpec)
                .body("id",is(1))
        ;
    }
}
