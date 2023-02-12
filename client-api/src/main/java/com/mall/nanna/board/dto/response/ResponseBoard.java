package com.mall.nanna.board.dto.response;

import com.mall.nanna.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ResponseBoard {

    private int key;

    private String title;

    private String text;

    private LocalDateTime dateCreate;

    public static ResponseBoard from(Board board) {

        return ResponseBoard.builder()
                .key(board.getKey())
                .title(board.getTitle())
                .text(board.getText())
                .dateCreate(board.getDateCreate())
                .build();
    }

    public static List<ResponseBoard> fromAll(List<Board> boardList) {

        return boardList.stream()
                .map(board -> ResponseBoard.builder()
                        .key(board.getKey())
                        .title(board.getTitle())
                        .text(board.getText())
                        .dateCreate(board.getDateCreate())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
