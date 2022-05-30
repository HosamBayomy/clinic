package org.clinic.scheduler;


import org.clinic.model.ClinicRoom;
import org.clinic.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SchedulerTest {

    private Clock clock;

    @BeforeEach
    public void setup(){
        clock = Clock.fixed(Instant.parse("2022-05-27T12:00:00.00Z"), ZoneId.of("GMT+2"));
    }

    @DisplayName("Schedule a visit for 1st patient in pediatric clinic room")
    @Test
    void scheduleVisitForFirstPatientInPediatricClinicRoom() {
        //Arrange
        Patient alexBob = new Patient(20220030, "Alex Bob");
        Scheduler scheduler = new Scheduler(clock);

        //Act
        Reservation actualVisit = scheduler
                .forPatient(alexBob)
                .inRoom(pediatricClinicRoom())
                .scheduleVisit();

        //Assert
        assertThat(actualVisit).isEqualTo(expectedVisitForFirstPatientInPediatricClinicRoom());
    }

    @DisplayName("Schedule a visit for 3rd patient in pediatric clinic room")
    @Test
    void scheduleVisitForThirdPatientInPediatricClinicRoom() {
        //Arrange
        Patient alexBob = new Patient(20220030, "Alex Bob");
        Scheduler scheduler = new Scheduler(clock, createTwoReservations());

        //Act
        Reservation actualVisit = scheduler
                .forPatient(alexBob)
                .inRoom(pediatricClinicRoom())
                .scheduleVisit();

        //Assert
        assertThat(actualVisit).isEqualTo(expectedVisitForThirdPatientInPediatricClinicRoom());
    }

    @DisplayName("Schedule a visit for 1st patient next day in pediatric clinic room")
    @Test
    void scheduleVisitForFirstPatientNextDayInPediatricClinicRoom() {
        //Arrange
        Patient alexBob = new Patient(20220030, "Alex Bob");
        Scheduler scheduler = new Scheduler(clock, createTenReservations());

        //Act
        Reservation actualVisit = scheduler
                .forPatient(alexBob)
                .inRoom(pediatricClinicRoom())
                .scheduleVisit();

        //Assert
        assertThat(actualVisit).isEqualTo(expectedVisitForFirstPatientNextDayInPediatricClinicRoom());
    }

    private ArrayDeque<Reservation> createTwoReservations() {
        ArrayDeque<Reservation> reservations = new ArrayDeque<>();
        reservations.add(createVisit(20220020, "28/05/2022", 1, "15:00:00"));
        reservations.add(createVisit(20220021, "28/05/2022", 2, "15:15:00"));
        return reservations;
    }

    private ArrayDeque<Reservation> createTenReservations() {
        ArrayDeque<Reservation> reservations = new ArrayDeque<>();
        reservations.add(createVisit(20220020, "28/05/2022", 1, "15:00:00"));
        reservations.add(createVisit(20220021, "28/05/2022", 2, "15:15:00"));
        reservations.add(createVisit(20220022, "28/05/2022", 3, "15:30:00"));
        reservations.add(createVisit(20220023, "28/05/2022", 4, "15:35:00"));
        reservations.add(createVisit(20220024, "28/05/2022", 5, "16:00:00"));
        reservations.add(createVisit(20220025, "28/05/2022", 6, "16:15:00"));
        reservations.add(createVisit(20220026, "28/05/2022", 7, "16:30:00"));
        reservations.add(createVisit(20220027, "28/05/2022", 8, "16:45:00"));
        reservations.add(createVisit(20220028, "28/05/2022", 9, "17:00:00"));
        reservations.add(createVisit(20220029, "28/05/2022", 10, "17:15:00"));
        return reservations;
    }


    private Reservation expectedVisitForFirstPatientInPediatricClinicRoom() {
        return createVisit(20220030, "28/05/2022", 1, "15:00:00");
    }

    private Reservation expectedVisitForThirdPatientInPediatricClinicRoom() {
        return createVisit(20220030, "28/05/2022", 3, "15:30:00");
    }

    private Reservation expectedVisitForFirstPatientNextDayInPediatricClinicRoom() {
        return createVisit(20220030, "30/05/2022", 1, "15:00:00");
    }

    private Reservation createVisit(int patientId, String visitDate, int orderInQueue,
                                    String expectedEntranceTime) {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new Reservation(patientId, 1,
                LocalDate.parse(visitDate, dateFormatter), orderInQueue,
                LocalTime.parse(expectedEntranceTime, timeFormatter));
    }

    private ClinicRoom pediatricClinicRoom() {
        return new ClinicRoom(1,
                "Pediatric", 10, getAvailableDays(), LocalTime.of(15, 0),
                15);
    }

    private List<DayOfWeek> getAvailableDays() {
        List<DayOfWeek> availableDays = new ArrayList<>();
        availableDays.add(DayOfWeek.SATURDAY);
        availableDays.add(DayOfWeek.MONDAY);
        availableDays.add(DayOfWeek.WEDNESDAY);
        return availableDays;
    }

}
