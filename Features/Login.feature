Feature: Login

  @sanity
  Scenario: Successful Login with valid Credentials
    Given User Launch Chrome Browser
    When User Open URL "https://admin-demo.nopcommerce.com/login"
    And User enter email as "admin@yourstore.com" and password as "admin"
    And Click on Login
    Then Page Title should be "Dashboard/nopCommerce administration"
    When User click on Log out link
    Then Page Title should be "Your Store. Login"
    And Close Browser

  @regression
  Scenario Outline: Login Data Driven
    Given User Launch Chrome Browser
    When User Open URL "https://admin-demo.nopcommerce.com/login"
    And User enter email as "<email>" and password as "<password>"
    And Click on Login
    Then Page Title should be "Dashboard/nopCommerce administration"
    When User click on Log out link
    Then Page Title should be "Your Store. Login"
    And Close Browser

    Examples: |email|password|
              |admin@yourstore.com|admin|
              |admin1@yourstore.com|admin123|


