@regression
@getBookingIds
Feature: GetBookingIds
Scenario: verify booking id
  When User send get request with end point "/booking"
  Then Status code get booking ids should be 200
  And  Each booking id should be unique



