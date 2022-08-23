package br.com.alura.leilao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

//    @BeforeEach
//    void beforeEach() {
//        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
//        WebDriver browser = new ChromeDriver();
//        browser.navigate().to("http://localhost:8080/login");
//
//    }


    @Test
    void should_Sign_In_When_The_Data_Is_Valid() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/login");
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertFalse(browser.getCurrentUrl().equals("http://localhost:8080/login"));
        Assertions.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
        browser.quit();
    }

    @Test
    void should_Not_Be_Sign_In_When_The_Data_Is_Invalid() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/login");
        browser.findElement(By.id("username")).sendKeys("syzsyfi");
        browser.findElement(By.id("password")).sendKeys("12345");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
        Assertions.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        Assertions.assertThrows(NoSuchElementException.class, ()-> browser.findElement(By.id("usuario-logado")));
        browser.quit();
    }
}
