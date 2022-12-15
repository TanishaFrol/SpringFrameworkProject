package ru.gb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.Data.Role;
import ru.gb.Data.User;
import ru.gb.services.RoleService;
import ru.gb.services.UserService;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

    @PostMapping("/reg")
    public String regNewCustomer(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam(required = false) String birthdate,
                                 @RequestParam(required = false) String email,
                                 @RequestParam(required = false) String mobile,
                                 @RequestParam(required = false) String address) {
        Collection<Role> roles = new ArrayList<>();
        roles.add(roleService.findByName("ROLE_CUSTOMER"));
        User newUser = new User(username, password, birthdate, email, mobile, address, roles);
        userService.saveUser(newUser);
        return "redirect:/login";
    }
}
