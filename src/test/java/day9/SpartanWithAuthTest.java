package day9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_utils.SpartanWithAuthBaseTest;

import static io.restassured.RestAssured.*;

public class SpartanWithAuthTest extends SpartanWithAuthBaseTest {
    @DisplayName("GET /spartans as public user expect 401")
    @Test
    public void testPublicUser(){
        when()
                .get("/spartans").
                then()
                .statusCode(401);

    }


    @DisplayName("GET /spartans as admin user expect 200")
    @Test
    public void testAdmin(){


        given()
                .auth().basic("admin","admin").
        when()
                .get("/spartans").
                then()
                .log().headers()
                .statusCode(200);
    }


    @DisplayName("DELETE /spartans/{id} as editor user expect 403")
    @Test
    public void testEditor(){
        given()
                .pathParam("id",77)
                .auth().basic("editor","editor").
                when()
                .delete("/spartans/{id}").
                then()
                .log().headers()
                .statusCode(403);
    }
}
