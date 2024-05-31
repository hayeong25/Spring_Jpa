package com.test.jpa.repository;

import com.test.jpa.entity.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.jpa.entity.QBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    QBoard board = QBoard.board;

    public List<Board> findBoardByTitle(String title) {
        return queryFactory
                .select(board)
                .from(board)
                .where(board.title.eq(title))
                .fetch();
    }
}