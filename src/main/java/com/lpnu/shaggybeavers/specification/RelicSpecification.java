package com.lpnu.shaggybeavers.specification;

import com.lpnu.shaggybeavers.filter.RelicFilter;
import com.lpnu.shaggybeavers.model.Relic;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RelicSpecification implements Specification<Relic> {

    private final RelicFilter filter;

    @Override
    public Predicate toPredicate(Root<Relic> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getHistoricalPeriods() != null) {
            predicates.add(root.get("relicInfo").get("historicalPeriod").get("name").in(filter.getHistoricalPeriods()));
        }
        if (filter.getStatuses() != null) {
            predicates.add(root.get("status").in(filter.getStatuses()));
        }
        if (filter.getTechniques() != null) {
            predicates.add(root.get("relicInfo").get("technique").get("name").in(filter.getTechniques()));
        }
        if (filter.getCollections() != null) {
            predicates.add(root.get("collection").in(filter.getCollections()));
        }
        if (filter.getCategories() != null) {
            predicates.add(root.get("relicCategories").get("category").get("categoryName").in(filter.getCategories()));
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
