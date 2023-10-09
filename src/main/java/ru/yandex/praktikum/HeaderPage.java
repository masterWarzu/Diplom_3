package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage
{
    private WebDriver driver;

    public HeaderPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private By constructorPageButton = By.xpath(".//*[text()= 'Конструктор']");
    private By logoButton = By.className("AppHeader_header__logo__2D0X2");
    private By profilePageButton = By.linkText("Личный Кабинет");

    @Step("Нажать на ссылку страницы конструктора")
    public void clickMainPage()
    {
        driver.findElement(constructorPageButton).click();
    }

    @Step("Нажать на кнопку ссылку личного кабинета")
    public void clickProfileButton()
    {
        driver.findElement(profilePageButton).click();
    }

    @Step("Нажать на логотип")
    public void clickLogoButton()
    {
        driver.findElement(logoButton).click();
    }
}
