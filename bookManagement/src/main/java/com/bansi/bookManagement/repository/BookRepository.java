package com.bansi.bookManagement.repository;

import com.bansi.bookManagement.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByAuthor(String author, Pageable pageable);

    boolean existsByTitle(String title);
}
