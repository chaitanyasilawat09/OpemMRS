import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RegisterAndFindPatientRecordTest extends BaseTest{

    LoginPage loginPage ;
    RegisterPatientPage registerPatientPage;
    ViewPatientPage viewPatientPage;
    FindPatientPage findPatientPage;
    HomePage homePage;

    @Test
    public void registerPatientTest(){
        String givenName = applicationProperties.getGivenName();
        String familyName = applicationProperties.getFamilyName();
        loadDriver();
        loginPage.loginForTest();

        assertThat(loginPage.getAdminPageHeading().getText().toLowerCase(),containsString(applicationProperties.getUserName().toLowerCase()));

        registerPatientPage.nameFieldOfPatient(givenName, familyName);
        registerPatientPage.selectGenderAndDOB();
        registerPatientPage.fillAddressAndRelationFields();
        registerPatientPage.verifyButtonsAndClickOnSubmit(viewPatientPage.getPatientGivenName());
        sleeps(5000);

        assertThat(viewPatientPage.getPatientGivenName().getText(), is(givenName));
        assertThat(viewPatientPage.getPatientFamilyName().getText(), is(familyName));

        sleeps(5000);
        deletePatient();
    }

    @Test
    public void findPatientRecordTest(){
        loadDriver();
        registerPatient();
        String patientId = viewPatientPage.getPatientId();

        assertThat(patientId,is(notNullValue()));

        homePage.clickOnHomeButton();
        homePage.clickOnFindPatientRecord();
        findPatientPage.verifyPatientIdIsDisplayInTable(patientId);

        assertThat(viewPatientPage.getPatientId(),is(patientId));

        deletePatient();

    }

    @Test
    public void viewPatientTest(){
        String givenName = applicationProperties.getGivenName();
        String familyName = applicationProperties.getFamilyName();
        loadDriver();
        registerPatient();
        String patientId = viewPatientPage.getPatientId();

        assertThat(patientId,is(notNullValue()));

        homePage.clickOnHomeButton();
        homePage.clickOnFindPatientRecord();
        findPatientPage.verifyPatientIdIsDisplayInTable(patientId);

        assertThat(viewPatientPage.getPatientId(),is(patientId));
        assertThat(viewPatientPage.getPatientGivenName().getText(), is(givenName));
        assertThat(viewPatientPage.getPatientFamilyName().getText(), is(familyName));
        assertThat(viewPatientPage.getPatientGender().getText().trim(), is("Male"));
        viewPatientPage.clickOnContactInfoButton();
        assertThat(viewPatientPage.getPatientTelephoneNumber().getText(), is(applicationProperties.getPhoneNo()));

        deletePatient();
    }

    @Test
    public void deleteRegisteredPatientTest(){
        String givenName = applicationProperties.getGivenName();
        String familyName = applicationProperties.getFamilyName();
        loadDriver();
        registerPatient();
        viewPatientPage.clickOnDeletePatientButton();

        assertThat(viewPatientPage.getDeletePatientInfoMessage().getText(),containsString(givenName+" "+familyName));

        viewPatientPage.verifyDeletePatientPopUPWindowFields();
        viewPatientPage.enterDeleteReasonAndDelete();

        assertThat(viewPatientPage.getReasonErrorMessage().isDisplayed(),is(false));

    }

    @Ignore
    @Test
    public void addAttachmentForPatientTest(){
        String givenName = applicationProperties.getGivenName();
        String familyName = applicationProperties.getFamilyName();
        registerPatient();
//      Not able to upload file using JS as  well as sendKeys
        viewPatientPage.clickOnStartVisit(givenName+" "+familyName);

    }

    public void loadDriver(){
        loginPage = new LoginPage(driver);
        registerPatientPage = new RegisterPatientPage(driver);
        viewPatientPage = new ViewPatientPage(driver);
        findPatientPage = new FindPatientPage(driver);
        findPatientPage = new FindPatientPage(driver);
        homePage = new HomePage(driver);
    }

    public void registerPatient(){
        String givenName = applicationProperties.getGivenName();
        String familyName = applicationProperties.getFamilyName();
        loginPage.loginForTest();

        assertThat(loginPage.getAdminPageHeading().getText().toLowerCase(),containsString(applicationProperties.getUserName().toLowerCase()));

        registerPatientPage.nameFieldOfPatient(givenName, familyName);
        registerPatientPage.selectGenderAndDOB();
        registerPatientPage.fillAddressAndRelationFields();
        registerPatientPage.verifyButtonsAndClickOnSubmit(viewPatientPage.getPatientGivenName());
        sleeps(5000);

        assertThat(viewPatientPage.getPatientGivenName().getText(), is(givenName));
        assertThat(viewPatientPage.getPatientFamilyName().getText(), is(familyName));
        sleeps(5000);
    }

    public void deletePatient(){
        String givenName = applicationProperties.getGivenName();
        String familyName = applicationProperties.getFamilyName();
//        homePage.clickOnHomeButton();
//        homePage.clickOnFindPatientRecord();
//        findPatientPage.verifyPatientIdIsDisplayInTable(patientId);
        viewPatientPage.clickOnDeletePatientButton();
        assertThat(viewPatientPage.getDeletePatientInfoMessage().getText(),containsString(givenName+" "+familyName));
        viewPatientPage.verifyDeletePatientPopUPWindowFields();
        viewPatientPage.enterDeleteReasonAndDelete();
        assertThat(viewPatientPage.getReasonErrorMessage().isDisplayed(),is(false));
    }
}
