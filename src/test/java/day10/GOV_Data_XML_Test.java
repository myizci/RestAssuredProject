package day10;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;

public class GOV_Data_XML_Test {
    //https://data.ct.gov/api/views/qm34-pq7e/rows.xml

    @BeforeAll
    public static void init(){
        baseURI="https://data.ct.gov";
        basePath="api/views/qm34-pq7e";

    }
    //GET https://data.ct.gov/api/views/qm34-pq7e/rows.xml
    // save all the numbers in "unknown" element in the response into the list
    // save all the year into the list
    // find out the max number and year of that max number
    @DisplayName("Find out the year max number of unknown race hired")
    @Test
    public void testMax(){
        XmlPath xp =
                given()
                .accept(ContentType.XML).
                get("rows.xml").xmlPath();

        List<Integer> unknownList = xp.getList("response.row.row.unknown",Integer.class);
        List<Integer> yearList =  xp.getList("response.row.row.year",Integer.class);
         System.out.println(unknownList.size());
        System.out.println("unknownList = " + unknownList);
        System.out.println("yearList = " + yearList);

        System.out.println("Collections.max(yearList) = " + Collections.max(yearList));
        System.out.println("Collections.max(unknownList) = " + Collections.max(unknownList));

        int maxYear = yearList.get(0);
        for (Integer integer : yearList) {

            if(integer>maxYear){
                maxYear=integer;
            }
        }

        System.out.println("maxYear = " + maxYear);

        int indexOfMaxUnknow = unknownList.indexOf(Collections.max(unknownList));
        System.out.println("yearList.get(indexOfMaxUnknow) = " + yearList.get(indexOfMaxUnknow));
    }


    @AfterAll
    public static void cleanup(){
        reset();
    }
}
