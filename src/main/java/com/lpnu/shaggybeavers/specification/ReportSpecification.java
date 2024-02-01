package com.lpnu.shaggybeavers.specification;

import com.lpnu.shaggybeavers.filter.ReportFilter;
import com.lpnu.shaggybeavers.model.Report;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ReportSpecification implements Specification<Report> {

    private final ReportFilter filter;

    @Override
    public Predicate toPredicate(Root<Report> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getUserId() != null) {
            predicates.add(root.get("user").get("id").in(filter.getUserId()));
        }
        if (filter.getRegionIds() != null) {
            predicates.add(root.get("region").get("id").in(filter.getRegionIds()));
        }
        if (filter.getCategoryIds() != null) {
            predicates.add(root.join("reportCategories").get("category").get("id").in(filter.getCategoryIds()));
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
