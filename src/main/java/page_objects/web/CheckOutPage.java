package page_objects.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutPage {

    @FindBy(css = "[id=billing_first_name]")
    public static WebElement FirstNameTxb;

    @FindBy(css = "[id=billing_last_name]")
    public static WebElement LastNameTxb;

    @FindBy(css = "[id=billing_company]")
    public static WebElement CompanyTxb;

    @FindBy(css = "[id=billing_email]")
    public static WebElement EmailTxb;

    @FindBy(css = "[id=billing_phone]")
    public static WebElement PhoneTxb;

    @FindBy(css = "[id=s2id_billing_country]")
    public static WebElement CountryDropdown;

    @FindBy(css = "[id=s2id_autogen1_search]")
    public static WebElement SearchCountryTxb;

    @FindBy(css = "[id=select2-results-1]")
    public static WebElement FirstResultSearch;

    @FindBy(css = "[id=billing_address_1]")
    public static WebElement AddressTxb;

    @FindBy(css = "[id=billing_city]")
    public static WebElement Town_CityTxb;

    @FindBy(css = "[id=billing_state]")
    public static WebElement State_CountryTxb;

    @FindBy(css = "[id=billing_postcode]")
    public static WebElement Postcode_ZipTxb;

    @FindBy(css = "[id=place_order]")
    public static WebElement PlaceOrderBtn;

    public static void selectCountry(String country) {
        CountryDropdown.click();
        SearchCountryTxb.sendKeys(country);
        FirstResultSearch.click();
    }
}
