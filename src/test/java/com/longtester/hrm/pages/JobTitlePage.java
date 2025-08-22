package com.longtester.hrm.pages;

import com.longtester.hrm.common.DataTest;
import com.longtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class JobTitlePage {
    private WebDriver driver;
    private By headerJobTitle = By.xpath("//h6[normalize-space()='Job Titles']");
    private By topbarJob = By.xpath("//span[contains(@class,'topbar') and normalize-space()='Job']");
    private By menuitemJobTitles = By.xpath("//a[contains(@class,'tab-link') and normalize-space()='Job Titles']");
    private By menuitemJobCategories = By.xpath("//a[contains(@class,'tab-link') and normalize-space()='Job Categories']");
    private By menuitemEmploymentStatus = By.xpath("//a[contains(@class,'tab-link') and normalize-space()='Employment Status']");
    private By buttonAdd = By.xpath("//button[normalize-space()='Add']");
    private By buttonEdit = By.xpath("//div[contains(@class,'table-body')]//div[4]//button[2]");
    private By buttonDelete = By.xpath("//div[contains(@class,'table-body')]//div[4]//button[1]");
    private By listJobTitle = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]");
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

    public void editJobTitle(String title) {
        int index = 0;
        WebUI.sleep(3);
        boolean check = false;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> jobtitle = WebUI.getWebElements(listJobTitle);
        for (int i = 0; i < jobtitle.size(); i++) {
            if (jobtitle.get(i).getText().equals(title)) {
                js.executeScript("arguments[0].scrollIntoView(true);", jobtitle.get(i));
                check = true;
                index = i;
                WebUI.logConsole("Index of job title: " + i);// Adjust index for the button position
                break;
            }
        }
        if (!check) {
            Assert.fail("Job title '" + title + "' not found in the list.");
        }
        List<WebElement> listButtonEdit = WebUI.getWebElements(buttonEdit);
        WebUI.logConsole("Click edit button for job title at index: " + index);
        js.executeScript("arguments[0].style.border='3px solid red';",listButtonEdit.get(index));
        listButtonEdit.get(index).click();
        WebUI.clearTextWithKey(inputJobTitle);
        WebUI.setText(inputJobTitle, DataTest.job_title_edit);
        WebUI.clickElement(buttonSave);
    }
    public void verifyJobTitleIsUpdated(){
        WebUI.sleep(5);
        boolean check = false;
        List<WebElement> e = WebUI.getWebElements(listJobTitle);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (WebElement element : e) {
            if (element.getText().equals(DataTest.job_title_edit)) {
                js.executeScript("arguments[0].scrollIntoView(true);", element);
                js.executeScript("arguments[0].style.border='3px solid red';", element);
                WebUI.sleep(2); // Nếu quay video thì cần sleep
                Assert.assertTrue(element.isDisplayed(), DataTest.job_title_edit + " is not displayed in the table.");
                check = true;
                break;
            }
        }
        if (!check) {
            Assert.fail(DataTest.job_title_edit + " is not found in the table.");
        }
    }

    public void verifySuccessMessageIsDisplayed() {
        Assert.assertTrue(WebUI.isElementDisplayed(toastMessageSuccess));
        WebUI.highlightElement(toastMessageSuccess);
    }

    public void verifyJobTitleIsDisplayedInTable() {
        WebUI.sleep(5);
        boolean check = false;
        List<WebElement> e = WebUI.getWebElements(listJobTitle);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (WebElement element : e) {
            if (element.getText().equals(DataTest.job_title)) {
                js.executeScript("arguments[0].scrollIntoView(true);", element);
                js.executeScript("arguments[0].style.border='3px solid red';", element);
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
