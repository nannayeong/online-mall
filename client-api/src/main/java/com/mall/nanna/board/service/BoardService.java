package com.mall.nanna.board.service;

import com.mall.nanna.board.dto.BoardPaging;
import com.mall.nanna.board.dto.request.RequestBoard;
import com.mall.nanna.board.dto.response.ResponseBoard;
import com.mall.nanna.board.entity.Board;
import com.mall.nanna.board.repository.mybatis.BoardRepository;
import com.mall.nanna.common.dto.request.RequestPaging;
import com.mall.nanna.common.dto.response.ResponsePaging;
import com.mall.nanna.common.exception.NoAuthorizationException;
import com.mall.nanna.common.exception.NoSuchPageException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public ResponsePaging<ResponseBoard> findBoardList(RequestPaging requestPaging) {

        BoardPaging boardPaging = BoardPaging.from(requestPaging);
        List<Board> boardList = boardRepository.findAll(boardPaging);
        int totalCount = boardRepository.findTotalCount(boardPaging);

        return ResponsePaging.<ResponseBoard>builder()
                .list(ResponseBoard.fromAll(boardList))
                .totalCount(totalCount)
                .build();
    }

    public ResponseBoard findBoard(int key) {

        Board board = boardRepository.findByKey(key)
                .orElseThrow(() -> new NoSuchPageException("존재하지 않는 게시물입니다."));

        return ResponseBoard.from(board);
    }

    public void addBoard(RequestBoard requestBoard) {
        boardRepository.insert(Board.insert(requestBoard));
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    public void updateBoard(int key, RequestBoard requestBoard) {

        Board board = boardRepository.findByKey(key)
                .orElseThrow(() -> new NoSuchPageException("존재하지 않는 게시물입니다."));

        if (!board.getPassword().equals(requestBoard.getPassword())) {
            throw new NoAuthorizationException("비밀번호가 일치하지 않습니다.");
        }

        boardRepository.update(board.update(requestBoard));
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    public void deleteBoard(int key, String password) {

        Board board = boardRepository.findByKey(key)
                .orElseThrow(() -> new NoSuchPageException("존재하지 않는 게시물입니다."));

        if (!board.getPassword().equals(password)) {
            throw new NoAuthorizationException("비밀번호가 일치하지 않습니다.");
        }

        boardRepository.deleteByKey(board.delete());
    }
}
