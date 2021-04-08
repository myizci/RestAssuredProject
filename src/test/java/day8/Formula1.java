package day8;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Driver;

import java.nio.file.DirectoryStream;
import java.util.List;

import static io.restassured.RestAssured.*;
public class Formula1 {
    @BeforeAll
    public static void init(){
        baseURI="http://ergast.com";
        basePath="/api/f1";
    }
    @AfterAll
    public static void cleanup(){
        reset();
    }

    @DisplayName("GET /driver.json")
    @Test
    public void getAllDrivers(){
        JsonPath jp=
        given()
                .accept(ContentType.JSON)
                .log().uri().
        when()
                .get("/drivers.json").jsonPath();

        Driver firstDriver = jp.getObject("MRData.DriverTable.Drivers[0]", Driver.class);

        List<Driver> drivers = jp.getList("MRData.DriverTable.Drivers", Driver.class);

        System.out.println(firstDriver);
      drivers.forEach(p->{if(p.getNationality().equals("American")) System.out.println(p);});
    }
}
