//package com.example.franonwheels.api.repository;
//
//import com.example.franonwheels.model.domain.Bookings;
//import com.example.franonwheels.model.domain.Classes;
//import com.example.franonwheels.model.domain.User;
//import com.example.franonwheels.model.domain.Vehicle;
//import com.example.franonwheels.repository.BookingsRepository;
//import com.example.franonwheels.service.impl.BookingsServiceImpl;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Arrays;
//import java.util.List;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ExtendWith(MockitoExtension.class)
//
//public class BookingsServiceUnitTest {
//
//    private final Vehicle vehicle1 = Vehicle.builder()
//            .id(1L)
//            .build();
//
//    private final User user1 = User.builder()
//            .id(1L)
//            .build();
//
//    private final Classes class1 = Classes.builder()
//            .id(1L)
//            .vehicle(vehicle1)
//            .user(user1)
//            .build();
//
//
//
//
////    private final List<Bookings> bookingsList = Arrays.asList(
////            Bookings.builder()
////                    .id(1L)
////                    .classes(class1)
////                    .build());
//
//    private final Bookings book = Bookings.builder()
//            .id(1L)
//            .classes(class1)
//            .user(user1)
//            .build();
//
//    @Mock
//    private BookingsRepository bookingsRepositoryMock;
//
//    @InjectMocks
//    private BookingsServiceImpl bookingsServiceImplMock;
//
//    @BeforeAll
//    public static void beforeAll() {
//        MockitoAnnotations.openMocks(BookingsServiceUnitTest.class);
//    }
//
//    @Test
//    public void BookingCreated_thenReturnSavedBooking(){
//
//        Mockito.when(this.bookingsRepositoryMock.save(Mockito.any(Bookings.class))).thenReturn(book);
//
//        this.bookingsServiceImplMock.createBooking(book);
//
//        Mockito.verify(this.bookingsRepositoryMock, Mockito.times(1)).save(Mockito.any(Bookings.class));
//
//
//    }
//
//}
