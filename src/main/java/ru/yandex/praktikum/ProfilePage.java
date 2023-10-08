package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage
{
    private WebDriver driver;
    public ProfilePage(WebDriver driver)
    {
        this.driver = driver;
    }
    private By profileButton = By.className("Account_link_active__2opc9");
    private By exitButton = By.xpath(".//button[text()='Выход']");
    public boolean isProfileLinkEnabled()
    {
        return driver.findElement(profileButton).isEnabled();
    }

    public void clickExitButton()
    {
        driver.findElement(exitButton).click();
    }

    public void waitForProfilePage()
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }
}
