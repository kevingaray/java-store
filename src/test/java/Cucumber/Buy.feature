Feature: buy

  @BuySimpleItem @BuyItem
  Scenario Outline: Buy an item
    Given I landed on Ecommerce Page
    When Logged in with correct credentials
    When Added product <product> to cart
    When I go to cart page
    When I buy the item <product>
    Then I should see the item <product> in the checkout page

    Examples:
      | product         |
      | ADIDAS ORIGINAL |


  @BuyMultipleItems @BuyItem
  Scenario Outline: Buy multiple items
    Given I landed on Ecommerce Page
    When Logged in with correct credentials
    When Added products <product1> and <product2> to cart
    When I go to cart page
    When I click the checkout button
    Then I should see the items <product1> and <product2> in the checkout page
    When I select the country <country>
    When I click the submit button
    When I save the orders numbers
    When I go to orders page
    Then My orders should be displayed

    Examples:
      | product1        | product2    | country |
      | ADIDAS ORIGINAL | ZARA COAT 3 | Peru    |
