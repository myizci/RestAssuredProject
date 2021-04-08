package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Department {

    @JsonProperty("department_id") // tells jackson to match departmentID with department_id
    private int departmentId;// json field: department_id

    @JsonProperty("department_name")
    private String name; //json field: department_name
    private int manager_id;
    private  int location_id;
}
