package com.example.jpa_study.domain.listener;

import com.example.jpa_study.domain.User;
import com.example.jpa_study.domain.UserHistory;
import com.example.jpa_study.repository.UserHistoryRepository;
import com.example.jpa_study.support.BeanUtils;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEntityListener {
    //이렇게는 Bean을 가져올 수 없음 오류남. BeanUtils 객체를 따로 만들어서 Bean을 가져와야함.
//    @Autowired
//    private UserHistoryRepository userHistoryRepository;
    @PostPersist
    @PostUpdate
    public void preUpdate(Object o){
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        //userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);
    }
}
