Feature: Create a new user

  Scenario Outline: Create a new user with user info
    Given Post Call to user API for create new user
    Then User created successfully and "<responseMessage>" is validated
    Examples:
      | responseMessage |
      |     201         |