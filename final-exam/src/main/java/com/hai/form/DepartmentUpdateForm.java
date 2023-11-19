package com.hai.form;

import com.hai.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentUpdateForm {
    private Integer id;
    private String name;
    private Integer totalMembers;
    private Department.Type type;
}
