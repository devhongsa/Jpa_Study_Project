package com.example.jpa_study.service;

import com.example.jpa_study.domain.Author;
import com.example.jpa_study.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void putAuthor(){
        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);
    }
}
