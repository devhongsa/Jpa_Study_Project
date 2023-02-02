package com.example.jpa_study.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Long bookId;

    // Long bookId대신 OneToOne 이노테이션을 활용. dsl create table 로그를 보면 book 객체가 book_id 컬럼으로 변환되서 테이블이 생성됨.
    // 여기서 optional = false 옵션은 null을 허용하지 않겠다는 뜻.
    @OneToOne(optional = false)
    private Book book;

    private float averageReviewScore;

    private int reviewCount;
}
