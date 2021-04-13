package day9;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import spartan_util.SpartanUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SpartanHW  extends SpartanWithAuthTest{
    @DisplayName("GET /spartans as admin user expect 200")
    @Test
    public void testAdmin(){


        given()
                .auth().basic("admin","admin").
                when()
                .get("/spartans").
                then()
                .log().headers()
                .statusCode(200);
    }


    @DisplayName("Post/spartans as admin user expect ")
    @Test
    public void PostAsAdmin(){

        Spartan spartan =new Spartan();

       spartan.setName("Halid");
       spartan.setGender("Male");
       spartan.setPhone(1234567878L);

       int newID=
        given()
                .log().all()
                .auth().basic("admin","admin").
                contentType(ContentType.JSON).
                body(spartan).
                when().
               post("/spartans").
                then()
                .log().all()
                .statusCode(201).
                extract().jsonPath().getInt("data.id")
        ;
        System.out.println(newID);



    }

    @DisplayName("PUT /spartans/{id} as Admin ")
    @Test
    public void putAsAdmin(){
        Spartan spartan =new Spartan();

        spartan.setName("Nimet");
        spartan.setGender("Female");
        spartan.setPhone(9998887766L);

        given()
                .log().uri()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .pathParam("id",368)
                .body(spartan).
                when()
                .put("/spartans/{id}").
                then()
                .statusCode(204);

        given()
                .log().uri()
                .auth().basic("admin","admin")
                .accept(ContentType.JSON)
                .pathParam("id",368).
                when()
                .get("/spartans/{id}").prettyPeek();

        spartan.setName("Nimet");
        spartan.setGender("Female");
        spartan.setPhone(9998887766L);



    }

    @DisplayName("PATCH /spartans/{id}")
    @Test
    public void patchAsAdmin(){

        String postStrBody = "{\n" +
                "    \"name\": \"John\",\n" +
                "}";
        given()
                .log().uri()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .pathParam("id",368)
                .body("{\"name\": \"John\" }" ).
                when()
                .patch("/spartans/{id}").prettyPeek().
                then()
                .statusCode(204);

        given()
                .log().uri()
                .auth().basic("admin","admin")
                .accept(ContentType.JSON)
                .pathParam("id",368).
                when()
                .get("/spartans/{id}").prettyPeek();
    }

    @DisplayName("DELETE /spartans/{id} as admin user expect 403")
    @Test
    public void testEditor(){
        given()
                .pathParam("id",79)
                .auth().basic("admin","admin").
                when()
                .delete("/spartans/{id}").
                then()
                .log().headers()
                .statusCode(204);
    }

    @DisplayName("Post/spartans as editor user expect 201")
    @Test
    public void PostAsEditor(){

        System.out.println("POST TEST");
        Spartan spartan =new Spartan();

        spartan.setName("Jacqlyn");
        spartan.setGender("Female");
        spartan.setPhone(6667778899L);

int newID=
                given()
                        .log().all()
                        .auth().basic("editor","editor").
                        contentType(ContentType.JSON).
                        body(spartan).
                        when().
                        post("/spartans").
                        then()
                        .log().all()
                        .statusCode(201).
                        extract().jsonPath().getInt("data.id")
        ;


        given()
                .log().uri()
                .auth().basic("editor","editor")
                .accept(ContentType.JSON)
                .pathParam("id",newID).
                when()
                .get("/spartans/{id}").prettyPeek();

        System.out.println("===========================");
        System.out.println("PUT TEST");
        Spartan spartan1=new Spartan();

        spartan1.setName("Jale");
        spartan1.setGender("Female");
        spartan1.setPhone(9998881122L);

        given()
                .log().uri()
                .auth().basic("editor","editor")
                .contentType(ContentType.JSON)
                .pathParam("id",newID)
                .body(spartan1).
                when()
                .put("/spartans/{id}").
                then()
                .statusCode(204);

        given()
                .log().uri()
                .auth().basic("editor","editor")
                .accept(ContentType.JSON)
                .pathParam("id",newID).
                when()
                .get("/spartans/{id}").prettyPeek();

        System.out.println("=========================");

        System.out.println("PATCH TEST");


        given()
                .log().uri()
                .auth().basic("editor","editor")
                .contentType(ContentType.JSON)
                .pathParam("id",newID)
                .body("{\"name\": \"Julide\" }" ).
                when()
                .patch("/spartans/{id}").prettyPeek().
                then()
                .statusCode(204);

        given()
                .log().uri()
                .auth().basic("editor","editor")
                .accept(ContentType.JSON)
                .pathParam("id",newID).
                when()
                .get("/spartans/{id}").prettyPeek();

    }

}
