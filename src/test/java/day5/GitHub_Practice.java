package day5;

import com.sun.glass.events.WheelEvent;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import pojo.GithubPOJO;
import test_utils.ConfigurationReader;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class GitHub_Practice {

    @BeforeAll
    public static void init(){
        baseURI="https://api.github.com";

    }

    @AfterAll
    public static void cleanup(){
        reset();
    }

    @Test
    public void getARepo(){

        given()
                .header("token", ConfigurationReader.getProperty("githubToken"))
                .log().all()
                .accept("application/vnd.github.v3+json")
                .pathParam("owner","myizci")
                .pathParam("repo","From-Postman-with-Love").
        when()
                .get("/repos/{owner}/{repo}").
        then()
                .statusCode(200)
                .log().all();
    }


    /*
    {
	"name":"From Postman with Love",
	"description":"First project",
	"homepage":"https://github.com",
	"private":"true",
	"has_issues":"true",
	"has_projects":"true",
	"has_wiki":"true"
}
     */
    @DisplayName("Creating a repo on gitHub")
    @Test
    public void createARepo(){

        String myRepo = "{\n" +
                "\t\"name\":\"Julia Roberts\",\n" +
                "\t\"description\":\"From Rest assured\",\n" +
                "\t\"homepage\":\"https://github.com\",\n" +
                "\t\"private\":\"false\",\n" +
                "\t\"has_issues\":\"true\",\n" +
                "\t\"has_projects\":\"true\",\n" +
                "\t\"has_wiki\":\"true\"\n" +
                "}";

        given()
                .header("Authorization",
                        "Bearer " +  ConfigurationReader.getProperty("githubToken"))
                .log().all()
                .accept("application/vnd.github.v3+json")
                .body(myRepo).
        when()
                .post("/user/repos").
        then()
                .log().all()
                .statusCode(201);



    }

@DisplayName("Create A GitHub Object and Create a New Repo")
    @Test
    public void addGithubPOJO(){
    GithubPOJO newRepo = new GithubPOJO();

    newRepo.setName("Magical_Repo");
    newRepo.setDescription("It is magical");
    newRepo.setPrivate(false);
    newRepo.setHas_issues(true);
    newRepo.setHas_projects(true);
    newRepo.setHas_wiki(true);

    given()
            .header("Authorization",
                    "Bearer " +  ConfigurationReader.getProperty("githubToken"))
            .log().all()
            .accept("application/vnd.github.v3+json")
            .body(newRepo).
            when()
            .post("/user/repos").
            then()
            .log().all()
            .statusCode(201);


}


}








