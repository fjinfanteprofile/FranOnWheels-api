package com.example.franonwheels.repository;

import com.example.franonwheels.model.domain.Bookings;
import com.example.franonwheels.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Finds users by their role name
    List<User> findByRoleName(@Param("roleName") String roleName);

    // Finds a user by their last name and first name
    User findByLastNameAndName(@Param("Name") String Name, @Param("lastName") String lastName);

    // Finds users by their first name (case-sensitive)
    User findByNameEquals(@Param("Name") String Name);

    // Finds users by their speciality name
    List<User> findUsersBySpecialityName(@Param("specialityName") String specialityName);

    // Custom query to find users with bookings on a specific date
    @Query("SELECT DISTINCT u FROM User u INNER JOIN u.bookings b WHERE b.classes.date = :bookingDate")
    List<User> findUsersWithBookingsOnDate(@Param("bookingDate") LocalDate bookingDate);

    // Custom query to find all bookings for a user based on their DNI
    @Query("SELECT b FROM User u JOIN u.bookings b WHERE u.dni = :dni")
    List<Bookings> findBookingsByUserDNI(@Param("dni") String dni);

    // Custom query to find all bookings for a user based on their DNI and name (like clause)
    @Query("SELECT b FROM User u JOIN u.bookings b WHERE u.dni = :dni AND u.name LIKE %:name%")
    List<Bookings> findBookingsByUserDNIAndNameLike(@Param("dni") String dni, @Param("name") String name);

    // Finds users whose first name contains a specified pattern (case-sensitive)
    List<User> findByNameIgnoreCaseContaining(@Param("Name") String Name);

    // Finds users whose age is less than the specified age
    List<User> findByAgeLessThan(@Param("Age") Integer Age);

    // Finds users whose age is greater than the specified age
    List<User> findByAgeGreaterThan(@Param("Age") Integer Age);

    // Finds users whose last name starts with the specified prefix using LIKE
    List<User> findByLastNameLike(@Param("Prefix") String Prefix);

    // Finds users whose address contains the specified keyword using LIKE
    List<User> findByAddressLike(@Param("Keyword") String Keyword);
}
