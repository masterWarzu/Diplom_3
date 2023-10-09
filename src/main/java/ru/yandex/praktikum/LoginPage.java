package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage
{
    private WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public By enterButton = By.xpath(".//button[text()='Войти']");
    public By emailField = By.xpath(".//fieldset[1]//input");
    public By passwordField = By.xpath(".//fieldset[2]//input");
    public By loginPageHeader = By.xpath(".//*[text()='Вход']");
    public By registerPageButton = By.xpath(".//a[text() = 'Зарегистрироваться']");
    private By passwordRecoveryLink = By.xpath(".//a[text()='Восстановить пароль']");

    @Step("Заполнить поле email")
    public void fillEmailField(String email)
    {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнить поле пароля")
    public void fillPasswordField(String password)
    {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать на кнопку авторизации")
    public void clickEntranceButton()
    {
        driver.findElement(enterButton).click();
    }

    @Step("Нажать на кнопку регистрации")
    public void clickRegisterButton()
    {
        driver.findElement(registerPageButton).click();
    }

    @Step("Нажать на кнопку восстановления пароля")
    public void clickpasswordRecoveryLink()
    {
        driver.findElement(passwordRecoveryLink).click();
    }

    public void waitForLoginPage()
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(loginPageHeader));
    }

    @Step("Авторизоваться пользователем")
    public void loginUser(String email, String password)
    {
        fillEmailField(email);
        fillPasswordField(password);
        clickEntranceButton();
    }
}
