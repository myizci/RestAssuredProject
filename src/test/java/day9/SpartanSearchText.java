package day9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import test_utils.DB_Utility;
import test_utils.SpartanNoAuthBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class SpartanSearchText extends SpartanNoAuthBaseTest {
    @DisplayName("test GET /spartans/search result with DB result")
    @Test
    public void testSearch(){

        DB_Utility.runQuery("SELECT * FROM SPARTANS WHERE LOWER(NAME) LIKE '%a%' AND GENDER ='Female'");

      //  DB_Utility.displayAllData();
        int expectedCount = DB_Utility.getRowCount();

        given()
                .queryParam("nameContains","a")
                .queryParam("gender","Female").
        when()
                .get("spartans/search").
        then()
                .statusCode(200)
                .body("totalElement",equalTo(expectedCount))
                .body("content",hasSize(expectedCount))
        ;

    }

    @ParameterizedTest
    @CsvSource(
            {
                    "e, Male",
                    "le, Female",
                    "k, Male",
                    "g, Male",
                    "u, Female",
                    "f, Male"
            }
    )
    public void testSearchParameterized(String nameArg, String genderArg){

        DB_Utility.runQuery("SELECT * FROM SPARTANS WHERE LOWER(NAME) LIKE '%"+nameArg+"%' AND GENDER ='"+genderArg+"'");
        int expectedCount = DB_Utility.getRowCount();
        System.out.println("expectedCount = " + expectedCount);

        given()
                .queryParam("nameContains",nameArg)
                .queryParam("gender",genderArg).
                when()
                .get("spartans/search").
                then()
                .statusCode(200)
                .body("totalElement",equalTo(expectedCount))
                .body("content",hasSize(expectedCount))
                .body("content.name",everyItem(containsStringIgnoringCase(nameArg)))
                .body("content.gender",everyItem(equalTo(genderArg)));
        ;
    }
}
