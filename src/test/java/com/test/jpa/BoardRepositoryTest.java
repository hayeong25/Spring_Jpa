package com.test.jpa;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.test.jpa.entity.Board;
import com.test.jpa.entity.QBoard;
import com.test.jpa.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("게시물 수정 시간 확인")
    public void updateBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                               .title("Title..." + i)
                               .content("Content..." + i)
                               .writer("Writer..." + i)
                               .build();

            boardRepository.save(board);
        });

        System.out.println("Before Update : " + boardRepository.findAll());

        Optional<Board> result = boardRepository.findById(25L);

        if (result.isPresent()) {
            Board board = result.get();

            board.modifyTitle("Changed Title...");
            board.modifyContent("Changed Content...");

            boardRepository.save(board);

            System.out.println("After Update : " + boardRepository.findAll());
        }
    }

    @Test
    @DisplayName("QueryDSL 단일 항목 조회")
    public void queryDSL1() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
            Board board = Board.builder()
                               .title("Title..." + i)
                               .content("Content..." + i)
                               .writer("Writer..." + i)
                               .build();

            boardRepository.save(board);
        });

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        QBoard qBoard = QBoard.board;

        // BooleanBuilder : WHERE 조건 컨테이너 (Predicate)
        // cf. Predicate : 인자를 받아 boolean 반환하는 함수형 인터페이스
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // title LIKE '%3%'
        // BooleanExpression은 null 반환 시 WHERE 절에서 조건 완전 무시된다
        BooleanExpression expression = qBoard.title.contains("3");

        booleanBuilder.and(expression);

        // BoardRepository의 QuerydslPredicateExecutor로 findAll(Predicate, Pageable) 사용 가능
        Page<Board> result = boardRepository.findAll(booleanBuilder, pageable);

        result.stream().forEach(System.out::println);
    }
    
    @Test
    @DisplayName("QueryDSL 다중 항목 조회")
    public void queryDSL2() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
            Board board = Board.builder()
                               .title("Title..." + i)
                               .content("Content..." + i)
                               .writer("Writer..." + i)
                               .build();

            boardRepository.save(board);
        });

        Pageable pageable = PageRequest.of(0, 13, Sort.by("bno").descending());

        QBoard qBoard = QBoard.board;

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        BooleanExpression expression = qBoard.title.contains("2").or(qBoard.content.contains("7"));

        booleanBuilder.and(expression);

        Page<Board> result = boardRepository.findAll(booleanBuilder, pageable);

        result.stream().forEach(System.out::println);
    }
}