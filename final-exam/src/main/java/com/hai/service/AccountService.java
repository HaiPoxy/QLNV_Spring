package com.hai.service;

import com.hai.dto.AccountDto;
import com.hai.entity.Account;
import com.hai.form.AccountCreateForm;
import com.hai.form.AccountFilterForm;
import com.hai.form.AccountUpdateForm;
import com.hai.repository.IAccountRepository;
import com.hai.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    private final IAccountRepository repository;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    @Autowired
    public AccountService(IAccountRepository repository, ModelMapper mapper, PasswordEncoder encoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public Page<AccountDto> findAll(AccountFilterForm form, Pageable pageable) {
        Specification<Account> spec = AccountSpecification.buildSpec(form);
        return repository.findAll(spec, pageable)
                .map(account -> mapper.map(account, AccountDto.class).withSelfRel());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(account -> mapper.map(account, AccountDto.class))
                .orElse(null);
    }

    @Override
    public void create(AccountCreateForm form) {
        Account account = mapper.map(form, Account.class);
        String encodedPassword = encoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
        repository.save(account);
    }

    @Override
    public void update(Integer id, AccountUpdateForm form) {
        Account account = mapper.map(form, Account.class);
        String encodedPassword = encoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
        account.setId(id);
        repository.save(account);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<Integer> ids) {
        repository.deleteAllById(ids);
    }
}
