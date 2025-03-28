Feature: Product Search
  As a customer
  I want to search for products
  So that I can find what I'm looking for quickly

  @smoke
  Scenario: Basic search with results
    Given I am on the home page
    When I search for "laptop"
    Then I should see search results
    And the search results should contain "laptop" in the title

  @regression
  Scenario: Search with no results
    Given I am on the home page
    When I search for "xyznonexistentproduct123"
    Then I should see a "No results found" message

  @regression
  Scenario Outline: Filter search results
    Given I am on the home page
    And I search for "<product>"
    When I filter by category "<category>"
    Then I should see results filtered by "<category>"

    Examples:
      | product | category |
      | laptop  | Electronics |
      | shirt   | Clothing    |
      | phone   | Gadgets     |