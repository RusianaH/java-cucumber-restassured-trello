Feature: Delete Board
As a Trello API user
I want to Delete my board safely
So that I want to delete board endpoint to allow create only with valid request

  Scenario: Check Delete Board
    Given a new board is created
    When the 'DELETE' request is sent to '/1/boards/{id}' endpoint
    Then the response status code is 200
    And The board is no longer in the board list
    And the response body value by path '_value' is null




