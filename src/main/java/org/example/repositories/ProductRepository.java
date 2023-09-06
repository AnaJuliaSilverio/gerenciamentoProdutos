package org.example.repositories;

import org.example.DAO.DAO;
import org.example.model.Product;

import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private DAO dao;

    public ProductRepository(DAO dao) {
        this.dao = dao;
    }
    public void creatProduct(Product product){
        dao.addProduct(product);
    }
    public Optional<Product> findProductById(Long id){
        return dao.findProductById(id);
    }
    public List<Product> getAllProduct(){
        return dao.getAllProduct();
    }
    public void updateProduct(Product product){
        dao.updateProduct(product);
    }
    public void deleteProduct(Long id){
        dao.deleteProduct(id);
    }
}
