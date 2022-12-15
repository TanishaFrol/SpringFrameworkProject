package ru.gb.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.Data.Order;
import ru.gb.Data.User;
import ru.gb.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;
    @Transactional
    public List<Order> getOrderList() {
        return (List<Order>) orderRepo.findAll();
    }
    @Transactional
    public Optional<Order> getOrderByID(Long id) {
        return orderRepo.findById(id);
    }
    @Transactional
    public Order addOrder(Order order) {
        return orderRepo.save(order);
    }
    @Transactional
    public Order updateOrder(Optional<Order> order) {
        return orderRepo.save(order.get());
    }
    @Transactional
    public void removeOrder(Long id) {
        orderRepo.deleteById(id);
    }
    @Transactional
    public int getOrderCost(Long id) {
        return orderRepo.findById(id).get().getTotalCost();
    }
    @Transactional
    public boolean isOrderPaid(Long id) {
        return orderRepo.findById(id).get().isPaid();
    }
    @Transactional
    public void makePaid(Long id) {
        orderRepo.findById(id).get().setPaid();
    }
}
