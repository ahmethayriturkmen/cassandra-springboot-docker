package com.example.productdemo.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ProductListResponseDTO extends BaseResponse {

    private List<ProductDTO> productList;
}
