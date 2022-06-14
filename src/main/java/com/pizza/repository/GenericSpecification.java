package com.pizza.repository;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {

    private List<SearchCriteria> searchCriteriaList;

    public GenericSpecification() {
        this.searchCriteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        searchCriteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : searchCriteriaList) {
            switch (criteria.getOperation()) {
                case GREATER_THAN:
                    if (criteria.getValue() instanceof LocalDate) {
                        predicates.add(criteriaBuilder.greaterThan(
                                root.get(criteria.getKey()), (LocalDate) criteria.getValue()));
                    } else {
                        predicates.add(criteriaBuilder.greaterThan(
                                root.get(criteria.getKey()), criteria.getValue().toString()));
                    }
                    break;
                case LESS_THAN:
                    if (criteria.getValue() instanceof LocalDate) {
                        predicates.add(criteriaBuilder.lessThan(
                                root.get(criteria.getKey()), (LocalDate) criteria.getValue()));
                    } else {
                        predicates.add(criteriaBuilder.lessThan(
                                root.get(criteria.getKey()), criteria.getValue().toString()));
                    }
                    break;
                case GREATER_OR_EQUAL_THAN:
                    if (criteria.getValue() instanceof LocalDate) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                                root.get(criteria.getKey()), (LocalDate) criteria.getValue()));
                    } else {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                                root.get(criteria.getKey()), criteria.getValue().toString()));
                    }
                    break;
                case LESS_OR_EQUAL_THAN:
                    if (criteria.getValue() instanceof LocalDate) {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(
                                root.get(criteria.getKey()), (LocalDate) criteria.getValue()));
                    }
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case NOT_EQUAL:
                    predicates.add(criteriaBuilder.notEqual(
                            root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case EQUAL:
                    predicates.add(criteriaBuilder.equal(
                            root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case MATCH:
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase() + "%"));
                    break;
                case STARTS_WITH:
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(criteria.getKey())),
                            criteria.getValue().toString().toLowerCase() + "%"));
                    break;
                case ENDS_WITH:
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase()));
                    break;
                case IN:
                    predicates.add(criteriaBuilder.in(root.get(criteria.getKey())).value(criteria.getValue()));
                    break;
                case NOT_IN:
                    predicates.add(criteriaBuilder.not(root.get(criteria.getKey())).in(criteria.getValue()));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    public Object convertData(Object inputData) {
        if (inputData instanceof LocalDate) {
            return inputData;
        } else {
            return inputData.toString();
        }
    }
}
