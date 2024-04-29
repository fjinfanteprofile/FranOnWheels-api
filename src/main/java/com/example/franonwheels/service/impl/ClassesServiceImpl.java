package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.ClassesMapper;
import com.example.franonwheels.model.domain.Classes;
import com.example.franonwheels.model.dtos.ClassesDTO;
import com.example.franonwheels.repository.ClassesRepository;
import com.example.franonwheels.service.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl implements ClassesService {

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

    // Activate operation
    public void activateClassById(Long id) {
        Optional<Classes> optionalClass = classesRepository.findById(id);
        optionalClass.ifPresent(class1 -> {
            class1.setActive(1);
            classesRepository.save(class1);
        });
    }

    // Deactivate operation
    public void deactivateClassById(Long id) {
        Optional<Classes> optionalClass = classesRepository.findById(id);
        optionalClass.ifPresent(class1 -> {
            class1.setActive(0);
            classesRepository.save(class1);
        });
    }

    // Get active classes
    public List<ClassesDTO> getActiveClasses() {
        List<Classes> activeClasses = classesRepository.findByActive(1);
        return activeClasses.stream()
                .map(ClassesMapper::ClassestoDTO)
                .collect(Collectors.toList());
    }

    // Get inactive classes
    public List<ClassesDTO> getInactiveClasses() {
        List<Classes> inactiveClasses = classesRepository.findByActive(0);
        return inactiveClasses.stream()
                .map(ClassesMapper::ClassestoDTO)
                .collect(Collectors.toList());
    }
}
