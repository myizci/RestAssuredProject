package test_utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static test_utils.DB_Utility.*;
import static io.restassured.RestAssured.*;

public class HR_ORDS_API_BaseTest {
    @BeforeAll
    public static void init() {

        baseURI = ConfigurationReader.getProperty("hr.ords.baseuri");
        basePath = "/ords/hr/api";

        String url = ConfigurationReader.getProperty("hr.database.url");
        String username =ConfigurationReader.getProperty("hr.database.username");
        String password = ConfigurationReader.getProperty("hr.database.password");
        createConnection(url,username,password); //This is coming from DB_Utility
    }

    @AfterAll
    public static void cleanup() {
        reset();
        destroy();//This is coming from DB_Utility
    }
}
