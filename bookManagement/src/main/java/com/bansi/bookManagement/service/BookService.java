package com.bansi.bookManagement.service;

import com.bansi.bookManagement.dto.BookRequested;
import com.bansi.bookManagement.dto.BookResponse;
import com.bansi.bookManagement.exception.BookAlreadyExist;
import com.bansi.bookManagement.exception.BookNotFoundException;
import com.bansi.bookManagement.model.Book;
import com.bansi.bookManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public String addBook(BookRequested requested) {
        if (bookRepository.existsByTitle(requested.getTitle())) {
            throw new BookAlreadyExist("Book with title '" + requested.getTitle() + "' already exists!");
        }
        Book book = new Book();
        book.setIsbn(requested.getIsbn());
        book.setTitle(requested.getTitle());
        book.setAuthor(requested.getAuthor());
        book.setAvailableCopies(requested.getAvailableCopies());
        bookRepository.save(book);
        return "Book added successfully";
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found!"));
        return toResponse(book);
    }

    public Page<BookResponse> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(this::toResponse);
    }

    public String updateBookById(Long id, String title) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found!"));
        book.setTitle(title);
        bookRepository.save(book);
        return "Book updated successfully";
    }

    public String deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
        return "Book deleted successfully";
    }

    public Page<BookRequested> getBookByAuthor(String author, Pageable pageable) {
        Page<Book> books = bookRepository.findByAuthor(author, pageable);
        if (books.isEmpty()) {
            throw new BookNotFoundException("No books found for author '" + author + "'");
        }
        return books.map(book -> new BookRequested(book.getTitle(), book.getAuthor()));
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getAvailableCopies());
    }
}
