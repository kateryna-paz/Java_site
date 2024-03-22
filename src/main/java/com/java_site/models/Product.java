package com.java_site.models;

import java.util.*;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Модель продукту.
 */
@jakarta.persistence.Entity
@jakarta.persistence.Table
@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name, description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "prod_group_id", nullable = false)
    private ProductGroup productGroup;

    @ManyToOne
    @JoinColumn(name = "product_firm_id", nullable = false)
    private ProductFirm productFirm;

    /**
     * Конструктор класу Product.
     * @param name Назва продукту.
     * @param description Опис продукту.
     * @param date Дата релізу продукту.
     * @param price Ціна продукту.
     * @param firm Фірма-виробник продукту.
     * @param group Група, до якої належить продукт.
     */
    public Product(String name, String description, java.util.Date date, Double price, ProductFirm firm, ProductGroup group) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.price = price;
        this.productFirm = firm;
        this.productGroup = group;
    }

    /**
     * Метод для отримання ідентифікатора фірми продукту.
     * @return Ідентифікатор фірми-виробника.
     */
    public Long getFirmId() {
        return productFirm.getId();
    }

    /**
     * Метод для отримання ідентифікатора групи, до якої належить продукт.
     * @return Ідентифікатор групи.
     */
    public Long getGroupId() {
        return productGroup.getId();
    }
}
