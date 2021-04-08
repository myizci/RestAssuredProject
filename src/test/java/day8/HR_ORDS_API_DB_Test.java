package day8;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static test_utils.DB_Utility.*;
import org.hamcrest.Matchers.*;
import pojo.Region;
import pojo.SpartanPOJO;
import static test_utils.DB_Utility.*;
import test_utils.SpartanNoAuthBaseTest;

import java.util.List;
import java.util.Map;
import test_utils.HR_ORDS_API_BaseTest;

public class HR_ORDS_API_DB_Test extends HR_ORDS_API_BaseTest {

@Test
    public void testHR_ORDS_API(){
    runQuery("SELECT * FROM REGIONS WHERE REGION_ID=1");
    displayAllData();
    // send request to GET /region/{id} and compare the result with DB result

    //save expected data for region_id of 1 into List

    // send API request to GET /region/{region_id} with id of 1 and save the result into POJO
    List<String> firstRowAsExpectedList = getRowDataAsList(1);
    System.out.println("firstRowAsExpectedList = " + firstRowAsExpectedList);

    Region r1= given()
            .pathParam("region_id",1).
          when().
             get("/regions/{region_id}").jsonPath()
            .getObject("items[0]",Region.class);
    System.out.println("r1 = " + r1);
    //compare the result
assertThat(r1.getRegion_id(),is(Integer.parseInt(firstRowAsExpectedList.get(0))));
assertThat(r1.getRegion_name(),is(firstRowAsExpectedList.get(1)));



}
}
