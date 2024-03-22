package com.java_site.services;

import com.java_site.models.ProductGroup;
import java.util.*;

/**
 * Інтерфейс, який визначає операції, що пов'язані з обробкою даних про групи продуктів.
 */
public interface ProductGroupService {

    List<ProductGroup> getAllGroups();

    ProductGroup getGroupById(Long id);

    ProductGroup createGroup(ProductGroup group);

    ProductGroup update(ProductGroup group);

    void deleteGroupById(Long id);

}
