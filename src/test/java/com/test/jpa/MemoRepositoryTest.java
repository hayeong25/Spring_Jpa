package com.test.jpa;

import com.test.jpa.entity.Memo;
import com.test.jpa.repository.MemoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTest {
    @Autowired
    MemoRepository memoRepository;

    @Test
    @Order(1)
    @DisplayName("메모 삽입 테스트")
    public void insertMemo() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder()
                            .memo("Memo..." + i)
                            .build();

            memoRepository.save(memo);
        });

        System.out.println(memoRepository.findAll());
    }
    
    @Test
    @Order(2)
    @DisplayName("메모 조회 테스트")
    public void selectMemo() {
        Long mno = 28L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("Memo Object : " + result);

        if (result.isPresent()) {
            Memo memo = result.get();

            System.out.println("Memo : " + memo);
        }
    }
    
    @Test
    @Order(3)
    @DisplayName("메모 수정 테스트")
    public void updateMemo() {
        Memo memo = Memo.builder()
                        .mno(2L)
                        .memo("Update Text...")
                        .build();

        memoRepository.save(memo);

        System.out.println("Update Memo : " + memoRepository.findAll());
    }
    
    @Test
    @Order(4)
    @DisplayName("메모 삭제 테스트")
    public void deleteMemo() {
        System.out.println("Memo : " + memoRepository.findById(5L));

        memoRepository.deleteById(5L);

        System.out.println("Delete Memo : " + memoRepository.findAll());
    }
}