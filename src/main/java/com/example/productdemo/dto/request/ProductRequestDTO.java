package com.example.productdemo.dto.request;

import lombok.Data;

@Data
public class ProductRequestDTO {

    private String name;
    private String description;
    private Integer quantity;
    private String origin;
}
