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


public class LoginTest
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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginFromHomePageTest()
    {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.loginButtonClick();
        loginPage.loginUser(email, password);
        homePage.waitForHomePage();

        String actual = homePage.getPrimaryButtonText();
        String expected = "Оформить заказ";

        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginFromProfilePageTest()
    {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.clickProfileButton();
        loginPage.loginUser(email, password);
        homePage.waitForHomePage();

        String actual = homePage.getPrimaryButtonText();
        String expected = "Оформить заказ";

        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginFromRegisterPageTest()
    {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        headerPage.clickProfileButton();
        loginPage.clickRegisterButton();
        registerPage.clickLoginButton();
        loginPage.loginUser(email, password);
        homePage.waitForHomePage();

        String actual = homePage.getPrimaryButtonText();
        String expected = "Оформить заказ";

        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginFromPasswordRecoveryPageTest()
    {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);

        headerPage.clickProfileButton();
        loginPage.clickpasswordRecoveryLink();
        passwordRecoveryPage.loginButtonClick();
        loginPage.loginUser(email, password);
        homePage.waitForHomePage();

        String actual = homePage.getPrimaryButtonText();
        String expected = "Оформить заказ";

        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @After
    public void tearDown()
    {
        Api.deleteUserByLoginAndPassword(new User(email, password));
        driver.quit();
    }
}
