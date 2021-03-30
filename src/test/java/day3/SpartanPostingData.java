package day3;

import com.sun.javafx.sg.prism.NGImageView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import test_utils.SpartanNoAuthBaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import test_utils.SpartanNoAuthBaseTest;

import java.io.File;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import test_utils.SpartanNoAuthBaseTest;
public class SpartanPostingData extends SpartanNoAuthBaseTest {

    @DisplayName("POST /spartans with String")
    @Test
    public void testPostDataWithStringBody(){

        String postStrBody = "{\n" +
                "    \"id\": 101,\n" +
                "    \"name\": \"Buket\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 8644417676\n" +
                "}";


        given()
                .log().all()
                //.header("Content-type","application/json")
              //  .contentType("application/json")
                .contentType(ContentType.JSON)
                .body(postStrBody)

                .when()
                .post("/spartans")

                .then()
                .log().all()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success",is("A Spartan is Born!"))
                .body("data.name",is("Buket"))
        ;
    }

    @DisplayName("POST /spartans with external file")
    @Test
    public void testPostDataWithJsonFileAsBody(){

        //singleSpartan.json with below content
        /*
        {
    "id": 1,
    "name": "Meade",
    "gender": "Male",
    "phone": 3584128232
}
         */

        File jsonFile = new File("singleSpartan.json");
            given().
                    log().all()
                    .contentType(ContentType.JSON)
                    .body(jsonFile).
                    when()
                    .post("/spartans").
                    then()
                    .log().all()
                    .statusCode(201);

    }

    @DisplayName("POST /spartans with Map Object")
    @Test
    public void testPostDataWithMapObjectAsBody(){

Map<String ,Object> bodyMap = new LinkedHashMap<>();

        bodyMap.put("name","Nukhet");
        bodyMap.put("gender","Female");
        bodyMap.put("phone",4443338899L);

        System.out.println("bodyMap = " + bodyMap);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201);
    }



    @DisplayName("POST /spartans with POJO")
    @Test
    public void testPostDataWithPOJOAsBody(){

        Spartan sp = new Spartan ("Ali","Male",5556667788L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(sp).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201);



    }


}


















