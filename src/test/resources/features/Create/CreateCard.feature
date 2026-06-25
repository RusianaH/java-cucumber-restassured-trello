@sad
Feature: Create Cards
  As a Trello API user
  I want to create my Card
  So that I want to call a single endpoint that will create my Card

  Scenario: Successfully create a card
    Given a request with authorization
    And the request has body params:
      | name   | Created New Card         |
      | idList | 6947fb85c9d5ca5b73207c60 |
    When the 'POST' request is sent to 'CREATE_CARD_URL' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value   |
      | name | Created New Card |

  Scenario: Created card appears in list
    Given a request with authorization
    And the request has path params:
      | name    | value                    |
      | list_id | 6947fb85c9d5ca5b73207c60 |
    And the request has query params:
      | fields | id,name |
    When the 'GET' request is sent to 'GET_ALL_CARDS' endpoint
    Then the response status code is 200
    And the response body contains board name "jkt-bandung"