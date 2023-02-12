package com.mall.nanna.board.entity;

import com.mall.nanna.board.dto.request.RequestBoard;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"key"})
public class Board {

    private Integer key;

    private String title;

    private String text;

    private String password;

    private LocalDateTime dateCreate;

    private LocalDateTime dateUpdate;

    private LocalDateTime dateDelete;

    private Boolean stateDelete;

    public static Board insert(RequestBoard requestBoard) {

        return Board.builder()
                .title(requestBoard.getTitle())
                .text(requestBoard.getText())
                .password(requestBoard.getPassword())
                .dateCreate(LocalDateTime.now())
                .stateDelete(false)
                .build();
    }

    public Board update(RequestBoard requestBoard) {

        this.title = requestBoard.getTitle();
        this.text = requestBoard.getText();
        this.dateUpdate = LocalDateTime.now();

        return this;
    }

    public Board delete() {

        this.dateDelete = LocalDateTime.now();
        this.stateDelete = true;

        return this;
    }
}
