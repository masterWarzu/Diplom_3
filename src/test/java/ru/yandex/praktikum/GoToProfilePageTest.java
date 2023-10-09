package ru.yandex.praktikum;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.Api;
import ru.yandex.praktikum.util.Webdriver;

public class GoToProfilePageTest
{
    static WebDriver driver;
    private String name = RandomStringUtils.randomAlphabetic(10);
    private String email = RandomStringUtils.randomAlphabetic(10) + "@sdff.com";
    private String password = RandomStringUtils.randomAlphabetic(6);

    @Before
    public void setUp()
    {
        driver = Webdriver.getWebDriver("chrome");
        driver.get("https://stellarburgers.nomoreparties.site");
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        Api.createUser(new User(email, password, name));
    }

    @Test
    @DisplayName("Переход в профиль по клику на «Личный кабинет»")
    public void clickProfileButtonTest()
    {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        headerPage.clickProfileButton();
        loginPage.loginUser(email, password);
        homePage.waitForHomePage();
        headerPage.clickProfileButton();

        profilePage.waitForProfilePage();
        Assert.assertTrue(profilePage.isProfileLinkEnabled());
    }

    @After
    public void tearDown()
    {
        Api.deleteUserByLoginAndPassword(new User(email, password));
        driver.quit();
    }
}
