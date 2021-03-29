package day1;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import test_utils.SpartanNoAuthBaseTest;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestsFromShortVideos extends SpartanNoAuthBaseTest {

//    @BeforeAll
//    public void setUpClass(){
//        baseURI="http://54.159.232.203:8000/api";
//    }

    @Test
    public void viewSpartanTest() {
        Response response = get("/spartans");
         assertThat(response.statusCode(),is(200));
         assertThat(response.body().asString(),containsString("Allen"));

    }

    @Test
    public void viewSpartanTest2() {
        Response response = given().accept(ContentType.JSON).when().get("/spartans");
        assertThat(response.statusCode(),is(200));

    }



    @Test
    public void PathTest1(){
      Response response =  given().accept(ContentType.JSON)
                .and().pathParam("id",18)
                .when().get("/spartans/{id}");
        //verify status code
        assertThat(response.statusCode(),is(200));
        //verify content type
        assertThat(response.contentType(),is("application/json"));

        assertThat(response.body().asString(),containsString("Allen"));

        response.body().prettyPrint();
    }

    @Test
    public void negativePathParam(){

        Response response = given().accept(ContentType.JSON)
                            .pathParam("id",500)
                            .when().get("/spartans/{id}");

        //verify status code is 404
        assertThat(response.statusCode(),is(404));

        //verify content type
        assertThat(response.contentType(),is("application/json"));

        //verify Spartan not found
        assertThat(response.body().asString(),containsString("Not Found"));

        response.prettyPrint();

    }

    @Test
    public void queryParam1(){

        Response response=given().accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .and().queryParam("nameContains","J")
                .when().get("/spartans/search");

        assertThat(response.statusCode(),is(200));
        assertThat(response.contentType(),is("application/json"));
        assertThat(response.body().asString(),not(containsString("Male")));
        assertThat(response.body().asString(),containsString("Janette"));

        response.prettyPrint();
    }

    @Test
    public void queryParam2(){

        //creating map
        Map<String, Object> paramsMap = new HashMap<>();

        paramsMap.put("gender","Female");
        paramsMap.put("nameContains","J");

        //send request
        Response response=given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get("/spartans/search");

        assertThat(response.statusCode(),is(200));
        assertThat(response.contentType(),is("application/json"));
        assertThat(response.body().asString(),not(containsString("Male")));
        assertThat(response.body().asString(),containsString("Janette"));

        response.prettyPrint();
    }

    @Test
    public void pathMethod(){
        Response response =
                given().accept(ContentType.JSON)
                .pathParam("id",10)
                .when().get("/spartans/{id}");

        assertThat(response.statusCode(),is(200));
        assertThat(response.contentType(),is("application/json"));

        System.out.println("id: "+response.body().path("id").toString());
        System.out.println("name: "+ response.body().path("name").toString());
        System.out.println("gender: "+response.path("gender").toString());
        System.out.println("phone: "+response.path("phone").toString());

        int id = response.path("id");
        String name = response.body().path("name");
        String gender = response.body().path("gender");
        long phone = response.body().path("phone");

        System.out.println("phone = " + phone);
        System.out.println("gender = " + gender);
        System.out.println("name = " + name);
        System.out.println("id = " + id);

        assertThat(id,is(10));
        assertThat(gender,is("Female"));
        assertThat(name,is("Lorenza"));
        assertThat(phone,is(3312820936L));

    }

    @Test
    public void pathMethod2(){

        Response response = get("/spartans");

         ArrayList<Integer> ids = response.path("id");

         int firstId = ids.get(0);

        System.out.println("firstId = " + firstId);

        String first1stName = response.path("name[0]");
        System.out.println("first1stName = " + first1stName);

        // get the last first name

            String lastFirstName = response.path("name[-2]");
        System.out.println("lastFirstName = " + lastFirstName);

        //Extract all the first names and print

        List<String> names = response.path("name");

        System.out.println("names = " + names);
        System.out.println("names size = " + names.size());

        List<Object> phoneNumbers = response.path("phone");

        for (Object each:
             phoneNumbers) {
            System.out.println(each.toString());
        }

    }

    @Test
    public void jsonPath(){
        
        Response response = 
                given().accept(ContentType.JSON)
                .pathParam("id",11)
                .when().get("/spartans/{id}");
        
        int id = response.path("id");
        System.out.println("id = " + id);
        
        // how to read with json

        JsonPath jsonPath = response.jsonPath();

        int id1= jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phoneNumber = jsonPath.getLong("phone");

        System.out.println("phoneNumber = " + phoneNumber);
        System.out.println("gender = " + gender);
        System.out.println("name = " + name);
        System.out.println("id1 = " + id1);
        
    }

    @Test
    public void hamcrestMatchers1(){

                //request
                given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/spartans/{id}")
                 // verification
                .then().statusCode(200)
                .and().assertThat().contentType("application/json");
    }

    @Test
    public void hamcrestMatchers2(){

        given().accept(ContentType.JSON)
               .pathParam("id",15)
               .when().get("/spartans/{id}")
               .then().assertThat().statusCode(200)
               .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", equalTo(15),
                "name",equalTo("Meta"),"gender",equalTo("Female"),
                "phone",equalTo(1938695106));

    }

@Test
    public void gsonTest1(){
        Response response =  given().accept(ContentType.JSON)
                .pathParam("id",11)
                .when().get("/spartans/{id}");
        // convert json response to java collections

               Map<String,Object> spartanMap= response.body().as(Map.class);

    System.out.println(spartanMap.get("id"));
    System.out.println(spartanMap.get("name"));
    assertThat(spartanMap.get("name"),equalTo("Nona"));


}

    @Test
    public void gsonTest2(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans");

       // response.prettyPrint();
        // convert full json body of list of maps

        List<Map<String,Object>> listOfSpartans = response.body().as(List.class);

        System.out.println(listOfSpartans.get(0));

        Map<String ,Object> firstSpartan = listOfSpartans.get(0);

        System.out.println(firstSpartan.get("name"));
        int counter=1;
        for(Map<String, Object> each:listOfSpartans){
            System.out.println(counter + " - spartan" + each);
            counter++;
        }


    }



    }
