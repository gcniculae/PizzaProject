package com.pizza.repository;

import com.pizza.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    List<Client> findByFirstName(String firstName);

    List<Client> findByLastName(String lastName);

    Optional<Client> findByPhoneNumber(String phoneNumber);

    Optional<Client> findByClientCode(String clientCode);

    List<Client> findByAddressContainingIgnoreCase(String address);

    @Query(value = "from Client c where c.dateOfBirth between :startDate and :endDate")
    List<Client> findClientsBornInTimeframe(LocalDate startDate, LocalDate endDate);

    void deleteByClientCode(String clientCode);

    void deleteByPhoneNumber(String phoneNumber);
}
