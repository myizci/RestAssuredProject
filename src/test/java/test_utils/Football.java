package test_utils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class Football {

    @BeforeAll
    public static void init(){
        baseURI="http://jsonmock.hackerrank.com";
        basePath="/api/football_matches";
    }

    @AfterAll
    public static void cleanup(){
        reset();
    }

    //GET http://jsonmock.hackerrank.com/api/football_matches?year=2012&team1=Arsenal&team2=Chelsea&page=1
    @DisplayName("GET year 2012 Arsenal vs Chelsea")
    @Test
    public void test1(){

        String team = "Chelsea";
        int year = 2012;

        JsonPath jp=
        given()
                .log().uri()
                .accept(ContentType.JSON)
                .queryParam("year",year)
                .queryParam("team1",team).

        when()
                .get().
        then()
                //.log().all()
                .statusCode(200).
        extract()
                .jsonPath();


        List<Integer> allGoals = new ArrayList<>();

        int totalPages = jp.getInt("total_pages");

        for(int i=1; i<=totalPages; i++ ){


            allGoals.addAll(
            given()
                    .accept(ContentType.JSON)
                    .queryParam("year",year)
                    .queryParam("team1",team)
                    .queryParam("page",i).
            when()
                    .get().jsonPath().getList("data.team1goals",Integer.class));


        }

        JsonPath jp2=
                given()
                        .log().uri()
                        .accept(ContentType.JSON)
                        .queryParam("year",year)
                        .queryParam("team2",team).
                        //.queryParam("team2","Chelsea").
                                when()
                        .get().
                        then()
                        //.log().all()
                        .statusCode(200).
                        extract()
                        .jsonPath();

        int totalPages2 = jp.getInt("total_pages");

        for(int i=1; i<=totalPages2; i++ ){


            allGoals.addAll(
                    given()
                            .accept(ContentType.JSON)
                            .queryParam("year",year)
                            .queryParam("team2",team)
                            .queryParam("page",i).
                            when()
                            .get().jsonPath().getList("data.team2goals",Integer.class));


        }

        System.out.println(allGoals);

        Integer sum =0;

        for(Integer each : allGoals){
            sum+=each;
        }

        System.out.println("Total number of goals of "+ team+" in " +year+" is  "+sum);
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
