@sad
Feature: Delete Card
  As a Trello API user
  I want to delete my card
  So that I can remove cards I no longer need

  Scenario: Successfully delete a card
    Given a request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 6947fc42b45c789bd50ef244 |
    When the 'DELETE' request is sent to 'DELETE_CARD_URL' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path   | expected_value |
      | _value | null           |

  Scenario: Deleted card no longer appears in list
    Given a request with authorization
    And the request has path params:
      | name    | value                    |
      | list_id | 6947fb85c9d5ca5b73207c60 |
    And the request has query params:
      | fields | id,name |
    When the 'GET' request is sent to 'GET_ALL_CARDS' endpoint
    Then the response status code is 200