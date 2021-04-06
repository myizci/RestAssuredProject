package test_utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
public class SoccerTask {

    @BeforeAll
    public static void init(){

        baseURI ="http://jsonmock.hackerrank.com";
        basePath="/api/football_matches";
    }

    @AfterAll
    public static void cleanup(){
        reset();
    }
    //GET http://jsonmock.hackerrank.com/api/football_matches?year=2012&team1=Arsenal&team2=Chelsea&page=1
    @DisplayName("Return data for match Arsenal vs Chelsea")
    @Test
    public void matchesBetweenArsenal_vs_Chelsea(){
        int sum=0;
        JsonPath jp =
        given()
                .log().uri()
                .queryParam("year","2013")
                .queryParam("team1","Chelsea")
               // .queryParam("team2","Olympiacos")
                .queryParam("page","1").
        when()
                .get().
        then()
                .log().all()
                .statusCode(200).
        extract()
                .jsonPath()
                ;


        Integer totalPages = jp.getInt("total_pages");

        for (int i = 0; i < totalPages; i++) {
            System.out.println();
            System.out.println("******************************");


            given()
                    .log().uri()
                    .queryParam("year","2013")
                    .queryParam("team1","Chelsea")
                    .queryParam("page",i).
            when()
                    .get().
            then()
                     .log().all()
                    .statusCode(200)

            ;
            for (int j = 0; j <jp.getInt("per_page") ; j++) {
                sum+=jp.getInt("data["+j+"].team1goals");
            }


        }

        for (int i = 0; i < totalPages; i++) {
            System.out.println();
            System.out.println("******************************");


            given()
                    .log().uri()
                    .queryParam("year","2013")
                    .queryParam("team2","Chelsea")
                    .queryParam("page",i).
                    when()
                    .get().
                    then()
                    .log().all()
                    .statusCode(200)

            ;
            for (int j = 0; j <jp.getInt("per_page") ; j++) {
                sum+=jp.getInt("data["+j+"].team1goals");
            }


        }
        System.out.println("sum = " + sum);
    }
}
/*
 * This is a hacker rank challenge give in the interview
 * Given this endpoint for getting football match data
 * http://jsonmock.hackerrank.com/api/football_matches
 * below query params are available
 * year  :  year the match occurred
 * team1 :  home team name
 * team2 :  visiting team name
 * page  :  page number , max result in per response is 10
 *           any result more than 10 will be on next pages
 *           so in order to get all data you need to
 *           make separate requests until there are no more pages
 *
 * Response Format :
 *  {
 *     "page": "1",
 *     "per_page": 10,
 *     "total": 23,
 *     "total_pages": 3,
 *     "data": [
 *         {
 *             "competition": "UEFA Champions League",
 *             "year": 2012,
 *             "round": "GroupB",
 *             "team1": "Arsenal",
 *             "team2": "Olympiacos",
 *             "team1goals": "3",
 *             "team2goals": "1"
 *         },
 *         {...},
 *         {...}
 *         ]
 *  }
 *
 * Examples Requests :
 *
 * GET http://jsonmock.hackerrank.com/api/football_matches?year=2012&team1=Arsenal&team2=Chelsea&page=1
 *  return data for match between Arsenal(home team) and Chelsea(visiting team) , show page 1
 * GET http://jsonmock.hackerrank.com/api/football_matches?year=2012&team1=Arsenal&page=1
 *  return data for 2012 match between Arsenal(home team) and any visiting team and show page 2
 * GET http://jsonmock.hackerrank.com/api/football_matches?year=2012&team2=Arsenal&page=2
 *  return data for match between any home team) and Chelsea(visiting team) and show page 2
 *
 *  Write a method to return total goals of a team in a given year
 *      * Include the both result where the team is home team or visiting team
 */
