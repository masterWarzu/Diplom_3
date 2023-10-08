package ru.yandex.praktikum.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Webdriver
{
    public static WebDriver getWebDriver(String browserName)
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        switch (browserName)
        {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                return new ChromeDriver(options);
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/yandexdriver.exe");
                return new ChromeDriver(options);
            default:
                throw new RuntimeException("Error: WebDriver not found");
        }
    }
}
