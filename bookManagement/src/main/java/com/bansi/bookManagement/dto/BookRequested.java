package com.bansi.bookManagement.dto;

import lombok.Data;

@Data
public class BookRequested {
    private String isbn;
    private String title;
    private String author;
    private Integer availableCopies;
    private String availabilityStatus;
}
