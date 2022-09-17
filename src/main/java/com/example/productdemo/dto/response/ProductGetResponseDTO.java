package com.example.productdemo.dto.response;

import lombok.Data;

@Data
public class ProductGetResponseDTO extends BaseResponse {
    private  ProductDTO productDTO;
}
