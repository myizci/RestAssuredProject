package day4;
import org.junit.jupiter.api.*;

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

import test_utils.LibraryAppBaseTest;

import java.util.List;

public class LibraryAppAuthorizedRequestTest extends LibraryAppBaseTest {


    @DisplayName("GET / get_user_by_id")
    @Test
    public void testOneUser(){



    given()
            .header("x-library-token", librarianToken)
            .pathParam("banana",1).
    when()
            .get("/get_user_by_id/{banana}").
    then()
            .log().all()
            .statusCode(200)
            ;
}

    @DisplayName("GET / get_all_user")
    @Test
    public void testGetAllUsers(){


        List<String> allNames =
        given()
                .header("x-library-token", librarianToken).

                when()
                .get("/get_all_users").

                then()
                .log().all()
                .statusCode(200)
         .extract()
                .jsonPath()  //extracting the json path so that we can call getList
                .getList("name",String.class)
        ;


        assertThat(allNames,hasSize(8665));

        Set<String > uniqueNames = new HashSet<>(allNames);
        System.out.println("uniqueNames.size()= " + uniqueNames.size());

    }

@DisplayName("POST /add_book")
    @Test
    public void testAddOneBook(){
/*
name
isbn
year
author
book_category
description

 */

Map<String ,Object> newBook = getRandomBook();
int newBookId=
    given()
            .log().all()
            .header("x-library-token",librarianToken)
            .contentType(ContentType.URLENC)
            // using formParams with s to pass multiple param in one shot
            .formParams(newBook).
    when()
            .post("/add_book").
    then()
            .log().all()
            .statusCode(200).
        extract().
                jsonPath().getInt("book_id")
            ;
// Send additional get request to GET / get_book_by_id/{book_id}
    // to verify all data has been added correctly

        given()
                .header("x-library-token",librarianToken)
                .log().uri()
                .pathParam("papaya",newBookId).
        when()
                .get("/get_book_by_id/{papaya}").
        then()
                .log().body()
                .statusCode(200)
                .body("id", is(newBookId+""))
                .body("name",is(newBook.get("name")))
                .body("author",is(newBook.get("author")))
                .body("isbn",is(newBook.get("isbn")+""))
                .body("year",is(newBook.get("year")+""))
                .body("book_category_id",is(newBook.get("book_category_id")+""))
                .body("description",is(newBook.get("description")))

                ;


}



}
