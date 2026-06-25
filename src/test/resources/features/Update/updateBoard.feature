@happy
Feature: update Board
  As a trello API user
  I want to update my board
  So that i want to call a single endpoint that will update my board

  Scenario: Check Update Board
    #Upadate board
  Given a request with authorization
    And the request has path params:
      | name   |  value                    |
      | id     |  686f5ddb7fbbd86a580ce442 |
    And the request has query params:
      | name | Updated Name |
    When the 'PUT' request is sent to 'UPDATE_A_BOARD' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path    |  expected_value           |
      | name    |  Updated Name             |

    #check board name updated
  Scenario: Verify board name after update
  Given a request with authorization
    And the request has query params:
      |fields  |  id,name     |
    And the request has path params:
      |name    |  value                      |
      | id     |  686f5ddb7fbbd86a580ce442   |
    When the 'GET' request is sent to 'UPDATE_A_BOARD' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path    |  expected_value           |
      | name    |  Updated Name             |


