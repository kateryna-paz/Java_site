package com.java_site.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.java_site.models.ProductFirm;
import com.java_site.repositories.ProductFirmRepository;
import java.util.*;

/**
 * Реалізація сервісу для роботи з фірмами продуктів.
 */
@Service
public class ProductFirmServiceImpl implements ProductFirmService {

    private final ProductFirmRepository firmRepository;

    @Autowired
    public ProductFirmServiceImpl(ProductFirmRepository firmRepository) {
        this.firmRepository = firmRepository;
    }

    /**
     * Отримати всі фірми продуктів.
     */
    @Override
    public List<ProductFirm> getAllFirms() {
        return firmRepository.findAll();
    }

    /**
     * Отримати фірму продукту за її ідентифікатором.
     * @param id Ідентифікатор фірми.
     * @return Фірма продукту.
     * @throws IllegalStateException Якщо фірма з вказаним ідентифікатором не існує.
     */
    @Override
    public ProductFirm getFirmById(Long id) {
        Optional<ProductFirm> firm = firmRepository.findById(id);
        if (firm.isEmpty()) {
            throw new IllegalStateException("Фірми продукту з ID " + id + " не існує");
        }
        return firm.get();
    }

    /**
     * Створити нову фірму продукту.
     * @param firm Фірма продукту, яка буде створена.
     * @return Створена фірма продукту.
     * @throws IllegalStateException Якщо фірма продукту з вказаною назвою вже існує.
     */
    @Override
    public ProductFirm createFirm(ProductFirm firm) {
        Optional<ProductFirm> firmOptional = firmRepository.findFirmByName(firm.getName());
        if (firmOptional.isPresent()) {
            throw new IllegalStateException("Ця фірма продукту вже існує");
        }
        return firmRepository.save(firm);
    }

    /**
     * Оновити інформацію про фірму продукту.
     * @param firm Фірма продукту, яка буде оновлена.
     * @return Оновлена фірма продукту.
     * @throws IllegalStateException Якщо фірма продукту з вказаним ідентифікатором не існує.
     */
    @Override
    public ProductFirm update(ProductFirm firm) {
        if (firm.getId() != null && firmRepository.existsById(firm.getId())) {
            return firmRepository.save(firm);
        } else {
            throw new IllegalStateException("Фірми продукту з ID " + firm.getId() + " не існує");
        }
    }

    /**
     * Видалити фірму продукту за її ідентифікатором.
     * @param id Ідентифікатор фірми продукту, яка буде видалена.
     * @throws IllegalStateException Якщо фірми продукту з вказаним ідентифікатором не існує.
     */
    @Override
    public void deleteFirmById(Long id) {
        if (firmRepository.existsById(id)) {
            firmRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Фірми продукту з ID " + id + " не існує");
        }
    }
}
