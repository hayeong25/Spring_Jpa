package com.test.jpa.repository;

import com.test.jpa.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/*
 * QueryDSL : @Entity가 붙은 엔터티들의 메타 정보를 담은 Q클래스 생성 및 Q클래스를 이용한 쿼리문 작성
 * 
 * QueryDSL 설정 후 build 하면 /build/classes/java/main...에 Q로 시작하는 엔티티 클래스 생성 -> 개발자가 건드리는 클래스 X. 내부 컬럼들이 변수 처리된 클래스
 *
 * QuerydslPredicateExecutor : QueryDSL 이용 시 상속 받는 인터페이스. 실무에서는 잘 사용하지 X
 */

public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {

}