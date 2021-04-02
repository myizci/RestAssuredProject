package day5;
import com.sun.glass.events.WheelEvent;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class MovieAPI_Practice {

    @DisplayName("Search Movies Json practice")
    @Test
    public void testSearch(){

    JsonPath jp=    given()
                .baseUri("http://www.omdbapi.com")
                .log().all()
                .queryParam("apikey","df9c561d")
                .queryParam("s","Superman")
                .queryParam("type","series").
        when()
                .get().
        then()
               // .log().all()
                .statusCode(200).
        extract()
                .jsonPath();

        List<String > allTitles = jp.getList("Search.Title",String.class);
        System.out.println("allTitles = " + allTitles);

        // assert the size of this list is 10
        assertThat(allTitles,hasSize(10));
        // assert first item contains String Superman
        assertThat(allTitles.get(0),containsString("Superman"));
        // assert it has item "Superman and Lois"
       // assertThat(allTitles.toString(),containsString("Superman and Lois"));
        assertThat(allTitles,hasItem("Superman and Lois"));
        // assert it has items "Superman and Lois" , "The New Adventures of Superman"
        assertThat(allTitles,hasItems("Superman and Lois","The New Adventures of Superman"));
        // assert every item contains superman
        assertThat(allTitles, everyItem(containsString("Superman")));
    }


    @DisplayName("Search Movies Json practice 2 all chained")
    @Test
    public void testSearch2(){
           given()
                .baseUri("http://www.omdbapi.com")
                .log().all()
                .queryParam("apikey","df9c561d")
                .queryParam("s","Superman")
                .queryParam("type","series").
                        when()
                .get().
                        then()
                .log().all()
                .statusCode(200)
                   .body("Search.Title",hasSize(10))
                   .body("Search[0].Title", containsString("Superman"))
                   .body("Search.Title", hasItem("Superman and Lois"))
                   .body("Search.Title",hasItems("Superman and Lois","The New Adventures of Superman"))
                   .body("Search.Title",everyItem(containsString("Superman")))
                   ;

    }
}
