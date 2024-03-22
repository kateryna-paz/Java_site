package com.java_site.controllers;

import com.java_site.models.ProductFirm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import com.java_site.services.ProductFirmService;
import org.springframework.ui.Model;
import java.util.*;

/**
 * Контролер для обробки запитів, пов'язаних з фірмами продукції.
 */
@Controller
@RequestMapping("/firms")
public class ProductFirmController {
    private final ProductFirmService productFirmService;
    @Autowired
    public ProductFirmController(ProductFirmService productFirmService) {
        this.productFirmService = productFirmService;
    }

    /**
     * Метод для створення нової фірми.
     * @param model Модель, яка використовується для передачі даних на сторінку.
     * @return Шаблон сторінки для створення нової фірми.
     */
    @GetMapping(path="/create")
    public String createNewFirm(Model model) {
        ProductFirm firm = new ProductFirm();
        model.addAttribute("firm", firm);
        return "addFirm";
    }

    /**
     * Метод для обробки POST-запиту на додавання нової фірми.
     * @param firm Об'єкт фірми, який додається.
     * @return Перенаправлення на сторінку з усіма фірмами після успішного додавання.
     */
    @PostMapping(path="/add")
    public String addNewFirm(ProductFirm firm) {
        productFirmService.createFirm(firm);
        return "redirect:/firms/all";
    }

    /**
     * Метод для відображення всіх фірм.
     * @param model Модель, яка використовується для передачі даних на сторінку.
     * @return Шаблон сторінки з усіма фірмами.
     */
    @GetMapping("/all")
    public String getAllFirms(Model model) {
        List<ProductFirm> firms = productFirmService.getAllFirms();
        model.addAttribute("firms", firms);
        return "showFirms";
    }

    /**
     * Метод для отримання фірми за ідентифікатором.
     * @param id Ідентифікатор фірми, яку потрібно отримати.
     * @return Об'єкт фірми з вказаним ідентифікатором.
     */
    @GetMapping("/{id}")
    public ProductFirm getFirmById(@PathVariable("id") Long id) {
        return productFirmService.getFirmById(id);
    }

    /**
     * Метод для відображення сторінки з підтвердженням видалення фірми.
     * @param id Ідентифікатор фірми, яку потрібно видалити.
     * @param model Модель, яка використовується для передачі даних на сторінку.
     * @return Шаблон сторінки з підтвердженням видалення фірми.
     */
    @GetMapping("/delete/{id}")
    public String showDeleteConfirmationPage(@PathVariable("id") Long id, Model model) {
        ProductFirm firm = productFirmService.getFirmById(id);
        model.addAttribute("firm", firm);
        return "deleteFirm";
    }

    /**
     * Метод для видалення фірми за ідентифікатором.
     * @param id Ідентифікатор фірми, яку потрібно видалити.
     * @return Перенаправлення на сторінку з усіма фірмами після успішного видалення.
     */
    @PostMapping("/delete/{id}")
    public String deleteFirmById(@PathVariable("id") Long id) {
        productFirmService.deleteFirmById(id);
        return "redirect:/firms/all";
    }
}
