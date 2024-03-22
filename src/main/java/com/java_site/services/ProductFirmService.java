package com.java_site.services;

import java.util.*;
import com.java_site.models.ProductFirm;

/**
 * Інтерфейс, який визначає операції, що пов'язані з обробкою даних про фірми продуктів.
 */
public interface ProductFirmService {
    List<ProductFirm> getAllFirms();
    ProductFirm getFirmById(Long id);
    ProductFirm createFirm(ProductFirm firm);
    ProductFirm update(ProductFirm firm);
    void deleteFirmById(Long id);

}
