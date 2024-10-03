### Testing of SwagLabs shop

### Test cases checklist for SwagLabs shop
|        | **Test Case**                                                             | **Test**                                                             |
|--------|---------------------------------------------------------------------------|----------------------------------------------------------------------|
| :white_check_mark: | Submitting an order (end-to-end)                                            | - submitOrderTest                                                    |
| :white_check_mark: | Login using the provided usernames on the landing page                      | - providedUsernamesTest                                              |
| :white_check_mark: | Adding any product to the cart                                              | - addProductToCartTest                                               |
| :white_check_mark: | Removing any product from the cart (from the Products page)                 | - removeProductFromCartTest                                          |
| :white_check_mark: | Accessing each product's page                                               | - accessEachProductPageTest                                          |
| :white_check_mark: | Verifying that each product has a price                                     | - eachProductWithPriceTest                                           |
| :white_check_mark: | Adding product to cart from the Product page                                | - addProductToCartFromProductPageTest                                |
| :white_check_mark: | Removing product from cart from the Product page                            | - removeProductFromCartFromProductPageTest                           |
| :white_check_mark: | Returning to Product Catalog page from Product page                         | - returnToProductCatalogTest                                         |
| :white_check_mark: | Sorting products by name ascending                                          | - sortProductsByNameAscTest                                          |
| :white_check_mark: | Sorting products by name descending                                         | - sortProductsByNameDescTest                                         |
| :white_check_mark: | Sorting products by price ascending                                         | - sortProductsByPriceAscTest                                         |
| :white_check_mark: | Sorting product by price descending                                         | - sortProductsByPriceDescTest                                        |
| :white_check_mark: | Going to Product Catalog via the All Items button                           | - allItemsButtonTest                                                 |
| :white_check_mark: | Going to SwagLabs page via the About button                                 | - aboutButtonTest                                                    |
| :white_check_mark: | Possible to logout via the Logout button                                    | - logoutButtonTest                                                   |
| :white_check_mark: | Resetting the page's state via the Reset button                             | - resetPageTest                                                      |
| :white_check_mark: | Removing product directly from cart                                         | - removeProductFromCartTest                                          |
| :white_check_mark: | Accessing each product's page from cart                                     | - accessEachProductFromCheckoutTest                                  |
| :white_check_mark: | Continuing checkout from cart                                               | - continueCheckoutFromCartTest                                       |
| :white_check_mark: | Continue shopping from cart's page                                          | - continueShoppingFromCartTest                                       |
| :white_check_mark: | Continue checkout when personal information is added                        | - continueWithPersonalInformationTest                                |
| :white_check_mark: | Cancelling order from cart                                                  | - cancelOrder                                                        |
| :white_check_mark: | Accessing each product's page from Checkout's page                          | - accessEachProductFromCheckoutTest                                  |
| :white_check_mark: | Tax calculated correctly                                                    | - taxCalculatedCorrectTest                                           |
| :white_check_mark: | Price total calculated correctly                                            | - priceTotalCorrectTest                                              |
| :white_check_mark: | Cancelling order from the Checkout's page                                   | - cancelOrder                                                        |
| :white_check_mark: | Finishing order from the Checkout's page                                    | - finishOrder                                                        |
| :white_check_mark: | Login without username                                                      | - noUsernameTest                                                     |
| :white_check_mark: | Login with incorrect username                                               | - incorrectUsernameTest                                              |
| :white_check_mark: | Login with incorrect password                                               | - incorrectPasswordTest                                              |
| :white_check_mark: | Login with incorrect username and password                                  | - incorrectUsernameAndPasswordTest                                   |
| :white_check_mark: | Continuing checkout without personal information                            | - continueWithNoPersonalInformationTest                              |
| :white_check_mark: | Continuing checkout without first name                                      | - continueWithoutFirstNameTest                                       |
| :white_check_mark: | Continuing checkout without second name                                     | - continueWithoutSecondNameTest                                      |
| :white_check_mark: | Continuing checkout without postal code                                     | - continueWithoutPostalTest                                          |
| :white_check_mark: | Adding inexistent product to the basket                                     | - productErrorValidationTest                                         |
| :white_check_mark: | Submitting empty order                                                      | - submitEmptyOrderTest                                               |



### Test results by Test Class & Test Cuite

| **Test Suite**         | **Test Class**      | **Test**                                                              | **Result**  |   
|------------------------|---------------------|------------------------------------------------------------------------|-------------|
| **E2E main flow test** | `SubmitOrderTests`         | - submitOrderTest  |:white_check_mark:
| **Page Objects Tests** | `LandingPageTests`  | - providedUsernamesTest |:white_check_mark:
|                        | `ProductCatalogTests`     | - addProductToCartTest <br> - removeProductFromCartTest <br> - accessEachProductPageTest <br> - eachProductWithPriceTest <br> - addProductToCartFromProductPageTest <br> - removeProductFromCartFromProductPageTest <br> - returnToProductCatalogTest <br> - sortProductsByNameAscTest <br> - sortProductsByNameDescTest <br> - sortProductsByPriceAscTest <br> - sortProductsByPriceDescTest  | :white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>
|                        | `SideBarMenuTests`     | - allItemsButtonTest <br> - aboutButtonTest <br> - logoutButtonTest <br> - resetPageTest |:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>
|                        | `CartPageTests`     | - removeProductFromCartTest <br> - accessEachProductFromCartTest <br> - continueCheckoutFromCartTest <br> - continueShoppingFromCartTest  |:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>
|                        | `CheckoutAddressTests`     | - continueWithPersonalInformationTest <br> - cancelOrder  |:white_check_mark:<br>:white_check_mark:
|                        | `CheckoutOverviewPageTests`     | - accessEachProductFromCheckoutTest <br> - taxCalculatedCorrectTest <br> - priceTotalCorrectTest  <br> - cancelOrder  <br> - finishOrder |:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>
| **Errors Testing**   | `LoginErrorValidationsTests`        | - noUsernameTest <br> - incorrectUsernameTest <br> - incorrectPasswordTest <br> - incorrectUsernameAndPasswordTest |:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>
|                        | `CheckoutAddressErrorValidationsTests` | - continueWithNoPersonalInformationTest <br> - continueWithoutFirstNameTest <br> - continueWithoutSecondNameTest <br> - continueWithoutPostalTest |:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:
|                        | `SubmitOrderErrorValidationsTests` | - productErrorValidationTest <br> - submitEmptyOrderTest |:white_check_mark:<br>:white_check_mark:
