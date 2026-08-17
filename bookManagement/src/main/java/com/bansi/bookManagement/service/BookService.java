package com.bansi.bookManagement.service;

import com.bansi.bookManagement.dto.BookRequested;
import com.bansi.bookManagement.exception.BookAlreadyExist;
import com.bansi.bookManagement.exception.BookNotFoundException;
import com.bansi.bookManagement.exception.NoContentException;
import com.bansi.bookManagement.model.Book;
import com.bansi.bookManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public String addBook(Book book) {
        bookRepository.save(book);
        return "Book added successfully";
    }

    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found!"));
        return book;
    }

    public List<Book> getAllBooks() {
        if (bookRepository.findAll().isEmpty()) {
            throw new NoContentException("No content available");
        }
        List<Book> allBooks = bookRepository.findAll();
        return allBooks;
    }

    public String updateBookById(Long id, String title) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found!"));
        book.setTitle(title);
        bookRepository.save(book);
        return "Book updated successfully";

    }
}
