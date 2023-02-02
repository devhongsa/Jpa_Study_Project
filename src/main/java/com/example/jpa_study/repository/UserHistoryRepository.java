package com.example.jpa_study.repository;

import com.example.jpa_study.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHistoryRepository extends JpaRepository<UserHistory,Long> {
    List<UserHistory> findByUserId(Long userId);
}
