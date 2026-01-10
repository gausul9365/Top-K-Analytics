package com.finding.top_k.service;

import com.finding.top_k.dto.ViewRequest;
import com.finding.top_k.entity.ViewEventEntity;
import com.finding.top_k.repository.ViewEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnalyticsService {

    private final ViewEventRepository viewEventRepository;

    public AnalyticsService(ViewEventRepository viewEventRepository) {
        this.viewEventRepository = viewEventRepository;
    }

    public void recordView(ViewRequest request) {

        ViewEventEntity entity = new ViewEventEntity();
        entity.setItemId(request.getItemId());
        entity.setViewedAt(LocalDateTime.now());

        viewEventRepository.save(entity);
    }
}
