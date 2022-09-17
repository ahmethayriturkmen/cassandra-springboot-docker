package com.example.productdemo.controller;

import com.example.productdemo.dto.request.ProductRequestDTO;
import com.example.productdemo.dto.response.*;
import com.example.productdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {


    @Autowired
    private  ProductService productService;

    @PostMapping("/addProduct")
    public ProductSaveResponseDTO addProduct(@RequestBody ProductRequestDTO newProduct){
        ProductSaveResponseDTO response = productService.addProduct(newProduct);
        return response;

    }

    @GetMapping("/getProduct")
    public ProductGetResponseDTO getProductById(@RequestParam("productNotificationId") String productNotificationId){
        ProductGetResponseDTO response = productService.getProductById(productNotificationId);
        return  response;
    }


    @GetMapping("/getAllProducts")
    public ProductListResponseDTO getAllProducts(){
        ProductListResponseDTO response = productService.getAllProducts();
        return response;
    }

    @PutMapping("/updateProduct")
    public ProductSaveResponseDTO updateProduct(@RequestBody ProductDTO product) {
        ProductSaveResponseDTO response = productService.updateProduct(product);
        return response;
    }

    @DeleteMapping("/deleteProduct")
    public ProductDeleteResponseDTO deleteProductById(@RequestParam("productNotificationId") String productNotificationId) {
        ProductDeleteResponseDTO response = productService.deleteProductById(productNotificationId);
        return response;
    }


}
