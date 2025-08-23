package com.longtester.hrm.testcases;

import com.longtester.hrm.common.BaseTest;
import com.longtester.hrm.common.DataTest;
import com.longtester.hrm.pages.BasePage;
import com.longtester.hrm.pages.LocationPage;
import com.longtester.hrm.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocationTest extends BaseTest {
    LoginPage loginPage;
    BasePage basePage;
    LocationPage locationPage;
    @BeforeMethod
    public void initPage(){
        loginPage = new LoginPage(driver);
        basePage = new BasePage(driver);
        locationPage = new LocationPage(driver);
    }
    @Test
    public void testAddLocation(){
        loginPage.loginHRM("Admin","admin123");
        basePage.clickMenuAdmin();
        locationPage.addLocation(DataTest.location_name);
        locationPage.verifySuccessMessageIsDisplayed();
        locationPage.verifyLocationIsDisplayedInTable(DataTest.location_name);
    }
}
