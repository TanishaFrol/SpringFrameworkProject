package ru.gb.Data;

import ru.gb.dto.ProductDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "cost")
    private int cost;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> ordersOfProduct;

    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product(ProductDto productDto) {
        this.id = productDto.getId();
        this.title = productDto.getTitle();
        this.cost = productDto.getCost();
    }

    @Override
    public String toString() {
        return "[ id:" + id + "\tcost: " + cost + "\ttitle: " + title + " ]";
    }
}
