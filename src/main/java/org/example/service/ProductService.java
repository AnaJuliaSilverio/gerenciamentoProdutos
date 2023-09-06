package org.example.service;

import org.example.model.Product;
import org.example.repositories.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product){
        productRepository.creatProduct(product);
        System.out.println(product.getName()+" inserido(a) com sucesso!");
    }
    public Product findProductById(Long id){
        return productRepository.findProductById(id).orElseThrow(EntityNotFoundException::new);
    }
    public List<Product> getAllProduct(){
        return productRepository.getAllProduct();
    }
    public void updateProduct(Product product){

        productRepository.updateProduct(product);
        System.out.println("Produto atualizado!");
    }
    public void deleteProduct(Long id){
        productRepository.deleteProduct(id);
        System.out.println("Produto deletado!");
    }
}
