package com.hai.service;

import com.hai.dto.AccountDto;
import com.hai.form.AccountCreateForm;
import com.hai.form.AccountFilterForm;
import com.hai.form.AccountUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccountService {
    Page<AccountDto> findAll(AccountFilterForm form, Pageable pageable);

    AccountDto findById(Integer id);

    void create(AccountCreateForm form);

    void update(Integer id, AccountUpdateForm form);

    void deleteById(Integer id);

    void deleteAllById(List<Integer> ids);
}
