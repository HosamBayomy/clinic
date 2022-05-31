package org.clinic.scheduler;

import org.clinic.model.ClinicRoom;
import org.clinic.model.Patient;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;


public class Scheduler {

    private static final Logger LOG = Logger.getLogger(Scheduler.class.getName());
    private Patient patient;
    private ClinicRoom clinicRoom;
    private final ArrayDeque<Reservation> clinicQueue;
    private Clock schedulerClock;

    public Scheduler(Clock clock) {
        schedulerClock = clock;
        clinicQueue = new ArrayDeque<>();
    }

    public Scheduler(Clock clock, Deque<Reservation> reservations){
        schedulerClock = clock;
        clinicQueue = new ArrayDeque<>(reservations);
    }

    public Scheduler forPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public Scheduler inRoom(ClinicRoom clinicRoom) {
        this.clinicRoom = clinicRoom;
        return this;
    }

    public Reservation scheduleVisit() {
        int orderInQueue = getLastReservationOfClinicQueueCurrentCapacity() % (clinicRoom.getDayCapacity()) + 1;
        int minutesOfWaiting = (orderInQueue - 1) * clinicRoom.getAveragePatientMinutes();
        LocalDate reservationStartingFromDate = getSchedulerStartingFromDate();
        Reservation reservation =  new Reservation(patient.getId(), clinicRoom.getRoomNumber(),
                scheduleForPatientInClinicFromDay(reservationStartingFromDate),
                orderInQueue, clinicRoom.getStartTime().plusMinutes(minutesOfWaiting));
        clinicQueue.add(reservation);
        return reservation;
    }

    private LocalDate getSchedulerStartingFromDate() {
        return clinicDayIsFullCapacity() ?
                getVisitDate(clinicQueue.getLast()).plusDays(1) :
                LocalDate.now(schedulerClock);
    }

    private boolean clinicDayIsFullCapacity() {
        return getLastReservationOfClinicQueueCurrentCapacity() == clinicRoom.getDayCapacity();
    }

    private LocalDate scheduleForPatientInClinicFromDay(LocalDate reservationDate) {
        LocalDate returnableDate = reservationDate;
        while (todayIsNotOneOfClinicAvailableDays(returnableDate)) {
            returnableDate = returnableDate.plusDays(1);
        }
        LOG.fine(returnableDate.format(DateTimeFormatter.BASIC_ISO_DATE));
        return returnableDate;
    }

    private boolean todayIsNotOneOfClinicAvailableDays(LocalDate reservationDate) {
        return ! clinicRoom.getAvailableDays().contains(reservationDate.getDayOfWeek());
    }

    private LocalDate getVisitDate(Reservation lastReservation) {
        return lastReservation.getVisitDate();
    }

    private int getLastReservationOfClinicQueueCurrentCapacity(){
        int capacity = 0;
        if (!clinicQueue.isEmpty()){
            capacity = getLastReservationOfClinicQueue().getOrderInQueue();
        }
        return capacity;
    }

    private Reservation getLastReservationOfClinicQueue() {
        return clinicQueue.getLast();
    }

}
