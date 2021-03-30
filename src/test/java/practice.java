import test_utils.SpartanNoAuthBaseTest;
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

public class practice {
    /*
     * for example :
     * GET http://www.omdbapi.com/?t=Superman&apiKey=YOUR KEY GOES HERE
     * save and print below information from the response using JsonPath
     *
     *  Title , Year , imdbRating  in correct data type
     *  Get second Ratings source
     *  Get first Ratings value
     *
     *
     * GET http://www.omdbapi.com/?s=Flash&type=series&apiKey=YOUR KEY GOES HERE
     *
     *  save and print 3rd json object fields : Title , Year , imdbID
     *  save and print all of the json array imdbID in to List<String>
     *
     *  print totalResult field value
     *  the request is designed to only give you 10 results per page
     *  Optionally :
     *      send more request if the result is more than 10
     *
     *  eventually save all movie titles from all the pages into List<String>
     */

    @BeforeAll
    public static void init() {
        baseURI = "http://www.omdbapi.com";
       // basePath = "/?t=Superman&apiKey=df9c561d";
    }
    @Test
    public void GodHelpMe() {



        JsonPath jp = get("/?t=Superman&apiKey=df9c561d").jsonPath();
        //response.prettyPeek();
      //  JsonPath jp = response.jsonPath();
        String Title_Superman = jp.getString("Title");
        String year = jp.getString("Year");
        String source = jp.getString("Ratings[1].Source");
        String value = jp.getString("Ratings[0].Value");
        String rating = jp.getString("Rated");

        System.out.println("source = " + source);
        System.out.println("Title_Superman = " + Title_Superman);
        System.out.println("year = " + year);
        System.out.println("rating = " + rating);
        System.out.println("value = " + value);

    }

    @Test
    public void test2(){
       // baseURI = "http://www.omdbapi.com/?s=Flash&type=series&apiKey=df9c561d";


        JsonPath jp = get("/?s=Flash&type=series&apiKey=df9c561d").jsonPath();

        String title = jp.getString("Search[2].Title");
        String year = jp.getString("Search[2].Year");
        String id = jp.getString("Search[2].imdbID");
        List<String> imdb = jp.getList("Search.imdbID");

        System.out.println("title = " + title);
        System.out.println("year = " + year);
        System.out.println("id = " + id);
        System.out.println("imdb = " + imdb.get(9));
        // "tt0236899"
    }

}
