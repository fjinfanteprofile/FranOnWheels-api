package com.example.demo.service;
import com.example.demo.model.domain.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Create operation
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Read operations
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    // Update operation
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    // Delete operation
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
