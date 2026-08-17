package com.bansi.bookManagement.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateTitle {

    @NotBlank(message = "Title is required")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
