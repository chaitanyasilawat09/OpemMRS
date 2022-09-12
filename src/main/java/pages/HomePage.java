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
public class HomePage extends BasePage {

    @FindBy(id = "referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension")
    private WebElement clickOnRegisterPatient;

    @FindBy(id = "coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension")
    private WebElement findPatientRecord;

    @FindBy(xpath = "//h2")
    private WebElement findPatientRecordPageHeading;

    @FindBy(xpath = "//i[contains(@class,'icon-home small')]")
    private WebElement homeIconButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public void clickOnRegisterPatient() {
        verifyElementIsDisplayed(clickOnRegisterPatient);
        clickOnElement(clickOnRegisterPatient);
    }

    public void clickOnFindPatientRecord() {
        verifyElementIsDisplayed(findPatientRecord);
        clickOnElement(findPatientRecord);
        findPatientRecordPageHeading.isDisplayed();
    }

    public void clickOnHomeButton() {
        verifyElementIsDisplayed(homeIconButton);
        clickOnElement(homeIconButton);
    }
}
