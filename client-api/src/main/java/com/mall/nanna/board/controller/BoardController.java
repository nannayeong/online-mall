package com.mall.nanna.board.controller;

import com.mall.nanna.board.dto.request.RequestBoard;
import com.mall.nanna.board.dto.response.ResponseBoard;
import com.mall.nanna.board.service.BoardService;
import com.mall.nanna.common.dto.request.RequestPaging;
import com.mall.nanna.common.dto.response.ResponsePaging;
import com.mall.nanna.common.group.Add;
import com.mall.nanna.common.group.Delete;
import com.mall.nanna.common.group.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping(value = "/board")
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponsePaging<ResponseBoard>> findBoardList(@Valid RequestPaging requestPaging) {
        log.info("[findBoardList] paging: {}", requestPaging);

        return ResponseEntity.ok(boardService.findBoardList(requestPaging));
    }

    @GetMapping(value = "/{key}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBoard> findBoard(@PathVariable(value = "key") int key) {
        log.info("[findBoard] key: {}", key);

        return ResponseEntity.ok(boardService.findBoard(key));
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addBoard(
            @Validated(value = Add.class) @RequestBody RequestBoard requestBoard
    ) {
        log.info("[saveBoard] requestBoard: {}", requestBoard);

        boardService.addBoard(requestBoard);

        return ResponseEntity.ok("");
    }

    @PutMapping(value = "/{key}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateBoard(
            @PathVariable(value = "key") int key,
            @Validated(value = Update.class) @RequestBody RequestBoard requestBoard
    ) {
        log.info("[updateBoard] key: {}, requestBoard: {}", key, requestBoard);

        boardService.updateBoard(key, requestBoard);

        return ResponseEntity.ok("");
    }

    @DeleteMapping(value = "/{key}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteBoard(
            @PathVariable(value = "key") int key,
            @Validated(value = Delete.class) @RequestBody RequestBoard requestBoard
    ) {
        log.info("[deleteBoard] key: {}, requestBoard: {}", key, requestBoard);

        boardService.deleteBoard(key, requestBoard.getPassword());

        return ResponseEntity.ok("");
    }
}
