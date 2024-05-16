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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        if (classesDTO.getDate() != null) {
            classesEntity.setDayOfWeek(classesDTO.getDate().getDayOfWeek().toString());
        }

        // Save the entity
        Classes savedClassEntity = classesRepository.save(classesEntity);
        classesDTO.setUserId(userId);
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
        existingClass = Classes.builder()
                .id(existingClass.getId())
                .date(classesDTO.getDate())
                .vehicle(Vehicle.builder().id(classesDTO.getVehicleId()).build())
                .timeEnd(classesDTO.getTimeEnd())
                .timeStart(classesDTO.getTimeStart())
                .active(classesDTO.getActive())
                .user(User.builder().id(classesDTO.getUserId()).build())
                .build();

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

    public List<String> getAvailableTimeSlotsForDate(LocalDate date) {
        // Retrieve all classes for the specified day
        List<Classes> classesForDate = classesRepository.findByDate(date);

        // Extract booked time slots from classes for the day
        List<LocalTime> bookedTimeSlots = classesForDate.stream()
                .map(classes -> LocalTime.parse(classes.getTimeStart()))
                .collect(Collectors.toList());

        // Define all possible time slots for a day
        List<String> allTimeSlots = getAllTimeSlotsForDate();

        // Convert all time slots to LocalTime objects for comparison
        List<LocalTime> allTimeSlotsLocalTime = allTimeSlots.stream()
                .map(LocalTime::parse)
                .collect(Collectors.toList());

        // Remove booked time slots from all time slots to get available time slots
        allTimeSlotsLocalTime.removeAll(bookedTimeSlots);

        // Sort the available time slots
        allTimeSlotsLocalTime.sort(Comparator.naturalOrder());

        // Convert back to string format
        return allTimeSlotsLocalTime.stream()
                .map(LocalTime::toString)
                .collect(Collectors.toList());
    }

    public List<String> getAllTimeSlotsForDate() {
        List<String> allTimeSlots = new ArrayList<>();

        // Morning slots from 10:00 to 14:00
        LocalTime morningStartTime = LocalTime.of(10, 0);
        LocalTime morningEndTime = LocalTime.of(14, 0);
        while (morningStartTime.isBefore(morningEndTime)) {
            allTimeSlots.add(morningStartTime.toString());
            morningStartTime = morningStartTime.plusHours(1);
        }

        // Evening slots from 17:00 to 22:00
        LocalTime eveningStartTime = LocalTime.of(17, 0);
        LocalTime eveningEndTime = LocalTime.of(22, 0);
        while (eveningStartTime.isBefore(eveningEndTime)) {
            allTimeSlots.add(eveningStartTime.toString());
            eveningStartTime = eveningStartTime.plusHours(1);
        }

        return allTimeSlots;
    }

    public List<ClassesDTO> getClassesByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Classes> classes = classesRepository.findByUser(user);
            return classes.stream()
                    .map(ClassesMapper::ClassestoDTO)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList(); // User not found
        }
    }
}
