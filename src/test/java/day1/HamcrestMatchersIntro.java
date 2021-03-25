package day1;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class HamcrestMatchersIntro {

    @Test
    public void simpleTest() {
        //assert 10 == 5+5

        assertThat(10, is(5 + 5));
        assertThat(10, equalTo(5 + 5));
        assertThat(5 + 5, not(11));
        assertThat(5 + 5, is(equalTo(10)));
        assertThat(5 + 5, is(not(11)));
        assertThat(5 + 5, is(not(equalTo(11))));

        // number comparison
        //   greaterThan()
        assertThat(5 + 5, is(greaterThan(8)));

        //   lessThan()
        assertThat(5 + 5, is(lessThan(11)));

        //   greaterThanOrEqualTo()
        assertThat(5 + 5, is(greaterThanOrEqualTo(10)));

        //   lessThanOrEqualTo
        assertThat(5 + 5, is(lessThanOrEqualTo(10)));

    }


    @DisplayName("Matchers related to Strings")
    @Test
    public void stringMatchers() {
        String msg = "B21 is learning Hamcrest";
        //checking for equality is same as numbers above

        assertThat(msg, is("B21 is learning Hamcrest"));
        assertThat(msg, equalTo("B21 is learning Hamcrest"));
        assertThat(msg, is(equalTo("B21 is learning Hamcrest")));
        // check if this msg starts with B21
        assertThat(msg, startsWith("B21"));
        assertThat(msg, startsWithIgnoringCase("b21"));
        assertThat(msg, endsWith("est"));
        assertThat(msg, containsString("ning"));
        assertThat(msg, containsStringIgnoringCase("LEARNING"));

        String str = "  ";
        // check if above str is blank
        assertThat(str, blankString());  // str is made of blank spaces

        assertThat(str.trim(), is(emptyString())); // str needs to be trimmed to use emptyString


    }

    @DisplayName("Hamcrest support for Collection")
    @Test
    public void testCollection() {

        List<Integer> list = Arrays.asList(1, 3, 4, 5, 6, 7, 8, 6);
        assertThat(list, hasSize(8));

        assertThat(list, hasItem(7));
        assertThat(list, hasItems(3,4,5,6));
        // check if every item in the list greater than zero

        assertThat(list, is(everyItem(greaterThan(0))));
    }

}
