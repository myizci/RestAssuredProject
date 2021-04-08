package day7;

import lombok.ToString;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Characters;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class BreakingBad_POJO {

    @BeforeAll
    public static void init(){
        baseURI="https://www.breakingbadapi.com";
        basePath="/api";
    }

    @AfterAll
    public static void cleanup(){
        reset();
    }

    @DisplayName("GET /characters")
    @Test
    public void testPracticeDeserialization(){
       //save the first character to a Characters object
        Characters c1 =
                get("/characters").jsonPath().getObject("[0]",Characters.class);

        System.out.println("c1 = " + c1);

        //save the all characters to a list of Characters object

        List<Characters> allCharacters =
                get("/characters").jsonPath().getList("",Characters.class);

      //  allCharacters.forEach(System.out::println);

        //print all characters who appeared in all 5 seasons

        allCharacters.forEach(p->{if(p.getAppearance().size()==5){
            System.out.println("p = " + p);
        }});


        //print all characters who appeared in only 3 seasons


        allCharacters.forEach(p->
        {if(p.getAppearance().size()==1&&p.getAppearance().get(0)==3){
            System.out.println("p = " + p);
        }});
    }

}
