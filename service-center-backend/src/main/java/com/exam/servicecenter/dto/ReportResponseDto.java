package com.exam.servicecenter.dto;

import com.exam.servicecenter.enums.ServiceLevel;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ReportResponseDto {

    private long totalClients;

    private long activeClients;

    private long terminatedClients;

    private long clientsWithIssuedItems;

    private Map<ServiceLevel, Long> clientsByLevel;

    private Map<String, Long> clientsByResponsibleEmployee;
}