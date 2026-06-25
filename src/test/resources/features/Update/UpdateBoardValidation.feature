@happy
Feature: Update Board validation
  As a Trello API user
  I want to update my board safely
  So that i want update board endpoint to allow update only with invalid req

  Scenario Outline: Board with invalid id
    Given a request without authorization
    And the request has path params:
      | name  | value |
      | id    | <id>  |
    When the 'PUT' request is sent to 'UPDATE_A_BOARD' endpoint
    Then the response status code is <status_code>
    And the response body is equal to '<error_message>'
    Examples:
      | id                        | error_message                         | status_code |
      |invalid                    | invalid id                            | 400         |
      |60d847d9aad2437cb984f8e1   | The requested resource was not found. | 404         |


    Scenario Outline:  Board with invalid auth
      Given a request without authorization
      And the request has query params:
        | key   | <key>   |
        | token | <token> |
      And the request has path params:
        |name       | value   |
        |id       | 6947fb6d9cd8d0e89aa127ee |
      When the 'PUT' request is sent to 'UPDATE_A_BOARD' endpoint
      Then the response status code is 401
      And  the response body is equal to '<error_message>'
      Examples:
        | key               | token                | error_message                     |
        | empty_value        | current_user_token    | invalid key                       |
        | current_user_key   | empty_value            | missing scopes                    |
        | another_user_key    | another_user_token     | unauthorized permission requested |





