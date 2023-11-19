package com.hai.dto;

import com.hai.controller.AccountController;
import com.hai.entity.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class AccountDto extends RepresentationModel<AccountDto> {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private Account.Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String departmentName;

    public AccountDto withSelfRel() {
        Link link = linkTo(methodOn(AccountController.class).findById(id)).withSelfRel();
        return add(link);
    }
}
