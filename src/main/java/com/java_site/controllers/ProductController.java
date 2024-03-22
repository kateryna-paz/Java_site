package com.java_site.controllers;

import com.java_site.models.*;
import com.java_site.services.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import com.java_site.services.ProductService;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;

/**
 * Контролер для обробки запитів, пов'язаних з продукцією.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductGroupService groupService;
    private final ProductFirmService firmService;
    @Autowired
    public ProductController(ProductService productService, ProductGroupService groupService, ProductFirmService firmService) {
        this.productService = productService;
        this.groupService = groupService;
        this.firmService = firmService;
    }

    /**
     * Метод для створення нового продукту.
     * @param model Модель, яка використовується для передачі даних на сторінку.
     * @return Шаблон сторінки для створення нового продукту.
     */
    @GetMapping(path="/create")
    public String createNewProduct(Model model) {
        // Створення нового об'єкту Product і додавання його в модель
        Product product = new Product();
        model.addAttribute("product", product);

        List<ProductGroup> groups = groupService.getAllGroups();
        List<ProductFirm> firms = firmService.getAllFirms();
        model.addAttribute("groups", groups);
        model.addAttribute("firms", firms);

        return "addProduct";
    }

    /**
     * Метод для обробки POST-запиту на додавання нового продукту.
     * @param product Об'єкт продукту, який додається.
     * @return Перенаправлення на сторінку з усіма продуктами після успішного додавання.
     */
    @PostMapping(path="/add")
    public String addNewProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/products/all";
    }

    /**
     * Метод для відображення всіх продуктів.
     * @param model Модель, яка використовується для передачі даних на сторінку.
     * @return Шаблон сторінки з усіма продуктами.
     */
    @GetMapping("/all")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "showProducts";
    }

    /**
     * Метод для редагування існуючого продукту.
     * @param id Ідентифікатор продукту, який потрібно редагувати.
     * @param model Модель, яка використовується для передачі даних на сторінку.
     * @return Шаблон сторінки для редагування продукту.
     */
    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        List<ProductGroup> groups = groupService.getAllGroups();
        List<ProductFirm> firms = firmService.getAllFirms();
        model.addAttribute("product", product);
        model.addAttribute("groups", groups);
        model.addAttribute("firms", firms);
        return "editProduct";
    }

    /**
     * Метод для оновлення існуючого продукту.
     * @param product Об'єкт продукту, який потрібно оновити.
     * @return Перенаправлення на сторінку з усіма продуктами після успішного оновлення.
     */
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        productService.update(product);
        return "redirect:/products/all";
    }

    /**
     * Метод для відображення сторінки з підтвердженням видалення продукту.
     * @param id Ідентифікатор продукту, який потрібно видалити.
     * @param model Модель, яка використовується для передачі даних на сторінку.
     * @return Шаблон сторінки з підтвердженням видалення продукту.
     */
    @GetMapping("/delete/{id}")
    public String showDeleteConfirmationPage(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "deleteProduct";
    }

    /**
     * Метод для видалення продукту за ідентифікатором.
     * @param id Ідентифікатор продукту, який потрібно видалити.
     * @return Перенаправлення на сторінку з усіма продуктами після успішного видалення.
     */
    @PostMapping("/delete/{id}")
    public String deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/products/all";
    }

    /**
     * Метод для пошуку продуктів за назвою фірми.
     * @param firmName Назва фірми, за якою виконується пошук.
     * @return Відповідь зі списком продуктів, що відповідають заданій фірмі.
     */
    @GetMapping("/searchByFirm")
    public ResponseEntity<List<Product>> searchByFirm(@RequestParam String firmName) {
        List<Product> products = productService.getProductsByFirm(firmName);
        return ResponseEntity.ok().body(products);
    }

    /**
     * Метод для пошуку продуктів за назвою групи.
     * @param groupName Назва групи, за якою виконується пошук.
     * @return Відповідь зі списком продуктів, що відповідають заданій групі.
     */
    @GetMapping("/searchByGroup")
    public ResponseEntity<List<Product>> searchByGroup(@RequestParam String groupName) {
        List<Product> products = productService.getProductsByGroup(groupName);
        return ResponseEntity.ok().body(products);
    }

}
