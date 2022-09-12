import org.testng.annotations.Test;
import pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @Test
    public void login(){
        String userName = applicationProperties.getUserName();
        String password = applicationProperties.getPassword();
        loginPage = new LoginPage(driver);
        loginPage.login(userName, password);

        assertThat(loginPage.getAdminPageHeading().getText().toLowerCase(),containsString(applicationProperties.getUserName().toLowerCase()));
    }

    @Test
    public void logout(){
        String userName = applicationProperties.getUserName();
        String password = applicationProperties.getPassword();
        loginPage = new LoginPage(driver);
        loginPage.login(userName, password);

        assertThat(loginPage.getAdminPageHeading().getText().toLowerCase(),containsString(applicationProperties.getUserName().toLowerCase()));

        loginPage.logout();
    }
}
