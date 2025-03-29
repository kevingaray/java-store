Feature: buy

  Background:
    Given I landed on Ecommerce Page
    When Logged in with correct credentials

  @BuyItem
  Scenario Outline: Buy an item
    When Added product <product> to cart
    When I go to cart page
    When I buy the item <product>
    Then I should see the item <product> in the payment page

    Examples:
      | product         |
      | ADIDAS ORIGINAL |
