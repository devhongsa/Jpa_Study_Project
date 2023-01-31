package com.example.jpa_study.domain.listener;

import java.time.LocalDateTime;

// domain Entity에서 Auditable 인터페이스 상속받게 해야함.
public interface Auditable {
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    void setCreatedAt(LocalDateTime createdAt);
    void setUpdatedAt(LocalDateTime updatedAt);
}
