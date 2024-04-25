package com.example.franonwheels.repository;

import com.example.franonwheels.model.domain.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {

    VehicleType findByName(String name);

}