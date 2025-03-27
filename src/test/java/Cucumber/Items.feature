Feature: items validation

  Background:
    Given I landed on Ecommerce Page
    When Logged in with correct credentials


  @ProductValidation
  Scenario Outline: Validation of product details
    When Select a product <product> to see details
    Then Product <product> name should coincide with the product name displayed

    Examples:
      | product         |
      | ADIDAS ORIGINAL |
