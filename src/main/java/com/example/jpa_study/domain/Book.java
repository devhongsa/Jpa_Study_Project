package com.example.jpa_study.domain;

import com.example.jpa_study.domain.listener.Auditable;
import com.example.jpa_study.repository.BookRepository;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@EntityListeners(value = AuditingEntityListener.class)
public class Book extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Long authorId;

    private Long publisherId;

    // 되도록 하나의 Entity에서만 OneToOne 관계를 맺어주는 것이 좋음.
//    @OneToOne
//    @ToString.Exclude // 2개의 Entity에서 모두 OneToOne 관계를 정의하게되면 순환참조 오류발생. 이 이노테이션으로 해결가능.
//    private BookReviewInfo bookReviewInfo;

    //밑에 코드 모두 BaseEntity객체를 상속해서 생략가능.
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

//    @PrePersist
//    public void prePersist(){
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate(){
//        this.updatedAt = LocalDateTime.now();
//    }
}
