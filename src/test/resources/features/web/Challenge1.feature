Feature: Challenge 1 - Automate 'User Registration' of an website with Selenium

  @web
  Scenario: Test Case 1 - Automate User Registration Process
    Given Open a web browser
    When Go to web challenge one
      And Enter information for signing up
        | First Name | Last Name |        Email            |     Phone    | Country |    City     |
        |    Quoc    |     La    | quangquoc1804@gmail.com | +84902239163 | Vietnam | Ho Chi Minh |
      And Submit information for signing up
    Then Verify user account is failed to create

  @web
  Scenario: Test Case 2 - Verify invalid email address error
    Given Open a web browser
    When Go to web challenge one
      And Enter information for signing up
        | First Name | Last Name |     Email       |     Phone    | Country |    City     |
        |    Quoc    |     La    | test@@gmail.com | +84902239163 | Vietnam | Ho Chi Minh |
      And Submit information for signing up
    Then Verify the toast error message is displayed

  @web
  Scenario: Test Case 3 – Verify Drag and Drop ability on website
    Given Open a web browser
    When Go to web challenge one
      And Drag and drop to the target
    Then Verify drag and drop successfully



