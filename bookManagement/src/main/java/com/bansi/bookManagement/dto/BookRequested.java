package com.bansi.bookManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BookRequested {
    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^(?:\\d{10}|\\d{13})$", message = "ISBN must be 10 or 13 digits")
    private String isbn;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @PositiveOrZero(message = "Available copies cannot be negative")
    private Integer availableCopies;

    public BookRequested(@NotBlank(message = "Title is required")
                         String title, @NotBlank(message = "Author is required") String author) {
    }
}
