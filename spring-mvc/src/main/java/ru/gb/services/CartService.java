package ru.gb.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.Data.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CartService {
    @Autowired
    ProductService productService;
    private List<Product> cart;

    @PostConstruct
    public void init() {
        cart = new ArrayList<>();
    }

    public void deleteProduct(Long id) {
        cart.removeIf(it -> Objects.equals(id, it.getId()));
    }

    public void addProduct(Long id) {
        cart.add(productService.getItemByID(id));
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    public int totalCost() {
        if (cart.isEmpty()) {
            return 0;
        } else {
            int cost = 0;
            for (int i = 0; i < cart.size(); i++) {
                cost += cart.get(i).getCost();
            }
            return cost;
        }
    }
}
