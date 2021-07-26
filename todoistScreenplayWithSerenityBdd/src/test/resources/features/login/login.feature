Feature: Test for Todoist.com
    Like an user of todoist
    I need verify the login module
    just for validate if it's working fine.

  Scenario Outline: General login success
    Given the user is on landing page
    When the user insert the credential
      |user|<user>|
      |password|<password>|
  Then the user sould see the the Add task page
      Examples:
          |user|password|
          |wl.interview.session@gmail.com|WL2021&*|
