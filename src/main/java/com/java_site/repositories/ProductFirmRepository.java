package com.java_site.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.java_site.models.ProductFirm;
/**
 * Репозиторій для доступу до даних про фірми продуктів.
 */
@Repository
public interface ProductFirmRepository extends JpaRepository<ProductFirm, Long> {
    /**
     * Пошук фірми за її назвою.
     * @param name Назва фірми.
     * @return Опціональний об'єкт типу ProductFirm.
     */
    java.util.Optional<ProductFirm> findFirmByName(String name);
}
