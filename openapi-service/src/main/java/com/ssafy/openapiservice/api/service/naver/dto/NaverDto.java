package com.ssafy.openapiservice.api.service.naver.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NaverDto {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Item> items = new ArrayList<>();
}
