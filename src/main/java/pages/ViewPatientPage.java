package pages;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ViewPatientPage extends BasePage {

    @FindBy(xpath = "//span[@class='PersonName-givenName']")
    private WebElement patientGivenName;

    @FindBy(xpath = "(//div[@class='gender-age col-auto']/span)[1]")
    private WebElement patientGender;

@FindBy(id = "patient-header-contactInfo")
    private WebElement patientContactInfoButton;

@FindBy(id = "coreapps-telephoneNumber")
    private WebElement patientTelephoneNumber;

    @FindBy(xpath = "//span[@class='PersonName-familyName']")
    private WebElement patientFamilyName;

    @FindBy(xpath = "//div[contains(text(),'Delete Patient')]")
    private WebElement deletePatientButton;

    @FindBy(xpath = "//div[contains(text(),'Start Visit')]")
    private WebElement startVisitButton;

    @FindBy(xpath = "(//div[contains(text(),'Attachments')])[2]")
    private WebElement AttachmentsButton;

    @FindBy(id = "start-visit-with-visittype-confirm")
    private WebElement startVisitConfirmButton;

    @FindBy(xpath = "//p[contains(text(),'Are you sure you want to DELETE the patient')]")
    private WebElement deletePatientInfoMessage;

    @FindBy(id = "delete-reason")
    private WebElement deletePatientReason;

    @FindBy(id = "delete-reason-empty")
    private WebElement reasonErrorMessage;

    @FindBy(xpath = "//div[@id='delete-patient-creation-dialog']/div/button[contains(text(),'Confirm')]")
    private WebElement confirmDeletePatientButton;

    @FindBy(xpath = "//div[@id='delete-patient-creation-dialog']/div/button[contains(text(),'Cancel')]")
    private WebElement cancelDeletePatientButton;

    @FindBy(xpath = "//em[contains(text(),'Patient ID')]/following-sibling::span")
    private WebElement getPatientIdFromViewPatientPage;

    @FindBy(xpath = "//form[@id='visit-documents-dropzone']")
    private WebElement uploadFile;

    public ViewPatientPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public void clickOnDeletePatientButton() {
        clickOnElement(deletePatientButton);
        verifyElementIsDisplayed(deletePatientInfoMessage);
    }

    public void verifyDeletePatientPopUPWindowFields() {
        verifyElementIsDisplayed(deletePatientInfoMessage);
        verifyElementIsDisplayed(deletePatientReason);
        verifyElementIsDisplayed(confirmDeletePatientButton);
        verifyElementIsDisplayed(cancelDeletePatientButton);
    }

    public void enterDeleteReasonAndDelete() {
        verifyElementIsDisplayed(deletePatientReason);
        enterText(deletePatientReason, "Not required");
        verifyElementIsDisplayed(confirmDeletePatientButton);
        verifyElementIsDisplayed(cancelDeletePatientButton);
        clickOnElement(confirmDeletePatientButton);
    }

    public String getPatientId() {
        verifyElementIsDisplayed(getPatientIdFromViewPatientPage);
        return getPatientIdFromViewPatientPage.getText();
    }

    public void clickOnContactInfoButton() {

        clickOnElement(patientContactInfoButton);
    }

    public void clickOnStartVisit(String patientName) {
        clickOnElement(startVisitButton);
        clickOnElement(startVisitConfirmButton);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'" + patientName + "')]"));
        verifyElementIsDisplayed(element);
        clickOnElement(element);
        clickOnElement(AttachmentsButton);
        verifyElementIsDisplayed(uploadFile);
        String filePath = System.getProperty("user.dir") + "/src/test/java/myFile.text";
        WebElement upload_file = driver.findElement(By.xpath("//form[@id='visit-documents-dropzone']"));

        //Upload file using selenium sendKeys
        upload_file.sendKeys(filePath);

        // Upload file using JavascriptExecutor
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].value='" + filePath + "';", upload_file);
    }

}
