package test_utils;

import com.github.javafaker.Faker;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static test_utils.DB_Utility.createConnection;

public class LibraryAppBaseTest {
    public static String librarianToken;

    public static RequestSpecification librarianSpec;
    public static ResponseSpecification librarianResponseSpec;
    @BeforeAll
    public static void init() {
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";
        librarianToken = getToken("librarian69@library","KNPXrm3S");

         librarianSpec = given().header("x-library-token", librarianToken);
         librarianResponseSpec =
                 expect().statusCode(200).contentType(ContentType.JSON).logDetail(LogDetail.ALL);

        String url = ConfigurationReader.getProperty("library1.database.url");
        String username =ConfigurationReader.getProperty("library1.database.username");
        String password = ConfigurationReader.getProperty("library1.database.password");
        createConnection(url,username,password);
    }

    public static String getToken(String username, String password){

        return
                given()
                        .log().all()
                        .contentType(ContentType.URLENC)
                        .formParam("email",username)
                        .formParam("password",password).
                        when()
                        .post("/login")
                        .path("token")
                ; // This is a String expression

    }

public static Map<String,Object> getRandomBook(){

    Map<String, Object> myBookMap = new HashMap<>();

   Faker faker = new Faker();

    myBookMap.put("name",faker.book().title());
    myBookMap.put("isbn",faker.number().digits(8));
    myBookMap.put("year",faker.number().numberBetween(1600,2021));
    myBookMap.put("author",faker.book().author());
    myBookMap.put("book_category_id",faker.number().numberBetween(1,20));
    myBookMap.put("description",faker.chuckNorris().fact());

    return myBookMap;
}




    @AfterAll
    public static void cleanUp() {

        reset();
    }

}
