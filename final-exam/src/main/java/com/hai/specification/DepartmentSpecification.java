package com.hai.specification;

import com.hai.entity.Account_;
import com.hai.entity.Department;
import com.hai.entity.Department_;
import com.hai.form.DepartmentFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DepartmentSpecification {
    public static Specification<Department> buildSpec(DepartmentFilterForm form) {
        return (form == null) ? null : (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(form.getSearch())) {
                String pattern = "%" + form.getSearch().trim() + "%";
                Predicate hasNameLike = builder.like(root.get(Department_.name), pattern);
                Predicate hasAccountUsernameLike = builder.like(
                        root.join(Department_.accounts, JoinType.LEFT).get(Account_.username), pattern);
                predicates.add(builder.or(hasNameLike, hasAccountUsernameLike));
            }
            if (form.getType() != null) {
                Predicate predicate = builder.equal(root.get(Department_.type), form.getType());
                predicates.add(predicate);
            }
            if (form.getMinTotalMembers() != null) {
                Predicate predicate = builder.greaterThanOrEqualTo(
                        root.get(Department_.totalMembers), form.getMinTotalMembers());
                predicates.add(predicate);
            }
            if (form.getMaxTotalMembers() != null) {
                Predicate predicate = builder.lessThanOrEqualTo(
                        root.get(Department_.totalMembers), form.getMaxTotalMembers());
                predicates.add(predicate);
            }
            if (form.getMinCreatedDate() != null) {
                LocalDateTime minCreatedAt = LocalDateTime.of(form.getMinCreatedDate(), LocalTime.MIN);
                Predicate predicate = builder.greaterThanOrEqualTo(root.get(Department_.createdAt), minCreatedAt);
                predicates.add(predicate);
            }
            if (form.getMaxCreatedDate() != null) {
                LocalDateTime maxCreatedAt = LocalDateTime.of(form.getMaxCreatedDate(), LocalTime.MAX);
                Predicate predicate = builder.lessThanOrEqualTo(root.get(Department_.createdAt), maxCreatedAt);
                predicates.add(predicate);
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
