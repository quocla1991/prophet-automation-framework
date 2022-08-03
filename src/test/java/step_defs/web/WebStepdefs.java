package step_defs.web;

import cucumber.api.java.en.*;
import io.cucumber.datatable.DataTable;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.web.*;
import utils.Config;
import utils.factory.DriverFactory;

import java.util.List;
import java.util.Map;

public class WebStepdefs {

    @When("Go to web challenge one")
    public void goToWebChallengeOne() {
        DriverFactory.getWebDriver().get(Config.ENV.WEB_CHALLENGE_1());
        new WebDriverWait(DriverFactory.getWebDriver(), 20).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @When("Go to web challenge two")
    public void goToWebChallengetwo() {
        DriverFactory.getWebDriver().get(Config.ENV.WEB_CHALLENGE_2());
        new WebDriverWait(DriverFactory.getWebDriver(), 20).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @And("Hover and search with text {string}")
    public void hoverAndSearchWithText(String string) {
        Actions action = new Actions(DriverFactory.getWebDriver());
        action.moveToElement(ProductPage.SearchIcon).build().perform();
        ProductPage.SearchIcon.clear();
        ProductPage.SearchIcon.sendKeys(string);
        ProductPage.SearchIcon.sendKeys(Keys.ENTER);
    }

    @Then("Verify the result contains {string}")
    public void verifyTheResultContains(String result) {
        WebElement element = DriverFactory.getWebDriver().findElement(By.xpath("//*[text()='" + result + "']"));
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(element.isDisplayed()).isTrue();
    }

    @And("Select product with name {string}")
    public void selectProductWithName(String name) {
        WebElement element = DriverFactory.getWebDriver().findElement(By.xpath("//h3[text()='"+ name +"']/parent::a/parent::li/a[2]"));
        element.click();
        WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(),10);
        wait.until(ExpectedConditions.attributeContains(element,"class", "added"));
    }

    @And("Go to cart")
    public void goToCart() {
        ProductPage.ViewCartIcon.click();
    }

    @And("Proceed to check out")
    public void proceedToCheckOut() {
        ViewCartPage.CheckOutBtn.click();
    }

    @And("Enter delivery information")
    public void enterDeliveryInformation(DataTable data) {
        List<Map<String, String>> list = data.asMaps(String.class, String.class);
        CheckOutPage.FirstNameTxb.sendKeys(list.get(0).get("First Name"));
        CheckOutPage.LastNameTxb.sendKeys(list.get(0).get("Last Name"));
        CheckOutPage.EmailTxb.sendKeys(list.get(0).get("Email"));
        CheckOutPage.PhoneTxb.sendKeys(list.get(0).get("Phone"));
        CheckOutPage.selectCountry(list.get(0).get("Country"));
        CheckOutPage.AddressTxb.sendKeys(list.get(0).get("Address"));
        CheckOutPage.Town_CityTxb.sendKeys(list.get(0).get("Town/City"));
    }

    @And("Proceed to order")
    public void proceedToOrder() {
        CheckOutPage.PlaceOrderBtn.click();
    }

    @Then("Verify buy product successfully")
    public void verifyBuyProductSuccessfully() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(OrderReceivedPage.ThankYouOrderLbl.isDisplayed()).isTrue();
    }

    @And("Change the product name {string} with quantity {string}")
    public void changeTheProductNameWithQuantity(String name, String quatity) {
        WebElement element = DriverFactory.getWebDriver().findElement(By.xpath("//a[text()='" + name + "']/parent::td/parent::tr//input"));
        element.clear();
        element.sendKeys(quatity);
    }

    @And("Proceed to update basket")
    public void proceedToUpdateBasket() {
        ViewCartPage.UpdateBasketBtn.click();
    }

    @Then("Verify the total price of {string} reflecting correctly with quantity {string}")
    public void verifyTheTotalPriceOfReflectingCorrectlyWithQuantity(String name, String quantity) {
        WebElement priceElement = DriverFactory.getWebDriver().findElement(By.xpath("//a[text()='" + name + "']/parent::td/parent::tr/td[contains(@data-title,'Price')]/span"));
        int currentPrice = Integer.parseInt(priceElement.getText().replaceAll("[^0-9]", ""));
        WebElement totalPriceElement = DriverFactory.getWebDriver().findElement(By.xpath("//a[text()='" + name + "']/parent::td/parent::tr/td[contains(@data-title,'Total')]/span"));
        int totalPrice = Integer.parseInt(totalPriceElement.getText().replaceAll("[^0-9]", ""));
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(currentPrice*Integer.parseInt(quantity)).isEqualTo(totalPrice);
    }

    @And("Enter information for signing up")
    public void enterInformationForSigningUp(DataTable data) {
        List<Map<String, String>> list = data.asMaps(String.class, String.class);
//        WebElement element = DriverFactory.getWebDriver().findElement(By.xpath("//iframe"));
        DriverFactory.getWebDriver().switchTo().frame(DriverFactory.getWebDriver().findElement(By.xpath("//iframe")));
        VolunteerSignUpPage.FirstNameTxb.sendKeys(list.get(0).get("First Name"));
        VolunteerSignUpPage.LastNameTxb.sendKeys(list.get(0).get("Last Name"));
        VolunteerSignUpPage.PhoneTxb.sendKeys(list.get(0).get("Phone"));
        VolunteerSignUpPage.CountryTxb.sendKeys(list.get(0).get("Country"));
        VolunteerSignUpPage.CityTxb.sendKeys(list.get(0).get("City"));
        VolunteerSignUpPage.EmailTxb.sendKeys(list.get(0).get("Email"));
    }

    @Then("Verify user account is failed to create")
    public void verifyUserAccountIsFailedToCreate() {
        WebElement priceElement = DriverFactory.getWebDriver().findElement(By.xpath("//*[text()='An error has occurred']"));
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(priceElement.isDisplayed()).isTrue();
    }

    @And("Submit information for signing up")
    public void submitInformationForSigningUp() {
        VolunteerSignUpPage.SubmitBtn.click();
    }

    @Then("Verify page contain tooltip error message {string}")
    public void verifyPageContainTooltipErrorMessage(String message) {
        WebElement ele = DriverFactory.getWebDriver().findElement(By.xpath("//"));
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getWebDriver();
        Boolean isValidInput = (Boolean)js.executeScript("return arguments[0].checkValidity();", ele);
        System.out.println(isValidInput);
        String validationMessage = (String)js.executeScript("return arguments[0].validationMessage;", ele);
        System.out.println(validationMessage);


    }

    @Then("Verify the toast error message is displayed")
    public void verifyTheToastErrorMessageIsDisplayed() {
        WebElement emailElement = DriverFactory.getWebDriver().findElement(By.xpath("//input[@type='email']/parent::div"));
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(emailElement.getAttribute("class")).contains("highlight");
    }

    @And("Drag and drop to the target")
    public void dragAndDropToTheTarget() {
        Actions builder = new Actions(DriverFactory.getWebDriver());

        Action dragAndDrop = builder.clickAndHold(VolunteerSignUpPage.DragElement)
                .moveToElement(VolunteerSignUpPage.DropTarget)
                .release(VolunteerSignUpPage.DropTarget)
                .build();

        dragAndDrop.perform();
    }

    @Then("Verify drag and drop successfully")
    public void verifyDragAndDropSuccessfully() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(VolunteerSignUpPage.DropTarget.getText()).isEqualTo("Dropped!");
    }
}
