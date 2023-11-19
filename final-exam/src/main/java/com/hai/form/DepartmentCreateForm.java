package com.hai.form;

import com.hai.entity.Account;
import com.hai.validation.AccountUsernameNotExist;
import com.hai.validation.DepartmentNameNotExist;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
public class DepartmentCreateForm {
    @NotBlank(message = "{DepartmentForm.name.NotBlank}")
    @Length(max = 50, message = "{DepartmentForm.name.Length}")
    @DepartmentNameNotExist
    private String name;

    @NotNull(message = "Số lượng nhân viên không được để trống.")
    @PositiveOrZero(message = "Số lượng nhân viên phải lớn hơn hoặc bằng 0.")
    private Integer totalMembers;

    @Pattern(
            regexp = "DEVELOPER|TESTER|SCRUM_MASTER|PROJECT_MANAGER",
            message = "Loại phòng ban phải là DEVELOPER, TESTER, SCRUM_MASTER hoặc PROJECT_MANAGER."
    )
    private String type;

    private List<@Valid AccountCreateForm> accounts;

    @Getter
    @Setter
    public static class AccountCreateForm {
        @NotBlank(message = "Tài khoản không được để trống.")
        @Length(max = 50, message = "Tài khoản có tối đa 50 ký tự")
        @AccountUsernameNotExist
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private Account.Role role;
    }
}
