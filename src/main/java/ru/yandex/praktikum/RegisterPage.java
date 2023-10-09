package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage
{
    private WebDriver driver;

    public RegisterPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public By nameField = By.xpath(".//fieldset[1]//input");
    public By emailField = By.xpath(".//fieldset[2]//input");
    public By passwordField = By.xpath(".//input[@type='password']");
    public By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    public By errorIncorrectPassword = By.className("input__error");
    public By loginPageButton = By.className("Auth_link__1fOlj");

    @Step("Заполнить поле имени")
    public void fillNameField(String name)
    {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Заполнить поле Email")
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

    @Step("Нажатие на кнопку регистрации")
    public void clickSignUpButton()
    {
        driver.findElement(registrationButton).click();
    }

    @Step("Нажать на ссылку страницы авторизации")
    public void clickLoginButton()
    {
        driver.findElement(loginPageButton).click();
    }

    @Step("Зарегистрировать пользователя")
    public void registerUser(String name, String email, String password)
    {
        fillNameField(name);
        fillEmailField(email);
        fillPasswordField(password);
        clickSignUpButton();
    }

    @Step("Проверить текст ошибки у поля пароля")
    public String getPasswordErrorText()
    {
        return driver.findElement(errorIncorrectPassword).getText();
    }
}
