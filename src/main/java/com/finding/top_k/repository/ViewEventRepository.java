package com.finding.top_k.repository;

import com.finding.top_k.dto.TopKResponse;
import com.finding.top_k.entity.ViewEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewEventRepository
        extends JpaRepository<ViewEventEntity, Long> {

    @Query("""
        SELECT new com.finding.top_k.dto.TopKResponse(
            v.itemId,
            COUNT(v)
        )
        FROM ViewEventEntity v
        GROUP BY v.itemId
    """)
    List<TopKResponse> fetchItemViewCounts();
}
