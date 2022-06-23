Feature: Get users details

  Scenario Outline: View users list
    Given Get Call to user API for users details
    Then Http Response Code "<responseMessage>" is validated
    Examples:
      | responseMessage |
      |      200        |
