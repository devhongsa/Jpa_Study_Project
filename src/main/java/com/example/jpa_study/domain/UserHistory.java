package com.example.jpa_study.domain;

import com.example.jpa_study.domain.listener.Auditable;
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
public class UserHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //FK
    //private Long userId;

    //ManyToOne 어노테이션으로 FK 설정 가능 User Entity의 PK가 UserHistory의 컬럼으로 구성이됨.
    // 이 경우는 Userhistory에서 데이터를 보다가 특정유저의 정보를 알고 싶은 경우에 이런식으로 ManyToOne으로 관계를 설정하면됨.
    @ManyToOne
    @ToString.Exclude // stackoverflow오류(순환참조) 해결하기위한 어노테이션
    private User user;

    private String name;

    private String email;

}
