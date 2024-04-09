package com.example.franonwheels.service;

import com.example.franonwheels.model.domain.Classes;

import java.util.List;
import java.util.Optional;

public interface ClassesService {

    Classes createClass(Classes classes);

    List<Classes> getAllClasses();

    Optional<Classes> getClassById(Long id);

    Classes updateClass(Classes classes);

    void deleteClassById(Long id);
}
