Feature: Automation testing using Screenplay pattern.
    As an automation tester
    I need to practice automation testing using Screenplay patter
    for learn about serenity BDD framework

  Scenario Outline: Fill a student registration form using mandatory fields
    Given the student is on landing page of Tools QA
    When him browse to registration form
    And him has filled it and submitted
    |firstName|<firstName>|
    |lastName|<lastName>|
    |gender|<gender>|
    |mobile|<mobile>|
    Then the student will see a registration information
Examples:
    |firstName|lastName|gender|mobile|
    |Iván     |Arroyave|Male  |1234567890|

