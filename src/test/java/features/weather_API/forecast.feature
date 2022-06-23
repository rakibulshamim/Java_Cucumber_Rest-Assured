Feature:Weather Forecast API

  Scenario Outline: Get All info from forecast API
    Given Get Call to forecast API
    Then Response code "<responseMessage>" is validated
    Examples:
      | responseMessage |
      |     200         |

  Scenario: Get alert info from forecast API
    Given Get Call to forecast API with alerts parameter
    Then weather alerts are validated

  Scenario Outline: Get forecast date info from forecast API
    Given Get Call to forecast API
    Then forecast "<date>" is validated
    Examples:
      |    date    |
      | 2022-06-23 |


  Scenario: Get forecast hour info from forecast API
    Given Get Call to forecast API
    Then forecast hour is validated


  Scenario: Get forecast sunset time info from forecast API
    Given Get Call to forecast API
    Then sunset time is validated