Feature: Sorting Functionality

Background:
  Given Admin is in LoginPage
  When Admin enters valid user and password with select role as Admin.
  And Admin lands on dashboard Page and clicks Program from navigation bar

  Scenario: Verify sorting of Program Name in Ascending order/Descending order
    Given Admin is on the Program page
    When Admin clicks on Arrow next to Program Name
    Then Admin sees the Program Name is sorted in Ascending order or Descending order

  Scenario: Verify sorting of Program Description in Ascending order/Descending order
    Given Admin is on the Program page
    When Admin clicks on Arrow next to Program Description
    Then Admin sees the Program Description is sorted in Ascending order or Descending order

  Scenario: Verify sorting of Program Status in Ascending order/Descending order
    Given Admin is on the Program page
    When Admin clicks on Arrow next to Program Status
    Then Admin sees the Program Status is sorted in Ascending order or Descending order
