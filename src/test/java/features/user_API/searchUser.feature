Feature: Search for a user

  Scenario Outline: user search by user id
    Given Get Call to user API with specific user id
    Then user id "<id>" is validated
    And user name "<name>" is validated
    Examples:
      |  id  |     name    |
      | 3396 | Sakib Hasan |
