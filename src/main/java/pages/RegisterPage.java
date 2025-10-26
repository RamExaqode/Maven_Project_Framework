package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    WebDriver driver;
    static Logger log = Logger.getLogger(RegisterPage.class);

    // Constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By firstName = By.cssSelector("input[placeholder='First Name']");
    By lastName = By.cssSelector("input[placeholder='Last Name']");
    By address = By.cssSelector("textarea[ng-model='Adress']");
    By email = By.cssSelector("input[type='email']");
    By phone = By.cssSelector("input[type='tel']");

    // Actions
    public void enterFirstName(String fname) {
        log.info("Entering first name: " + fname);
        driver.findElement(firstName).sendKeys(fname);
    }

    public void enterLastName(String lname) {
        log.info("Entering last name: " + lname);
        driver.findElement(lastName).sendKeys(lname);
    }

    public void enterAddress(String addr) {
        log.info("Entering address: " + addr);
        driver.findElement(address).sendKeys(addr);
    }

    public void enterEmail(String mail) {
        log.info("Entering email: " + mail);
        driver.findElement(email).sendKeys(mail);
    }

    public void enterPhone(String number) {
        log.info("Entering phone number: " + number);
        driver.findElement(phone).sendKeys(number);
    }
}
