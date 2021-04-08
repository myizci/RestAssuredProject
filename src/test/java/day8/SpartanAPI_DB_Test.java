package day8;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static test_utils.DB_Utility.*;
import test_utils.SpartanNoAuthBaseTest;
import java.util.Map;

public class SpartanAPI_DB_Test extends SpartanNoAuthBaseTest {

    @Test
    public void testDB_Connection() {
        runQuery("Select * from Spartans");
        displayAllData();
    }

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
                .body("phone",is(Integer.parseInt(firstRowMap.get("PHONE"))));
        ;

    }
}
