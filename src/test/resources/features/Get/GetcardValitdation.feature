Feature: Get Card Validation
As a Trello API user
  I want to have mu card protected
  So that I want to call a single endpoint that will return my card only for me

  Scenario Outline: Check Get with Invalid Id
    Given a request with authorization
    And the request has path params:
      | name      | value       |
      | id        | <id_value>  |
    When the 'GET' request is sent to 'GET_CARD_URL' endpoint
    Then the response status code is <status_code>
    And the response body is equal to '<error_message>'
    Examples:
      | id_value                | status_code       | error_message                        |
      | invalid                 | 400               | invalid id                           |
      |60d847d9aad2437cb984f8e1 | 404               | The requested resource was not found.|

    Scenario Outline: Check Card with invalid Auth
      Given a request without authorization
      And the request has path params:
        | name      | value                      |
        | id        | 6947fc42b45c789bd50ef244   |
      When the 'GET' request is sent to 'GET_CARD_URL' endpoint
      Then the response status code is 401
      And the response body is equal to '<error_message>'
      Examples:
        | auth_condition | error_message                          |
        | no_auth        | unauthorized card permission requested |
        | only_key       | unauthorized card permission requested |
        | only_token     | unauthorized card permission requested |