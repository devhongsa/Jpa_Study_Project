package com.example.jpa_study.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // EntityListenr 객체를 따로 만들지 않아도 listener를 사용할수있게해줌.
public class JpaConfiguration {
}
