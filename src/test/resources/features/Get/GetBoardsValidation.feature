Feature: Get Boards validation
  As a Trello API user
  I want to have my board protected
  So that i want to call a single endpoint that will return my board only for me

  Scenario Outline: Check Get Board with Invalid Id
    Given  a request with authorization
    And the request has path params:
    |name   | value     |
    |id     |<id_value> |
    When the 'GET' request is sent to 'GET_A_BOARD' endpoint
    Then the response status code is <status_code>
    And the response body is equal to '<error_message>'
    Examples:
      | id_value                | status_code       | error_message                        |
      | invalid                 | 400               | invalid id                           |
      |60d847d9aad2437cb984f8e1 | 404               | The requested resource was not found.|

  Scenario Outline: Check Get Board with Invalid Auth
    Given a request with '<auth_condition>' auth condition
    And the request has path params:
      | name | value                    |
      | id   | 6947fb6d9cd8d0e89aa127ee |
    When the 'GET' request is sent to 'GET_A_BOARD' endpoint
    Then the response status code is 401
    And the response body is equal to '<error_message>'
    Examples:
      | auth_condition | error_message                     |
      | no_auth        | unauthorized permission requested |
      | only_key       | missing scopes                    |
      | only_token     | invalid key                       |
      | another_user   | unauthorized permission requested |


