Feature: Add Admin User
  As a admin
  I want to add new admin user to system
  So that then I can add more admins to the system


  Background: Successful login with valid credentials
    Given I am on the login screen
    When I enter a valid username "Admin"
    And I enter a valid password "admin123"
    And I click the Login button
    Then I should see the dashboard
    When I click the admin button
    Then I should see the user management screen
    When I click the add button
    Then I should see the Add User Form


  @TestCaseId("UI_TC010")
  Scenario Outline: Successful user creation with all required fields filled correctly
    Then I select a user role from the dropdown
    And I select a status from the dropdown
    And I type "Ranga Akunuri" in the employee name field
    And I enter "Rang454e5" in the user name field
    And I enter "Password123!" in the password field
    And I enter "Password123!" in the confirm password field
    Then I click the Save button
    Then I should see a message with title "<title>" and message "<message>"

    Examples:
      | title   | message            |
      | Success | Successfully Saved |


  @TestCaseId("UI_TC003")
  Scenario Outline: Validation error when required fields are left empty
    Then I leave the user role field empty
    And I leave the status field empty
    And I leave the employee name field empty
    And I leave the user name field empty
    And I leave the password field empty
    And I leave the confirm password field empty
    When I click the Save button
    Then I should see validation errors for all fields with the message "<message>"

    Examples:
      | message            |
      | Required           |

#
#
#  @TestCaseId("UI_TC004")
#  Scenario Outline: Error message displayed when passwords do not match
#    Then I select a "user role" from the dropdown
#    And I select a "status" from the dropdown
#    And I type "John Doe" in the employee name field
#    And I enter "johndoe" in the user name field
#    And I enter "Password123!" in the password field
#    And I enter "Password456!" in the confirm password field
#    When I click the Save button
#    Then I should see a password mismatch error with message "<message>"
#
#    Examples:
#      | message                 |
#      | The passwords do not match |
#
#
#  @TestCaseId("UI_TC005")
#  Scenario Outline: Validation error for weak password
#    Given I select a "user role" from the dropdown
#    And I select a "status" from the dropdown
#    And I type "John Doe" in the employee name field
#    And I enter "johndoe" in the user name field
#    And I enter "<weak_password>" in the password field
#    And I confirm the password by entering "<weak_password>" in the confirm password field
#    When I click the Save button
#    Then I should see a password strength error with message "<message>"
#
#    Examples:
#      | weak_password | message                          |
#      | 123456        | Password is too weak             |
#      | password      | Password must contain symbols    |
#      | abc123        | Password must have uppercase     |
#      | 111111        | Password is too common           |
#
#
