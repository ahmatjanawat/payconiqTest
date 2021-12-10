@regression
@getBookingById

Feature: Get booking by id

  Scenario: sending get  request by valid id
    When User send get  request with valid bookingId
    Then Status code get booking by id should be 200
    And  User should be able to see all the booking information

  Scenario: sending get  request by invalid id
    When User send get  request with invalid  bookingId
    Then Status code should be 404
    And  User should   be able to see "Not Found" message