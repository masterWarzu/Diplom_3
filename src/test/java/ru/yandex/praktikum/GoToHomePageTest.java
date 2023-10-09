package ru.yandex.praktikum;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.util.Webdriver;


public class GoToHomePageTest
{
    static WebDriver driver;

    @Before
    public void setUp()
    {
        driver = Webdriver.getWebDriver("chrome");
        driver.get("https://stellarburgers.nomoreparties.site/feed");
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    @DisplayName("Переход на страницу конструктора по клику на «Конструктор»")
    public void clickProfileButtonTest()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.clickMainPage();
        homePage.waitForHomePage();

        Assert.assertTrue(homePage.isConstructorHeaderDisplayed());
    }

    @Test
    @DisplayName("Переход на страницу конструктора по клику на логотип")
    public void clickMainLogoButtonTest()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.clickLogoButton();
        homePage.waitForHomePage();

        Assert.assertTrue(homePage.isConstructorHeaderDisplayed());
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
}
