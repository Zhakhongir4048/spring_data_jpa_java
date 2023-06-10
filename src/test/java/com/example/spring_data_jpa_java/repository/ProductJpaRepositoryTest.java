package com.example.spring_data_jpa_java.repository;

import com.example.spring_data_jpa_java.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.List.of;

@SpringBootTest
class ProductJpaRepositoryTest {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Test
    void saveMethod() {
        // create product
        Product product = new Product();
        product.setName("product 1");
        product.setDescription("product 1 description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("product1.png");
        // save product
        Product savedObject = productJpaRepository.save(product);
        // display product info
        System.out.println(savedObject.getId());
        System.out.println(savedObject);
    }

    @Test
    void updateUsingSaveMethod() {
        // find or retrieve on entity by id
        Long id = 1L;
        Product product = productJpaRepository.findById(id).stream().findFirst().orElseGet(Product::new);
        // update entity information
        product.setName("updated product 1");
        product.setDescription("updated product 1 desc");
        // save updated entity
        productJpaRepository.save(product);
    }

    @Test
    void findByIdMethod() {
        Long id = 1L;
        Product product = productJpaRepository.findById(id).orElseGet(() -> {
            Product newProduct = new Product();
            newProduct.setId(100L);
            newProduct.setDescription("findById error");
            System.out.println(newProduct);
            return newProduct;
        });
        System.out.println(product);
    }

    @Test
    void saveAllMethod() {
        // create product
        Product product = new Product();
        product.setName("product 2");
        product.setDescription("product 2 description");
        product.setSku("200ABC");
        product.setPrice(new BigDecimal(200));
        product.setActive(true);
        product.setImageUrl("product2.png");
        // create product
        Product product3 = new Product();
        product3.setName("product 3");
        product3.setDescription("product 3 description");
        product3.setSku("300ABC");
        product3.setPrice(new BigDecimal(300));
        product3.setActive(true);
        product3.setImageUrl("product3.png");
        // save products
        productJpaRepository.saveAll(of(product, product3));
    }

    @Test
    void findAllMethod() {
        List<Product> products = productJpaRepository.findAll();
        products.forEach(product -> System.out.println(product.getName()));
    }

    @Test
    void deleteByIdMethod() {
        Long id = 1L;
        productJpaRepository.deleteById(id);
    }

    @Test
    void deleteMethod() {
        // find on entity by id
        Long id = 2L;
        Product product = productJpaRepository.findById(id).orElseGet(Product::new);
        // delete(entity)
        productJpaRepository.delete(product);
    }

    @Test
    void deleteAllMethod() {
        // productJpaRepository.deleteAll();
        Product product = productJpaRepository.findById(4L).orElseGet(Product::new);
        Product product1 = productJpaRepository.findById(5L).orElseGet(Product::new);
        productJpaRepository.deleteAll(of(product, product1));
    }

    @Test
    void countMethod() {
        long count = productJpaRepository.count();
        System.out.println("count = " + count);
    }

    @Test
    void existsByIdMethod() {
        boolean exists = productJpaRepository.existsById(1L);
        System.out.println("exists = " + exists);
    }

    @Test
    void findByDateCreatedBetween() {
        // start date
        List<Product> products = productJpaRepository.findByDateCreatedBetween(
                LocalDateTime.of(2023, 6, 9, 23, 55),
                LocalDateTime.now()
        );
        products.forEach(product -> System.out.println(product.getSku() + product.getId()));
    }

}