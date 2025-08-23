package com.longtester.hrm.pages;

import com.longtester.hrm.common.DataTest;
import com.longtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LocationPage {
    private WebDriver driver;
    private By topbarOrganization = By.xpath("//span[contains(@class,'topbar') and normalize-space()='Organization']");
    private By menuitemLocations = By.xpath("//a[contains(@class,'tab-link') and normalize-space()='Locations']");
    private By inputSearchLocation = By.xpath("//label[text()='Name']/parent::div/following-sibling::div/input");
    private By buttonSearch = By.xpath("//button[normalize-space()='Search']");
    private By buttonAdd = By.xpath("//button[normalize-space()='Add']");
    private By buttonEdit = By.xpath("//div[contains(@class,'table-body')]//div//button[2]");
    private By buttonDelete = By.xpath("//div[contains(@class,'table-body')]//div//button[1]");
    private By buttonConfirmDelete = By.xpath("//button[normalize-space()='Yes, Delete']");
    private By listLocation = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]");
    // Add New Location form
    private By inputLocationName = By.xpath("//label[text()='Name']/parent::div/following-sibling::div/input");
    private By selectCountry = By.xpath("//label[text()='Country']/parent::div/following-sibling::div//div[contains(@class,'select-wrapper')]");
    private By optionCountry = By.xpath("//div[@role='option']/span[text()='" + DataTest.country + "']");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");
    private By toastMessageSuccess = By.xpath("//div[contains(@class,'toast--success')]");
    public LocationPage(WebDriver driver) {
        this.driver = driver;
        new WebUI(driver);
    }
    public void clickMenuLocation() {
        WebUI.clickElement(topbarOrganization);
        WebUI.clickElement(menuitemLocations);
    }
    public void addLocation(String locationName) {
        clickMenuLocation();
        WebUI.clickElement(buttonAdd);
        WebUI.setText(inputLocationName, locationName);
        WebUI.clickElement(selectCountry);
        WebUI.scrollToElementAtTop(optionCountry);
        WebUI.highlightElement(optionCountry);
        WebUI.clickElement(optionCountry);
        WebUI.clickElement(buttonSave);
    }
    public void verifySuccessMessageIsDisplayed() {
        Assert.assertTrue(WebUI.isElementDisplayed(toastMessageSuccess));
        WebUI.highlightElement(toastMessageSuccess);
    }
    public void verifyLocationIsDisplayedInTable(String location) {
        WebUI.sleep(5);
        WebUI.setText(inputSearchLocation, location);
        WebUI.clickElement(buttonSearch);
        WebUI.sleep(2);
        WebUI.highlightElement(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + location + "']"));
        Assert.assertTrue(WebUI.isElementDisplayed(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + location + "']")));
    }

}
