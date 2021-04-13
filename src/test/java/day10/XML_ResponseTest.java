package day10;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_utils.SpartanWithAuthBaseTest;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

public class XML_ResponseTest extends SpartanWithAuthBaseTest {


    @DisplayName("Test GET /spartans xml response")
    @Test
    public void testXML() {
        // provide credentials and provide header as xml and send request
        // assert status code 200
        // assert content type is xml
        // assert first data name is Wonder Woman

        given()
                .auth().basic("user", "user")
                .accept(ContentType.XML).
                when()
                .get("/spartans").
                then()
                .statusCode(200)
                .log().all()
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Meade"))
                .body("List.item[0].id", is("1"));
    }

    @DisplayName("Test GET /spartans xml rsponse with XmlPath")
    @Test
    public void testXML_extractData() {

        Response response =
                given()
                        .auth().basic("user", "user")
                        .accept(ContentType.XML).
                        when()
                        .get("/spartans");

        XmlPath xp = response.xmlPath();

        int firstID = xp.getInt("List.item[0].id");
        System.out.println("firstID = " + firstID);
        String firstName = xp.getString("List.item[0].name");
        System.out.println("firstName = " + firstName);
        long thirdPhoneNumber = xp.getLong("List.item[2].phone");
        System.out.println("thirdPhoneNumber = " + thirdPhoneNumber);

        List<Integer> allIDs = xp.getList("List.item.id",Integer.class);
        //System.out.println(allIDs);

        //assert below in one shot with JUNIT 5 AssertAll
assertAll(

        () ->   assertEquals(1,firstID),
        () ->   assertEquals("Meade",firstName),
        () ->   assertEquals(6105035231L,thirdPhoneNumber),
        () ->   assertEquals(329,allIDs.size())
        );
    }

}
