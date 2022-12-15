package ru.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.Converters.ProductConverter;
import ru.gb.Data.Order;
import ru.gb.Data.User;
import ru.gb.services.CartService;
import ru.gb.services.OrderService;
import ru.gb.services.ProductService;
import ru.gb.services.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;
    @Autowired
    ProductConverter productConverter;
    @Autowired
    UserService userService;

    @GetMapping("")
    public String orderPage(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(required = false) Integer min,
                            @RequestParam(required = false) Integer max,
                            @RequestParam(required = false) String titlePart,
                            Model model1, Model model2, Model model3)
    {
        if (page < 1) {page = 1;}
        model1.addAttribute("productList", productService.find(min, max, titlePart, page).map(s -> productConverter.entityToDto(s)));
        model2.addAttribute("productOfOrder", cartService.getCart().stream().map((s -> productConverter.entityToDto(s))));
        model3.addAttribute("totalCost", cartService.totalCost());
        return "order";
    }

    @PostMapping("/add/{id}")
    public String addItemToCart(@PathVariable Long id) {
        cartService.addProduct(id);
        return "redirect:/order";
    }

    @PostMapping("/delete/{id}")
    public String deleteItemFromCart(@PathVariable Long id) {
        cartService.deleteProduct(id);
        return "redirect:/order";
    }

    @PostMapping("/placeOrder")
    public String placeOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        Order newOrder = new Order(user, cartService.getCart());
        orderService.addOrder(newOrder);
        cartService.getCart().clear();
        return "redirect:/profile";
    }
}
