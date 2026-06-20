Feature: Delete Board Validation
  As a Trello API user
  I want to Delete my board safely
  So that I want to delete board endpoint to allow create only with valid request

  Scenario Outline: Delete Board Validation
    Given a request with authorization
    And the request has path params:
      | name      | value     |
      | id        | <id>       |
    When the 'DELETE' request is sent to 'DELETE_A_BOARD' endpoint
    Then the response status code is <status_code>
    And the response body is equal to '<error_message>'
    Examples:
      | id                         | status_code             | error_message                         |
      | invalid                    | 400                     | invalid id                            |
      | 60d847d9aad2437cb984f8e1   | 404                     | The requested resource was not found. |

    Scenario Outline: Check Delete Board With Invalid Auth
      Given a request with '<auth_condition>' auth condition
      And the request has path params:
        |   name    | value                        |
        |   id      |6947fb6d9cd8d0e89aa127ee      |
      When the 'DELETE' request is sent to 'DELETE_A_BOARD' endpoint
      Then the response status code is 401
      And the response body is equal to '<error_message>'
      Examples:
        | auth_condition | error_message                     |
        | no_auth        | missing scopes                    |
        | only_key       | missing scopes                    |
        | only_token     | invalid key                 |
        | another_user   | unauthorized permission requested |











    
    


