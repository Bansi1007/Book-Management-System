package com.bansi.bookManagement.controller;

import com.bansi.bookManagement.dto.BookRequested;
import com.bansi.bookManagement.dto.BookResponse;
import com.bansi.bookManagement.dto.UpdateTitle;
import com.bansi.bookManagement.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("addBook")
    public ResponseEntity<String> addBook(@Valid @RequestBody BookRequested book) {
        bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully");
    }

    @GetMapping("/getBookByID/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(id));
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<Page<BookResponse>> getAllBooks(
        @PageableDefault(size=20,sort="id") Pageable pageable){
            return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks(pageable));

    }

    @PutMapping("/updateBookById/{id}")
    public ResponseEntity<String> updateBookById(@PathVariable Long id, @Valid @RequestBody UpdateTitle title) {
        bookService.updateBookById(id, title.getTitle());
        return ResponseEntity.status(HttpStatus.OK).body("Book updated successfully");
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");
    }

    @GetMapping("/getBookByAuthor")
    public ResponseEntity<Page<BookRequested>> getBookByAuthor(@Valid @NotBlank @RequestParam("author") String author,
                                                               @PageableDefault (size = 20,sort = "title")Pageable pageable) {
        return ResponseEntity.ok(bookService.getBookByAuthor(author,pageable));
    }
}
