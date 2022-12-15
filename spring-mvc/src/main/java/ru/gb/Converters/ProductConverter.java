package ru.gb.Converters;

import org.springframework.stereotype.Component;
import ru.gb.Data.Product;
import ru.gb.dto.ProductDto;

@Component
public class ProductConverter {
    public Product dtoToEntity (ProductDto productDto) {
        return new Product(productDto);
    }

    public ProductDto entityToDto (Product product) {
        return new ProductDto(product);
    }
}
