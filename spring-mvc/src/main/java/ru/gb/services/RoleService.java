package ru.gb.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.Data.Role;
import ru.gb.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Transactional
    public List<Role> getRoleList() {
        return (List<Role>) roleRepository.findAll();
    }
    @Transactional
    public Role findByName(String name) {return roleRepository.findByName(name);}
}
