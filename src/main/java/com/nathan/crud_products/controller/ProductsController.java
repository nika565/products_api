package com.nathan.crud_products.controller;

import com.nathan.crud_products.domain.products.Products;
import com.nathan.crud_products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @PostMapping
    public Products registerProducts(@RequestBody Products product){
        return productsService.resgister(product);
    }

    @GetMapping
    public List<Products> returnAllProducts(){
        return productsService.findAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Products> returnOneProduct(@PathVariable("id") Long id){
        return productsService.findOneProduct(id);
    }

    @PutMapping("/{id}")
    public Products editProduct(@PathVariable("id") Long id, @RequestBody Products product){
        return productsService.editProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productsService.deleteProduct(id);
    }

}
