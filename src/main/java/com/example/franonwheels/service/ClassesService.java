package com.example.franonwheels.service;

import com.example.franonwheels.model.domain.Classes;
import com.example.franonwheels.model.dtos.ClassesDTO;

import java.util.List;
import java.util.Optional;

public interface ClassesService {

    ClassesDTO createClass(ClassesDTO classesDTO);

    List<ClassesDTO> getAllClasses();

    Optional<ClassesDTO> getClassById(Long id);

    ClassesDTO updateClass(ClassesDTO classesDTO);

    void deleteClassById(Long id);
}
