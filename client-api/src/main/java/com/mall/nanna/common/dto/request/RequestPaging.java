package com.mall.nanna.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Positive;

@Getter
@ToString
@AllArgsConstructor
public class RequestPaging {

    @Positive(message = "양수를 입력해주세요.")
    private Integer limit;

    @Positive(message = "양수를 입력해주세요.")
    private Integer page;

    private String searchColumn;

    private String searchWord;

    private String directionColumn;

    private String direction;
}
