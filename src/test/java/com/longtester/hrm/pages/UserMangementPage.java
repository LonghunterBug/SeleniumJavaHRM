package com.longtester.hrm.pages;

import com.longtester.hrm.common.DataTest;
import com.longtester.keywords.WebUI;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class UserMangementPage {
    private WebDriver driver;
    private By buttonAddNewUser = By.xpath("//button[normalize-space()='Add']");
    private By inputSearchUserName = By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input");
    private By buttonSearch = By.xpath("//button[normalize-space()='Search']");
    private By buttonEdit = By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[6]//button[2]");
    // Add New User form
    private By selectUserRole = By.xpath("//label[text()='User Role']/parent::div/following-sibling::div//div[contains(@class,'select-text--active')]");
    private By selectStatus = By.xpath("//label[text()='Status']/parent::div/following-sibling::div//div[contains(@class,'select-text--active')]");
    private By inputEmployeeName = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private By inputUserName = By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input");
    private By inputPassword = By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input");
    private By inputConfirmPassword = By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");
    private By toastMessageSuccess = By.xpath("//div[contains(@class,'toast--success')]");


    public UserMangementPage(WebDriver driver) {
        this.driver = driver;
        new WebUI(driver);
    }

    public void addNewUser(String role, String status, String employee_name, String username, String password, String confirmpassword) {
        WebUI.clickElement(buttonAddNewUser);
        WebUI.clickElement(selectUserRole);
        WebUI.clickElement(By.xpath("//label[text()='User Role']/parent::div/following-sibling::div//span[text()='" + role + "']"));
        WebUI.setText(inputEmployeeName, employee_name);
        WebUI.waitForElementVisible(By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//span[text()='" + employee_name + "']"));
        WebUI.clickElement(By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//span[text()='" + employee_name + "']"));
        WebUI.clickElement(selectStatus);
        WebUI.clickElement(By.xpath("//label[text()='Status']/parent::div/following-sibling::div//span[text()='" + status + "']"));
        WebUI.setText(inputUserName, username);
        WebUI.setText(inputPassword, password);
        WebUI.setText(inputConfirmPassword, confirmpassword);
        WebUI.clickElement(buttonSave);
    }

    public void verifySuccessMessageIsDisplayed() {
        Assert.assertTrue(WebUI.isElementDisplayed(toastMessageSuccess));
    }
    public void verifyUserIsDisplayedInTable(){
        WebUI.setText(inputSearchUserName, DataTest.username_addnew);
        WebUI.clickElement(buttonSearch);
        Assert.assertTrue(WebUI.isElementDisplayed(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + DataTest.username_addnew + "']")));
    }
    public void verifyEmployeeNameIsUpdated(){
        WebUI.setText(inputSearchUserName, DataTest.username_addnew);
        WebUI.clickElement(buttonSearch);
        String actual_employeename = WebUI.getElementText(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[4]"));
        Assert.assertEquals(actual_employeename,DataTest.update_employeename);
    }

    public void editEmployeeName(String username) {
        searchByUserName(username);
        WebUI.clickElement(buttonEdit);
        WebUI.clearText(inputEmployeeName);
        WebUI.sleep(3);
        WebUI.setText(inputEmployeeName, DataTest.update_employeename);
        WebUI.sleep(3);
//        WebUI.waitForElementVisible(By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//span[text()='" + DataTest.update_employeename + "']"));
//        WebUI.clickElement(By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//span[text()='" + DataTest.update_employeename + "']"));
//        WebUI.clickElement(buttonSave);
    }

    public void searchByUserName(String username) {
        WebUI.setText(inputSearchUserName, username);
        WebUI.clickElement(buttonSearch);
        WebUI.waitForElementVisible(By.xpath("//div[contains(@class,'table-body')]//div[contains(@class,'table-row')]/div[2]/div[text()='" + username + "']"));
    }


}
