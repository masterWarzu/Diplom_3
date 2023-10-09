package ru.yandex.praktikum;

import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.Api;
import ru.yandex.praktikum.util.Webdriver;

import static org.hamcrest.CoreMatchers.equalTo;

public class LogoutTest
{
    static WebDriver driver;
    private String name = RandomStringUtils.randomAlphabetic(10);
    private String email = RandomStringUtils.randomAlphabetic(10) + "@sdff.com";
    private String password = RandomStringUtils.randomAlphabetic(6);

    @Before
    public void setUp()
    {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        driver = Webdriver.getWebDriver("chrome");
        Api.createUser(new User(email, password, name));
        driver.get("https://stellarburgers.nomoreparties.site/login");
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете.")
    public void clickProfileExitButtonTest()
    {
        LoginPage loginPage = new LoginPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.waitForLoginPage();
        loginPage.loginUser(email, password);
        homePage.waitForHomePage();
        headerPage.clickProfileButton();
        profilePage.waitForProfilePage();

        profilePage.clickExitButton();
        loginPage.waitForLoginPage();
        headerPage.clickLogoButton();
        homePage.waitForHomePage();

        String actual = homePage.getPrimaryButtonText();
        String expected = "Войти в аккаунт";

        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @After
    public void tearDown()
    {
        Api.deleteUserByLoginAndPassword(new User(email, password));
        driver.quit();
    }
}
