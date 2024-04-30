package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.Bookings;
import com.example.franonwheels.model.domain.Classes;
import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.dtos.BookingsDTO;

public class BookingsMapper {

    public static BookingsDTO BookingstoDTO(Bookings bookings) {

        if (bookings == null) {

            return null;

        }
        return BookingsDTO.builder()
                .id(bookings.getId())
                .classId(bookings.getClasses().getId())
                .userId(bookings.getUser().getId())
                .active(bookings.getActive())
                .build();

    }

    public static Bookings BookingsDTOtoEntity(BookingsDTO bookingsDTO) {

        if (bookingsDTO == null) {

            return null;

        }
        return Bookings.builder()
                .id(bookingsDTO.getId())
                .active(bookingsDTO.getActive())
                .classes(Classes.builder()
                .id(bookingsDTO.getClassId())
                .active(bookingsDTO.getActive())
                .build())
                .user(User.builder()
                .id(bookingsDTO.getUserId())
                .active(bookingsDTO.getActive())
                .build())
                .build();
    }
}
