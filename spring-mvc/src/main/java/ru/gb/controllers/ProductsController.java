package ru.gb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.Data.User;
import ru.gb.services.ProductService;
import ru.gb.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("")
    public String getProductList(Principal principal, Model model1, Model model2) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unknown user"));

        model1.addAttribute("productList", productService.getProductList());
        model2.addAttribute("user", user);

        return "products";
    }
    @PostMapping("/add")
    public String addItem(@RequestParam String title, @RequestParam int cost) {
        productService.addItem(title, cost);
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        productService.removeItem(id);
        return "redirect:/products";
    }

}
