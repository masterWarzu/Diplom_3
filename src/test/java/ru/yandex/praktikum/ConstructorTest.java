package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.util.Webdriver;

import static org.hamcrest.CoreMatchers.equalTo;

public class ConstructorTest
{
    static WebDriver driver;

    @Before
    public void setUp()
    {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        driver = Webdriver.getWebDriver("chrome");
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @Test
    @DisplayName("Переключение секции на 'Булки' в конструкторе")
    public void clickBunsConstructorButton()
    {
        HomePage homePage = new HomePage(driver);

        homePage.saucesConstructorButtonClick();
        homePage.bunsConstructorButtonClick();

        String actual = homePage.getCheckedConstructorButtonText();
        String expected = "Булки";

        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("Переключение секции на 'Соусы' в конструкторе")
    public void clickSaucesConstructorButton()
    {
        HomePage homePage = new HomePage(driver);

        homePage.saucesConstructorButtonClick();

        String actual = homePage.getCheckedConstructorButtonText();
        String expected = "Соусы";

        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("Переключение секции на 'Начинки' в конструкторе")
    public void clickFillingsConstructorButton()
    {
        HomePage homePage = new HomePage(driver);

        homePage.fillingsConstructorButtonClick();

        String actual = homePage.getCheckedConstructorButtonText();
        String expected = "Начинки";

        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
}
