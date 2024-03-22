package com.java_site.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.java_site.models.ProductGroup;
import com.java_site.repositories.ProductGroupRepository;
import java.util.*;

/**
 * Реалізація сервісу для роботи з групами продуктів.
 */
@Service
public class ProductGroupServiceImpl implements ProductGroupService {

    private final ProductGroupRepository groupRepository;

    @Autowired
    public ProductGroupServiceImpl(ProductGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    /**
     * Отримати всі групи продуктів.
     * @return Список усіх груп продуктів.
     */
    @Override
    public List<ProductGroup> getAllGroups() {
        return groupRepository.findAll();
    }

    /**
     * Отримати групу продуктів за її ідентифікатором.
     * @param id Ідентифікатор групи продуктів.
     * @return Група продуктів з вказаним ідентифікатором.
     * @throws IllegalStateException Якщо група з вказаним ідентифікатором не існує.
     */
    @Override
    public ProductGroup getGroupById(Long id) {
        Optional<ProductGroup> group = groupRepository.findById(id);
        if (group.isEmpty()) {
            throw new IllegalStateException("Product group with id " + id + " does not exist");
        }
        return group.get();
    }

    /**
     * Створити нову групу продуктів.
     * @param group Група продуктів для створення.
     * @return Створена група продуктів.
     * @throws IllegalStateException Якщо група з вказаним іменем вже існує.
     */
    @Override
    public ProductGroup createGroup(ProductGroup group) {
        Optional<ProductGroup> groupOptional = groupRepository.findGroupByName(group.getName());
        if (groupOptional.isPresent()) {
            throw new IllegalStateException("This product group exists");
        }
        return groupRepository.save(group);
    }

    /**
     * Оновити існуючу групу продуктів.
     * @param group Група продуктів для оновлення.
     * @return Оновлена група продуктів.
     * @throws IllegalStateException Якщо група з вказаним ідентифікатором не існує.
     */
    @Override
    public ProductGroup update(ProductGroup group) {
        if (group.getId() != null && groupRepository.existsById(group.getId())) {
            return groupRepository.save(group);
        } else {
            throw new IllegalStateException("Product group with id " + group.getId() + " does not exists");
        }
    }

    /**
     * Видалити групу продуктів за її ідентифікатором.
     * @param id Ідентифікатор групи продуктів для видалення.
     * @throws IllegalStateException Якщо групи продукту з вказаним ідентифікатором не існує.
     */
    @Override
    public void deleteGroupById(Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Фірми продукту з ID " + id + " не існує");
        }
    }
}
