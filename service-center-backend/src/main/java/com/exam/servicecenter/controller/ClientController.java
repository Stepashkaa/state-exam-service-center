package com.exam.servicecenter.controller;

import com.exam.servicecenter.dto.ClientCreateDto;
import com.exam.servicecenter.dto.ClientResponseDto;
import com.exam.servicecenter.dto.ClientUpdateDto;
import com.exam.servicecenter.enums.ClientStatus;
import com.exam.servicecenter.enums.ServiceLevel;
import com.exam.servicecenter.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientResponseDto> findAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) ClientStatus status,
            @RequestParam(required = false) ServiceLevel serviceLevel,
            @RequestParam(required = false) String responsibleEmployee,
            @RequestParam(required = false) Boolean hasIssuedItem
    ) {
        return clientService.findAll(
                search,
                status,
                serviceLevel,
                responsibleEmployee,
                hasIssuedItem
        );
    }

    @GetMapping("/{id}")
    public ClientResponseDto findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping
    public ClientResponseDto create(@Valid @RequestBody ClientCreateDto dto) {
        return clientService.create(dto);
    }

    @PutMapping("/{id}")
    public ClientResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody ClientUpdateDto dto
    ) {
        return clientService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }

    @PatchMapping("/{id}/terminate")
    public ClientResponseDto terminate(
            @PathVariable Long id,
            @RequestBody Map<String, String> request
    ) {
        return clientService.terminate(id, request.get("reason"));
    }

    @PatchMapping("/{id}/assign-employee")
    public ClientResponseDto assignEmployee(
            @PathVariable Long id,
            @RequestBody Map<String, String> request
    ) {
        return clientService.assignEmployee(id, request.get("employeeName"));
    }

    @PatchMapping("/{id}/issue-item")
    public ClientResponseDto issueItem(
            @PathVariable Long id,
            @RequestBody Map<String, String> request
    ) {
        return clientService.issueItem(id, request.get("issuedItem"));
    }

    @PatchMapping("/{id}/extend-service")
    public ClientResponseDto extendService(
            @PathVariable Long id,
            @RequestBody Map<String, String> request
    ) {
        LocalDate newEndDate = LocalDate.parse(request.get("newEndDate"));
        return clientService.extendService(id, newEndDate);
    }

    @PatchMapping("/{id}/change-level")
    public ClientResponseDto changeLevel(
            @PathVariable Long id,
            @RequestBody Map<String, String> request
    ) {
        ServiceLevel serviceLevel = ServiceLevel.valueOf(request.get("serviceLevel"));
        return clientService.changeLevel(id, serviceLevel);
    }
}