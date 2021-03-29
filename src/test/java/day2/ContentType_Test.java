package day2;
import test_utils.SpartanNoAuthBaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_utils.SpartanNoAuthBaseTest;

import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
@DisplayName("Spartan ContentType Summary")
public class ContentType_Test extends SpartanNoAuthBaseTest {

    @DisplayName("GET /hello")
    @Test
    public void testHelloContentType(){

        when()
                .get("/hello").
        then()
                .contentType(ContentType.TEXT)
                .body(is("Hello from Sparta"))
        ;

    }

    @DisplayName("Get / spartans in json")
    @Test
    public void testSpartansJsonContentType(){

        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans").
        then()
                .contentType(ContentType.JSON);

    }


    @DisplayName("Get / spartans in xml")
    @Test
    public void testSpartansXmlContentType(){

        given()
                .accept(ContentType.XML).
                when()
                .get("/spartans").
                then()
                .contentType(ContentType.XML);


    }


}
