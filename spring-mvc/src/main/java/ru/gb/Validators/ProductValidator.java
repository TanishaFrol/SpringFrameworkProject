package ru.gb.Validators;

import org.springframework.stereotype.Component;
import ru.gb.dto.ProductDto;
import ru.gb.exceptions.ValidationException;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        if (productDto.getCost() < 1) {
            throw new ValidationException("Некорректная цена товара");
        }
        if (productDto.getTitle().isBlank()) {
            throw new ValidationException("Продукт не может иметь пустое название");
        }
    }
}
