package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddHotelFormPage;
import pages.RemoveHotelPage;

public class AddHotelFormTest extends InitialTest {
    AddHotelFormPage AddHotelFormPageobj;
    RemoveHotelPage RemoveHotelPageobj;

    //generate fake data for hotel info
    Faker fakeDataObj = new Faker();
    //1st tab info
    String hotelname = fakeDataObj.funnyName().name();
    String hoteldesc = fakeDataObj.lorem().fixedString(100);
    String mapAddress = fakeDataObj.address().city();
    String mapLatitude = fakeDataObj.address().latitude();
    String mapLongitude = fakeDataObj.address().longitude();
    String location = "Cai";
    String B2CMarkup = String.valueOf(fakeDataObj.number().numberBetween(1, 100));
    String B2BMarkup = String.valueOf(fakeDataObj.number().numberBetween(1, 100));
    String B2EMarkup = String.valueOf(fakeDataObj.number().numberBetween(1, 100));
    String serviceFee =  String.valueOf(fakeDataObj.number().numberBetween(1, 100));
    String depositValue = String.valueOf(fakeDataObj.number().numberBetween(1, 100));
    String taxValue =  String.valueOf(fakeDataObj.number().numberBetween(1, 100));
    //3rd tab info
    String metaTitle = fakeDataObj.lorem().fixedString(10);
    String metaKeywords = fakeDataObj.lorem().fixedString(15);
    String metaDesc = fakeDataObj.lorem().fixedString(20);
    //4th tab info
    String checkinTime = "01:00 PM";
    String checkoutTime = "11:00 AM";
    String policyTerms = fakeDataObj.lorem().fixedString(15);





    @Test
    public void FillNewHotelForm() {
        AddHotelFormPageobj = new AddHotelFormPage(driver);

        AddHotelFormPageobj.FillHotelForm1stTab(hotelname, hoteldesc, mapAddress, mapLatitude,
                mapLongitude, location, B2CMarkup, B2BMarkup,
                B2EMarkup, serviceFee, depositValue, taxValue);

        AddHotelFormPageobj.FillHotelForm2ndTab();

        AddHotelFormPageobj.FillHotelForm3rdTab(metaTitle, metaKeywords, metaDesc);

        AddHotelFormPageobj.FillHotelForm4thTab(checkinTime, checkoutTime, policyTerms);

        AddHotelFormPageobj.SubmitHotelForm();

    }

    @Test(dependsOnMethods = "FillNewHotelForm")
    public void ValidateNewHotelAdded() {
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://phptravels.net/api/admin/hotels/");
    }
    @Test(dependsOnMethods = "ValidateNewHotelAdded")
    public void EditHotelInfo() {
        AddHotelFormPageobj.EditHotelForm(hotelname+" updated");

    }
    @Test(dependsOnMethods = "EditHotelInfo")
    public void RevalidateNewHotelAdded() {

        RemoveHotelPageobj = new RemoveHotelPage(driver);
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://phptravels.net/api/admin/hotels/");
        Assert.assertTrue(RemoveHotelPageobj.isHotelFoundinDB(hotelname+" updated"));
    }
    //negative testing
    @Test(dependsOnMethods = "RevalidateNewHotelAdded")
    public void TestRemoveisNotPossible() {

        RemoveHotelPageobj.removeLatestHotel();

        Assert.assertTrue(RemoveHotelPageobj.isHotelFoundinDB(hotelname + " updated"),
                "bug: Hotel is wrongly removed while no remove permission granted");
    }

    @Test
    public void logout() {

        AddHotelFormPageobj.Logout();


    }

}