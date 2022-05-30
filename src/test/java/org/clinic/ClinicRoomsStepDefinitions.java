package org.clinic;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.util.List;
import java.util.Map;

public class ClinicRoomsStepDefinitions {
    @Given("Clinic Rooms available are as follows:")
    public void clinicRoomsAvailableAreAsFollows(List<Map<String, String>> table) {
        
    }

    @And("Last reserved date for each Clinic Room is as follows:")
    public void lastReservedDateForEachClinicRoomIsAsFollows(List<Map<String, String>> table) {
    }
}
