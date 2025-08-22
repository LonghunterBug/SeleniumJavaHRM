package com.longtester.hrm.pages;

import com.longtester.hrm.common.DataTest;
import com.longtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class JobTitlePage {
    private WebDriver driver;
    private By topbarJob = By.xpath("//span[contains(@class,'topbar') and normalize-space()='Job']");
    private By menuitemJobTitles = By.xpath("//a[contains(@class,'tab-link') and normalize-space()='Job Titles']");
    private By menuitemJobCategories = By.xpath("//a[contains(@class,'tab-link') and normalize-space()='Job Categories']");
    private By menuitemEmploymentStatus = By.xpath("//a[contains(@class,'tab-link') and normalize-space()='Employment Status']");
    private By buttonAdd = By.xpath("//button[normalize-space()='Add']");
    private By buttonEdit = By.xpath("//div[contains(@class,'table-body')]//div[4]//button[2]");
    private By buttonDelete = By.xpath("//div[contains(@class,'table-body')]//div[4]//button[1]");
    private By listJobTitle = By.xpath("//div[contains(@class,'table-body')]//div[2]");
    // Add New Job Title form
    private By inputJobTitle = By.xpath("//label[text()='Job Title']/parent::div/following-sibling::div/input");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");
    private By toastMessageSuccess = By.xpath("//div[contains(@class,'toast--success')]");

    public JobTitlePage(WebDriver driver) {
        this.driver = driver;
        new WebUI(driver);
    }
    public void clickMenuJob() {
        WebUI.clickElement(topbarJob);
        WebUI.clickElement(menuitemJobTitles);
    }

    public void addJobTitle(String jobTitle) {
        WebUI.clickElement(buttonAdd);
        WebUI.setText(inputJobTitle, jobTitle);
        WebUI.clickElement(buttonSave);
    }

    public void verifySuccessMessageIsDisplayed() {
        Assert.assertTrue(WebUI.isElementDisplayed(toastMessageSuccess));
    }

    public void verifyJobTitleIsDisplayedInTable() {
        WebUI.sleep(5);
        boolean check = false;
        List<WebElement> e = WebUI.getWebElements(listJobTitle);
        //WebUI.logConsole(e.size());
        for (WebElement element : e) {
            WebUI.logConsole(element.getText());
            if (element.getText().equals(DataTest.job_title)) {
                Actions action = new Actions(driver);
                action.moveToElement(element).perform();// Hover over the element to ensure it is visible
                WebUI.sleep(2); // Nếu quay video thì cần sleep
                Assert.assertTrue(element.isDisplayed(), DataTest.job_title + " is not displayed in the table.");
                check = true;
                break;
            }
        }
        if (!check) {
            Assert.fail(DataTest.job_title + " is not found in the table.");
        }
    }

}
