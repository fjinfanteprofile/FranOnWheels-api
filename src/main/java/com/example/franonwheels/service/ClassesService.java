package com.example.franonwheels.service;

import com.example.franonwheels.model.domain.Classes;
import com.example.franonwheels.model.dtos.ClassesDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClassesService {

    ClassesDTO createClass(ClassesDTO classesDTO, Long id);

    List<ClassesDTO> getAllClasses();

    Optional<ClassesDTO> getClassById(Long id);

    ClassesDTO updateClass(ClassesDTO classesDTO, Long id);

    void deleteClassById(Long id);

    List<String> getAvailableTimeSlotsForDate(LocalDate date);

    List<String> getAllTimeSlotsForDate();

    List<ClassesDTO> getClassesByUserId(Long userId);

}
