package com.example.productdemo.repository;

import com.example.productdemo.model.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface ProductRepository extends CassandraRepository<Product, String> {

    Product findProductByNotificationAndActiveTrue(String notification);
    List<Product> findAllByActiveTrue();
}
