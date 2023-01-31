package com.example.jpa_study.domain.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

// 각각의 Entity에서 똑같은 걸 계속구현해줬던거를, 하나의 객체로 통합
public class MyEntityListener {
    @PrePersist
    public void prePersist(Object o){
        if (o instanceof Auditable){
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o){
        if (o instanceof Auditable){
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
