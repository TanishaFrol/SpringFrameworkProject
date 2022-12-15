package ru.gb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.Data.User;
import ru.gb.repositories.RoleRepository;
import ru.gb.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Objects;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @GetMapping("")
    public String getUsers(Principal principal, Model model1, Model model2, Model model3) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unknown user"));
        ArrayList<User> customers = new ArrayList<>();
        ArrayList<User> employees = new ArrayList<>();
        for (User u : userService.getUsers()) {
            if (Objects.nonNull(u.getRoles().stream().filter(it -> Objects.equals("ROLE_CUSTOMER", it.getName())).findFirst().orElse(null))) {
                customers.add(u);
            }
            if (Objects.nonNull(u.getRoles().stream().filter(it -> Objects.equals("ROLE_MANAGER", it.getName()) || Objects.equals("ROLE_ADMIN", it.getName())).findFirst().orElse(null))) {
                employees.add(u);
            }
        }
        model1.addAttribute("customersList", customers);
        model2.addAttribute("employeesList", employees);
        model3.addAttribute("user", user);
        return "users";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam(required = false) String password, @RequestParam String role) {
        if (password.isBlank()) {
            User user = userService.findByUsername(name).orElseThrow(() -> new RuntimeException("Unknown user"));
            user.getRoles().add(roleRepository.findByName(role));
            userService.saveUser(user);
        } else {
            userService.addUser(name, password, role);
        }
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

}
