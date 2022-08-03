Feature: Challenge 2 - Automate 'Buy Product' functionality of an e-commerce website with Selenium

  @web
  Scenario: Test Case 1 - Automate End to End Buy Order functionality
    Given Open a web browser
    When Go to web challenge two
      And Select product with name "Selenium Ruby"
      And Go to cart
      And Proceed to check out
      And Enter delivery information
           | First Name | Last Name |        Email            |     Phone    | Country |    Address     | Town/City   |
           |    Quoc    |     La    | quangquoc1804@gmail.com | +84902239163 | Vietnam | 13 Nguyen Kiem | Ho Chi Minh |
      And Proceed to order
    Then Verify buy product successfully

  @web
  Scenario: Verify that Total Price is reflecting correctly if user changes quantity on 'Shopping Cart Summary' Page
    Given Open a web browser
    When Go to web challenge two
      And Select product with name "Selenium Ruby"
      And Select product with name "Thinking in HTML"
      And Select product with name "Mastering JavaScript"
      And Go to cart
      And Change the product name "Selenium Ruby" with quantity "2"
      And Change the product name "Thinking in HTML" with quantity "3"
      And Change the product name "Mastering JavaScript" with quantity "4"
      And Proceed to update basket
    Then Verify the total price of "Selenium Ruby" reflecting correctly with quantity "2"
    Then Verify the total price of "Thinking in HTML" reflecting correctly with quantity "3"
    Then Verify the total price of "Mastering JavaScript" reflecting correctly with quantity "4"

