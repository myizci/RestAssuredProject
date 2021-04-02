package day5;

import com.sun.glass.events.WheelEvent;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class StarWarsAPI_Test {

    /*

    Interview Questions :
Send request to  GET https://swapi.dev/api/people/
Find out average height of all people showed up in the response
     */

    @BeforeAll
    public static void init() {
        baseURI = "https://swapi.dev";
        basePath = "/api";
    }


    @AfterAll
    public static void cleanup() {
        reset();
    }

    @DisplayName("GET average height of all chars")
    @Test
    public void getAverageHeight() {

        List<Integer> list = get("/people")
                .jsonPath()
                .getList("results.height", Integer.class);
      //  System.out.println(list.size());
        int total = 0;
        for (Integer height : list) {
            total += height;
        }

        int average = total / list.size(); //instead of 10 ,

        System.out.println(average);

    }

    //Above code has only the data from the first page, 10 people's heights
    //There are more than 10 people in starWars
    //get total count from the first page
    //figure out how many pages needs to be visited
    //add to total and find the average

    //Steps
    //Create an empty Integer List
    //Send Get/people
    //capture the heights using jsonPath
    //save first page heights into the list

    //loop from page 3 till the last page
    //get the list of height integer using jsonpath
    //add this to the list

    @DisplayName("Get all heights from all the pages and find average")
    @Test
    public void testGetAllPagesAverageHeight() {
       // int pageCount=0;
        List<Integer> allHeights = new ArrayList<>(); // empty, 82 heights will be added

        JsonPath jp = get("/people").jsonPath();

       int peopleCount= jp.getInt("count"); // =82

        int pageCount = (peopleCount % 10==0)  ? peopleCount / 10  :  (peopleCount / 10)+1 ;
//
//       if(peopleCount%10==0){
//           pageCount = peopleCount/10;
//       }else{
//           pageCount = (peopleCount/10)+1;
//       }

        List<Integer> firstPageHeights = jp.getList("results.height",Integer.class);
        allHeights.addAll(firstPageHeights);
        //now it is time to loop and get the rest of the pages
        for (int pageNum=2; pageNum<=pageCount ; pageNum++){
            List<Integer> heightsOnThisPage = get("/people?page = " + pageNum)
                    .jsonPath()
                    .getList("results.height",Integer.class);
            System.out.println("heightsOnThisPage = " + heightsOnThisPage);
            allHeights.addAll(heightsOnThisPage);

        }


//       List<Integer> firstPageHeights = jp.getList("results.height",Integer.class);
//        System.out.println("firstPageHeights = " + firstPageHeights);
//       allheights.addAll(firstPageHeights);
//
//        for(int i =2; i<=pageCount; i++ ){
//
//       List<Integer> heights = get("/people?page = "+i).jsonPath().getList("results.height",Integer.class);
//            System.out.println("heights = " + heights);
//       allheights.addAll(heights);
//        }



       int total=0;

       for(Integer each:allHeights){
           total+=each;
       }

       int average = total/allHeights.size();

        System.out.println("average = " + average);


    }





}
