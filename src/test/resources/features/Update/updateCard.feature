@sad
Feature: Update Card
  As a Trello API user
  I want to update my card
  So that I can call a single endpoint that will update my card

  Scenario: Update card name
    Given a request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 6947fff5222a1744b68aa471 |
    And the request has query params:
      | name | Updated Name |
    And the request has headers:
      | Content-type| Application/json        |
    When the 'PUT' request is sent to 'UPDATE_CARD_ID' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path    |  expected_value           |
      | name    |  Updated Name             |

  Scenario: Verify card name after update
    Given a request with authorization
    And the request has query params:
      | fields | id,name |
    And the request has path params:
      | name | value                    |
      | id   | 6947fff5222a1744b68aa471 |
    When the 'GET' request is sent to 'UPDATE_CARD_ID' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path    |  expected_value           |
      | name    |  Updated Name             |