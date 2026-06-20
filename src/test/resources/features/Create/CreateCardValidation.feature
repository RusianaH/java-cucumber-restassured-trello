Feature: Create Card Validation
  As a Trello API user
  I want to create my card safely
  So that I want to create card endpoint to allow create only with valid request

  Scenario Outline: Check board invalid body
    Given a request with authorization
    And the request has body params:
      | key    | value                |
      | name   | <name>               |
      | idList | <id_list>            |
    When the 'POST' request is sent to '/1/cards' endpoint
    Then the response status code is 400
    And the response body is equal to '<error_message>'
    Examples:
      | name     | idList                   | error_message            |
      | 12345    | 6947fb85c9d5ca5b73207c60 | invalid value for idList |
      | New card |                          | invalid value for idList |
      | New card | invalid                  | invalid value for idList |
