package com.java_site.controllers;

import com.java_site.models.ProductGroup;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import com.java_site.services.ProductGroupService;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import java.util.*;

/**
 * Контролер для обробки запитів, пов'язаних з групами продуктів.
 */
@Controller
@RequestMapping("/groups")
public class ProductGroupController {

    private final ProductGroupService productGroupService;
    @Autowired
    public ProductGroupController(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }

    /**
     * Метод для створення нової групи продуктів.
     * @param model Модель, яка використовується для передачі даних на сторінку.
     * @return Шаблон сторінки для створення нової групи продуктів.
     */
    @GetMapping(path="/create")
    public String createNewGroup(Model model) {
        ProductGroup group = new ProductGroup();
        model.addAttribute("group", group);
        return "addGroup";
    }

    /**
     * Метод для обробки POST-запиту на додавання нової групи продуктів.
     * @param group Об'єкт групи продуктів, який додається.
     * @return Перенаправлення на сторінку з усіма групами продуктів після успішного додавання.
     */
    @PostMapping(path="/add")
    public String addNewGroup(ProductGroup group) {
        productGroupService.createGroup(group);
        return "redirect:/groups/all";
    }

    /**
     * Метод для відображення всіх груп продуктів.
     * @param model Модель, яка використовується для передачі даних на сторінку.
     * @return Шаблон сторінки з усіма групами продуктів.
     */
    @GetMapping("/all")
    public String getAllGroups(Model model) {
        List<ProductGroup> groups = productGroupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "showGroups";
    }

    /**
     * Метод для отримання групи продуктів за її ідентифікатором.
     * @param id Ідентифікатор групи продуктів.
     * @return Об'єкт групи продуктів.
     */
    @GetMapping("/{id}")
    public ProductGroup getGroupById(@PathVariable("id") Long id) {
        return productGroupService.getGroupById(id);
    }

    /**
     * Метод для відображення сторінки з підтвердженням видалення групи продуктів.
     * @param id Ідентифікатор групи продуктів, яку потрібно видалити.
     * @param model Модель, яка використовується для передачі даних на сторінку.
     * @return Шаблон сторінки з підтвердженням видалення групи продуктів.
     */
    @GetMapping("/delete/{id}")
    public String showDeleteConfirmationPage(@PathVariable("id") Long id, Model model) {
        ProductGroup group = productGroupService.getGroupById(id);
        model.addAttribute("group", group);
        return "deleteGroup";
    }

    /**
     * Метод для видалення групи продуктів за її ідентифікатором.
     * @param id Ідентифікатор групи продуктів, яку потрібно видалити.
     * @return Перенаправлення на сторінку з усіма групами продуктів після успішного видалення.
     */
    @PostMapping("/delete/{id}")
    public String deleteGroupById(@PathVariable("id") Long id) {
        productGroupService.deleteGroupById(id);
        return "redirect:/groups/all";
    }
}
