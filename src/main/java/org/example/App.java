package org.example;

import org.example.DAO.DAO;
import org.example.model.Product;
import org.example.repositories.ProductRepository;
import org.example.service.ProductService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        DAO dao = new DAO();
        ProductRepository productRepository = new ProductRepository(dao);
        ProductService productService = new ProductService(productRepository);

        while (true){
            try {
                System.out.println("Digite a opcao desejada:\n1-Criar novo produto\n2-Procurar produto pelo id\n3-Buscar todos os produtos\n4-Atualiza produto \n5-Deletar produto\n6-Sair");
                switch (sc.next()) {
                    case "1" -> {
                        Product product = inputProduct();
                        productService.createProduct(product);
                    }
                    case "2" -> {
                        System.out.println("Digite o id do produto:");
                        Long id = sc.nextLong();
                        System.out.println(productService.findProductById(id));
                    }
                    case "3" -> {
                        List<Product> people = productService.getAllProduct();
                        people.forEach(System.out::println);
                    }
                    case "4"->{
                        System.out.println("Digite o id do produto:");
                        Long id = sc.nextLong();
                        System.out.println("Agora digite os dados do novo produto: ");
                        Product product = inputProduct();
                        product.setId(id);
                        productService.updateProduct(product);
                    }
                    case "5"->{
                        System.out.println("Digite o id do produto:");
                        Long id = sc.nextLong();
                        productService.deleteProduct(id);
                    }
                    case "6" -> {
                        System.exit(0);
                    }
                    default -> System.out.println("Opção inválida");
                }
            }catch (EntityNotFoundException exception){
                System.out.println("Produto não encontrado!");
            }
        }

    }
    public static Product inputProduct(){
        Scanner sc = new Scanner(System.in);
        Product product = new Product();
        System.out.println("Digite o nome do produto:");
        product.setName(sc.next());
        System.out.println("Digite o preco do produto:");
        product.setPrice(sc.nextDouble());
        return product;
    }
}
