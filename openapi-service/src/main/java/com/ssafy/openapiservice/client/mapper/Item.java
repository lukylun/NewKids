package com.ssafy.openapiservice.client.mapper;

import lombok.Data;

@Data
public class Item {
    private String sup_no;
    private String word;
    private String target_code;
    private Sense sense;
    private String pos;
}
