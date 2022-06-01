package com.pizza.repository;

import com.pizza.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    void deleteByFirstNameAndLastName(String firstName, String lastName);
}
