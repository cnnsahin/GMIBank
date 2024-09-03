Feature: login functionality test



@login
  Scenario Outline: test login function

    Given user navigates to login page
    And user provides "<username>" and "<password>"
    Then user validates user login

    Examples: login credentials
    |username|password|
    |CWoburn |Cw192837?|
