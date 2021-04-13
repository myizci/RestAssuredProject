package day10;

import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormulaOneAPI_Test {

//    @BeforeAll
//    public static void init() {
//        baseURI = "http://ergast.com";
//        basePath = "/api/f1/";
//    }

    @AfterAll
    public static void cleanup() {
        reset();
    }


    @DisplayName("Test GET/drivers/{driver_id}")
    @Test
    public void testDriverOne() {
        //
        XmlPath xp =
                given()
                        .pathParam("driver_id", "alonso").
                        when().get("/drivers/{driver_id}").xmlPath();

        int permanentNumber = xp.getInt("MRData.DriverTable.Driver.PermanentNumber");
        String givenName = xp.getString("MRData.DriverTable.Driver.GivenName");
        String familyName = xp.getString("MRData.DriverTable.Driver.FamilyName");

        assertEquals(permanentNumber, 14);
        assertEquals(givenName, "Fernando");
        assertEquals(familyName, "Alonso");

    }

    @DisplayName("Test GET/drivers")
    @Test
    public void testAllDrivers() {

        XmlPath xp = get("/drivers").xmlPath();
        String firstName = xp.getString("MRData.DriverTable.Driver.GivenName[0]");
        String firstName2 = xp.getString("MRData.DriverTable.Driver[0].GivenName");
        String thirdNationality = xp.getString("MRData.DriverTable.Driver[3].Nationality");
        List<String> allLastNames = xp.getList("MRData.DriverTable.Driver.FamilyName");

        System.out.println("firstName = " + firstName);
        System.out.println("firstName2 = " + firstName2);
        System.out.println("thirdNationality = " + thirdNationality);
        System.out.println("allLastNames = " + allLastNames);


    }

    @DisplayName("Test GET/drivers/{driver_id}")
    @Test
    public void testDriverOneAttribute() {
        //
        XmlPath xp =
                given()
                        .pathParam("driver_id", "alonso").
                        when().get("/drivers/{driver_id}").xmlPath();

        String driverCode = xp.getString("MRData.DriverTable.Driver.@code");
        String driverUrl = xp.getString("MRData.DriverTable.Driver.@url");

        System.out.println("driverCode = " + driverCode);
        System.out.println("driverUrl = " + driverUrl);
    }

    @DisplayName("Test GET/ WandaVison")
    @Test
    public void getAllWanda() {
//        XmlPath xp =
//                given()
//                        .baseUri("http://www.omdbapi.com").
//                        queryParam("apikey","df9c561d")
//                        .queryParam("s", "WandaVision")
//                        .queryParam("r", "xml").
//
//                        when().get().xmlPath();
//
//        List<String> allTitles = xp.getList("root.result.@title",String.class);
//        System.out.println("allTitles = " + allTitles);


        XmlPath xp = given()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apikey","df9c561d")
                .queryParam("s","WandaVision")
                .queryParam("r","xml").
                        when()
                .get()
                .xmlPath() ;
        List<String> allTitles = xp.getList("root.result.@title", String.class);
        System.out.println("allTitles = " + allTitles);
    }

    @DisplayName("Get movies attributes in xml")
    @Test
    public void testAllMovieAttributes(){

        XmlPath xp = given()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apikey","df9c561d")
                .queryParam("s","Superman")
                .queryParam("r","xml").
                        when()
                .get()
                .xmlPath() ;
        List<String> allTitles = xp.getList("root.result.@title", String.class);
        System.out.println("allTitles = " + allTitles);


    }

}
