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
    And I click the Log in button
    Then I should be visible Accounts Overview section

  Scenario Outline: Unsuccessful login with invalid credentials
    Given I am on the log in page
    When I enter an invalid username "<username>"
    And I enter an invalid password "<password>"
    And I click the Log in button
    Then I should be visible 'The username and password could not be verified.' error message

    # 1: Invalid password with valid userName
    # 2: Invalid username with valid password
    # 3: Invalid username and password
    Examples:
      | username    | password   |
      | testUser123 | Test@12345 |
      | testUser    | Test@123   |
      | testUser    | Test@12345 |



