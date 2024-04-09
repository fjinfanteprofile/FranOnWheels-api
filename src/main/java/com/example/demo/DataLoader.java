package com.example.demo;

import com.example.demo.model.domain.Bookings;
import com.example.demo.model.domain.Classes;
import com.example.demo.model.domain.Role;
import com.example.demo.model.domain.Schedule;
import com.example.demo.model.domain.Speciality;
import com.example.demo.model.domain.User;
import com.example.demo.model.domain.Vehicle;
import com.example.demo.model.domain.VehicleType;
import com.example.demo.repository.BookingsRepository;
import com.example.demo.repository.ClassesRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.repository.SpecialityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.VehicleTypeRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;


import java.time.LocalDate;
import java.util.List;
import java.util.Random;


@Component
public class DataLoader {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SpecialityRepository specialityRepository;
    private final ClassesRepository classesRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final ScheduleRepository scheduleRepository;
    private final BookingsRepository bookingsRepository;

    private final Random random = new Random();


    public DataLoader(UserRepository userRepository, RoleRepository roleRepository,
                      SpecialityRepository specialityRepository, ClassesRepository classesRepository,
                      VehicleRepository vehicleRepository, VehicleTypeRepository vehicleTypeRepository,
                      ScheduleRepository scheduleRepository, BookingsRepository bookingsRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.specialityRepository = specialityRepository;
        this.classesRepository = classesRepository;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.scheduleRepository = scheduleRepository;
        this.bookingsRepository = bookingsRepository;
    }


    @PostConstruct
    public void init() {
        // Create roles and specialities if they do not exist
        createRoles();
        createSpecialities();
        // Insert vehicle types if the repository is empty
        createVehicleTypes();
        // Insert users if the repository is empty
        createUsers();
        // Insert vehicles if the repository is empty
        createVehicles();
        // Insert schedules if the repository is empty
        createSchedules();
        // Insert classes if the repository is empty
        createClassesAndBookings();
    }



//      Creates and saves roles if they do not exist in the database.

    private void createRoles() {
        // Check if roles already exist in the database
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role("USER")); // Save ROLE_USER
            roleRepository.save(new Role("ADMIN")); // Save ROLE_ADMIN
        }
    }

//
//      Creates and saves specialities if they do not exist in the database.
//
private void createSpecialities() {
    // Check if specialities already exist in the database
    if (specialityRepository.count() == 0) {
        specialityRepository.save(new Speciality("A1")); // Save Speciality A1
        specialityRepository.save(new Speciality("A2")); // Save Speciality A2
        specialityRepository.save(new Speciality("AM")); // Save Speciality AM
        specialityRepository.save(new Speciality("A")); // Save Speciality A
        specialityRepository.save(new Speciality("B1")); // Save Speciality B1
        specialityRepository.save(new Speciality("B")); // Save Speciality B
        specialityRepository.save(new Speciality("C1")); // Save Speciality C1
        specialityRepository.save(new Speciality("C")); // Save Speciality C
        specialityRepository.save(new Speciality("D1")); // Save Speciality D1
        specialityRepository.save(new Speciality("D")); // Save Speciality D
        specialityRepository.save(new Speciality("BE")); // Save Speciality BE
        specialityRepository.save(new Speciality("C1E")); // Save Speciality C1E
        specialityRepository.save(new Speciality("CE")); // Save Speciality CE
    }
}


//      Inserts users into the database with specified roles and specialities.
//      Two users are inserted, one with ROLE_USER and Speciality A1, and another with ROLE_ADMIN and Speciality A2.

    private void createUsers() {
        List<Role> roles = roleRepository.findAll();
        List<Speciality> specialities = specialityRepository.findAll();

        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .name("User" + i)
                    .lastName("LastName" + i)
                    .dni(generateRandomDNI())
                    .email("user" + i + "@example.com")
                    .address("Address " + i)
                    .phoneNumber(generateRandomPhoneNumber())
                    .role(roles.get(random.nextInt(roles.size())))
                    .speciality(specialities.get(random.nextInt(specialities.size())))
                    .build();
            userRepository.save(user);
        }
    }
    private String generateRandomDNI() {
        StringBuilder dni = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            dni.append(random.nextInt(10));
        }
        return dni.toString();
    }

    private String generateRandomPhoneNumber() {
        StringBuilder phoneNumber = new StringBuilder("+34");
        for (int i = 0; i < 9; i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }
    private void createVehicleTypes() {
        // Check if vehicle types already exist in the database
        if (vehicleTypeRepository.count() == 0) {
            vehicleTypeRepository.save(VehicleType.builder().name("Car").build());
            vehicleTypeRepository.save(VehicleType.builder().name("Motorcycle").build());
            vehicleTypeRepository.save(VehicleType.builder().name("Truck").build());

        }
    }
    private void createVehicles() {
        // Implement logic to create and save vehicles
        // For demonstration, let's assume we're creating 5 vehicles
        for (int i = 0; i < 5; i++) {
            Vehicle vehicle = Vehicle.builder()
                    .type(generateRandomVehicleType())
                    .model("Model" + i)
                    .year(random.nextInt(30) + 1990) // Random year between 1990 and 2019
                    .licensePlate(generateRandomLicensePlate())
                    .gearbox("Automatic") // Assuming all vehicles have automatic gearbox for simplicity
                    .displacementCc(random.nextInt(3000) + 1000) // Random displacement between 1000 and 3999 cc
                    .build();
            vehicleRepository.save(vehicle);
        }
    }

    private VehicleType generateRandomVehicleType() {
        List<VehicleType> vehicleTypes = vehicleTypeRepository.findAll();
        return vehicleTypes.get(random.nextInt(vehicleTypes.size()));
    }

    private String generateRandomLicensePlate() {
        StringBuilder licensePlate = new StringBuilder();
        // Generate a random license plate format (e.g., 1234 ABC)
        for (int i = 0; i < 4; i++) {
            licensePlate.append((char) (random.nextInt(26) + 'A'));
        }
        licensePlate.append(' ');
        for (int i = 0; i < 3; i++) {
            licensePlate.append(random.nextInt(10));
        }
        return licensePlate.toString();
    }



    private void createSchedules() {
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};


        for (String day : daysOfWeek) {
            // Create schedule for 10:00 to 14:00
            Schedule morningSchedule = Schedule.builder()
                    .dayOfWeek(day)
                    .starttime("10:00")
                    .endtime("14:00")
                    .date(LocalDate.now())
                    .build();
            scheduleRepository.save(morningSchedule);

            // Create schedule for 17:00 to 22:00
            Schedule eveningSchedule = Schedule.builder()
                    .dayOfWeek(day)
                    .starttime("17:00")
                    .endtime("22:00")
                    .date(LocalDate.now())
                    .build();
            scheduleRepository.save(eveningSchedule);
        }
    }

    private void createClassesAndBookings() {
        List<User> users = userRepository.findAll();
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<Schedule> schedules = scheduleRepository.findAll();

        for (User user : users) {
            Vehicle randomVehicle = getRandomElement(vehicles);
            Schedule randomSchedule = getRandomElement(schedules);

            LocalDate scheduleDate = randomSchedule.getDate();

            Classes classEntity = Classes.builder()
                    .user(user)
                    .vehicle(randomVehicle)
                    .date(scheduleDate)
                    .timeStart(randomSchedule.getStarttime())
                    .timeEnd(randomSchedule.getEndtime())
                    .build();

            Classes savedClass = classesRepository.save(classEntity);

            // Create a booking for each class
            Bookings booking = Bookings.builder()
                    .classes(savedClass)
                    .user(user)
                    .build();

            bookingsRepository.save(booking);
        }
    }

    private <T> T getRandomElement(List<T> list) {

        return list.get(random.nextInt(list.size()));
    }

}
