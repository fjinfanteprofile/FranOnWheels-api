package com.example.franonwheels.repository;

import com.example.franonwheels.model.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByActive(Integer active);

    List<Vehicle> findByType_NameIgnoreCaseAndActive(String typeName, int active);

}
