# Author: Pramod Jayathilaka
# Date: 01/12/2024
# Description: Feature file for testing the apply leave functionality

Feature: Apply Leave
  As a user
  I want to apply for leave
  So that I can request time off successfully

  Background: Successful login with valid credentials
    Given I am on the login screen
    When I enter a valid username "Admin"
    And I enter a valid password "admin123"
    And I click the Login button
    Then I should see the dashboard

  @TestCaseId("UI_TC001")
  Scenario: Successful leave application for three days with all the required data
    When I click the Apply Leave button
    Then I should see the Apply Leave screen
    Given I select the leave type
    And I enter a valid from date
    And I enter a valid to date
    Then I should see the "Partial Days" selector
    And I enter comments as "Test Comment"
    And I click the Apply button
    Then I should see a success message confirming the leave application with title "Success" and message "Successfully Saved"




