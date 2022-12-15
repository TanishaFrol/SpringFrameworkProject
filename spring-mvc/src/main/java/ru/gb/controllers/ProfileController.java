package ru.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.Converters.ProductConverter;
import ru.gb.Data.Role;
import ru.gb.Data.User;
import ru.gb.services.CartService;
import ru.gb.services.OrderService;
import ru.gb.services.ProductService;
import ru.gb.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductConverter productConverter;

    @GetMapping("")
    public String orderPage(Principal principal, Model model1, Model model2) {
        User user = userService.findByUsername(principal.getName()).get();

        model1.addAttribute("user", user);
        model2.addAttribute("ordersList", user.getOrders());
        return "profile";
    }
    @PostMapping("/pay/{id}")
    public String payOrder(@PathVariable Long id) {
        orderService.makePaid(id);
        return "redirect:/profile";
    }
    @GetMapping("/update")
    public String changeData() {
        return "form";
    }
    @PostMapping("/update")
    public String regNewCustomer(@RequestParam(required = false) String newUsername,
                                 @RequestParam(required = false) String password,
                                 @RequestParam(required = false) String birthdate,
                                 @RequestParam(required = false) String email,
                                 @RequestParam(required = false) String mobile,
                                 @RequestParam(required = false) String address,
                                 Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        if (newUsername != null) { user.setUsername(newUsername); }
        if (password != null) { user.setPassword(password); }
        if (birthdate != null) { user.setBirthdate(birthdate); }
        if (email != null) { user.setEmail(email); }
        if (mobile != null) { user.setMobile(mobile); }
        if (address != null) { user.setAddress(address); }
        userService.saveUser(user);
        System.out.println(">>>>>>> " + user);
        return "redirect:/profile";
    }

}
