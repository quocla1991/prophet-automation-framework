package page_objects.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {

    @FindBy(css = "[title=Search]")
    public static WebElement SearchIcon;

    @FindBy(css = "[title='View your shopping cart']")
    public static WebElement ViewCartIcon;

}
