Feature: Building automation testing with serenity BDD and POM pattern.
  Like an automation tester engineer
  I need build automations using serenity BDD on https://demoqa.com/
  for learn about serenity BDD framework.

  @Test
  Scenario Outline: Text Box section
    Given I'm a normal user on web page of demoqa
    When I write any text in every text box and submit it
    |fullName|<fullName>|
    |email|<email>|
    |currentAddress|<currentAddress>|
    |permanentAddress|<permanentAddress>|
    Then a section should appear with submitted data
    Examples:
      | fullName |email|currentAddress|permanentAddress|
      | Jorge Durango|jdur@demoqa.com|La paz, Bolivia; Calle 34 Sur # 45-30|La paz, Bolivia; Calle 120 # 34-90|
      | Martha Mora|mmor@demoqa.com|Bogotá, Colombia; Calle 55 Norte # 78-50|Bogotá, Colombia; Calle 100 # 45-09|
      | María Camacho|mCam@demoqa.com|Ciudad de Mexico, Mexico; Calle 23 Sur # 78-30|Ciudad de Mexico, Mexico; Calle 120 # 12-98|

