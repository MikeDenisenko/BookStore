package com.mike.service;

import com.mike.model.Role;
import com.mike.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role find(int id) {
        return roleRepository.find(id);
    }

    public void save(Role role) {
        roleRepository.saveAndFlush(role);
    }

    public void delete(Role role) {
        roleRepository.delete(role);
    }

    public void update(Role role) {
        roleRepository.saveAndFlush(role);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
