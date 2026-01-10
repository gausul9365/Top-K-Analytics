package com.finding.top_k.controller;

import com.finding.top_k.dto.ViewRequest;
import com.finding.top_k.dto.TopKResponse;
import com.finding.top_k.service.AnalyticsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
@Validated
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @PostMapping("/view")
    @ResponseStatus(HttpStatus.CREATED)
    public void recordView(@Valid @RequestBody ViewRequest request) {
        analyticsService.recordView(request);
    }

    @GetMapping("/counts")
    public List<TopKResponse> getCounts() {
        return analyticsService.getItemViewCounts();
    }


    @GetMapping("/top")
    public List<TopKResponse> getTopK(
            @RequestParam
            @Min(value = 1, message = "k must be >= 1")
            int k,

            @RequestParam(required = false)
            @Min(value = 1, message = "minutes must be >= 1")
            Integer minutes
    ) {
        if (minutes == null) {
            return analyticsService.getTopK(k);
        }
        return analyticsService.getTopK(k, minutes);
    }
}
