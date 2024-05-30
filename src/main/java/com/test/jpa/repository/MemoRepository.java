package com.test.jpa.repository;

import com.test.jpa.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// @Query : JPA가 제공하는 쿼리 메소드 이외의 처리 원할 시 사용

public interface MemoRepository extends JpaRepository<Memo, Long> {
    @Query("SELECT m FROM MEMO m ORDER BY m.mno DESC")
    List<Memo> getListDesc();

    // @Query 파라미터 바인딩
    @Transactional
    @Modifying
    @Query("UPDATE MEMO m SET m.memo =: memo WHERE m.mno = :mno")
    int updateMemo(@Param("mno") Long mno, @Param("memo") String memo);

    // @Query 페이징 처리
    @Query(value = "SELECT m FROM MEMO m WHERE m.mno > :mno", countQuery = "SELECT COUNT(m) FROM MEMO m WHERE m.mno > :mno")
    Page<Memo> getListWithQuery(Long mno, Pageable pageable);

    // Native SQL
    @Query(value = "SELECT * FROM MEMO WHERE mno > 0", nativeQuery = true)
    List<Object[]> getNativeResult();
}