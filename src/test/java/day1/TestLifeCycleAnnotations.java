package day1;


import org.junit.jupiter.api.*;

@DisplayName("Learning Test Lifecycle Annotations")
public class TestLifeCycleAnnotations {

    @BeforeAll
    public static void init() {
        System.out.println("Before all is running");
    }

    @AfterAll // it is always static, why, this is the way!!!
    public static void terminate() {
        System.out.println("After all is running");
    }

    @BeforeEach // it is always static, why, this is the way!!!
    public void initEach() {
        System.out.println("Before each is running");
    }

    @AfterEach
    public void terminateEach() {
        System.out.println("After each is running");
    }

    @Test
    public void test1() {
        System.out.println("Test 1 is running");
    }

    @Disabled
    @Test
    public void test2() {
        System.out.println("Test 2 is running");

    }

    // Ignoring certain test, for what ever reason
    // Use @Disabled

}
