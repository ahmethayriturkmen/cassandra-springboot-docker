package com.example.productdemo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductDemoApplication {

    public static void main(String[] args) {
        //veritabanı bağlantısında timed out hatası aldığım için ekledim
        System.setProperty("datastax-java-driver.basic.request.timeout", "20 seconds");
        SpringApplication.run(ProductDemoApplication.class, args);
    }


    //modelmapper bean olusturuldu
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
