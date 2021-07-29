package pages;

import com.codeborne.selenide.Condition;
import models.AccountCreation;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage extends BasePage {
    private final static String URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation";
    private final static By GENDER_MALE = By.id("id_gender1");
    private final static By GENDER_FEMALE = By.id("id_gender2");
    private final static By FIRST_NAME = By.id("customer_firstname");
    private final static By LAST_NAME = By.id("customer_lastname");
    private final static By PASSWORD = By.id("passwd");
    private final static By DATE_OF_BIRTH_DAYS = By.id("days");
    private final static By DATE_OF_BIRTH_MONTHS = By.id("months");
    private final static By DATE_OF_BIRTH_YEARS = By.id("years");
    private final static By ADDRESS = By.id("address1");
    private final static By CITY = By.id("city");
    private final static By STATE = By.id("id_state");
    private final static By ZIP_CODE = By.id("postcode");
    private final static By COUNTRY = By.id("id_country");
    private final static By ADDITIONAL_INFORMATION = By.id("other");
    private final static By MOBILE_PHONE = By.id("phone_mobile");
    private final static By ADDRESS_ALIAS = By.id("alias");
    private final static By SUBMIT_REGISTRATION = By.id("submitAccount");
    private final static By ERROR_MESSAGE = By.id("create_account_error");

    public RegistrationPage openPage() {
        open(URL);
        isPageOpened();
        return this;
    }

    public RegistrationPage isPageOpened() {
        $(PAGE_VALIDATION).waitUntil(Condition.visible, 30000);
        return this;
    }

    public RegistrationPage fillRegistrationForm(AccountCreation accountCreation) {
        isPageOpened();
        if (!accountCreation.getGender().equals("Mr.")) {
            $(GENDER_FEMALE).click();
        } else {
            $(GENDER_MALE).click();
        }
        $(FIRST_NAME).sendKeys(accountCreation.getFirst_name());
        $(LAST_NAME).sendKeys(accountCreation.getLast_name());
        $(PASSWORD).setValue(accountCreation.getPassword());
        $(DATE_OF_BIRTH_DAYS).selectOption(accountCreation.getDay());
        $(DATE_OF_BIRTH_MONTHS).selectOption(accountCreation.getMonth());
        $(DATE_OF_BIRTH_YEARS).selectOptionByValue(accountCreation.getYear());
        $(ADDRESS).sendKeys(accountCreation.getAddress());
        $(CITY).sendKeys(accountCreation.getCity());
        $(STATE).selectOptionContainingText(accountCreation.getState());
        $(ZIP_CODE).sendKeys(accountCreation.getZip_code());
        $(COUNTRY).selectOption(accountCreation.getCountry());
        $(ADDITIONAL_INFORMATION).sendKeys(accountCreation.getAdditional_information());
        $(MOBILE_PHONE).sendKeys(accountCreation.getMobile_phone());
        $(ADDRESS_ALIAS).clear();
        $(ADDRESS_ALIAS).sendKeys(accountCreation.getAddress_alias());
        return this;
    }

    public MyAccountPage submitRegistration() {
        isPageOpened();
        $(SUBMIT_REGISTRATION).click();
        MyAccountPage myAccountPage = new MyAccountPage();
        myAccountPage.isPageOpened();
        return myAccountPage;
    }

    public LoginPage errorAccountCreation(String expectedErrorMessage){
        Assert.assertEquals($(ERROR_MESSAGE).getText(), expectedErrorMessage, "Error message is not displayed");
        return new LoginPage();
    }
}