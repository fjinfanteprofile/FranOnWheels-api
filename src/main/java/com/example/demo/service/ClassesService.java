package com.example.demo.service;

import com.example.demo.model.domain.Classes;
import com.example.demo.repository.ClassesRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class ClassesService {

    private final ClassesRepository classesRepository;

    @Autowired
    public ClassesService(ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }

    // Create operation
    public Classes createClass(Classes classes) {
        return classesRepository.save(classes);
    }

    // Read operations
    public List<Classes> getAllClasses() {
        return classesRepository.findAll();
    }

    public Optional<Classes> getClassById(Long id) {
        return classesRepository.findById(id);
    }

    // Update operation
    public Classes updateClass(Classes classes) {
        return classesRepository.save(classes);
    }

    // Delete operation
    public void deleteClassById(Long id) {
        classesRepository.deleteById(id);
    }
}
