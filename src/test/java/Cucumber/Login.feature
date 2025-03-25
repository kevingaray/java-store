Feature: login validation

  Background:
    Given I landed on Ecommerce Page


  @ErrorValidation
  Scenario Outline: Validation of credentials
    When  Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | username         | password   |
      | kevger@gmail.com | WrongPassw |
