package com.exam.servicecenter.mapper;

import com.exam.servicecenter.dto.ClientCreateDto;
import com.exam.servicecenter.dto.ClientResponseDto;
import com.exam.servicecenter.dto.ClientUpdateDto;
import com.exam.servicecenter.entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientEntity toEntity(ClientCreateDto dto) {
        return ClientEntity.builder()
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .serviceLevel(dto.getServiceLevel())
                .issuedItem(dto.getIssuedItem())
                .responsibleEmployee(dto.getResponsibleEmployee())
                .serviceStartDate(dto.getServiceStartDate())
                .serviceEndDate(dto.getServiceEndDate())
                .build();
    }

    public void updateEntity(ClientEntity client, ClientUpdateDto dto) {
        client.setFullName(dto.getFullName());
        client.setPhone(dto.getPhone());
        client.setEmail(dto.getEmail());
        client.setServiceLevel(dto.getServiceLevel());
        client.setIssuedItem(dto.getIssuedItem());
        client.setResponsibleEmployee(dto.getResponsibleEmployee());
        client.setServiceStartDate(dto.getServiceStartDate());
        client.setServiceEndDate(dto.getServiceEndDate());
        client.setTerminationReason(dto.getTerminationReason());
    }

    public ClientResponseDto toResponseDto(ClientEntity client) {
        return ClientResponseDto.builder()
                .id(client.getId())
                .fullName(client.getFullName())
                .phone(client.getPhone())
                .email(client.getEmail())
                .status(client.getStatus())
                .serviceLevel(client.getServiceLevel())
                .issuedItem(client.getIssuedItem())
                .responsibleEmployee(client.getResponsibleEmployee())
                .serviceStartDate(client.getServiceStartDate())
                .serviceEndDate(client.getServiceEndDate())
                .terminationReason(client.getTerminationReason())
                .createdAt(client.getCreatedAt())
                .updatedAt(client.getUpdatedAt())
                .build();
    }
}