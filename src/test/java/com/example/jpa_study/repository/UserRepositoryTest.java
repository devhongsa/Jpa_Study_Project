package com.example.jpa_study.repository;

import com.example.jpa_study.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() {
        User user1 = new User("jack", "jack@gmail.com");
        User user2 = new User("jhon", "Jhon@gmail.com");

        //Create, Update
        //userRepository.save(user1); //create
        user1.setEmail("hongsa@gg");
        userRepository.save(user1);  //이렇게하면 update

        userRepository.saveAll(Lists.newArrayList(user1, user2));

        //Delete
//        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
//        userRepository.deleteById(1L);
//        userRepository.deleteAll(); //쿼리를 행마다 실행시키기 때문에 데이터가 많을수록 성능이슈 생김.
//        userRepository.deleteAllInBatch(); // 쿼리 한번으로 한꺼번에 지워버림.
//        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L,3L)));

        //Read
        List<User> users1 = userRepository.findAllById(Lists.newArrayList(1L,2L));
        users1.forEach(System.out::println);


        List<User> users2 = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));

        //pagination
        Page<User> users3 = userRepository.findAll(PageRequest.of(1, 3)); // page : size는 page당 데이터를 몇개씩 할당할것인지, page는 몇번째 페이지를 Read 할 것인지(zero-based index)
        System.out.println("page : " + users3);
        System.out.println("totalElements : " + users3.getTotalElements());
        System.out.println("totalPages : " + users3.getTotalPages());
        System.out.println("numberOfElements : " + users3.getNumberOfElements()); //현재 가져온 레코드 갯수
        System.out.println("sort : " + users3.getSort());
        System.out.println("size : " + users3.getSize());

        // ExampleMatcher : sql like 쿼리
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name") //name필드는 무시, 이거 없으면 name필드를 exact match로 search 하게됨
                .withMatcher("email",contains()); //email필드 like문 : contains는 특정 값이 포함되어있는경우, endWith는 특정값으로 끝나는 경우 탐색
        Example<User> example = Example.of(new User("ma", "gmail"), matcher); // email에 gmail.com이 들어가는 데이터 찾기
        userRepository.findAll(example).forEach(System.out::println);

        long count = userRepository.count();
        boolean exists = userRepository.existsById(1L);
        
    }

    @Test
    void queryMethodTest() {
        //System.out.println(userRepository.findByName("hongsa"));

        //System.out.println(userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));

        //System.out.println(userRepository.findByAddressIsNotEmpty());
        //System.out.println(userRepository.findByIdIsNotNull());

        System.out.println(userRepository.findByNameIn(Lists.newArrayList("martin","hongsa")));

        userRepository.findFirstByName("hongsa",Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email")));

        userRepository.findByName("martin", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getContent();
    }
}