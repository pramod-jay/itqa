# Author Pramod Jayathilaka
# Date 01/12/2024
# Description test cases for login page
# Feature file for test login

Feature: loginSteps
  As a user
  I want to log in to the dashboard
  So that I can access my account

  Scenario: Successful login with valid credentials
    Given  I am on the log in page
    When I enter a valid username
    And I enter a valid password
    And I click the "Log in" button
    Then I should be visible "Accounts Overview" section


