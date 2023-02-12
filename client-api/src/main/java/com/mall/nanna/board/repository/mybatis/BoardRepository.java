package com.mall.nanna.board.repository.mybatis;

import com.mall.nanna.board.dto.BoardPaging;
import com.mall.nanna.board.entity.Board;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository {

    /**
     * 전체 조회 페이징
     * @param boardPaging 게시판 페이징 dto
     * @return 게시판 목록
     */
    List<Board> findAll(BoardPaging boardPaging);

    int findTotalCount(BoardPaging boardPaging);

    /**
     * 상세 조회
     * @param key 게시판 키
     * @return 게시판 상세
     */
    Optional<Board> findByKey(int key);

    /**
     * 저장
     * @param board 게시판 저장 개체
     */
    void insert(Board board);

    /**
     * 수정
     * @param board 게시판 수정 개체
     */
    void update(Board board);

    /**
     * 삭제
     * @param board 게시판 삭제 개체
     */
    void deleteByKey(Board board);
}
