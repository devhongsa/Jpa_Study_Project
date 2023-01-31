package com.example.jpa_study.domain;

import com.example.jpa_study.domain.listener.Auditable;
import com.example.jpa_study.domain.listener.UserEntityListener;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor  // 인자가 없는 생성자 생성, 필수적으로 넣어줘야함
@AllArgsConstructor // 모든 멤버변수를 인자로 갖는 생성자 생성
@RequiredArgsConstructor // NonNull 멤버변수를 인자로 갖는 생성자 생성
@Data // Getter + Setter + ToString + RequiredArgsConstructor + EqualsAndHashCode
@Builder // User.builder().name("martin").email("11@gs").build(); 이런식으로 이어쓰기위한 어노테이션
@Entity //database와 연결해주기 위해 Entity 선언
//@EntityListeners(value = {MyEntityListener.class, UserEntityListener.class}) //domain마다 똑같은 Listener들을 구현하는것을 생략하게 해줌.
//@EntityListeners(value = {AuditingEntityListener.class, UserEntityListener.class}) // AuditingEntityListenr는 main에서 @EnableJpaAuditing 어노테이션을 설정해줬을때 사용.
@EntityListeners(value = UserEntityListener.class) //BaseEntity를 상속받으면 AuditingEntityListener.class 포함안해줘도 됨.
@ToString(callSuper = true) //BaseEntity에서 createdAt, updatedAt 컬럼을 상속받았기때문에 ToString에서도 상위클래스꺼를 가져와줘야함. 그래야 createdAt, updatedAt이 출력됨.
@EqualsAndHashCode(callSuper = true) //위와 마찬가지이유
@Table(name = "user2")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

//    @Column(name="createdAt", updatable = false)
//    @CreatedDate // AuditingEntityListener 어노테이션설정으로, prePersist기능실행
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate // AuditingEntityListener 어노테이션설정으로, prePersist기능실행
//    private LocalDateTime updatedAt;

    //EnumType 에서 String으로 해주는 이유는 나중에 GENDER ENUM에서 다른 요소를 추가하게되면
    //디비에는 요소 순서대로의 인덱스값으로 저장되어있기때문에, 다른요소가 들어와버리면 인덱스값이 바뀌게 된다.
    //그래서 ORDINAL 말고 STRING으로 해줘야함.
    @Enumerated(value = EnumType.STRING)
    private Gender gender;


    //One: User, Many: Address, User가 Address를 여러개 가질 수 있음.
    //EAGER : Entity정보를 미리 읽어옴. LAZY : 요청할때 읽어옴.
    @OneToMany(fetch=FetchType.EAGER)
    private List<Address> address;


    /////Entity Listener/////
    // insert하기직전 자동으로 실행, createdAt과 같은 컬럼을 자동으로 채워주기 위함.
//    @PrePersist
//    public void prePersist(){
//        System.out.println(">>>prePersist");
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//    // insert한 직후에 자동으로 실행
//    @PostPersist
//    public void postPersist(){
//        System.out.println(">>>postPersist");
//    }
//    // update 직전에 자동으로 실행, updatedAt과 같은 컬럼 자동으로 바꿔줌
//    @PreUpdate
//    public void preUpdate(){
//        System.out.println(">>>preUpdate");
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PostUpdate
//    public void postUpdate(){
//        System.out.println(">>>postUpdate");
//    }
//
//    @PreRemove
//    public void preRemove(){
//        System.out.println(">>>preRemove");
//    }
//
//    @PostRemove
//    public void postRemove(){
//        System.out.println(">>>postRemove");
//    }
//    // select쿼리 직전에 자동으로 실행
//    @PostLoad
//    public void postLoad(){
//        System.out.println(">>>postLoad");
//    }
}
