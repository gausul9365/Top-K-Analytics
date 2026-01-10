package com.finding.top_k.repository;

import com.finding.top_k.entity.ViewEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewEventRepository
        extends JpaRepository<ViewEventEntity, Long> {
}
