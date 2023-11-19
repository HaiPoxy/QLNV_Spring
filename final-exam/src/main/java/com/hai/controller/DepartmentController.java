package com.hai.controller;

import com.hai.dto.DepartmentDto;
import com.hai.form.DepartmentCreateForm;
import com.hai.form.DepartmentFilterForm;
import com.hai.form.DepartmentUpdateForm;
import com.hai.service.IDepartmentService;
import com.hai.validation.DepartmentIdExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    @Autowired
    private IDepartmentService service;

    @GetMapping
    public Page<DepartmentDto> findAll(DepartmentFilterForm form, Pageable pageable) {
        return service.findAll(form, pageable);
    }

    @GetMapping("/{id}")
    public DepartmentDto findById(@PathVariable("id") @DepartmentIdExists Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public void create(@RequestBody @Valid DepartmentCreateForm form) {
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") @DepartmentIdExists Integer id, @RequestBody DepartmentUpdateForm form) {
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
