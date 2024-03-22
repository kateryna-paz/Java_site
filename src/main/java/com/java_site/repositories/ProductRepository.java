package com.java_site.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import com.java_site.models.Product;

import java.util.List;
/**
 * Репозиторій для доступу до даних про продукти.
 */
@Repository
public interface ProductRepository extends JpaRepository<com.java_site.models.Product, Long> {

    /**
     * Пошук продуктів за назвою групи.
     * @param groupName Назва групи.
     * @return Список продуктів.
     */
    @Query("SELECT p FROM Product p WHERE p.productGroup.name = :groupName")
    List<Product> findByGroupName(@Param("groupName") String groupName);

    /**
     * Пошук продуктів за назвою фірми.
     * @param firmName Назва фірми.
     * @return Список продуктів.
     */
    @Query("SELECT p FROM Product p WHERE p.productFirm.name = :firmName")
    List<Product> findByFirmName(@Param("firmName") String firmName);
}
