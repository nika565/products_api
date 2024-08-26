package com.nathan.crud_products.service;

import com.nathan.crud_products.domain.products.Products;
import com.nathan.crud_products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    ProductRepository repository;

    public Products resgister(Products product){
        return repository.save(product);
    }

    public List<Products> findAllProducts(){
        return repository.findAll();
    }

    public Optional<Products> findOneProduct(Long id) {
        return repository.findById(id);
    }

    public Products editProduct(Long id, Products values) {
        Products product = repository.findById(id).orElseThrow(() -> null);

        if(product == null) {
            return null;
        }

        product.setNome(values.getNome());
        product.setPreco(values.getPreco());
        product.setDescricao(values.getDescricao());
        product.setQuantidadeEmEstoque(values.getQuantidadeEmEstoque());

        return repository.save(product);
    }

    public void deleteProduct(Long id){
        repository.deleteById(id);
    }

}
