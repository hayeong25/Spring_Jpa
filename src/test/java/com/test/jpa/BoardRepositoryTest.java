package com.test.jpa;

import com.test.jpa.entity.Board;
import com.test.jpa.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}