Feature: Add qualifications
  As a user
  I want to add my qualifications

  Scenario: Successful login with valid credentials
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
