package com.hai.form;

import com.hai.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRegisterForm {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Account.Role role;
    private Integer departmentId;
}
