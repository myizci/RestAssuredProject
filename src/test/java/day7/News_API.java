package day7;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ArticlePOJO;

import java.util.List;

import static io.restassured.RestAssured.*;

public class News_API {
    @BeforeAll
    public static void init(){
        baseURI="https://newsapi.org";
        basePath="/v2";
    }

    @AfterAll
    public static void cleanup(){
        reset();
    }

    @DisplayName("GET /top-headlines")
    @Test
    public void getAuthorAndTitle(){

        Response response =  given()
                .queryParam("country" , "us")
                .queryParam("category" , "business")
                .queryParam("apiKey" , "7e6c1fb8a2804f44a892891bd9aa4b63")
                .when()
                .get("/top-headlines/")
                .then()
                .statusCode(200)
                .extract().response();



       List<ArticlePOJO> allArticles = response.jsonPath().getList("articles" , ArticlePOJO.class);

       //allArticles.forEach(System.out::println);

allArticles.forEach(p->{if(p.getSource().getId()!=null){
    System.out.println("Author: "+p.getAuthor());
    System.out.println("Title: "+p.getTitle());

}});

    }
}
