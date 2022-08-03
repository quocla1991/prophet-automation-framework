package page_objects.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VolunteerSignUpPage {

    @FindBy(css = "input[id='RESULT_TextField-1']")
    public static WebElement FirstNameTxb;

    @FindBy(css = "input[id='RESULT_TextField-2']")
    public static WebElement LastNameTxb;

    @FindBy(css = "input[id='RESULT_TextField-3']")
    public static WebElement PhoneTxb;

    @FindBy(css = "input[id='RESULT_TextField-4']")
    public static WebElement CountryTxb;

    @FindBy(css = "input[id='RESULT_TextField-5']")
    public static WebElement CityTxb;

    @FindBy(css = "input[id='RESULT_TextField-6']")
    public static WebElement EmailTxb;

    @FindBy(css = "[id=FSsubmit]")
    public static WebElement SubmitBtn;

    @FindBy(css = "[id=draggable]")
    public static WebElement DragElement;

    @FindBy(css = "[id=droppable]")
    public static WebElement DropTarget;


}
