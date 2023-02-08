package com.example.jpa_study.service;

import com.example.jpa_study.domain.Author;
import com.example.jpa_study.domain.Book;
import com.example.jpa_study.repository.AuthorRepository;
import com.example.jpa_study.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor //autowired 안쓰려면 lombok annotation 사용
public class BookService {
    //final로 선언해줘야함. autowired 대체
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    @Transactional(propagation = Propagation.REQUIRED) //transactional 설정을 하면 putBookAndAuthor메소드가 모두가 완료가 되기 전까지는 save(book), save(author)가 DB에 실제 반영이 되지 않음. 메소드가 모두 성공적으로 끝났을때 데이터가 실제 DB에 반영이 됨.
    // 만일 외부에서 putBookAndAuthor메소드를 바로 호출하지않고 BookService내부의 다른메소드를 통해서 putBookAndAuthor메소드를 간접실행하게 된다면, Transactional이 작동하지 않게됨.
    public void putBookAndAuthor(){
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        authorService.putAuthor();

        //throw new RuntimeException("오류 발생"); //runtimeException은 unchecked예외이고, 예외발생시 transactional roll back 일어남

        //throw new Exception("오류 발생"); // Exception은 checked 예외이고, 메소드 내에서 예외 발생시 transactional roll back이 일어나지 않음. 현업에서도 이 부분에 대한 실수가 많이 발생한다.
    }

    @Transactional
    public void get(Long id){
        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());
    }
}
