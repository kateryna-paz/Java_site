package com.java_site.models;

import java.util.*;
import jakarta.persistence.*;
/**
 * Модель групи продукту.
 */
@Entity
@Table
@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor

public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
