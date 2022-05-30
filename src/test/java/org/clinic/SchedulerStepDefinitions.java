package org.clinic;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class SchedulerStepDefinitions {
    @When("Operator requests to reserve a visit for Patient {string} in a {string} Clinic Room")
    public void operatorRequestsToReserveAVisitForPatientInAClinicRoom(String patientId, String clinicRoomNumber) {

    }

    @Then("the system make Reservation as follows:")
    public void theSystemMakeReservationAFollows(List<Map<String, String>> table) {
    }
}
