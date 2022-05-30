package org.clinic.scheduler;

import org.clinic.model.ClinicRoom;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private Integer patientId;
    private Integer clinicRoomNumber;
    private LocalDate visitDate;
    private Integer orderInQueue;
    private LocalTime expectedEntranceTime;

    public Integer getPatientId() {
        return patientId;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public Integer getOrderInQueue() {
        return orderInQueue;
    }

    public LocalTime getExpectedEntranceTime() {
        return expectedEntranceTime;
    }

    public Integer getClinicRoomNumber(){
        return clinicRoomNumber;
    }

    public Reservation(Integer patientId, Integer clinicRoomNumber, LocalDate visitDate, Integer orderInQueue,
                       LocalTime expectedEntranceTime) {
        this.patientId = patientId;
        this.clinicRoomNumber = clinicRoomNumber;
        this.visitDate = LocalDate.from(visitDate);
        this.orderInQueue = orderInQueue;
        this.expectedEntranceTime = LocalTime.from(expectedEntranceTime);
    }

    @Override
    public boolean equals(Object anotherOne){
        Reservation reservation = (Reservation) anotherOne;
        return patientId.equals(reservation.getPatientId()) &&
                clinicRoomNumber.equals(reservation.getClinicRoomNumber()) &&
                visitDate.equals(reservation.getVisitDate()) &&
                orderInQueue.equals(reservation.getOrderInQueue()) &&
                expectedEntranceTime.equals(reservation.getExpectedEntranceTime());
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[")
                .append("PatientID=")
                .append(patientId)
                .append(",")
                .append("ClinicRoomNumber=")
                .append(clinicRoomNumber)
                .append(",")
                .append("VisitDate=")
                .append(visitDate)
                .append(",")
                .append("OrderInQueue=")
                .append(orderInQueue)
                .append(",")
                .append("ExpectedEntranceTime=")
                .append(expectedEntranceTime)
                .append("]");
        return builder.toString();
    }
}
