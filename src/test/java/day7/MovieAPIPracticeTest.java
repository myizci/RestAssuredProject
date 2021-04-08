package day7;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Movie;
import pojo.Rating;

import java.util.List;

import static io.restassured.RestAssured.*;
public class MovieAPIPracticeTest {
@BeforeAll
public static void init(){
    baseURI= "http://www.omdbapi.com";
}
    // save the result of your request
    // SEND GET http://www.omdbapi.com/?t=Avenger&apikey=YOUR OWN API KEY goes here
    // save the response into Movie POJO , title Str, year int , Released str ,Language
    // ignore any unknown properties
    // match the json fields to pojo fields

    @DisplayName("GET http://www.omdbapi.com/?t=Superman&apiKey=df9c561d")
    @Test
    public void testMovieToPOJO(){

        Movie m1 = given()
                            .queryParam("apikey","df9c561d")
                            .queryParam("t","Avenger").
                  when()
                            .get()
                                //.jsonPath()
                               // .getObject("",Movie.class)
                                 .as(Movie.class)
                    ;

        System.out.println("m1 = " + m1);


    }

    @DisplayName("GET Search for avenger and save ratings field into List<Rating> ")
    @Test
    public void testMovieRatingToPOJO(){
        List<Rating> allRatings=
                given()
                        .queryParam("apikey","df9c561d")
                        .queryParam("t","Avenger").
                when()
                         .get().jsonPath().getList("Ratings",Rating.class);

        System.out.println("allRatings = " + allRatings);

    }


}
