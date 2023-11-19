package com.hai.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUpdateForm {
    private String oldPassword;
    private String newPassword;
}
