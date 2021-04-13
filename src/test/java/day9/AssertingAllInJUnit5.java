package day9;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class AssertingAllInJUnit5 {
    @Test
    public void testAdd() {
        //we want to be able to evaluate all see assertions and return
        //
//        assertThat(5+5,is(10));
//        assertThat(5+4,equalTo(10));
//        assertThat(5+6,equalTo(10));

        assertAll(
                () -> assertThat(5 + 5, is(10)),
                () -> assertThat(5 + 4, equalTo(10)),
                () -> assertThat(5 + 6, equalTo(10))
        );
    }
}
