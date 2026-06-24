#Feature: Delete card
#  As a Trello API user
#  I want to Delete my card safely
#  So that I want to delete card endpoint to allow create only with valid request
#
#  Scenario: Check Delete card
#    Given a new card is created
#    When the 'DELETE' request is sent to '/1/cards/{id}' endpoint
#    Then the response status code is 200
#    And The card is no longer in the board list
#    And the response body value by path '_value' is null
#
#
#
#
