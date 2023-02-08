package com.example.jpa_study.repository;

import com.example.jpa_study.domain.Book;
import com.example.jpa_study.domain.Publisher;
import com.example.jpa_study.domain.Review;
import com.example.jpa_study.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void bookTest() {
        Book book = new Book();
        book.setName("Jpa 초격차 패키지");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        User user = userRepository.findByEmail("martin@fastcampus.com");

        System.out.println("Review : " + user.getReviews());
        System.out.println("Book : " + user.getReviews().get(0).getBook());
        System.out.println("Publisher : " + user.getReviews().get(0).getBook().getPublisher());

    }

    @Test
    //@Transactional
    void bookCascadeTest() {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");

        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        book.setPublisher(publisher);
        bookRepository.save(book);

        //밑에코드는 Book Entity에서 cascade를 설정하면 필요가 없게됨.
        //publisher.getBooks().add(book);
        //publisher.addBook(book);
        //publisherRepository.save(publisher);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("슬로우캠퍼스");

        bookRepository.save(book1);

        System.out.println("publishers : " + publisherRepository.findAll());

        bookRepository.deleteById(1L);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());
    }

    private void givenBookAndReview() {
        givenReview(givenUser(),givenBook(givenPublisher()));
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("내 인생을 바꾼 책");
        review.setContent("존나 재밌다");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    private User givenUser(){
        return userRepository.findByEmail("martin@fastcampus.com");
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("홍라이온출판사");

        return publisherRepository.save(publisher);
    }

    private Book givenBook(Publisher publisher){
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

}