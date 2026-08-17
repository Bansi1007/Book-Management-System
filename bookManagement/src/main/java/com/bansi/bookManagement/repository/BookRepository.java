package com.bansi.bookManagement.repository;

import com.bansi.bookManagement.dto.BookRequested;
import com.bansi.bookManagement.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {



}
