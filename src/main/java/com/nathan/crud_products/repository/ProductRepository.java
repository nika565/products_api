package com.nathan.crud_products.repository;

import com.nathan.crud_products.domain.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {

}
