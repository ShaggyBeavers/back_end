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

        if (filter.getHistoricalPeriod() != null) {
            root.join("relicInfo");
            predicates.add(criteriaBuilder.equal(root.get("relicInfo").get("historicalPeriod").get("name"), filter.getHistoricalPeriod()));
        }
        if (filter.getStatus() != null) {
            predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
        }
        if (filter.getTechnique() != null) {
            root.join("relicInfo");
            predicates.add(criteriaBuilder.equal(root.get("relicInfo").get("technique").get("name"), filter.getTechnique()));
        }
        if (filter.getCollection() != null) {
            predicates.add(criteriaBuilder.equal(root.get("collection"), filter.getCollection()));
        }
        if (filter.getCategory() != null) {
            root.join("relicCategories");
            predicates.add(criteriaBuilder.equal(root.get("relicCategories").get("category").get("categoryName"), filter.getCategory()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
