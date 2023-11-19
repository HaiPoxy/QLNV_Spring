package com.hai.controller;

import com.hai.form.AuthRegisterForm;
import com.hai.form.AuthUpdateForm;
import com.hai.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final IAuthService service;

    @Autowired
    public AuthController(IAuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public void create(@RequestBody AuthRegisterForm form) {
        service.create(form);
    }

    @PutMapping("/update")
    public void update(Principal principal, @RequestBody AuthUpdateForm form) {
        String username = principal.getName();
        service.update(username, form);
    }
}
