Feature: Update user details

  Scenario Outline: Update user info PUT Method
    Given Put Call to user API for update user info
    Then User info update successfully and "<responseMessage>" is validated
    Examples:
      | responseMessage |
      |     200         |


  Scenario Outline: Update user info PATCH Method
    Given Patch Call to user API for update user info
    Then User info update successfully and "<responseMessage>" is validated
    Examples:
      | responseMessage |
      |     200         |

