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

public class RegisterTest
{
    static WebDriver driver;
    private String name = RandomStringUtils.randomAlphabetic(10);
    private String email = RandomStringUtils.randomAlphabetic(10) + "@sdff.com";
    private String password = RandomStringUtils.randomAlphabetic(6);
    private String invalidPassword = RandomStringUtils.randomAlphabetic(5);

    @Before
    public void setUp()
    {
        driver = Webdriver.getWebDriver("chrome");
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    @DisplayName("Регистрация нового пользователя")
    public void registerTest()
    {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        registerPage.registerUser(name, email, password);
        loginPage.waitForLoginPage();
        loginPage.loginUser(email, password);
        homePage.waitForHomePage();

        String actual = homePage.getPrimaryButtonText();
        String expected = "Оформить заказ";

        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("Попытка регистрации нового пользователя с паролем менее 6 символов")
    public void registerWithShortPasswordTest()
    {
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.registerUser(name, email, invalidPassword);

        String actual = registerPage.getPasswordErrorText();
        String expected = "Некорректный пароль";

        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @After
    public void tearDown()
    {
        Api.deleteUserByLoginAndPassword(new User(email, password));
        driver.quit();
    }
}