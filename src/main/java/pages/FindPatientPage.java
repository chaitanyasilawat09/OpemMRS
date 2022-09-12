package pages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class FindPatientPage extends BasePage {

    @FindBy(id = "patient-search")
    private WebElement findPatientField;

    @FindBy(xpath = "//h2")
    private WebElement findPatientPageHeading;

    public FindPatientPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public void verifyPatientIdIsDisplayInTable(String patientId) {
        verifyElementIsDisplayed(findPatientField);
        enterText(findPatientField, patientId);
        findPatientField.sendKeys(Keys.ENTER);
    }

}


