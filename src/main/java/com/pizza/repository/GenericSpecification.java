package com.pizza.repository;

import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                case ">":
                    if (criteria.getValue() instanceof LocalDate) {
                        predicates.add(criteriaBuilder.greaterThan(
                                root.get(criteria.getKey()), (LocalDate) criteria.getValue()));
                    } else {
                        predicates.add(criteriaBuilder.greaterThan(
                                root.get(criteria.getKey()), criteria.getValue().toString()));
                    }
                    break;
                case "<":
                    if (criteria.getValue() instanceof LocalDate) {
                        predicates.add(criteriaBuilder.lessThan(
                                root.get(criteria.getKey()), (LocalDate) criteria.getValue()));
                    } else {
                        predicates.add(criteriaBuilder.lessThan(
                                root.get(criteria.getKey()), criteria.getValue().toString()));
                    }
                    break;
                case ">=":
                    if (criteria.getValue() instanceof LocalDate) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                                root.get(criteria.getKey()), (LocalDate) criteria.getValue()));
                    } else {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                                root.get(criteria.getKey()), criteria.getValue().toString()));
                    }
                    break;
                case "<=":
                    if (criteria.getValue() instanceof LocalDate) {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(
                                root.get(criteria.getKey()), (LocalDate) criteria.getValue()));
                    }
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case "!=":
                    predicates.add(criteriaBuilder.notEqual(
                            root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case "==":
                    predicates.add(criteriaBuilder.equal(
                            root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case "match":
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase() + "%"));
                    break;
                case "beginsWith":
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(criteria.getKey())),
                            criteria.getValue().toString().toLowerCase() + "%"));
                    break;
                case "endsWith":
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase()));
                    break;
                case "in":
                    predicates.add(criteriaBuilder.in(root.get(criteria.getKey())).value(criteria.getValue()));
                    break;
                case "notIn":
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
