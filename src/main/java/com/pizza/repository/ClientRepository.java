package com.pizza.repository;

import com.pizza.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByFirstName(String firstName);

    List<Client> findByLastName(String lastName);

    Optional<Client> findByPhoneNumber(String phoneNumber);

    Optional<Client> findByClientCode(String clientCode);

    void deleteByClientCode(String clientCode);

    void deleteByPhoneNumber(String phoneNumber);

    List<Client> findByAddressContainingIgnoreCase(String address);
}
