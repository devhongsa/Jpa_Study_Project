package com.example.jpa_study.domain;

import org.junit.jupiter.api.Test;

import javax.sound.midi.SysexMessage;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void test() {
        User user = new User();
        user.setEmail("hong@fastcampus.com");
        user.setName("hong");

        System.out.println(">>>>" + user);
    }
}