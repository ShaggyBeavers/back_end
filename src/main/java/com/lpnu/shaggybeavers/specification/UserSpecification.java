package com.lpnu.shaggybeavers.specification;

import com.lpnu.shaggybeavers.filter.UserFilter;
import com.lpnu.shaggybeavers.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserSpecification implements Specification<User> {

    private final UserFilter filter;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getRoleName() != null) {
            predicates.add(root.get("role").get("name").in(filter.getRoleName()));
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

}
