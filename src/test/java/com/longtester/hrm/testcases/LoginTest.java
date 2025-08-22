package com.longtester.hrm.testcases;

import com.longtester.hrm.common.BaseTest;
import com.longtester.hrm.pages.BasePage;
import com.longtester.hrm.pages.LoginPage;
import com.longtester.keywords.WebUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    BasePage basePage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
        basePage = new BasePage(driver);
    }

    @Test
    public void testLoginSuccess() {
        loginPage.loginHRM("Admin", "admin123");
        //basePage.verifyMainMenuDisplayed();
    }

    @Test
    public void testLoginFailureInvalidCredential() {
        loginPage.loginHRM("User", "admin123");
        loginPage.verifyErrorInvalidCredentialDisplayed();
    }

}
