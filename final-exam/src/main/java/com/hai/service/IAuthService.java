package com.hai.service;

import com.hai.form.AuthRegisterForm;
import com.hai.form.AuthUpdateForm;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthService extends UserDetailsService {
    void create(AuthRegisterForm form);

    void update(String username, AuthUpdateForm form);
}
