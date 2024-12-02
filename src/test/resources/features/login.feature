# Author Pramod Jayathilaka
# Date 01/12/2024
# Description Feature file for test login functionality

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
    Then I should be visible "The username and password could not be verified." error message

    # 1: Invalid password with valid userName
    # 2: Invalid username with valid password
    # 3: Invalid username and password
    Examples:
      | username    | password   |
      | testUser123 | Test@12345 |
      | testUser    | Test@123   |
      | testUser    | Test@12345 |

  Scenario Outline: Unsuccessful login with empty fields
    Given I am on the log in page
    When I enter a username "<username>"
    And I enter a password "<password>"
    And I click the Log in button
    Then I should be visible "Please enter a username and password." error message

    # 1: Password with empty username
    # 2: Username with empty password
    # 3: Empty username and empty password
    Examples:
      | username    | password  |
      |             | Test@123  |
      | testUser123 |           |
      |             |           |

