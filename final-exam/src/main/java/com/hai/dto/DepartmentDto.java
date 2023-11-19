package com.hai.dto;

import com.hai.controller.AccountController;
import com.hai.controller.DepartmentController;
import com.hai.entity.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class DepartmentDto extends RepresentationModel<DepartmentDto> {
    private Integer id;
    private String name;
    private Integer totalMembers;
    private String type;
    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", locale = "vi_VN")
    private LocalDateTime createdAt;
    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", locale = "vi_VN")
    private LocalDateTime updatedAt;
    private List<AccountDto> accounts;

    public DepartmentDto withSelfRel() {
        if (accounts != null) {
            for (AccountDto account : accounts) {
                Link link = linkTo(methodOn(AccountController.class).findById(account.id)).withSelfRel();
                account.add(link);
            }
        }
        Link link = linkTo(methodOn(DepartmentController.class).findById(id)).withSelfRel();
        return add(link);
    }

    @Getter
    @Setter
    public static class AccountDto extends RepresentationModel<AccountDto> {
        // @JsonProperty("accountId")
        private Integer id;
        private String username;
        private String firstName;
        private String lastName;
        private Account.Role role;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public AccountDto withSelfRel() {
            Link link = linkTo(methodOn(AccountController.class).findById(id)).withSelfRel();
            return add(link);
        }
    }
}
