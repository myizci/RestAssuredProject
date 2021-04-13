package day8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pojo.SpartanPOJO;
import test_utils.DB_Utility;
import test_utils.SpartanNoAuthBaseTest;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import java.util.List;



public class SpartanHW extends SpartanNoAuthBaseTest {
    // AS HOMEWORK  GET /spartans/search
    // search for nameContains a and Female
    // compare DB Result total count with API Result total count
    // SELECT * FROM SPARTANS WHERE LOWER(NAME) LIKE '%a%' and GENDER = 'Female'

    // Make sure all your data in json array match exact criteria above (nameContains a and Female
    // Optionally , Write a parametrized Test with multiple different search criteria
    @DisplayName("HOMEWORK  GET /spartans/search")
    @Test
    public void queryParam_API_DB_Comparison_Test(){
        List<SpartanPOJO> list =
        given()
                .queryParam("nameContains","a")
                .queryParam("gender","Female")
                .log().uri().
        when()
                .get("/spartans/search").jsonPath().getList("content",SpartanPOJO.class);
        System.out.println(list.size());

        DB_Utility.runQuery("SELECT * FROM SPARTANS WHERE LOWER(NAME) LIKE '%a%' AND GENDER = 'Female'");
        int allRowCount = DB_Utility.getRowCount();

       assertThat(list.size(),is(allRowCount));
    }

    @Test
    public static List<SpartanPOJO> allFemaleSpartansWith_a(){
        return
                given()
                        .queryParam("nameContains","a")
                        .queryParam("gender","Female")
                        .log().uri().
                        when()
                        .get("/spartans/search").jsonPath().getList("content",SpartanPOJO.class);
    }

    @DisplayName("Parameterized Test")
    @ParameterizedTest
    @MethodSource("allFemaleSpartansWith_a")
    public void checkCriteriaNameContains_a_GenderFemale(SpartanPOJO s){
        assertThat(s.getName().toLowerCase(),containsString("a"));
        assertThat(s.getGender(),is("Female"));
    }

}
