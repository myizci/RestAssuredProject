package day7;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.SpartanPOJO;
import test_utils.SpartanNoAuthBaseTest;

import java.util.List;

import static io.restassured.RestAssured.*;

public class SpartanDeserialization extends SpartanNoAuthBaseTest {
    //Serialization : Java Object to Json (or any type of text)
    // De-Serialization : Json(text) to Java

    @DisplayName("GET /spartans/{id}")
    @Test
    public void testGetOneData() {

        given()
                .pathParam("id", 16).
                when()
                .get("/spartans/{id}").
                then()
                .log().all()
                .statusCode(200);

        //send same request , store the result into a SpartanPOJO object

        Response response =
                given()
                        .pathParam("id", 16).
                        when()
                        .get("/spartans/{id}");

        SpartanPOJO sp1 = response.as(SpartanPOJO.class);

        System.out.println("sp1 = " + sp1);

        JsonPath jp = response.jsonPath();
        SpartanPOJO sp2 = jp.getObject("", SpartanPOJO.class);
        System.out.println("sp2 = " + sp2);
    }

    @DisplayName("GET /spartans/search")
    @Test
    public void testSearch() {
        //spartans/search?nameContains=J
        //send get request to above endpoint and save first object type SpartanPOJO
        JsonPath jp =
                given()
                        .queryParam("nameContains", "J")
                        .queryParam("gender", "Male").
                        when()
                        .get("/spartans/search").jsonPath();

        //JsonPath jp = response.jsonPath();

        SpartanPOJO sp3 = jp.getObject("content[0]", SpartanPOJO.class);
        List<SpartanPOJO> spList = jp.getList("content", SpartanPOJO.class);
        System.out.println("sp3 = " + sp3);
        System.out.println(spList.size());


        // get all in one chain, let's do the thing

        SpartanPOJO sp4 = given()
                .queryParam("nameContains", "J")
                .queryParam("gender", "Male").
                        when()
                .get("/spartans/search")
                .jsonPath().getObject("content[1]", SpartanPOJO.class);
        System.out.println("sp4 = " + sp4);
    }

    @DisplayName("GET /spartans/search and save as List")
    @Test
    public void testSearchSaveAsList() {
        List<SpartanPOJO> list =
                given()
                        .queryParam("nameContains", "K")
                        .queryParam("gender", "Male").
                        when()
                        .get("/spartans/search").jsonPath().getList("content",SpartanPOJO.class);


        System.out.println("list = " + list);
        list.forEach(p -> System.out.println(p));
        System.out.println(list.size());
    }

}
