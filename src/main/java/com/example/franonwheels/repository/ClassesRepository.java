package com.example.franonwheels.repository;

import com.example.franonwheels.model.domain.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {

    void deleteClassesByUserId(Long userId);

}
