package day8;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static test_utils.DB_Utility.*;

import pojo.SpartanPOJO;
import test_utils.DB_Utility;
import test_utils.SpartanNoAuthBaseTest;
import java.util.Map;

public class SpartanAPI_DB_Test extends SpartanNoAuthBaseTest {

    @Test
    public void testDB_Connection() {
        runQuery("Select * from Spartans");
        displayAllData();
    }
@DisplayName("Test GET map data from DB and match with jsonPath")
    @Test
    public void testGetSingleSpartanResponseMatchDB_Result() {
        //expected data -- D b query result
        // actual data -- api response json

        // we want to test spartan with ID of 44 , api response json match database base

        // run a query --> select  * from spartans where spartan_id =44

        int spartanIdToCheck = 77;
        runQuery("select * from spartans where spartan_id =" + spartanIdToCheck);
        displayAllData();

        Map<String, String> firstRowMap = getRowMap(1);
        System.out.println("firstRowMap = " + firstRowMap);

        given()
                .pathParam("id",spartanIdToCheck)
                .log().uri().
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(200)
                .body("id",is(spartanIdToCheck))
                .body("name",is(firstRowMap.get("NAME")))
                .body("gender",is(firstRowMap.get("GENDER")))
                // jsonpath is returning int but map is returning long
                // both sides have  to be long
                //we will use groovy to get long from json all the time
                //"phone" --> "phone.toLong()"
                .body("phone.toLong()",is(Long.parseLong(firstRowMap.get("PHONE"))));
        reset();
        ;

    }

    @DisplayName("Test GET map data from DB and match with POJO")

    @Test
    public void testGetSingleSpartanResponsePOJO_MatchDB_Result() {


        int spartanIdToCheck = 77 ;

        runQuery("SELECT * FROM SPARTANS WHERE SPARTAN_ID = " + spartanIdToCheck ) ;
        Map<String,String> expectedResultMap = DB_Utility.getRowMap(1) ;

        SpartanPOJO actualResultInPojo =  given()
                .pathParam("id" , spartanIdToCheck).
                        when()
                .get("/spartans/{id}")
                .as(SpartanPOJO.class) ;
        System.out.println("actualResultInPojo = " + actualResultInPojo);

        assertThat( actualResultInPojo.getId() , is (spartanIdToCheck) ) ;
        assertThat( actualResultInPojo.getName() , is ( expectedResultMap.get("NAME") )    );
        assertThat( actualResultInPojo.getGender() , is ( expectedResultMap.get("GENDER") )    );
        assertThat(actualResultInPojo.getPhone() ,  is(  Long.parseLong( expectedResultMap.get("PHONE"))   )   );

    }

    @DisplayName("Test GET /spartans/{id} match DB Data with POJO 2")
    @Test
    public void testGetSingleSpartanResponsePOJO_MatchDB_Result2(){

        DB_Utility.runQuery("SELECT * FROM SPARTANS") ;
        Map<String,String> expectedResultMap = DB_Utility.getRowMap(1) ;
        // get the ID from this map and save it into below variable
        int spartanIdToCheck =  Integer.parseInt(  expectedResultMap.get("SPARTAN_ID"))  ;

        SpartanPOJO actualResultInPojo =  given()
                .pathParam("id" , spartanIdToCheck).
                        when()
                .get("/spartans/{id}")
                .as(SpartanPOJO.class) ;
        System.out.println("actualResultInPojo = " + actualResultInPojo);

        assertThat( actualResultInPojo.getId() , is (spartanIdToCheck) ) ;
        assertThat( actualResultInPojo.getName() , is ( expectedResultMap.get("NAME") )    );
        assertThat( actualResultInPojo.getGender() , is ( expectedResultMap.get("GENDER") )    );
        assertThat(actualResultInPojo.getPhone() ,  is(  Long.parseLong( expectedResultMap.get("PHONE"))   )   );



    }
}
