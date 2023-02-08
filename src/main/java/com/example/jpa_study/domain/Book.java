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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

//    private Long publisherId; // 밑에 ManyToOne 으로 publisher를 선언해주었기때문에 이거는 없어도됨.

    // 되도록 하나의 Entity에서만 OneToOne 관계를 맺어주는 것이 좋음.
//    @OneToOne
//    @ToString.Exclude // 2개의 Entity에서 모두 OneToOne 관계를 정의하게되면 순환참조 오류발생. 이 이노테이션으로 해결가능.
//    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name="book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //Persist는 Book객체에 대하여 insert가 일어날때 연관관계가 있는 Entity에도 자동으로 데이터 삽입해줌. Merge는 update, Remove는 delete시 연관된 Entity정보까지도 삭제함.
    @ToString.Exclude
    private Publisher publisher;

    //실무에서 ManyToMany는 거의 사용하지않으려고 한다. 만약 사용해야한다면 중간 domain Entity를 만들어서 1대N, N대1 로 연결시켜서 사용한다.
    @ManyToMany
    @ToString.Exclude
    private List<Author> authors = new ArrayList<>();

    public void addAuthor(Author... author){
        Collections.addAll(this.authors, author);

    }
}
