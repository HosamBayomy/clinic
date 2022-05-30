package org.clinic.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ClinicRoom {
    private Integer roomNumber;
    private String medicalSpecialization;
    private Integer dayCapacity;
    private List<DayOfWeek> availableDays;
    private LocalTime startTime;
    private Integer averagePatientMinutes;

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public String getMedicalSpecialization() {
        return medicalSpecialization;
    }

    public Integer getDayCapacity() {
        return dayCapacity;
    }

    public List<DayOfWeek> getAvailableDays() {
        return availableDays;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public Integer getAveragePatientMinutes() {
        return averagePatientMinutes;
    }

    public ClinicRoom(Integer roomNumber, String medicalSpecialization, Integer dayCapacity, List<DayOfWeek> availableDays,
                      LocalTime startTime, Integer averagePatientMinutes) {
        this.roomNumber = roomNumber;
        this.medicalSpecialization = medicalSpecialization;
        this.dayCapacity = dayCapacity;
        this.availableDays = new ArrayList<>(availableDays);
        this.startTime = LocalTime.from(startTime);
        this.averagePatientMinutes = averagePatientMinutes;
    }

    @Override
    public boolean equals(Object anotherObject){
        ClinicRoom clinicRoom = (ClinicRoom) anotherObject;
        return roomNumber.equals(clinicRoom.roomNumber) &&
                medicalSpecialization.equals(clinicRoom.medicalSpecialization);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[")
                .append("RoomNumber=")
                .append(roomNumber)
                .append(",")
                .append("MedicalSpecialization=")
                .append(medicalSpecialization)
                .append("]");
        return builder.toString();
    }

}
