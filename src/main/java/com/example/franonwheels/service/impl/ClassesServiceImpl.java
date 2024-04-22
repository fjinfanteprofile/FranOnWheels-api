package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.ClassesMapper;
import com.example.franonwheels.model.domain.Classes;
import com.example.franonwheels.model.dtos.ClassesDTO;
import com.example.franonwheels.repository.ClassesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl {

    private final ClassesRepository classesRepository;


    // Create operation
    public ClassesDTO createClass(ClassesDTO classesDTO) {

        return ClassesMapper.ClassestoDTO(this.classesRepository.save(ClassesMapper.ClassesDTOtoEntity(classesDTO)));
    }

    // Read operations
    public List<ClassesDTO> getAllClasses() {

        List<Classes> classes = classesRepository.findAll();

        return classes.stream()
                .map(ClassesMapper::ClassestoDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    public Optional<ClassesDTO> getClassById(Long id) {
        Optional<Classes> classes = classesRepository.findById(id);
        return classes.map(ClassesMapper::ClassestoDTO);
    }

    // Update operation
    public ClassesDTO updateClass(ClassesDTO classesDTO) {

        return ClassesMapper.ClassestoDTO(this.classesRepository.save(ClassesMapper.ClassesDTOtoEntity(classesDTO)));

    }

    // Delete operation
    public void deleteClassById(Long id) {
        classesRepository.deleteById(id);
    }
}
