package com.example.franonwheels.repository;

import com.example.franonwheels.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    List<Role> findByActive(Integer active);
}