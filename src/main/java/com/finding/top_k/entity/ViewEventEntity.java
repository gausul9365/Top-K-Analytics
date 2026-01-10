package com.finding.top_k.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "view_event")
public class ViewEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id", nullable = false)
    private String itemId;

    @Column(name = "viewed_at", nullable = false)
    private LocalDateTime viewedAt;



    public ViewEventEntity() {
    }

    public ViewEventEntity(String itemId, LocalDateTime viewedAt) {
        this.itemId = itemId;
        this.viewedAt = viewedAt;
    }

    public Long getId() {
        return id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public LocalDateTime getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(LocalDateTime viewedAt) {
        this.viewedAt = viewedAt;
    }
}
