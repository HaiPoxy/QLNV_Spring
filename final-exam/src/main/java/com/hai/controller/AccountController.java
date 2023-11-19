package com.hai.controller;

import com.hai.dto.AccountDto;
import com.hai.form.AccountCreateForm;
import com.hai.form.AccountFilterForm;
import com.hai.form.AccountUpdateForm;
import com.hai.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    private IAccountService service;

    @GetMapping
    public Page<AccountDto> findAll(AccountFilterForm form, Pageable pageable) {
        return service.findAll(form, pageable);
    }

    @GetMapping("/{id}")
    public AccountDto findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody AccountCreateForm form) {
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody AccountUpdateForm form) {
        service.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllById(@RequestBody List<Integer> ids) {
        service.deleteAllById(ids);
    }
}
