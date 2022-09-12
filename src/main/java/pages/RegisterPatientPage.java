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
public class RegisterPatientPage extends BasePage {

    @FindBy(xpath = "//div/h2")
    private WebElement heading;

    @FindBy(name = "givenName")
    private WebElement givenNameField;

    @FindBy(name = "familyName")
    private WebElement familyNameField;

    @FindBy(id = "next-button")
    private WebElement nextPage;

    @FindBy(id = "gender-field")
    private WebElement selectGender;

    @FindBy(id = "birthdateDay-field")
    private WebElement selectBirthDay;

    @FindBy(id = "birthdateMonth-field")
    private WebElement selectBirthMonth;

    @FindBy(id = "birthdateYear-field")
    private WebElement selectBirthYear;

    @FindBy(xpath = "//label[@name='address1']/following-sibling::input")
    private WebElement address1Fields;

    @FindBy(xpath = "//label[@name='address2']/following-sibling::input")
    private WebElement address2Fields;

    @FindBy(xpath = "//label[@name='cityVillage']/following-sibling::input")
    private WebElement cityVillageFields;

    @FindBy(xpath = "//label[@name='stateProvince']/following-sibling::input")
    private WebElement stateProvinceFields;

    @FindBy(xpath = "//label[@name='country']/following-sibling::input")
    private WebElement countryFields;

    @FindBy(xpath = "//label[@name='postalCode']/following-sibling::input")
    private WebElement postalCodeFields;

    @FindBy(xpath = "//label[contains(text(),'patient phone number')]")
    private WebElement patientPhoneNumberPageHeading;

    @FindBy(xpath = "//label[contains(text(),'patient phone number')]/following-sibling::input")
    private WebElement phoneNoFields;

    @FindBy(id = "relationship_type")
    private WebElement relationshipWithPatient;

    @FindBy(xpath = "//input[@placeholder='Person Name']")
    private WebElement relativeName;

    @FindBy(id = "cancelSubmission")
    private WebElement cancelSubmissionButton;

    @FindBy(id = "submit")
    private WebElement submitSubmissionButton;

    public RegisterPatientPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public void nameFieldOfPatient(String givenName, String familyName) {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnRegisterPatient();
        enterText(givenNameField, applicationProperties.getGivenName());
        enterText(familyNameField, applicationProperties.getFamilyName());
        heading.isDisplayed();
        clickOnElement(nextPage);
    }

    public void selectGenderAndDOB() {
        verifyElementIsDisplayed(selectGender);
        selectValueFromDropDown(selectGender, applicationProperties.getGender());
        clickOnElement(nextPage);
        enterText(selectBirthDay, applicationProperties.getBirthDate());
        enterText(selectBirthYear, applicationProperties.getBirthYear());
        selectValueFromDropDown(selectBirthMonth, applicationProperties.getBirthMonth());
        clickOnElement(nextPage);

    }
    public void fillAddressAndRelationFields() {
        enterText(address1Fields, applicationProperties.getAddress1());
        enterText(address2Fields, applicationProperties.getAddress2());
        enterText(cityVillageFields, applicationProperties.getCity());
        enterText(stateProvinceFields, applicationProperties.getState());
        enterText(countryFields, applicationProperties.getCountry());
        enterText(postalCodeFields, applicationProperties.getPostalCode());
        clickOnElement(nextPage);
        enterText(phoneNoFields, applicationProperties.getPhoneNo());
        clickOnElement(nextPage);
        selectValueFromDropDown(relationshipWithPatient, applicationProperties.getRelationShip());
        enterText(relativeName, applicationProperties.getRelativeName());
        clickOnElement(nextPage);
    }

    public void verifyButtonsAndClickOnSubmit(WebElement element) {
        verifyElementIsDisplayed(cancelSubmissionButton);
        verifyElementIsDisplayed(submitSubmissionButton);
        clickOnElement(submitSubmissionButton);
        verifyElementIsDisplayed(element);
    }
}
