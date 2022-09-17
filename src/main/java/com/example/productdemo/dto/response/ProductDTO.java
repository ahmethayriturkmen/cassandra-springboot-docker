package com.example.productdemo.dto.response;

import lombok.Data;

@Data
public class ProductDTO {

    private String name;
    private String description;
    private Integer quantity;
    private String notification;
    private String origin;
}
