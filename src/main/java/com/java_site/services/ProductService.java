package com.java_site.services;

import com.java_site.models.*;
import java.util.*;

/**
 * Інтерфейс, який визначає операції, що пов'язані з обробкою даних про продукти.
 */
public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void createProduct(com.java_site.models.Product product);
    Product update(Product product);
    void deleteProductById(Long id);

    List<Product> getProductsByFirm(String firmName);

    List<Product> getProductsByGroup(String groupName);
}
