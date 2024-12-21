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
    When I click the Apply Leave button
    Then I should see the Apply Leave screen

  @TestCaseId("UI_TC001")
  Scenario Outline: Successful leave application for three days with all the required data
    Then I select the leave type
    And I enter a valid from date
    And I enter a valid to date
    Then I should see the "Partial Days" selector
    And I enter comments as "Test Comment"
    Then I click the Apply button
    Then I should see a message with title "<title>" and message "<message>"

    Examples:
      | title   | message            |
      | Success | Successfully Saved |

  @TestCaseId("UI_TC002")
  Scenario: Unsuccessful leave application without required data
    Then I click the Apply button
    Then I should see a "Required" error message below all the required fields

  @TestCaseId("UI_TC003")
  Scenario Outline: Unsuccessful leave application with non-working days
    Then I select the leave type
    And I enter a weekend date as the from date
    And I enter a weekend date as the to date
    Then I click the Apply button
    Then I should see a message with title "<title>" and message "<message>"

    Examples:
      | title | message                                    |
      | Error | Failed to Submit: No Working Days Selected |

  @TestCaseId("UI_TC004")
  Scenario: Successful leave application on a day









