### Testing of SwagLabs Shop
* [Test Plan for SwagLabs Shop](https://drive.google.com/file/d/1YMIyXFVouesNAc82pV6Nb0RfhYL3kmOY/view?usp=sharing)
* [Test Cases written in Zephyr Scale](https://drive.google.com/file/d/1jyUGSKtnhO0WYDmINyWEV97COnhHn6Fl/view?usp=sharing)
* [Defect report of a bug found during testing](https://drive.google.com/file/d/1UCT2eYaYDY0NOvOcebe3EIG5QpT2YQUw/view?usp=sharing)

### Test cases checklist for SwagLabs Shop
| **Test Created**   | **Test Case**                                                      | **Test**                                  |
|--------------------|--------------------------------------------------------------------|-------------------------------------------|
| :white_check_mark: | User can submit an order (end-to-end)                              | - submitOrderTest                         |
| :white_check_mark: | User can login using the provided usernames on the landing page    | - providedUsernamesTest                   |
| :white_check_mark: | User can add any product to the cart                               | - addProductToCartOnProductsPageTest      |
| :white_check_mark: | User can remove any product from the cart (from the Products page) | - removeProductFromCartOnProductsPageTest |
| :white_check_mark: | User can access each product's page                                | - accessEachProductPageOnProductsPageTest |
| :white_check_mark: | Verify that each product has a price                               | - eachProductWithPriceTest                |
| :white_check_mark: | User can add product to cart from the Product page                 | - addProductToCartOnProductPageTest       |
| :white_check_mark: | User can remove product from cart from the Product page            | - removeProductFromCartOnProductPageTest  |
| :white_check_mark: | User can return to Product Catalog page from Product page          | - returnToProductCatalogTest              |
| :white_check_mark: | User can sort products by name ascending                           | - sortProductsByNameAscTest               |
| :white_check_mark: | User can sort products by name descending                          | - sortProductsByNameDescTest              |
| :white_check_mark: | User can sort products by price ascending                          | - sortProductsByPriceAscTest              |
| :white_check_mark: | User can sort product by price descending                          | - sortProductsByPriceDescTest             |
| :white_check_mark: | User can go to Product Catalog via the All Items button            | - allItemsButtonTest                      |
| :white_check_mark: | User can go to SwagLabs page via the About button                  | - aboutButtonTest                         |
| :white_check_mark: | User can logout via the Logout button                              | - logoutButtonTest                        |
| :white_check_mark: | User can reset the page's state via the Reset button               | - resetPageTest                           |
| :white_check_mark: | User can remove product directly from cart                         | - removeProductOnCartTest                 |
| :white_check_mark: | User can access each product's page from cart                      | - accessEachProductOnCartTest             |
| :white_check_mark: | User can continue checkout from cart                               | - continueCheckoutOnCartTest              |
| :white_check_mark: | User can continue shopping from cart's page                        | - continueShoppingOnCartTest              |
| :white_check_mark: | User can continue checkout when personal information is added      | - continueWithPersonalInformationTest     |
| :white_check_mark: | User can cancel order on Checkout (personal information) page      | - cancelOrderOnAddressCheckoutTest        |
| :white_check_mark: | User can access each product's page from Checkout's page           | - accessEachProductOnCheckoutTest         |
| :white_check_mark: | Tax calculated correctly                                           | - taxCalculatedCorrectTest                |
| :white_check_mark: | Price total calculated correctly                                   | - priceTotalCorrectTest                   |
| :white_check_mark: | User can cancel order from the Checkout's page                     | - cancelOrderOnCheckoutTest               |
| :white_check_mark: | User can finish order from the Checkout's page                     | - finishOrderTest                         |
| :white_check_mark: | User can't login without username                                  | - noUsernameTest                          |
| :white_check_mark: | User can't login with incorrect username                           | - incorrectUsernameTest                   |
| :white_check_mark: | User can't login with incorrect password                           | - incorrectPasswordTest                   |
| :white_check_mark: | User can't login with incorrect username and password              | - incorrectUsernameAndPasswordTest        |
| :white_check_mark: | User can't continue checkout without personal information          | - continueWithNoPersonalInformationTest   |
| :white_check_mark: | User can't continue checkout without first name                    | - continueWithoutFirstNameTest            |
| :white_check_mark: | User can't continue checkout without second name                   | - continueWithoutSecondNameTest           |
| :white_check_mark: | User can't continue checkout without postal code                   | - continueWithoutPostalTest               |
| :white_check_mark: | User can't add inexistent product to the basket                    | - productErrorValidationTest              |
| :white_check_mark: | User can't submit an empty order                                   | - submitEmptyOrderTest                    |



### Test results by Test Class & Test Suite

| **Test Suite**         | **Test Class**                         | **Test**                                                                                                                                                                                                                                                                                                                                                                                                                            | **Execution Result**                                                                                                                                                                                                                               |   
|------------------------|----------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **E2E main flow test** | `SubmitOrderTests`                     | - submitOrderTest                                                                                                                                                                                                                                                                                                                                                                                                                   | :white_check_mark:                                                                                                                                                                                                                                 |
| **Page Objects Tests** | `LandingPageTests`                     | - providedUsernamesTest                                                                                                                                                                                                                                                                                                                                                                                                             | :white_check_mark:                                                                                                                                                                                                                                 |
|                        | `ProductCatalogTests`                  | - addProductToCartOnProductsPageTest <br> - removeProductFromCartOnProductsPageTest <br> - accessEachProductPageOnProductsPageTest <br> - eachProductWithPriceTest <br> - addProductToCartOnProductPageTest <br> - removeProductFromCartOnProductPageTest <br> - returnToProductCatalogTest <br> - sortProductsByNameAscTest <br> - sortProductsByNameDescTest <br> - sortProductsByPriceAscTest <br> - sortProductsByPriceDescTest | :white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br> |
|                        | `SideBarMenuTests`                     | - allItemsButtonTest <br> - aboutButtonTest <br> - logoutButtonTest <br> - resetPageTest                                                                                                                                                                                                                                                                                                                                            | :white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>                                                                                                                                                           |
|                        | `CartPageTests`                        | - removeProductOnCartTest <br> - accessEachProductOnCartTest <br> - continueCheckoutOnCartTest <br> - continueShoppingOnCartTest                                                                                                                                                                                                                                                                                                    | :white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>                                                                                                                                                           |
|                        | `CheckoutAddressTests`                 | - continueWithPersonalInformationTest <br> - cancelOrderOnAddressCheckoutTest                                                                                                                                                                                                                                                                                                                                                       | :white_check_mark:<br>:white_check_mark:                                                                                                                                                                                                           |
|                        | `CheckoutOverviewPageTests`            | - accessEachProductOnCheckoutTest <br> - taxCalculatedCorrectTest <br> - priceTotalCorrectTest  <br> - cancelOrderOnCheckoutTest  <br> - finishOrderTest                                                                                                                                                                                                                                                                            | :white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>                                                                                                                                     |
| **Errors Testing**     | `LoginErrorValidationsTests`           | - noUsernameTest <br> - incorrectUsernameTest <br> - incorrectPasswordTest <br> - incorrectUsernameAndPasswordTest                                                                                                                                                                                                                                                                                                                  | :white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>                                                                                                                                                           |
|                        | `CheckoutAddressErrorValidationsTests` | - continueWithNoPersonalInformationTest <br> - continueWithoutFirstNameTest <br> - continueWithoutSecondNameTest <br> - continueWithoutPostalTest                                                                                                                                                                                                                                                                                   | :white_check_mark:<br>:white_check_mark:<br>:white_check_mark:<br>:white_check_mark:                                                                                                                                                               |
|                        | `SubmitOrderErrorValidationsTests`     | - productErrorValidationTest <br> - submitEmptyOrderTest                                                                                                                                                                                                                                                                                                                                                                            | :white_check_mark:<br>:white_check_mark:                                                                                                                                                                                                           |
