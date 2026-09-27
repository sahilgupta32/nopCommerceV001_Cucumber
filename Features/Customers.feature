Feature: Customers

  Background: Below are the common steps for each scenario
    Given User Launch Chrome Browser
    When User Open URL "https://admin-demo.nopcommerce.com/login"
    And User enter email as "admin@yourstore.com" and password as "admin"
    And Click on Login
    Then User can view Dashboard

  @sanity
  Scenario: Add new Customer
    When User click on customers Menu
    And click on customers Menu Item
    And click on Add new button
    Then User can view Add new Customer Page
    When User enter customer info
    And click on save button
    Then User can view Confirmation message "The new customer has been added successfully."
    And Close Browser

  @regression
  Scenario: Search Customer by Email ID
    When User click on customers Menu
    And click on customers Menu Item
    And Enter Customer Email
    When Click on Search Button
    Then User should found Email in the Search Table
    And Close Browser

  @regression
  Scenario: Search Customer by Name
    When User click on customers Menu
    And click on customers Menu Item
    And Enter Customer FirstName
    And Enter Customer LastName
    When Click on Search Button
    Then User should found Name in the Search Table
    And Close Browser