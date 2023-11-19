package com.hai.specification;

import com.hai.entity.Account;
import com.hai.entity.Account_;
import com.hai.entity.Department_;
import com.hai.form.AccountFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AccountSpecification {
    public static Specification<Account> buildSpec(AccountFilterForm form) {
        return (form == null) ? null : (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(form.getSearch())) {
                String pattern = "%" + form.getSearch().trim() + "%";
                Predicate hasUsernameLike = builder.like(root.get(Account_.username), pattern);
                Predicate hasDepartmentNameLike = builder.like(
                        root.get(Account_.department).get(Department_.name), pattern);
                predicates.add(builder.or(hasUsernameLike, hasDepartmentNameLike));
            }
            if (form.getRole() != null) {
                Predicate predicate = builder.equal(root.get(Account_.role), form.getRole());
                predicates.add(predicate);
            }
            if (form.getMinId() != null) {
                Predicate predicate = builder.greaterThanOrEqualTo(root.get(Account_.id), form.getMinId());
                predicates.add(predicate);
            }
            if (form.getMaxId() != null) {
                Predicate predicate = builder.lessThanOrEqualTo(root.get(Account_.id), form.getMaxId());
                predicates.add(predicate);
            }
            if (form.getMinCreatedDate() != null) {
                LocalDateTime minCreatedAt = LocalDateTime.of(form.getMinCreatedDate(), LocalTime.MIN);
                Predicate predicate = builder.greaterThanOrEqualTo(root.get(Account_.createdAt), minCreatedAt);
                predicates.add(predicate);
            }
            if (form.getMaxCreatedDate() != null) {
                LocalDateTime maxCreatedAt = LocalDateTime.of(form.getMaxCreatedDate(), LocalTime.MAX);
                Predicate predicate = builder.lessThanOrEqualTo(root.get(Account_.createdAt), maxCreatedAt);
                predicates.add(predicate);
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
