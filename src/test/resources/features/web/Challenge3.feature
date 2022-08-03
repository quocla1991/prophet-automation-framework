Feature: Challenge 3 - Automate 'Search Product' functionality of an e-commerce website

  @web
  Scenario: Test Case 1 - Automate 'Search Product' Functionality of an e-commerce website with Selenium
    Given Open a web browser
    When Go to web challenge two
      And Hover and search with text "Selenium"
    Then Verify the result contains "Selenium Ruby"
      And Hover and search with text "Python"
    Then Verify the result contains "Sorry, nothing found."