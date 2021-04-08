package day8;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Dog;
import pojo.DogOwner;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class DioTest {


    @DisplayName("GET dio dog")
    @Test
    public void getDio(){
        JsonPath jp=
       get("https://e94d0713-391b-4218-906d-66e9614ea580.mock.pstmn.io/dio").jsonPath();

        Dog dio = jp.getObject("",Dog.class);
        System.out.println(dio);
    }
}
