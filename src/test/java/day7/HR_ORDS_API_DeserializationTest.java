package day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Country;
import pojo.Department;
import pojo.Employee;
import pojo.Region;
import test_utils.HR_ORDS_API_BaseTest;

import java.util.List;

import static io.restassured.RestAssured.*;

public class HR_ORDS_API_DeserializationTest extends HR_ORDS_API_BaseTest {

    //send request to/regions and save the result into List<Region>
    // assert the list has size 4


    @DisplayName("GET /regions")
    @Test
    public void regionsAsListTest(){

        List<Region> list =
                get("/regions").jsonPath().getList("items",Region.class);

        System.out.println("list = " + list);

    }

    @DisplayName("GET /Countries")
    @Test
    public void testAllCountries(){

        //save 3rd country as country POJO
        //save all countries as List<Country>

        Country c3=
        get("/countries").jsonPath().getObject("items[2]",Country.class);

        List<Country> countryList=
        get("/countries").jsonPath().getList("items",Country.class);

       // System.out.println("countryList = " + countryList);




        //countryList.forEach(p-> System.out.println(p));
        countryList.forEach(System.out::println); //countryList.forEach(soutc -->tab);
    }

    //We want to create pojo that represents Employee data
    //We only care about employee_id , first name, last name, salary department_id
    // we want to save the json data as pojo and we want to ignore any other fields

    @DisplayName("GET /employees")
    @Test
    public void testAllEmployees(){

        Employee e1 =
                get("/employees").jsonPath().getObject("items[0]",Employee.class);

        System.out.println("e1 = " + e1);

    }

    //Till this moment we have been naming our pojo class fields names
    // to match exact name from json field name
    // and yet there will be situations that the json field name
    //does not rally work for java naming convention
    //we want to be able to name the pojo field anything we want

    /*
     {
            "department_id": 10, --> departmentId
            "department_name": "Administration", --> name
            "manager_id": 200,
            "location_id": 1700
        },
     */

    @DisplayName("GET /departments")
    @Test
    public void testAllDepartments(){

        Department d1 = get("/departments").jsonPath()
                .getObject("items[0]",Department.class);

        System.out.println("d1 = " + d1);

    }
}
