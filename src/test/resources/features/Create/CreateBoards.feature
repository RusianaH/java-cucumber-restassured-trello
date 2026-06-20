Feature: Create Board
  As a Trello API user
  I want to create a new board
  So that I can organize my tasks

  Scenario: Successfully create a board
    Given a request with authorization
    And the request has "name" body param with value "New Board"
    When the 'POST' request is sent to '/1/boards/' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value |
      | name | New Board      |

  Scenario: Created board appears in board list
    Given a new board is created
    And a request with authorization
    And the request has path params:
      | name   | value      |
      | member | rusianahlm |
    When the 'GET' request is sent to '/1/members/{member}/boards' endpoint
    Then the response status code is 200
    And the response body contains board name "New Board"