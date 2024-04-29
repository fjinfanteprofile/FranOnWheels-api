package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.ClassesMapper;
import com.example.franonwheels.model.domain.Classes;
import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.domain.Vehicle;
import com.example.franonwheels.model.dtos.ClassesDTO;
import com.example.franonwheels.repository.ClassesRepository;
import com.example.franonwheels.repository.UserRepository;
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
    private final UserRepository userRepository;


    public ClassesDTO createClass(ClassesDTO classesDTO, Long userId) {
        if (userId == null) {
            return null;
        }

        Classes classesEntity = ClassesMapper.ClassesDTOtoEntity(classesDTO);
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        classesEntity.setUser(user);

        // Save the entity
        Classes savedClassEntity = classesRepository.save(classesEntity);

        // Map entity back to DTO
        return ClassesMapper.ClassestoDTO(savedClassEntity);
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
    public ClassesDTO updateClass(ClassesDTO classesDTO, Long id) {
        Long classId = id;
        if (classId == null) {
            return null;
        }

        // Retrieve existing Classes entity from the database
        Optional<Classes> optionalClass = classesRepository.findById(classId);
        if (!optionalClass.isPresent()) {
            return null; // Handle appropriately if class is not found
        }

        // Update fields of the existing entity with values from the DTO
        Classes existingClass = optionalClass.get();
        existingClass.setDate(classesDTO.getDate());
        existingClass.setVehicle(Vehicle.builder().id(classesDTO.getVehicleId()).build());
        existingClass.setTimeEnd(classesDTO.getTimeEnd());
        existingClass.setTimeStart(classesDTO.getTimeStart());

        // Save the updated entity
        Classes updatedClassEntity = classesRepository.save(existingClass);

        // Map entity back to DTO
        return ClassesMapper.ClassestoDTO(updatedClassEntity);
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
