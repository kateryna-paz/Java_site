package com.java_site.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.java_site.models.Product;
import com.java_site.models.ProductFirm;
import com.java_site.models.ProductGroup;
import com.java_site.repositories.ProductRepository;

import java.util.*;

/**
 * Реалізація сервісу для роботи з продуктами.
 */
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductFirmService firmService;
    private final ProductGroupService groupService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductFirmService firmService, ProductGroupService groupService) {
        this.productRepository = productRepository;
        this.firmService = firmService;
        this.groupService = groupService;
    }

    /**
     * Отримати всі продукти.
     * @return Список усіх продуктів.
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Отримати продукт за його ідентифікатором.
     * @param id Ідентифікатор продукту.
     * @return Продукт з вказаним ідентифікатором.
     * @throws IllegalStateException Якщо продукт з вказаним ідентифікатором не існує.
     */
    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new IllegalStateException("Продукт з id " + id + " не існує");
        }
        return product.get();
    }

    /**
     * Створити новий продукт.
     * @param product Об'єкт продукту для створення.
     * @throws IllegalArgumentException Якщо переданий об'єкт продукту порожній або містить неправильні дані.
     */
    @Override
    public void createProduct(com.java_site.models.Product product) {
        if (Objects.isNull(product)) throw new IllegalArgumentException("Об'єкт продукту не може бути порожнім");

        try {
            // Валідація вхідних даних
            validateProductInput(product);

        } catch (Exception e) {
            // Обробка помилки валідації
            System.out.println("Помилка при створенні продукту: " + e.getMessage());
        }

        // Збереження продукту в базі даних
        productRepository.save(product);
    }

    /**
     * Перевірка коректності вхідних даних для створення продукту.
     * @param product Об'єкт продукту для перевірки.
     * @throws IllegalArgumentException Якщо дані продукту неправильні або неповні.
     */
    private void validateProductInput(Product product) {
        String name = product.getName();
        Double price = product.getPrice();
        String description = product.getDescription();
        Date date = product.getDate();
        ProductFirm firm = product.getProductFirm();
        ProductGroup group = product.getProductGroup();

        // Перевірка на наявність всіх необхідних даних продукту
        if (Objects.isNull(name) || name.isEmpty() ||
                Objects.isNull(price) || price <= 0 ||
                Objects.isNull(description) ||
                Objects.isNull(date) ||
                Objects.isNull(firm) || Objects.isNull(firm.getId()) ||
                Objects.isNull(group) || Objects.isNull(group.getId())) {
            throw new IllegalArgumentException("Поля продукту повинні бути заповнені та містити коректні дані");
        }
    }

    /**
     * Оновити існуючий продукт.
     * @param updatedProduct Оновлений об'єкт продукту.
     * @return Оновлений продукт.
     * @throws IllegalArgumentException Якщо переданий об'єкт продукту порожній або містить неправильні дані.
     */
    @Override
    public Product update(Product updatedProduct) {
        if (Objects.isNull(updatedProduct)) throw new IllegalArgumentException( "Об'єкт продукту не може бути порожнім");

        // Шукаємо існуючий продукт з таким ідентифікатором
        Product existingProduct = getProductById(updatedProduct.getId());

        try {
            // Валідація оновлених даних продукту
            validateProductInput(updatedProduct);

            // Оновлення назви, ціни, опису та дати продукту
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setDate(updatedProduct.getDate());

            // Оновлення фірми продукту
            existingProduct.setProductFirm(updatedProduct.getProductFirm());

            // Оновлення групи продукту
            existingProduct.setProductGroup(updatedProduct.getProductGroup());

        } catch (Exception e) {
            // Обробка помилки валідації або оновлення
            System.out.println("Помилка при оновленні продукту: " + e.getMessage());
        }

        // Збереження оновленого продукту у базі даних
        return productRepository.save(existingProduct);
    }

    /**
     * Видалити продукт за його ідентифікатором.
     * @param id Ідентифікатор продукту для видалення.
     * @throws IllegalArgumentException Якщо продукт з вказаним ідентифікатором не існує.
     */
    @Override
    public void deleteProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Продукт з id " + id + " не існує");
        }
    }

    /**
     * Отримати список продуктів за назвою фірми.
     * @param firmName Назва фірми для пошуку.
     * @return Список продуктів, що відносяться до вказаної фірми.
     */
    @Override
    public List<Product> getProductsByFirm(String firmName) {
        return productRepository.findByFirmName(firmName);
    }

    /**
     * Отримати список продуктів за назвою групи.
     * @param groupName Назва групи для пошуку.
     * @return Список продуктів, що відносяться до вказаної групи.
     */
    @Override
    public List<Product> getProductsByGroup(String groupName) {
        return productRepository.findByGroupName(groupName);
    }
}
