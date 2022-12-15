package ru.gb.Data;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "orderData")
    private LocalDate orderData;
    @Column(name = "totalCost")
    private int totalCost;

    @Column(name = "paid")
    private boolean paid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productsOfOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderData() {
        return orderData;
    }

    public void setOrderData(LocalDate orderData) {
        this.orderData = orderData;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid() {
        this.paid = true;
    }

    public User getCustomer() {
        return user;
    }

    public void setCustomer(User user) {
        this.user = user;
    }

    public List<Product> getProductsOfOrder() {
        return productsOfOrder;
    }

    public void setProductsOfOrder(List<Product> productsOfOrder) {
        this.productsOfOrder = productsOfOrder;
    }

    public int getTotalCost() {
        return totalCost(productsOfOrder);
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public Order(User user, List<Product> productsOfOrder) {
        this.id = id;
        this.orderData = LocalDate.now();
        this.user = user;
        this.productsOfOrder = productsOfOrder;
        this.totalCost = totalCost(productsOfOrder);
        this.paid = false;
    }

    public Order() {
    }

    public int totalCost(List<Product> productsOfOrder) {
        if (productsOfOrder.isEmpty()) {
            return 0;
        } else {
            int cost = 0;
            for (int i = 0; i < productsOfOrder.size(); i++) {
                cost += productsOfOrder.get(i).getCost();
            }
            return cost;
        }
    }

    @Override
    public String toString() {
        return "[ order id:" + id + "\tdata: " + orderData + "\tcustomer: " + user.getId() + "\tproducts: " + productsOfOrder + " ]";
    }
}