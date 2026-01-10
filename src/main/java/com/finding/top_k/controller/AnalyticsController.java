package com.finding.top_k.controller;

import com.finding.top_k.dto.ViewRequest;
import com.finding.top_k.service.AnalyticsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @PostMapping("/view")
    @ResponseStatus(HttpStatus.CREATED)
    public void recordView(@RequestBody ViewRequest request) {
        analyticsService.recordView(request);
    }
}
