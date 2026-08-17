package com.bansi.bookManagement.controller;

import com.bansi.bookManagement.dto.BookRequested;
import com.bansi.bookManagement.dto.UpdateTitle;
import com.bansi.bookManagement.model.Book;
import com.bansi.bookManagement.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("addBook")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return ResponseEntity.ok("Book added successfully");
    }

    @GetMapping("/getBookByID/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        if (id != null) {
            bookService.getBookById(id);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(bookService.getBookById(id));
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        if (bookService.getAllBooks().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<Book>());
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @PutMapping("/updateBookById/{id}")
    public ResponseEntity<String> updateBookById(@PathVariable Long id, @Valid @RequestBody UpdateTitle title) {
        bookService.updateBookById(id, title.getTitle());
        return ResponseEntity.status(HttpStatus.OK).body("Book updated successfully");
    }

}
