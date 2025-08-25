package com.longtester.hrm.pages;

import com.longtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeePage {
    private WebDriver driver;
    private By buttonAddNewEmployee = By.xpath("");
    private By inputSearchID = By.xpath("");
    private By buttonSearch = By.xpath("");
    // Add New Employee form
    private By inputFirstName = By.xpath("");
    private By inputMiddleName = By.xpath("");
    private By inputLastName = By.xpath("");
    private By inputID = By.xpath("");
    private By buttonSave = By.xpath("");
    private By toastMessageSuccess = By.xpath("//div[contains(@class,'toast--success')]");

    public EmployeePage(WebDriver driver){
        this.driver = driver;
        new WebUI(driver);
    }
    public void addNewEmployee(String firstname,String middlename,String lastname,String id){
        WebUI.clickElement(buttonAddNewEmployee);
        WebUI.setText(inputFirstName,firstname);
        WebUI.setText(inputMiddleName,middlename);
        WebUI.setText(inputLastName,lastname);
        WebUI.setText(inputID,id);
        WebUI.clickElement(buttonSave);
    }
    public void searchByID(String id) {
        WebUI.setText(inputSearchID, id);
        WebUI.clickElement(buttonSearch);
        WebUI.waitForElementVisible(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + id + "']"));
    }
    public void verifyEmployeeIsDisplayedInTable(String id){
        WebUI.sleep(5);
        searchByID(id);
        WebUI.highlightElement(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + id + "']"));
        By textfirtname_middlename = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[3]/div");
        WebUI.verifyDisplay(textfirtname_middlename,WebUI.isElementDisplayed(textfirtname_middlename),"FirstName & MiddleName not display");
    }
}
