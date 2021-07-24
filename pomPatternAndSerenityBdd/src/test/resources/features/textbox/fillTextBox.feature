Feature: Building automation testing with serenity BDD and POM pattern.
  Like an automation tester engineer
  I need build automations using serenity BDD on https://demoqa.com/
  for learn about serenity BDD framework.

  @TestNumber1
  Scenario Outline: Text Box section
    Given I'm a normal user on web page of demoqa
    When I write any text in every text box and submit it
    |fullName|<fullName>|
    |email|<email>|
    |currentAddress|<currentAddress>|
    |permanentAddress|<permanentAddress>|
    Then a section should appear with submitted data
    Examples:
      |fullName|email|currentAddress|permanentAddress|
      |Jorge Durango|jdur@demoqa.com|calle 123|calle 11|
      |Martha Durango|mmor@demoqa.com|calle 123|calle 123|
      |Jorge Camacho|mcam@demoqa.com|calle 123|calle 123|

