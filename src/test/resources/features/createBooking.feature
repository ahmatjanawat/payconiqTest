@createBooking
@regression
Feature: create booking

  Scenario: sending post request
    When User send post request with end point "/booking"
    Then Status code of create booking  should be 200
    And  User should be able to see all of created information of the book
