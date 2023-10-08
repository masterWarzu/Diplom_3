package ru.yandex.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage
{
    private WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
    }

    public By homePageHeader = By.xpath(".//*[text()='Соберите бургер']");
    private final By loginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    public By bunsConstructorButton = By.xpath(".//span[text()='Булки']");
    public By saucesConstructorButton = By.xpath(".//span[text()='Соусы']");
    public By fillingsConstructorButton = By.xpath(".//span[text()='Начинки']");
    public By checkedConstructorButton = By.className("tab_tab_type_current__2BEPc");
    public By primaryButton = By.className("button_button_type_primary__1O7Bx");

    public void waitForHomePage()
    {
        new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOfElementLocated(homePageHeader));
    }

    public void loginButtonClick()
    {
        driver.findElement(loginButton).click();
    }

    public void bunsConstructorButtonClick()
    {
        driver.findElement(bunsConstructorButton).click();
    }

    public void saucesConstructorButtonClick()
    {
        driver.findElement(saucesConstructorButton).click();
    }

    public void fillingsConstructorButtonClick()
    {
        driver.findElement(fillingsConstructorButton).click();
    }

    @Step("Проверить текст выбранной секции в конструкторе")
    public String getCheckedConstructorButtonText()
    {
        return driver.findElement(checkedConstructorButton).getText();
    }

    public boolean isConstructorHeaderDisplayed()
    {
        return driver.findElement(homePageHeader).isDisplayed();
    }

    @Step("Проверить текст кнопки на главном экране")
    public String getPrimaryButtonText()
    {
        return driver.findElement(primaryButton).getText();
    }
}
