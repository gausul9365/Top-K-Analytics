package com.finding.top_k.dto;

public class TopKResponse {

    private String itemId;
    private long count;

    public TopKResponse(String itemId, long count) {
        this.itemId = itemId;
        this.count = count;
    }

    public String getItemId() {
        return itemId;
    }

    public long getCount() {
        return count;
    }
}
