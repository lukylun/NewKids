package com.ssafy.openapiservice.client.mapper;

import lombok.Data;

import java.util.List;

@Data
public class Channel {
    private int total;
    private int num;
    private String title;
    private int start;
    private String description;
    private List<Item> item;
    private String link;
    private String lastbuilddate;
}
