import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPatientPage;
import pages.ViewPatientPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class RegisterPatientTest extends BaseTest{
    LoginPage loginPage ;
    RegisterPatientPage registerPatientPage;
    ViewPatientPage viewPatientPage;



    @Test
    public void login(){
        String givenName = applicationProperties.getGivenName();
        String familyName = applicationProperties.getFamilyName();

        loginPage = new LoginPage(driver);
        registerPatientPage = new RegisterPatientPage(driver);
        viewPatientPage = new ViewPatientPage(driver);
        loginPage.loginForTest();

        assertThat(loginPage.getAdminPageHeading().getText().toLowerCase(),containsString(applicationProperties.getUserName().toLowerCase()));

        registerPatientPage.nameFieldOfPatient(givenName, familyName);
        registerPatientPage.selectGenderAndDOB();
        registerPatientPage.fillAddressAndRelationFields();
        registerPatientPage.verifyButtonsAndClickOnSubmit(viewPatientPage.getPatientGivenName());
        sleeps(5000);

        assertThat(viewPatientPage.getPatientGivenName().getText(), is(givenName));
        assertThat(viewPatientPage.getPatientFamilyName().getText(), is(familyName));

        viewPatientPage.clickOnDeletePatientButton();
        assertThat(viewPatientPage.getDeletePatientInfoMessage().getText(),containsString(givenName+" "+familyName));
        viewPatientPage.verifyDeletePatientPopUPWindowFields();
        viewPatientPage.enterDeleteReasonAndDelete();
        assertThat(viewPatientPage.getReasonErrorMessage().isDisplayed(),is(false));
    }
}
