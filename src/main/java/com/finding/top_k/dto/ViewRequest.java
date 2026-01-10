package com.finding.top_k.dto;

import jakarta.validation.constraints.NotBlank;

public class ViewRequest {

    @NotBlank(message = "itemId must not be blank")
    private String itemId;

    public ViewRequest() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
