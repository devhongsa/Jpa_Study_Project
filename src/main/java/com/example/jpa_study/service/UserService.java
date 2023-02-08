package com.example.jpa_study.service;

import com.example.jpa_study.domain.User;
import com.example.jpa_study.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    //@Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void put(){
        // entity 생명주기 (비영속, 영속, 준영속, 삭제)
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@fastcampus.com");
        // 여기까지는 객체의 비영속성

//        userRepository.save(user); // 이렇게 하면 객체 영속성 실현

        entityManager.persist(user); // 객체 영속성 주입, 위에 save구현체로 가보면 entityManager를 사용함.
//        entityManager.detach(user); // 객체 영속성을 끊어줌. 준영속상태

        user.setName("newUserAfterPersist"); //만약 detach코드가 없다면 save코드를 실행하지않아도 디비에 데이터가 반영됨. 이는 persist로 영속성을 부여해줌으로써 user객체가 managed되고 있기 때문임.

        entityManager.merge(user); //다시 영속성을 부여해줌. 이때 위에서 했던 setName이 적용이 되어서 디비 데이터에 반영이됨.
//        entityManager.flush(); // DB에 바로 반영시켜줌.
//        entityManager.clear(); // DB에 업데이트 될 예정인 데이터들을 clear함

        entityManager.remove(user); // DB에서도 user객체 정보를 삭제시키고 아예 객체를 지워버림. 삭제상태
    }
}
