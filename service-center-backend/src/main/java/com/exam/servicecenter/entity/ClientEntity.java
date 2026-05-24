package com.exam.servicecenter.entity;

import com.exam.servicecenter.enums.ClientStatus;
import com.exam.servicecenter.enums.ServiceLevel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    private String phone;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClientStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceLevel serviceLevel;

    private String issuedItem;

    private String responsibleEmployee;

    private LocalDate serviceStartDate;

    private LocalDate serviceEndDate;

    private String terminationReason;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}