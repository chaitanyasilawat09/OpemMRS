package pages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class LoginPage extends BasePage {

    @FindBy(xpath = "//label[contains(text(),'Username:')]/following-sibling::input")
    private WebElement userNameField;

    @FindBy(xpath = "//label[contains(text(),'Password:')]/following-sibling::input")
    private WebElement passwordFields;

    @FindBy(xpath = "//label[contains(text(),'Location for this session:')]")
    private WebElement locationHeading;

    @FindBy(id = "Inpatient Ward")
    private WebElement inpatientWard;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    @FindBy(xpath = "//h4")
    private WebElement adminPageHeading;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public void login(String userName, String password) {
        enterText(userNameField, applicationProperties.getUserName());
        enterText(passwordFields, applicationProperties.getPassword());
        locationHeading.isDisplayed();
        clickOnElement(inpatientWard);
        clickOnElement(loginButton);
    }

    public void loginForTest() {
        String userName = applicationProperties.getUserName();
        String password = applicationProperties.getPassword();
        login(userName, password);
    }

    public void logout() {
        verifyElementIsDisplayed(logoutButton);
        clickOnElement(logoutButton);
        verifyElementIsDisplayed(userNameField);
        verifyElementIsDisplayed(passwordFields);
    }

}
