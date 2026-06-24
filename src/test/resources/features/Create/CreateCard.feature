#Feature: Create Card Validation
#  As a Trello API user
#  I want to create my card safely
#  So that the create card endpoint rejects requests with invalid input
#
#  Scenario: Create card with empty name
#    Given a request with authorization
#    And the request has body params:
#      | name   | Created New Card |
#    And the request has headers:
#      | Content-Type | application/json |
#    When the 'POST' request is sent to 'CREATE_CARD_URL' endpoint
#    Then the response status code is 200
#    And body value has the following values by paths:
#      | path | expected_value |
#      | name | Created New Card        |
#    And the card ID from the response is remembered
#    Given a request with '<auth_condition>' auth condition
#    And the request has body params:
#      | name   | New Card                 |
#      | idList | 6947fb85c9d5ca5b73207c60 |
#    When the 'POST' request is sent to 'GET_ALL_CARDS' endpoint
#    Then the response status code is 401
#    And the response body has any item by paths:
#      | id | created_cardid |