@happy
Feature: Get Boards
  As a Trello API user
  I want to access all my boards
  So that I want to call a single endpoint that will return all my boards

  Scenario: Check Get Boards
    Given a request with authorization
    And the request has query params:
    |fields|id,name|
    And the request has path params:
    | name   |  value       |
    | member |  rusianahlm  |
    When the 'GET' request is sent to 'GET_ALL_BOARDS' endpoint
    Then the response status code is 200
    And the response matches 'get_boards.json' schema

  Scenario: Check Get Board
    Given a request with authorization
    And the request has query params:
      |fields|id,name|
    And the request has path params:
      | name   |  value                     |
      | id     |  6947fb6d9cd8d0e89aa127ee  |
    When the 'GET' request is sent to 'GET_A_BOARD' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path    |  expected_value           |
      | name    |  itenerary                |
      | id     |  6947fb6d9cd8d0e89aa127ee  |
    And the response matches 'get_board.json' schema