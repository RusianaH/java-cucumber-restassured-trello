Feature: Create Card Validation
  As a Trello API user
  I want to create my card safely
  So that the create card endpoint rejects requests with invalid input

  Scenario: Create card with empty name
    Given a request with authorization
    And the request has body params:
      | name   |                          |
      | idList | 6947fb85c9d5ca5b73207c60 |
    When the 'POST' request is sent to '/1/cards' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value |
      | name |                |

  Scenario Outline: Create card with invalid auth
    Given a request with '<auth_condition>' auth condition
    And the request has body params:
      | name   | New Card                 |
      | idList | 6947fb85c9d5ca5b73207c60 |
    When the 'POST' request is sent to '/1/cards' endpoint
    Then the response status code is 401
    And the response body is equal to '<error_message>'
    Examples:
      | auth_condition | error_message  |
      | no_auth        | missing scopes |
      | only_key       | missing scopes |
      | only_token     | invalid key    |