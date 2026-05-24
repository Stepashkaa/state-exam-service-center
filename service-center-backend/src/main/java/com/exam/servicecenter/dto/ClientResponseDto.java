package com.exam.servicecenter.dto;

import com.exam.servicecenter.enums.ClientStatus;
import com.exam.servicecenter.enums.ServiceLevel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ClientResponseDto {

    private Long id;

    private String fullName;

    private String phone;

    private String email;

    private ClientStatus status;

    private ServiceLevel serviceLevel;

    private String issuedItem;

    private String responsibleEmployee;

    private LocalDate serviceStartDate;

    private LocalDate serviceEndDate;

    private String terminationReason;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}