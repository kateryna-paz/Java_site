package com.java_site.models;

import jakarta.persistence.*;

/**
 * Модель фірми продукту.
 */
@Entity
@Table
@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class ProductFirm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
