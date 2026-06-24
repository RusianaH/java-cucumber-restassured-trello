Feature: Update Card validation
  As a Trello API user
  I want to update my card safely
  So that i want update board endpoint to allow update only with invalid req

  Scenario Outline: card with invalid id
    Given a request without authorization
    And the request has path params:
      | name  | value |
      | id    | <id>  |
    When the 'PUT' request is sent to 'UPDATE_CARD_ID' endpoint
    Then the response status code is <status_code>
    And the response body is equal to '<error_message>'
    Examples:
      | id                        | error_message                         | status_code |
      |6107c3c8772cbe6e3734fc31   | The requested resource was not found. | 404         |
      |invalid                    | invalid id                            | 400         |


  Scenario Outline:  card with invalid auth
    Given a request without authorization
    And the request has query params:
      | key   | <key>   |
      | token | <token> |
    And the request has path params:
      |name       | value                     |
      |id         | 6947fc42b45c789bd50ef244 |
    When the 'PUT' request is sent to 'UPDATE_CARD_ID' endpoint
    Then the response status code is 401
    And  the response body is equal to '<error_message>'
  Examples:
  | key                | token                  | error_message                     |
  | empty_value        | current_user_token     | invalid key                       |
  | current_user_key   | empty_value            | missing scopes                    |
  | another_user_key   | another_user_token     | unauthorized card permission requested |






