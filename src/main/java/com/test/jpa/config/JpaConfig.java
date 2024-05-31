package com.test.jpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Config : QueryDSL 관련 설정
 * @Configuration : 스프링 빈 설정, 스프링 컨테이너가 관리하는 빈 등록
 */

@Configuration
public class JpaConfig {

    /*
     * @PersistenceContext : EntityManager 의존성 주입
     * EntityManager : JPA 엔티티 생성, 조회, 수정, 삭제를 수행하는 객체
     */
    @PersistenceContext
    private EntityManager entityManager;

    /*
     * JPAQueryFactory : JPA 엔터티를 이용해 JPQL을 쉽게 편리하게 작성할 수 있도록 하는 QueryDSL 도구
     */
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}