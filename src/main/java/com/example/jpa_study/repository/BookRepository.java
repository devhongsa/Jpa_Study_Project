package com.example.jpa_study.repository;

import com.example.jpa_study.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
