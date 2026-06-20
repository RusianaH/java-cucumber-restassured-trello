Feature: Delete card Validation
  As a Trello API user
  I want to Delete my card safely
  So that I want to delete card endpoint to allow create only with valid request

  Scenario Outline: Delete card Validation
    Given a request with authorization
    And the request has path params:
      | name      | value     |
      | id        | <id>       |
    When the 'DELETE' request is sent to 'DELETE_CARD_URL' endpoint
    Then the response status code is <status_code>
    And the response body is equal to '<error_message>'
    Examples:
      | id                         | status_code             | error_message                         |
      | invalid                    | 400                     | invalid id                            |
      | 60d847d9aad2437cb984f8e1   | 404                     | The requested resource was not found. |

  Scenario Outline: Check Delete card With Invalid Auth
    Given a request with '<auth_condition>' auth condition
    And the request has path params:
      |   name    | value                        |
      |   id      |6947fc42b45c789bd50ef244      |
    When the 'DELETE' request is sent to 'DELETE_CARD_URL' endpoint
    Then the response status code is 401
    And the response body is equal to '<error_message>'
    Examples:
      | auth_condition | error_message                     |
      | no_auth        | missing scopes                    |
      | only_key       | missing scopes                    |
      | only_token     | invalid key                       |
      | another_user   | unauthorized card permission requested |














