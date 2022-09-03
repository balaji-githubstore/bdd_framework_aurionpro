@patient
Feature: Patient
  In order to maintain patient records 
  As an admin
  I want to add, delete, edit patient records

  @addpatient
  Scenario Outline: Add Patient
    Given I have browser with OpenEMR application
    When I enter username as '<username>'
    And I enter password as '<password>'
    And I select language as '<language>'
    And I click on login
    And I click on Patient Menu
    And I click on New Search Menu
    And I fill the who section form
      | firstname | lastname | dob   | gender   |
      | <fname>   | <lname>  | <dob> | <gender> |
    And I click on create new patient
    And I click on confirm create new patient
    And I store the alert text and handle it
    And I close the happy birthday if available
    Then I should verify the alert text contains '<alert_text>'
    And I should get the added patient details as '<expected_patient_name>'

    Examples: 
      | username | password | language         | fname | lname | dob        | gender | alert_text | expected_patient_name                 |
      | admin    | pass     | English (Indian) | John1 | Wick  | 2022-08-30 | Male   | Assessment | Medical Record Dashboard - John1 Wick |
      #| admin    | pass     | English (Indian) | John2 | Wick  | 2022-08-29 | Male   | Assessment | Medical Record Dashboard - John2 Wick |
