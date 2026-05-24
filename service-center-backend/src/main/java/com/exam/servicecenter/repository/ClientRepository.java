package com.exam.servicecenter.repository;

import com.exam.servicecenter.entity.ClientEntity;
import com.exam.servicecenter.enums.ClientStatus;
import com.exam.servicecenter.enums.ServiceLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    long countByStatus(ClientStatus status);

    @Query("""
            SELECT COUNT(c)
            FROM ClientEntity c
            WHERE c.issuedItem IS NOT NULL AND c.issuedItem <> ''
            """)
    long countClientsWithIssuedItems();

    @Query("""
            SELECT c
            FROM ClientEntity c
            WHERE (:search IS NULL OR :search = ''
                OR LOWER(c.fullName) LIKE LOWER(CONCAT('%', :search, '%'))
                OR LOWER(c.phone) LIKE LOWER(CONCAT('%', :search, '%'))
                OR LOWER(c.email) LIKE LOWER(CONCAT('%', :search, '%')))
            AND (:status IS NULL OR c.status = :status)
            AND (:serviceLevel IS NULL OR c.serviceLevel = :serviceLevel)
            AND (:responsibleEmployee IS NULL OR :responsibleEmployee = ''
                OR LOWER(c.responsibleEmployee) LIKE LOWER(CONCAT('%', :responsibleEmployee, '%')))
            AND (
                :hasIssuedItem IS NULL
                OR (:hasIssuedItem = true AND c.issuedItem IS NOT NULL AND c.issuedItem <> '')
                OR (:hasIssuedItem = false AND (c.issuedItem IS NULL OR c.issuedItem = ''))
            )
            ORDER BY c.id DESC
            """)
    List<ClientEntity> findByFilters(
            @Param("search") String search,
            @Param("status") ClientStatus status,
            @Param("serviceLevel") ServiceLevel serviceLevel,
            @Param("responsibleEmployee") String responsibleEmployee,
            @Param("hasIssuedItem") Boolean hasIssuedItem
    );
}