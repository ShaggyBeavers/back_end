package com.lpnu.shaggybeavers.specification;

import com.lpnu.shaggybeavers.domain.RoleEnum;
import com.lpnu.shaggybeavers.filter.ReportFilter;
import com.lpnu.shaggybeavers.model.Report;
import jakarta.persistence.criteria.*;
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

        if (filter.getUserRole() == RoleEnum.MODERATOR) {
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.in(root.get("region").get("id")).in(filter.getRegionIds()),
                    criteriaBuilder.isNull(root.get("region").get("id"))
            ));

        }
        if (filter.getUserRole() == RoleEnum.REGIONAL_MODERATOR) {
            Predicate sameRegionOrNull = criteriaBuilder.or(
                    root.get("region").get("id").in(filter.getRegionIds()),
                    root.get("region").get("id").isNull()
            );
            predicates.add(sameRegionOrNull);
        }
        if (filter.getFile() != null) {
            predicates.add(filter.getFile() ? criteriaBuilder.isNotEmpty(root.get("reportFiles")) : criteriaBuilder.isEmpty(root.get("reportFiles")));
        }

        predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
