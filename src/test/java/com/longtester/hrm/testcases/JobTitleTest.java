package com.longtester.hrm.testcases;

import com.longtester.hrm.common.BaseTest;
import com.longtester.hrm.common.DataTest;
import com.longtester.hrm.pages.BasePage;
import com.longtester.hrm.pages.JobTitlePage;
import com.longtester.hrm.pages.LoginPage;
import com.longtester.hrm.pages.UserMangementPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JobTitleTest extends BaseTest {
    LoginPage loginPage;
    BasePage basePage;
    JobTitlePage jobTitlePage;
    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
        basePage = new BasePage(driver);
        jobTitlePage = new JobTitlePage(driver);
    }
    @Test
    public void testAddJobTitle() {
        loginPage.loginHRM("Admin", "admin123");
        basePage.clickMenuAdmin();
        jobTitlePage.clickMenuJob();
        jobTitlePage.addJobTitle(DataTest.job_title);
        jobTitlePage.verifySuccessMessageIsDisplayed();
        jobTitlePage.verifyJobTitleIsDisplayedInTable();
    }
}
