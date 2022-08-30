# created by balaji on Aug 29
@login
Feature: Login
  In order to maintain health records 
  As a user
  I want to access the OpenEMR dashboard
	
	@valid 	@high
  Scenario Outline: Valid Credential
    Given I have browser with OpenEMR application
    When I enter username as '<username>'
    And I enter password as '<password>'
    And I select language as '<language>'
    And I click on login
    Then I should get access to portal with title as 'OpenEMR'

    Examples: 
      | username  | password  | language         |
      | admin     | pass      | English (Indian) |
      | physician | physician | English (Indian) |

	@invalid 	@high
  Scenario: Invalid Credential
    Given I have browser with OpenEMR application
    When I enter username as 'john'
    And I enter password as 'john123'
    And I select language as 'Dutch'
    And I click on login
    Then I should not get access to portal with error message as 'Invalid username or password'
