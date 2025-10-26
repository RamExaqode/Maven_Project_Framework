package tests;

import org.testng.annotations.Test;
import base.BaseTest;
import pages.RegisterPage;
import utils.ConfigReader;
import utils.ExtentTestManager;

public class RegisterTest extends BaseTest {

    @Test(dataProvider = "registrationData")
    public void fillRegistrationForm(String firstName, String lastName, String address, String email, String phone) {
        ExtentTestManager.getTest().info("Navigating to registration page");
        ExtentTestManager.getTest().info("Filling registration form");

        driver.get(ConfigReader.get("baseUrl"));

        RegisterPage registerPage = new RegisterPage(driver);

        ExtentTestManager.getTest().info("Entering first name: " + firstName);
        registerPage.enterFirstName(firstName);

        ExtentTestManager.getTest().info("Entering last name: " + lastName);
        registerPage.enterLastName(lastName);

        ExtentTestManager.getTest().info("Entering address: " + address);
        registerPage.enterAddress(address);

        ExtentTestManager.getTest().info("Entering email: " + email);
        registerPage.enterEmail(email);

        ExtentTestManager.getTest().info("Entering phone number: " + phone);
        registerPage.enterPhone(phone);

        ExtentTestManager.getTest().pass("Form filled successfully with provided data");
    }

    @org.testng.annotations.DataProvider(name = "registrationData")
    public Object[][] getData() {
        return new Object[][] {
            {"Ram", "Marshivane", "123 Street, Pune", "ram@example.com", "9876543210"},
            {"Sita", "Verma", "456 Lane, Mumbai", "sita@example.com", "9123456789"},
            {"Lakshman", "Sharma", "789 Road, Delhi", "lakshman@example.com", "9988776655"}
        };
    }
}
