package day3;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import pojo.Spartan;
import test_utils.SpartanNoAuthBaseTest;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import test_utils.SpartanNoAuthBaseTest;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUpdatingData_Test extends SpartanNoAuthBaseTest{

    // you may repeat everything previously in post test for providing body
    // we will just look at map and POJO

    @DisplayName("PUT /spartans/{id} body as Map")
    @Test
    public void testUpdateDataWithMapObject(){


        Map<String ,Object> bodyMap = new LinkedHashMap<>();

        bodyMap.put("name","Hande"); //Hale
        bodyMap.put("gender","Female");
        bodyMap.put("phone",1111112222L);

        given()
                .log().all()
                .pathParam("id",33)
                .contentType(ContentType.JSON)
                .body(bodyMap).
                when()
                .put("spartans/{id}").
                then()
                .statusCode(204);



    }

    @DisplayName("PUT /spartans/{id} body POJO")
    @Test
    public void testUpdateDataWithPOJO(){


        Spartan sp = new Spartan("Yusuf","Male",4445556677L);

        given()
                .log().all()
                .pathParam("id",2)
                .contentType(ContentType.JSON)
                .body(sp).

                when()
                .put("spartans/{id}").

                then()
                .statusCode(204);



    }

    @DisplayName("PATCH /spartans/{id} body as String")
    @Test
    public void testUpdateDataWithString(){

     String patchBody = "{\"phone\":9999998888}";
      given()
      .log().all()
      .pathParam("id",2)
      .contentType(ContentType.JSON)
      .body(patchBody).

              when()
      .patch("/spartans/{id}").

              then()
      .statusCode(204);

    }

    @DisplayName("Test DELETE /spartans/{id} body as String")
    @Test
    public void testDeleteOne(){

        given()
                .log().uri()
                .pathParam("id",2).
                when()
                .delete("/spartans/{id}").
                then()
                .statusCode(equalTo(204));
    }


}
