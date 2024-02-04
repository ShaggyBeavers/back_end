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
            predicates.add(criteriaBuilder.like(root.get("role").get("name"), filter.getRoleName()));
        }
        if (filter.getRegionIds() != null && !filter.getRegionIds().isEmpty()) {
            predicates.add(root.join("userRegions").get("region").get("id").in(filter.getRegionIds()));
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

}
