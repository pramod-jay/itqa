Feature: Add qualifications
  As a user
  I want to add my qualifications

  Background: Successful login with valid credentials and navigate to add work experience
    Given I am on the login screen
    When I enter a valid username "Admin"
    And I enter a valid password "admin123"
    And I click the Login button
    Then I should see the dashboard
    When I click the My info button
    Then I should see the personal info screen
    When I click the Qualifications button
    Then I should see the Qualifications screen
    When I click work experience add button
    Then I should see the work experience adding section

  Scenario Outline: Successful add work experience with all required data
    Then I enter "ABC company" in the company field
    And I enter "Software engineer" in the job title field
    And I click date in the from date calender
    And I click date in the to date calender
    And I enter comments as "Qualifications comment"
    When I click the Qualifications Save button
    Then I should see a message with title "<title>" and message "<message>"

    Examples:
      | title   | message            |
      | Success | Successfully Saved |

  Scenario: Unsuccessful work experience adding without required fields
    When I click the Qualifications Save button
    Then I should see the "Required" error below the required fields

