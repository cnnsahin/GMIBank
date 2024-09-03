Feature: generate new applicants



  Background: user navigation
    Given user navigates to lending page



    @newApplicant
    Scenario Outline: generate new users for GMI
      Given user navigates to registration page
      And user provides "<ssn>" number
      And user provides "<firstname>"
      When user provides "<lastname>" of user
      Then user provides "<address>" info
      And user provides mobile "<phone>" number
      And user provides unique "<username>"
      And user gives a valid "<email>" id
      When user sends "<password>"
      Then user provides same "<passwordCon>"
      And user valides applicant registration
      Then user saves the applicant info

      Examples: User registration data
      |ssn|firstname|lastname|address|phone|username|email|password|passwordCon|
      |200-37-8594|Arya|Stark|maden way|463-372-4637|mellowy|mellowy@gmail.com|Stark2000+|Stark2000+|
