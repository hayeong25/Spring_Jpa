package com.test.jpa.utils;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 테이블로 생성되지 않게. BaseEntity를 상속받는 엔티티는 BaseEntity의 필드를 컬럼으로 사용 가능
@EntityListeners(value = AuditingEntityListener.class) // AuditingEntityListener : 엔티티 객체 생성/변경 감지
public class BaseEntity {
    @CreatedDate
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
}