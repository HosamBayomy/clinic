package org.clinic;

import io.cucumber.java.en.Given;

public class GeneralStepDefinitions {
    @Given("Operator is logged in")
    public void operatorIsLoggedIn() {
    }

    @Given("Today is {string} and current time is {string} with time zone {string}")
    public void todayIsAndCurrentTimeIsWithTimeZone(String date, String time, String zone) {
    }
}
