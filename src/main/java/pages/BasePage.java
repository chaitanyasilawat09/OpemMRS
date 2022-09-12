package pages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.ApplicationProperties;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class BasePage {

    public WebDriver driver;
    ApplicationProperties applicationProperties = ApplicationProperties.INSTANCE;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterText(WebElement element, String text) {
        try {
            element.isDisplayed();
            element.click();
            element.clear();
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].value='" + text + "';", element);
        } catch (Exception e) {
            System.out.println("not able to enter text :-" + text + " on element :-" + element);
            e.printStackTrace();
        }
    }

    public void clickOnElement(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            element.isDisplayed();
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String result = js.executeScript("return document.readyState").toString();
            if (!result.equals("complete")) {
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            System.out.println("not able to Click on Element :-" + element);
            e.printStackTrace();
        }
    }

    public void verifyElementIsDisplayed(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.isDisplayed();
        } catch (Exception e) {
            System.out.println("Element not displayed on the page :-" + element);
            e.printStackTrace();
        }
    }

    public void selectValueFromDropDown(WebElement elementDropDown, String value) {
        Select select = new Select(elementDropDown);
        try {
            select.selectByVisibleText(value);
        } catch (Exception e) {
            System.out.println("Not able to select value :-" + value + " from element  :-" + elementDropDown);
            e.printStackTrace();
        }
    }

}
