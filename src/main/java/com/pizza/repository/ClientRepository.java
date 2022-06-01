package com.pizza.repository;

import com.pizza.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByFirstName(String firstName);

    List<Client> findByLastName(String lastName);

    void deleteByClientCode(String clientCode);

    Optional<Client> findByPhoneNumber(String phoneNumber);
}