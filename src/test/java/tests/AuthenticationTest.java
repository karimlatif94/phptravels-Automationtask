package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import data.ConfigFileReader;

public class AuthenticationTest extends InitialTest {

    HomePage homePageObj;
    LoginPage loginPageObj;
    ConfigFileReader configFileObj;

    // Default admin login To Home page
    @Test(priority = 1, alwaysRun = true)
    public void loginToHomePage(){

        loginPageObj = new LoginPage(driver);
        configFileObj = new ConfigFileReader();
        loginPageObj.userLogin(configFileObj.getDefaultAdminUsername(), configFileObj.getDefaultAdminPassword());

    }

    @Test(dependsOnMethods = "loginToHomePage")
    public void addNewAdminPage() {

        homePageObj = new HomePage(driver);
        homePageObj.openAdminsManagementPage();
        homePageObj.openAddAdminPage();
    }


}
