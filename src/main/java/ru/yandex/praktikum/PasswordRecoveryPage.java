package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordRecoveryPage
{
    private WebDriver driver;
    public PasswordRecoveryPage(WebDriver driver)
    {
        this.driver = driver;
    }
    private final By loginButton = By.className("Auth_link__1fOlj");

    public void loginButtonClick()
    {
        driver.findElement(loginButton).click();
    }
}
