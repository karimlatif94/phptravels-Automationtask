package tests;

import com.github.javafaker.Faker;
import data.ConfigFileReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddAdminFormPage;

public class AddAdminFormTest extends InitialTest {
    AddAdminFormPage addAdminFormPageobj;
    ConfigFileReader configFileObj;

    //generate fake data for user info
    Faker fakeDataObj = new Faker();
    String firstname = fakeDataObj.name().firstName();
    String lastname = fakeDataObj.name().lastName();
    String email = fakeDataObj.internet().emailAddress();
    String password = fakeDataObj.number().digits(6);
    String country = "Egypt";//fakeDataObj.address().country(); //commented as it generates unknown countries

    @Test
    public void FillNewAdminForm() {
        addAdminFormPageobj = new AddAdminFormPage(driver);
        configFileObj = new ConfigFileReader();

        //save new admin email and password inside object for privacy
        configFileObj.setAdminUsername(email);
        configFileObj.setAdminPassword(password);


        addAdminFormPageobj.FillAdminForm(firstname, lastname, configFileObj.getAdminUsername(), configFileObj.getAdminPassword(), country);
    }

    @Test(dependsOnMethods = "FillNewAdminForm")
    public void ValidateNewAdminAdded() {
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://phptravels.net/api/admin/accounts/admins/");
    }
    @Test(dependsOnMethods = "ValidateNewAdminAdded")
    public void EditAdminInfo() {
        addAdminFormPageobj.EditAdminForm(configFileObj.getAdminUsername(), configFileObj.getAdminPassword());

    }
    @Test(dependsOnMethods = "EditAdminInfo")
    public void RevalidateNewAdminAdded() {
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://phptravels.net/api/admin/accounts/admins/");
    }


    @Test(dependsOnMethods = "RevalidateNewAdminAdded")
    public void logout() {
        addAdminFormPageobj.Logout();
    }


}