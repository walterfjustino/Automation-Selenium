package br.com.alura.leilao;

import org.junit.After;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static br.com.alura.leilao.LoginPage.URL_LOGIN;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    void beforeEach() {
        this.loginPage = new LoginPage();
        this.loginPage.browser.navigate().to(URL_LOGIN);
    }
    @AfterEach
    void afterEach() {
        this.loginPage.browser.quit();
    }
    @Test
    void should_Sign_In_When_The_Data_Is_Valid() {
        loginPage.fillTheLoginPage("fulano","pass");
        loginPage.logIn();

        Assertions.assertFalse(this.loginPage.isLoginPage());
        Assertions.assertEquals("fulano",this.loginPage.getLoggedUser());
    }
    @Test
    void should_Not_Be_Sign_In_When_The_Data_Is_Invalid() {
        loginPage.fillTheLoginPage("invalido","123");
        loginPage.logIn();

        Assertions.assertNull(this.loginPage.getLoggedUser());
        Assertions.assertTrue(this.loginPage.isLoginPageError());
        Assertions.assertTrue(this.loginPage.getContainsText("Usuário e senha inválidos."));
    }

    @Test
    void should_Not_Access_The_Restricted_Page_Without_Being_Logged_In() {
        this.loginPage.goToLeiloesPage();

        Assertions.assertTrue(this.loginPage.isLoginPage());
        Assertions.assertFalse(this.loginPage.getContainsText("Dados do Leilão"));
    }
}
