package day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import test_utils.SpartanNoAuthBaseTest;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import test_utils.SpartanNoAuthBaseTest;

public class SpartanJsonPath extends SpartanNoAuthBaseTest {

    @Test
    public void test1() {
        Response response =
                given()
                        .log().all()
                        .pathParam("id", 10)
                        .when().get("/spartans/{id}")
                        .prettyPeek();

        int myId = response.path("id");
        System.out.println("myId = " + myId);

        JsonPath jsonPath = response.jsonPath();

        int myId1 = jsonPath.getInt("id");
        System.out.println("jsonPath.getInt(\"id\") = " + myId1);

        long myPhoneNumber = jsonPath.getLong("phone");
        System.out.println("myPhoneNumber = " + myPhoneNumber);
        System.out.println("Save the json object as a Map Object: " + jsonPath.getMap(""));

        Map<String, Object> resultJsonInMap = jsonPath.getMap("");
        System.out.println("resultJsonInMap = " + resultJsonInMap);

    }

    @DisplayName("Extract data from GET /spartans")
    @Test
    public void testGetAllSpartans() {

        // Response response = get("/spartans");

        //JsonPath jsonPath = response.jsonPath();

        JsonPath jp = get("/spartans").jsonPath();

        // print the first the id in the json array

        System.out.println("jp.getInt(\"id[0]\") = " + jp.getInt("id[0]"));
        // get the
        System.out.println("jp.getString(\"name[1]\") = " + jp.getString("name[1]"));

        System.out.println("jp.getMap(\"[0]\") = " + jp.getMap("[0]"));

        System.out.println("jp.getInt(\"[0].id\") = " + jp.getInt("[0].id"));
    }


    @DisplayName("Extract data from Fet /spartans/search")
    @Test
    public void testGetSearchSpartans() {

        //  nameContains=Abigale  gender= Male

        JsonPath jp =
                given()
                        .queryParam("nameContains", "Ja")
                        .queryParam("gender", "Female")
                        .log().all()

                        .when().get("/spartans/search")
                        .prettyPeek()
                        .jsonPath();

        //find out first person id, second person name
        //content[0].id

        System.out.println("jp.getInt(\"content[0].id\") = " + jp.getInt("content[0].id"));

        System.out.println("jp.getString(\"Content[1].name\") = " + jp.getString("content[1].name"));

        // store the first json object into a map
        Map<String, Object> firstJsonInMap = jp.getMap("content[0]");

        System.out.println("firstJsonInMap = " + firstJsonInMap);

    }


    @DisplayName("Saving json array fields into List")
    @Test
    public void testSavingJsonArrayFieldsIntoList() {

        JsonPath jp =
                given().queryParam("nameContains", "J")
                        .queryParam("gender", "Male")
                        .log().all()
                        .when()
                        .get("spartans/search")
                        .prettyPeek()
                        .jsonPath();
        //save all the id into a list
        System.out.println("jp.getList(\"content.id\") = " + jp.getList("content.id"));
        System.out.println("jp.getList(\"content.name\") = " + jp.getList("content.name"));
        System.out.println("jp.getList(\"content.phone\") = " + jp.getList("content.phone"));

        //getList has two overloaded version
        //getList("path") --> data type will be determined automatically
        // getList("path", Type.class)  --> data type is explicitly enforced

        List<Integer> allIds = jp.getList("content.id");
        List<Integer> allIds2 = jp.getList("content.id", Integer.class);

        List<String> allNames = jp.getList("content.name", String.class);
        List<String> allNames2 = jp.getList("content.name");

        List<Long> allPhoneNumbers = jp.getList("content.phone", Long.class);
        List<Long> allPhoneNumbers2 = jp.getList("content.phone");

    }


    @DisplayName("Get List Practice foer GET /spartans")
    @Test
    public void testGetListOutOfAllSpartans() {

        JsonPath jp = get("/spartans").jsonPath();
        // save the list into an object and assert the size

        List<Integer> allIds = jp.getList("id", Integer.class); // why not content.id
        List<String> allNames = jp.getList("name", String.class);
        List<Long> allPhones = jp.getList("phone",Long.class);

        assertThat(allIds, hasSize(100));
        assertThat(allNames,hasSize(100));
        assertThat(allPhones, hasSize(100));

    }
//Practice json path for all the requests we have done in Postman
   // ex: get {{movie_api}}?t=Superman&apiKey=YOUR KEY GOES HERE
    // save and print below information from the response using Jsonpath

}
