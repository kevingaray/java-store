Feature: carts

  Background:
    Given I landed on Ecommerce Page
    When Logged in with correct credentials


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

