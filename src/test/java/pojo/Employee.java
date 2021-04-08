package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
//represent Employee data with he given fields and ignore the rest of the fields
@Getter
@Setter
@ToString
//we wan to ignore any json that does not match the below pojo class fields
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    private int employee_id;
    private String first_name;
    private String last_name;
    private int salary;
    private int department_id;
}
