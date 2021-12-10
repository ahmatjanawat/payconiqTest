@regression
@partialUpdateBooking

Feature: partial update booking
  Scenario: sending patch  request
    When User send patch request with end point "/booking"
    Then Status code of partial update booking should be 200
    And  User should be able to see all of updated information of the book
