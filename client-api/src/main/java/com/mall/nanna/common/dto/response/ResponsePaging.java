package com.mall.nanna.common.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResponsePaging <T> {

    private int totalCount;

    private List<T> list;
}
