package com.example.jpa_study.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;

    @Test
    void entityMangerTest() {
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
    }
}
