Feature: Delete user details

  Scenario Outline: Update user info
    Given Delete Call to user API for delete user info
    Then User info delete successfully and "<responseMessage>" is validated
    Examples:
      | responseMessage |
      |     204         |