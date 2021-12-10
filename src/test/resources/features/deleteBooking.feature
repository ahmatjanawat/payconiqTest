@regression
@deleteBooking


Feature: delete booking

  Scenario: sending delete  request
    When User send delete request with end point
    Then status code of delete booking  should be 201
    And  User should be able to see "Created" message
