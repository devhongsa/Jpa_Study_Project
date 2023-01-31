package com.example.jpa_study.repository;

import com.example.jpa_study.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

// JpaRepository<domain객체, primary key dataType> 를 상속받으면 됨.
public interface UserRepository extends JpaRepository<User, Long> {
    //query method이름 findByName 에서 Name이 User class에 정의되어 있어야함. 대소문자 상관없음.
    //User findByName(String name); //찾으려는 값이 중복이 있으면 에러를 일으킴.
    List<User> findByName(String name); //List로 리턴해야 중복데이터를 모두 찾아줌.

    //limit 쿼리메소드
    List<User> findFirst2ByName(String name);
    List<User> findTop2ByName(String name);

    //And Or 쿼리메소드
    List<User> findByEmailAndName(String email, String name);
    List<User> findByEmailOrName(String email, String name);

    //값비교 쿼리메소드
    List<User> findByCreatedAtAfter(LocalDateTime dateTime);
    List<User> findByCreatedAtBefore(LocalDateTime dateTime);
    List<User> findByIdGreaterThan(Long id);
    List<User> findByIdGreaterThanEqual(Long id);
    List<User> findByIdLessThan(Long id);
    List<User> findByIdBetween(Long id1, Long id2);

    //IsNotNull 쿼리메소드
    List<User> findByIdIsNotNull();
    List<User> findByAddressIsNotEmpty(); // 잘 사용하지는 않음

    // IN, NOT IN 쿼리메소드
    List<User> findByNameIn(List<String> names);

    // Like 쿼리메소드
    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContains(String name);

    // 정렬 쿼리메소드
    List<User> findTop2ByNameOrderByIdDesc(String name);
    List<User> findFirstByName(String name, Sort sort);

    // paging 쿼리메소드
    Page<User> findByName(String name, Pageable pageable);
}
