package com.mall.nanna.board.dto.request;

import com.mall.nanna.common.group.Add;
import com.mall.nanna.common.group.Delete;
import com.mall.nanna.common.group.Update;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@AllArgsConstructor
public class RequestBoard {
    @NotNull(groups = {Add.class, Update.class}, message = "필수값입니다.")
    private String title;

    @NotNull(groups = {Add.class, Update.class}, message = "필수값입니다.")
    private String text;

    @NotNull(groups = {Add.class, Update.class, Delete.class}, message = "필수값입니다.")
    private String password;
}
