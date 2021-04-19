package day11;

import static io.restassured.RestAssured.*;

import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class dot {

    @BeforeAll
    public static void init() {

        baseURI = "https://vpic.nhtsa.dot.gov/";
        basePath = "/api/vehicles";//
    }

    @AfterAll
    public static void cleanup() {
        reset();
    }

    @DisplayName("Test GET /GetMakeForManufacturer/988 Honda from DOT")
    @Test
    public void testHonda() {

        XmlPath xp =
                given()
                        .log().uri()
                        .pathParam("id", 988)
                        .queryParam("format", "xml")
                        .when().get("/GetMakeForManufacturer/{id}").
                        then().statusCode(200).log().body()
                        .extract().xmlPath();

        String message = xp.getString("Response.Message");
        int totalCount = xp.getInt("Response.Count");
        int firstId = xp.getInt("Response.Results.MakesForMfg[0].Make_ID");
        List<Integer> Make_ID = xp.getList("Response.Results.MakesForMfg.Make_ID");
        System.out.println(Make_ID.size());
        System.out.println("Make_ID = " + Make_ID);
        System.out.println("firstId = " + firstId);
        System.out.println("totalCount = " + totalCount);
        assertThat(totalCount, is(2));
        assertThat(message, equalToIgnoringCase("Results returned successfully"));

    }

    @DisplayName("Test GET /GetMakeForManufacturer/988 Honda from DOT")
    @Test
    public void testHondaMethodChain() {

        given()
                .log().uri()
                .pathParam("id", 988)
                .queryParam("format", "xml")
                .when().get("/GetMakeForManufacturer/{id}").
                then().statusCode(200).log().body()
                .body("Response.Count",is("2"))
                .body("Response.Message",is("Results returned successfully"))
                .body("Response.Results.MakesForMfg[0].Make_ID",is("474"))
                .body("Response.Results.MakesForMfg[0].Make_Name",is("HONDA"))
        ;

    }
}
