# Author Pramod Jayathilaka
# Date 01/12/2024
# Description test cases for login page
# Feature file for test login

Feature: loginSteps
  Feature to test login functionality

  Scenario: Check login is successful with valid credentials
    Given  user is on login page
    When user enters username and password
    And clicks on login button
    Then user is navigated to the home page
