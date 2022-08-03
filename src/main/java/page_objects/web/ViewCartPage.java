package page_objects.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewCartPage {

    @FindBy(css = "[value='Update Basket']")
    public static WebElement UpdateBasketBtn;

    @FindBy(css = ".checkout-button")
    public static WebElement CheckOutBtn;

}
