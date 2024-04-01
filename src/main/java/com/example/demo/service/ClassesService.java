package com.example.demo.service;

import com.example.demo.model.Classes;
import com.example.demo.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassesService {

    private final ClassesRepository classesRepository;

    @Autowired
    public ClassesService(ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }

    public List<Classes> getAllClasses() {
        return classesRepository.findAll();
    }

    // Add more methods as needed
}
