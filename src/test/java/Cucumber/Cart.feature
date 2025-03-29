Feature: carts

  Background:
    Given I landed on Ecommerce Page
    When Logged in with correct credentials

  @RemoveItem
  Scenario Outline: Remove item from cart
    When Added products <product1> and <product2> to cart
    When I go to cart page
    When I remove <product1> from cart
    Then <product1> should not be displayed in the cart

    Examples:
      | product1        | product2    |
      | ADIDAS ORIGINAL | ZARA COAT 3 |

  @EmptyCart
  Scenario: Init with empty cart
    When I go to cart page
    Then I should see an empty cart

  @DisplayItem
  Scenario Outline: Add an item to cart
    When Added product <product1> to cart
    Then It should increase to <cart_number> the cart number
    When I go to cart page
    Then <product1> should be displayed in the cart

    Examples:
      | product1        | cart_number |
      | ADIDAS ORIGINAL | 1           |
