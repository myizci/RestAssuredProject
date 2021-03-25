package day1;

import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Intro to RestAssured")
public class RestAssuredIntro {

    @Test
    public void test(){
       //http://54.159.232.203/8000/api/hello

        Response response = get("http://54.159.232.203:8000/api/hello");

        // extracting information from response object

        System.out.println("response.statusCode() = "+response.statusCode());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.getHeader(\"Content-Type\") = " + response.getHeader("Content-Type"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));


        System.out.println("response.getContentType() = " + response.getContentType());
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.asString() = " + response.asString());
        System.out.println("response.asPrettyString() = " + response.asPrettyString());

        assertThat(response.statusCode(),is(200));
        assertThat(response.getContentType(),is("text/plain;charset=UTF-8"));
        assertThat(response.contentType(),startsWith("text/plain"));
        assertThat(response.contentType(), containsString("set=UTF-"));
        assertThat(response.asString(),is("Hello from Sparta"));

        System.out.println("response.getTimeIn(TimeUnit.MILLISECONDS) = " + response.getTimeIn(TimeUnit.MILLISECONDS));
        System.out.println("response.getTime() = " + response.getTime());
        // printing the result
        // prettyPrint() --> print and return String
        // prettyPeek()  --> print and return same Response Object

        System.out.println("response.prettyPrint() = " + response.prettyPrint());
        System.out.println("============================");
        System.out.println(response.prettyPeek());

    }

    @DisplayName("Testing GET /api/spartans/{id} Endpoint")
    @Test
    public void testingSingleSpartan(){
        Response response = get("http://54.159.232.203:8000/api/spartans/1").prettyPeek();
//        System.out.println("response.prettyPeek() = " + response.prettyPeek());

        assertThat(response.statusCode(),is(200));
        assertThat(response.contentType(),is("application/json"));
        assertThat(response.header("Connection"),equalTo("keep-alive"));
       // System.out.println(response.asString());
        // getting the field value of Json Body
        //path method
        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"gender\") = " + response.path("gender"));
        System.out.println("response.path(\"phone\") = " + response.path("phone"));
        int myId = response.path("id");
        String myName = response.path("name");
        long myPhone = response.path("phone");
        System.out.println("myPhone = " + myPhone);
        System.out.println("myName = " + myName);
        System.out.println("myId = " + myId);
    }

}
