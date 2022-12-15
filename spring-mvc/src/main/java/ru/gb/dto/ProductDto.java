package ru.gb.dto;
import ru.gb.Data.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.Data.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int cost;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
    }

    @Override
    public String toString() {
        return "[ id:" + id + "\tcost: " + cost + "\ttitle: " + title + " ]";
    }
}