# Author: Nathali Fernando
# Date: 27/12/2024
# Description: Feature file for testing Claim functionality

Feature: Claim
  As a user
  I want to claim my allowance
  So that I can get my allowance approved

  Scenario: Successful login with valid credentials
    Given I am on the login screen
    When I enter a valid username "Admin"
    And I enter a valid password "admin123"
    And I click the Login button
    Then I should see the dashboard
    When I click the Claim button
    Then I should see the Claim screen


#  @TestCaseId("UI_TC001")
#  Scenario Outline: Successful leave application for three days with all the required data
#    Then I select the leave type
#    And I enter a "today" as the from date
#    And I enter a "day_after_tomorrow" as the to date
#    Then I should see the Partial Days selector
#    And I enter comments as "Leave Comment"
#    Then I click the Apply button
#    Then I should see a message with title "<title>" and message "<message>"
#
#    Examples:
#      | title   | message            |
#      | Success | Successfully Saved |

