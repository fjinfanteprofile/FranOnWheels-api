package com.example.demo.repository;

import com.example.demo.model.domain.Bookings;
import com.example.demo.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    // Finds users by their role name
    List<User> findByRoleName(String roleName);

    // Finds a user by their last name and first name
    User findByLastNameAndName(String Name, String firstName);

    // Finds users by their first name (case-sensitive)
    User findByNameEquals(String Name);

    // Finds users by their speciality name
    List<User> findUsersBySpecialityName(String specialityName);

    // Custom query to find users with bookings on a specific date
    @Query("SELECT DISTINCT u FROM User u INNER JOIN u.bookings b WHERE b.classes.date = :bookingDate")
    List<User> findUsersWithBookingsOnDate(LocalDate bookingDate);

    // Custom query to find all bookings for a user based on their DNI
    @Query("SELECT b FROM User u JOIN u.bookings b WHERE u.dni = :dni")
    List<Bookings> findBookingsByUserDNI(String dni);

    // Custom query to find all bookings for a user based on their DNI and name (like clause)
    @Query("SELECT b FROM User u JOIN u.bookings b WHERE u.dni = :dni AND u.name LIKE %:name%")
    List<Bookings> findBookingsByUserDNIAndNameLike(String dni, String name);

    // Finds users whose first name contains a specified pattern (case-sensitive)
    List<User> findByNameLike(String Name);

    // Finds users whose age is less than the specified age
    List<User> findByAgeLessThan(Integer age);

    // Finds users whose age is greater than the specified age
    List<User> findByAgeGreaterThan(Integer age);

    // Finds users whose last name starts with the specified prefix using LIKE
    List<User> findByLastNameLike(String prefix);

    // Finds users whose address contains the specified keyword using LIKE
    List<User> findByAddressLike(String keyword);


}