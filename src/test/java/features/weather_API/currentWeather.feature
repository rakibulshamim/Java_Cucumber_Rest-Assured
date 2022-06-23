Feature:Current weather API

  Scenario Outline: Get All info from current weather
    Given Get Call to current weather API
    Then Response Code "<responseMessage>" is validated
    Examples:
      | responseMessage |
      |     200         |


  Scenario: Get current weather condition info
    Given Get Call to current weather API with language parameter
    Then Current weather condition text is validated


  Scenario Outline: Get current weather Country info
    Given Get Call to current weather API
    Then Country name "<country>" is validated
    And Response Code "<responseMessage>" is validated
    Examples:
      |  country   |  responseMessage |
      | Bangladesh |         200      |


  Scenario Outline: Get current weather last update date and time
    Given Get Call to current weather API
    When Current date and time are displayed
    Then location name "<name>" is validated
    Examples:
      |  name  |
      | Dhaka  |


