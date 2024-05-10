package com.example.franonwheels;

import com.example.franonwheels.model.domain.Bookings;
import com.example.franonwheels.model.domain.Classes;
import com.example.franonwheels.model.domain.Role;
import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.domain.Vehicle;
import com.example.franonwheels.model.domain.VehicleType;
import com.example.franonwheels.repository.BookingsRepository;
import com.example.franonwheels.repository.ClassesRepository;
import com.example.franonwheels.repository.RoleRepository;
import com.example.franonwheels.repository.UserRepository;
import com.example.franonwheels.repository.VehicleRepository;
import com.example.franonwheels.repository.VehicleTypeRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;


@Component
public class DataLoader {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ClassesRepository classesRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final BookingsRepository bookingsRepository;

    private final Random random = new Random();


    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, ClassesRepository classesRepository,
                      VehicleRepository vehicleRepository, VehicleTypeRepository vehicleTypeRepository,
                       BookingsRepository bookingsRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.classesRepository = classesRepository;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.bookingsRepository = bookingsRepository;
    }


    @PostConstruct
    public void init() {
        // Create roles and specialities if they do not exist
        createRoles();
        // Insert vehicle types if the repository is empty
        createVehicleTypes();
        // Insert users if the repository is empty
        createUsers();
        // Insert vehicles if the repository is empty
        createVehicles();
        // Insert classes if the repository is empty
        createClassesAndBookings();
    }



//      Creates and saves roles if they do not exist in the database.

    private void createRoles() {
        // Check if roles already exist in the database
        if (roleRepository.count() == 0) {
            roleRepository.save((Role.builder().name("USER").active(1).build()));  // Save ROLE_USER
            roleRepository.save((Role.builder().name("ADMIN").active(1).build())); // Save ROLE_ADMIN
        }
    }

//
//      Creates and saves specialities if they do not exist in the database.
//



//      Inserts users into the database with specified roles and specialities.
//      Two users are inserted, one with ROLE_USER and Speciality A1, and another with ROLE_ADMIN and Speciality A2.

    private void createUsers() {
        List<Role> roles = roleRepository.findAll();

        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .active(1)
                    .username("username" + i)
                    .name("User" + i)
                    .lastName("LastName" + i)
                    .dni(generateRandomDNI())
                    .email("user" + i + "@example.com")
                    .address("Address " + i)
                    .age(i)
                    .password("pass" + i)
                    .phoneNumber(generateRandomPhoneNumber())
                    .role(roles.get(random.nextInt(roles.size())))
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
            vehicleTypeRepository.save(VehicleType.builder().name("Car").active(1).build());
            vehicleTypeRepository.save(VehicleType.builder().name("Motorcycle").active(1).build());
            vehicleTypeRepository.save(VehicleType.builder().name("Truck").active(1).build());

        }
    }
    private void createVehicles() {

        for (int i = 0; i < 5; i++) {
            Vehicle vehicle = Vehicle.builder()
                    .type(generateRandomVehicleType())
                    .model("Model" + i)
                    .year(random.nextInt(30) + 1990) // Random year between 1990 and 2019
                    .licensePlate(generateRandomLicensePlate())
                    .gearbox("Automatic") // Assuming all vehicles have automatic gearbox for simplicity
                    .displacementCc(random.nextInt(3000) + 1000) // Random displacement between 1000 and 3999 cc
                    .active(1)
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
    

    private void createClassesAndBookings() {
        List<User> users = userRepository.findAll();
        List<Vehicle> vehicles = vehicleRepository.findAll();
        Random random = new Random();

        for (User user : users) {
            LocalDate randomDate;
            DayOfWeek randomDayOfWeek;

            // Generate a random date until it's not a Saturday or Sunday
            do {
                // Generate a random date within a range, for example, the last 30 days
                randomDate = LocalDate.now().minusDays(random.nextInt(30));
                randomDayOfWeek = randomDate.getDayOfWeek();
            } while (randomDayOfWeek == DayOfWeek.SATURDAY || randomDayOfWeek == DayOfWeek.SUNDAY);

            Vehicle randomVehicle = getRandomElement(vehicles);

            // Randomly select an hour within the morning range (10:00 - 14:00)
            LocalTime morningStartTime = LocalTime.of(10, 0);
            LocalTime morningEndTime = LocalTime.of(14, 0);
            LocalTime morningRandomTime = morningStartTime.plusHours(random.nextInt(4));

            // Randomly select an hour within the evening range (17:00 - 22:00)
            LocalTime eveningStartTime = LocalTime.of(17, 0);
            LocalTime eveningEndTime = LocalTime.of(22, 0);
            LocalTime eveningRandomTime = eveningStartTime.plusHours(random.nextInt(5));

            // Create a class for the user with the random morning time
            Classes morningClassEntity = Classes.builder()
                    .user(user)
                    .vehicle(randomVehicle)
                    .date(randomDate)
                    .timeStart(morningRandomTime.toString())
                    .timeEnd(morningRandomTime.plusHours(1).toString()) // End time is one hour later
                    .active(1)
                    .dayOfWeek(randomDayOfWeek.toString())
                    .build();

            Classes savedMorningClass = classesRepository.save(morningClassEntity);

            // Create a booking for the morning class
            Bookings morningBooking = Bookings.builder()
                    .classes(savedMorningClass)
                    .user(user)
                    .active(1)
                    .build();

            bookingsRepository.save(morningBooking);

            // Create a class for the user with the random evening time
            Classes eveningClassEntity = Classes.builder()
                    .user(user)
                    .vehicle(randomVehicle)
                    .date(randomDate)
                    .timeStart(eveningRandomTime.toString())
                    .timeEnd(eveningRandomTime.plusHours(1).toString()) // End time is one hour later
                    .active(1)
                    .dayOfWeek(randomDayOfWeek.toString())
                    .build();

            Classes savedEveningClass = classesRepository.save(eveningClassEntity);

            // Create a booking for the evening class
            Bookings eveningBooking = Bookings.builder()
                    .classes(savedEveningClass)
                    .user(user)
                    .active(1)
                    .build();

            bookingsRepository.save(eveningBooking);
        }
    }





    private <T> T getRandomElement(List<T> list) {

        return list.get(random.nextInt(list.size()));
    }

}
