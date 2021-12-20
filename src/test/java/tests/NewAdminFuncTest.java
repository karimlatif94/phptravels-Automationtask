package tests;

import data.ConfigFileReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.NewAdminFuncPage;

public class NewAdminFuncTest extends InitialTest {
    LoginPage loginPageObj;
    NewAdminFuncPage NewAdminFuncPageObj;
    ConfigFileReader configFileObj;

    // new admin login To Home page
    @Test
    public void loginNewAdminToHomePage() {

        loginPageObj = new LoginPage(driver);
        configFileObj = new ConfigFileReader();
        loginPageObj.userLogin(configFileObj.getAdminUsername(), configFileObj.getAdminPassword());

    }

    @Test(dependsOnMethods = "loginNewAdminToHomePage")
    public void validateHotelsMenuFound(){
        NewAdminFuncPageObj = new NewAdminFuncPage(driver);
        Assert.assertTrue(NewAdminFuncPageObj.isHotelsMenuDisplayed(),"Bug found: Admin permissions are not saved.");
    }

    @Test(dependsOnMethods = "validateHotelsMenuFound")
    public void addNewHotelPage() {


        NewAdminFuncPageObj.openHotelsManagementPage();
        NewAdminFuncPageObj.openAddHotelPage();
    }

//    @Test(dependsOnMethods = "loginNewAdminToHomePage")
//    public void validateHotelsMenuFound(){
//        NewAdminFuncPageObj = new NewAdminFuncPage(driver);
//        Assert.assertTrue(NewAdminFuncPageObj.isHotelsMenuDisplayed(),"Bug found: Admin permissions are not saved.");
//    }



}
