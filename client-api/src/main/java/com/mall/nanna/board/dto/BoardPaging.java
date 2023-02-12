package com.mall.nanna.board.dto;

import com.mall.nanna.common.dto.request.RequestPaging;
import com.mall.nanna.common.enumerated.Direction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class BoardPaging {

    private Integer limit;

    private Integer page;

    private String searchColumn;

    private String searchWord;

    private String directionColumn;

    private Direction direction;

    public static BoardPaging from(RequestPaging requestPaging) {

        return BoardPaging.builder()
                .limit(Objects.isNull(requestPaging.getLimit()) ? 10 : requestPaging.getLimit())
                .page(Objects.isNull(requestPaging.getPage()) ? 1 : requestPaging.getPage())
                .searchColumn(searchColumnSwitch(requestPaging.getSearchColumn()))
                .searchWord(Objects.isNull(requestPaging.getSearchWord()) ? "" : requestPaging.getSearchWord())
                .directionColumn(directionColumnSwitch(requestPaging.getDirectionColumn()))
                .direction(directionSwitch(requestPaging.getDirection()))
                .build();
    }

    public static String searchColumnSwitch(String searchColumn) {

        switch (searchColumn.toUpperCase()) {
            case "title": return "title";
            case "text": return "text";
            default: return "";
        }
    }

    public static String directionColumnSwitch(String directionColumn) {

        switch (directionColumn.toLowerCase()) {
            default: return "date_create";
        }
    }

    public static Direction directionSwitch(String direction) {

        switch (direction.toUpperCase()) {
            case "ASC": return Direction.ASC;
            default: return Direction.DESC;
        }
    }
}
