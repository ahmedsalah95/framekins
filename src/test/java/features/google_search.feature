Feature: Google Search
  Scenario: Searching for Cucumber
    Given User is on Google search page
    When  User types "Cucumber"
    And   User clicks on search button
    Then  User sees search results
