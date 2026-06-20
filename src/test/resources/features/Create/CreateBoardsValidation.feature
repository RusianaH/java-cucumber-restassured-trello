Feature: Create Board Validation
  As a Trello API user
  I want to create my board safely
  So that I want to create board endpoint to allow create only with valid request

  Scenario: Check board invalid id
    Given a request with authorization
    And the request has body params:
      | name |          |
    When the 'POST' request is sent to 'UPDATE_A_BOARD' endpoint
    Then the response status code is 400
    And body value has the following values by paths:
      | path    | expected_value         |
      | message | invalid value for name |
      | error   | ERROR                  |

  Scenario Outline: Create board with invalid auth
    Given a request with '<auth_condition>' auth condition
    And the request has body params:
      | name | New Board |
    When the 'POST' request is sent to 'UPDATE_A_BOARD' endpoint
    Then the response status code is 401
    And the response body is equal to '<error_message>'
    Examples:
      | auth_condition | error_message  |
      | no_auth        | missing scopes |
      | only_key       | missing scopes |
      | only_token     | invalid key    |