package com.java_site.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.java_site.models.ProductGroup;

/**
 * Репозиторій для доступу до даних про групи продуктів.
 */
@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {
    /**
     * Пошук групи за її назвою.
     * @param name Назва групи.
     * @return Опціональний об'єкт типу ProductGroup.
     */

    java.util.Optional<ProductGroup> findGroupByName(String name);
}
