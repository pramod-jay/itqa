# Author: Erandi Malshika
# Date: 29/12/2024
# Description: Feature file for testing add candidate functionality

Feature: Add Candidate
  As a admin
  I want to add candidate

  Background: Successful login with valid credentials
    Given I am on the login screen
    When I enter a valid username "Admin"
    And I enter a valid password "admin123"
    And I click the Login button
    Then I should see the dashboard
    When I click the Recruitment button
    Then I should see the Recruitment screen

  @DeleteTest
  @TestCaseId("UI_TC013")
  Scenario: Creating candidate successfully with required data
    Given I navigate to the add candidate
    When I add the candidate details:
      | First Name | Middle Name | Last Name | Email                  | Contact No | Vacancy           | Resume      | Keywords                | Comment             | Application Date |
      | John       | M           | Doe       | john.doe@example.com  | 1234567890 | Software Engineer | resume.pdf  | Java, Selenium, Automation | Strong technical skills | 2024-29-12 |
    Then I should see a confirmation message "Success Successfully Saved"