package com.exam.servicecenter.service;

import com.exam.servicecenter.dto.ClientCreateDto;
import com.exam.servicecenter.dto.ClientResponseDto;
import com.exam.servicecenter.dto.ClientUpdateDto;
import com.exam.servicecenter.dto.ReportResponseDto;
import com.exam.servicecenter.entity.ClientEntity;
import com.exam.servicecenter.enums.ClientStatus;
import com.exam.servicecenter.enums.ServiceLevel;
import com.exam.servicecenter.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientResponseDto> findAll(
            String search,
            ClientStatus status,
            ServiceLevel serviceLevel,
            String responsibleEmployee,
            Boolean hasIssuedItem
    ) {
        return clientRepository.findByFilters(
                        search,
                        status,
                        serviceLevel,
                        responsibleEmployee,
                        hasIssuedItem
                )
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public ClientResponseDto findById(Long id) {
        return toResponseDto(getClientOrThrow(id));
    }

    public ClientResponseDto create(ClientCreateDto dto) {
        ClientEntity client = ClientEntity.builder()
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .status(ClientStatus.ACTIVE)
                .serviceLevel(dto.getServiceLevel())
                .issuedItem(dto.getIssuedItem())
                .responsibleEmployee(dto.getResponsibleEmployee())
                .serviceStartDate(dto.getServiceStartDate() != null ? dto.getServiceStartDate() : LocalDate.now())
                .serviceEndDate(dto.getServiceEndDate())
                .build();

        return toResponseDto(clientRepository.save(client));
    }

    public ClientResponseDto update(Long id, ClientUpdateDto dto) {
        ClientEntity client = getClientOrThrow(id);

        client.setFullName(dto.getFullName());
        client.setPhone(dto.getPhone());
        client.setEmail(dto.getEmail());
        client.setStatus(dto.getStatus());
        client.setServiceLevel(dto.getServiceLevel());
        client.setIssuedItem(dto.getIssuedItem());
        client.setResponsibleEmployee(dto.getResponsibleEmployee());
        client.setServiceStartDate(dto.getServiceStartDate());
        client.setServiceEndDate(dto.getServiceEndDate());
        client.setTerminationReason(dto.getTerminationReason());

        return toResponseDto(clientRepository.save(client));
    }

    public void delete(Long id) {
        ClientEntity client = getClientOrThrow(id);
        clientRepository.delete(client);
    }

    public ClientResponseDto terminate(Long id, String reason) {
        ClientEntity client = getClientOrThrow(id);

        client.setStatus(ClientStatus.TERMINATED);
        client.setTerminationReason(reason);
        client.setServiceEndDate(LocalDate.now());

        return toResponseDto(clientRepository.save(client));
    }

    public ClientResponseDto assignEmployee(Long id, String employeeName) {
        ClientEntity client = getClientOrThrow(id);

        client.setResponsibleEmployee(employeeName);

        return toResponseDto(clientRepository.save(client));
    }

    public ClientResponseDto issueItem(Long id, String issuedItem) {
        ClientEntity client = getClientOrThrow(id);

        client.setIssuedItem(issuedItem);

        return toResponseDto(clientRepository.save(client));
    }

    public ClientResponseDto extendService(Long id, LocalDate newEndDate) {
        ClientEntity client = getClientOrThrow(id);

        client.setServiceEndDate(newEndDate);

        return toResponseDto(clientRepository.save(client));
    }

    public ClientResponseDto changeLevel(Long id, ServiceLevel serviceLevel) {
        ClientEntity client = getClientOrThrow(id);

        client.setServiceLevel(serviceLevel);

        return toResponseDto(clientRepository.save(client));
    }

    public ReportResponseDto getReport() {
        long totalClients = clientRepository.count();
        long activeClients = clientRepository.countByStatus(ClientStatus.ACTIVE);
        long terminatedClients = clientRepository.countByStatus(ClientStatus.TERMINATED);
        long clientsWithIssuedItems = clientRepository.countClientsWithIssuedItems();

        List<ClientEntity> clients = clientRepository.findAll();

        Map<ServiceLevel, Long> clientsByLevel = Arrays.stream(ServiceLevel.values())
                .collect(Collectors.toMap(
                        level -> level,
                        level -> clients.stream()
                                .filter(client -> client.getStatus() == ClientStatus.ACTIVE)
                                .filter(client -> client.getServiceLevel() == level)
                                .count()
                ));

        Map<String, Long> clientsByResponsibleEmployee = clients.stream()
                .filter(client -> client.getResponsibleEmployee() != null)
                .filter(client -> !client.getResponsibleEmployee().isBlank())
                .filter(client -> client.getStatus() == ClientStatus.ACTIVE)
                .collect(Collectors.groupingBy(
                        ClientEntity::getResponsibleEmployee,
                        Collectors.counting()
                ));

        return ReportResponseDto.builder()
                .totalClients(totalClients)
                .activeClients(activeClients)
                .terminatedClients(terminatedClients)
                .clientsWithIssuedItems(clientsWithIssuedItems)
                .clientsByLevel(clientsByLevel)
                .clientsByResponsibleEmployee(clientsByResponsibleEmployee)
                .build();
    }

    private ClientEntity getClientOrThrow(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Клиент с id " + id + " не найден"));
    }

    private ClientResponseDto toResponseDto(ClientEntity client) {
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