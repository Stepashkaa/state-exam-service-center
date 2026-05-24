package com.exam.servicecenter.dto;

import com.exam.servicecenter.enums.ServiceLevel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientCreateDto {

    @NotBlank(message = "ФИО клиента обязательно")
    private String fullName;

    private String phone;

    @Email(message = "Некорректный email")
    private String email;

    @NotNull(message = "Уровень обслуживания обязателен")
    private ServiceLevel serviceLevel;

    private String issuedItem;

    private String responsibleEmployee;

    private LocalDate serviceStartDate;

    private LocalDate serviceEndDate;
}