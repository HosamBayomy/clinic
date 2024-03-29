Feature: Schedule a visit for a patient

  Background:

    Given Today is "28/05/2022" and current time is "10:00:00 AM" with time zone "GMT+2"
    And Clinic Rooms available are as follows:
      | Room Number | Medical Specialization | Day Capacity | Available Days | Start Time | Average Patient Minutes | Physician |
      | 1           | Pediatric              | 10           | Sat, Mon, Wed  | 03:00 PM   | 15                      | Sherif    |
      | 2           | Cardiology             | 6            | Sat, Sun, Mon  | 07:00 PM   | 30                      | Yasser    |
      | 3           | Nephrology             | 6            | Mon, Tue, Thu  | 06:00 PM   | 30                      | Ahmed     |
    And Last reserved date for each Clinic Room is as follows:
      | Room Number | Reserved Date | Patient Order In Queue | Physician |
      | 1           | 28/05/2022    | 7                      | Sherif    |
      | 2           | 28/05/2022    | 6                      | Yasser    |
      | 3           | 30/05/2022    | 3                      | Ahmed     |


  Scenario: Schedule a visit for a patient
    Given Operator is logged in
    When  Operator requests to reserve a visit for Patient "20220030" in a "Pediatric" Clinic Room with physician "Ahmed"
    Then  the system make Reservation as follows:
      | Patient  | Date       | Order In Queue | Expected Entrance Time | Physician |
      | 20220023 | 28/05/2022 | 8              | 05:45 PM               | Sherif    |
