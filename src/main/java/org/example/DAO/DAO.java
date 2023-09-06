package org.example.DAO;

import org.example.model.Product;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class DAO {

    public void addProduct(Product product){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            entityManager.persist(product);

            entityTransaction.commit();
        }
        catch (Exception exception) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            exception.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }

    public Optional<Product> findProductById(Long id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Product.class, id));
        }
        finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }
    public List<Product> getAllProduct(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
            return query.getResultList();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }

    public void deleteProduct(Long id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            Product product = Optional.ofNullable(entityManager.find(Product.class, id)).orElseThrow(EntityNotFoundException::new);

            entityManager.remove(product);

            entityTransaction.commit();
        }

        finally {
            entityManager.close();
        }
    }
    public void updateProduct(Product productRecive){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            Product product = Optional.ofNullable(entityManager.find(Product.class, productRecive.getId())).orElseThrow(EntityNotFoundException::new);
            product.setName(productRecive.getName());
            product.setPrice(productRecive.getPrice());
            entityManager.merge(product);

            entityTransaction.commit();
        }

        finally {
            entityManager.close();
        }
    }
}
