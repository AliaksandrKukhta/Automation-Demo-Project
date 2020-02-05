@loginfeature
Feature: Test page

 Scenario Outline: Test positive login page
     Given I am on main URL
     When I login as user with "<name>" and "<password>"
     Then I see logout link
     Examples:
           | name   | password    |
           | Automation_pvt| 123345768   |
           | XXXXXX| hfgyt124jfk|