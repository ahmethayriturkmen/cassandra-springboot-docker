package com.example.productdemo.model;

import lombok.Data;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

@Data
@Table(value = "Product")
public class Product {

    @PrimaryKey
    @CassandraType(type = CassandraType.Name.TEXT)
    private String id;

    @CassandraType(type = CassandraType.Name.TEXT)
    @Column("name")
    private String name;


    @Column("description")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String description;

    @Column("origin")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String origin;

    @Column("quantity")
    @CassandraType(type = CassandraType.Name.INT)
    private Integer quantity;


    @Column("active")
    @CassandraType(type = CassandraType.Name.BOOLEAN)
    private Boolean active;

    @Column("notification")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String notification;

}
