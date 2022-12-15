package ru.gb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.gb.Data.Product;
import ru.gb.Data.Role;
import ru.gb.Data.User;
import ru.gb.repositories.OrderRepository;
import ru.gb.repositories.ProductRepository;
import ru.gb.repositories.RoleRepository;
import ru.gb.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class DatabaseFiller {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private OrderRepository orderRepo;
    private List<Product> productList;

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabaseOnStartApplication(){
        createRoles();
        createUsers();
        createProductList();
    }
    private void createRoles() {
        roleRepo.save(new Role("ROLE_CUSTOMER"));
        roleRepo.save(new Role("ROLE_MANAGER"));
        roleRepo.save(new Role("ROLE_ADMIN"));
    }
    private void createUsers() {
        Collection<Role> roles = new ArrayList<>();
        roles.add(roleRepo.findById(1L).get());
        userRepo.save(new User("customer", "$2a$12$aFCkIQfUgB/SHFpbksyHUeMfxhTEkh/o3tbyDkF9Wds4YyHbwputW", roles));
        roles.clear();
        roles.add(roleRepo.findById(2L).get());
        userRepo.save(new User("manager", "$2a$12$aFCkIQfUgB/SHFpbksyHUeMfxhTEkh/o3tbyDkF9Wds4YyHbwputW", roles));
        roles.clear();
        roles.add(roleRepo.findById(3L).get());
        userRepo.save(new User("admin", "$2a$12$aFCkIQfUgB/SHFpbksyHUeMfxhTEkh/o3tbyDkF9Wds4YyHbwputW", roles));
    }
    private void createProductList() {
        productList = new ArrayList<>();
        productList.add(new Product("apple", 100));
        productList.add(new Product("bananas", 100));
        productList.add(new Product("bread", 50));
        productList.add(new Product("butter", 50));
        productList.add(new Product("cheese", 350));
        productList.add(new Product("chocolate", 150));
        productList.add(new Product("cookies", 200));
        productList.add(new Product("cucumber", 150));
        productList.add(new Product("egg", 100));
        productList.add(new Product("fish", 500));
        productList.add(new Product("meat", 400));
        productList.add(new Product("milk", 100));
        productList.add(new Product("nuts", 400));
        productList.add(new Product("orange", 150));
        productList.add(new Product("pasta", 100));
        productList.add(new Product("potato", 50));
        productList.add(new Product("sausage", 350));
        productList.add(new Product("tomato", 100));
        productList.add(new Product("vine", 1000));
        productList.add(new Product("yogurt", 50));
        for (Product p : productList) {
            productRepo.save(p);
        }
    }
}
