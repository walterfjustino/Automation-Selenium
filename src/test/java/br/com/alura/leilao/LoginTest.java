package br.com.alura.leilao;

import org.junit.After;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    public static final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
    }

    @BeforeEach
    void beforeEach() {
        this.browser = new ChromeDriver();
        browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    void afterEach() {
        this.browser.quit();
    }

    @Test
    void should_Sign_In_When_The_Data_Is_Valid() {
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
        Assertions.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());

    }

    @Test
    void should_Not_Be_Sign_In_When_The_Data_Is_Invalid() {
        browser.findElement(By.id("username")).sendKeys("syzsyfi");
        browser.findElement(By.id("password")).sendKeys("12345");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN + "?error"));
        Assertions.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        Assertions.assertThrows(NoSuchElementException.class, ()-> browser.findElement(By.id("usuario-logado")));

    }


    @Test
    void should_Not_Access_The_Restricted_Page_Without_Being_Logged_In() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");

        Assertions.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
        Assertions.assertFalse(browser.getPageSource().contains("Dados do Leilão"));

    }
}
