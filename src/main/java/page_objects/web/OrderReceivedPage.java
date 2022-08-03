package page_objects.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderReceivedPage {

    @FindBy(css = "p[class*='thankyou-order-received']")
    public static WebElement ThankYouOrderLbl;
}
