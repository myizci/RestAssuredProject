package test_utils;

import groovyjarjarantlr4.v4.misc.FrequencySet;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.reset;

public abstract class SpartanWithAuthBaseTest {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://18.235.32.166:7000";
        RestAssured.basePath="/api";
    }

    @AfterAll
    public static void cleanup(){
        reset();
    }
}
