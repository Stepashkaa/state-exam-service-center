package com.exam.servicecenter.controller;

import com.exam.servicecenter.dto.ReportResponseDto;
import com.exam.servicecenter.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ReportController {

    private final ClientService clientService;

    @GetMapping("/summary")
    public ReportResponseDto getSummaryReport() {
        return clientService.getReport();
    }
}