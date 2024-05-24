package com.example.franonwheels.repository;

import com.example.franonwheels.model.domain.Bookings;
import com.example.franonwheels.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Finds users by their role name
    List<User> findByRoleNameIgnoreCaseContaining(@Param("roleName") String roleName);


    // Custom query to find users with bookings on a specific date
    @Query("SELECT DISTINCT u FROM User u INNER JOIN u.bookings b WHERE b.classes.date = :bookingDate")
    List<User> findUsersWithBookingsOnDate(@Param("bookingDate") LocalDate bookingDate);

    // Custom query to find all bookings for a user based on their DNI
    @Query("SELECT b FROM User u JOIN u.bookings b WHERE u.dni = :dni")
    List<Bookings> findBookingsByUserDNI(@Param("dni") String dni);

    // Finds users whose first name contains a specified pattern (case-sensitive)
    List<User> findByNameIgnoreCaseContaining(@Param("Name") String Name);

    // Finds users whose age is greater than the specified age
    List<User> findByAgeGreaterThan(@Param("Age") Integer Age);

    // Finds users whose age is less than the specified age
    List<User> findByAgeLessThan(@Param("Age") Integer Age);

    List<User> findByRoleId(Long id);

    List<User> findByActive(Integer active);

    User findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);

    Optional<User> findByEmail(String email);
}
