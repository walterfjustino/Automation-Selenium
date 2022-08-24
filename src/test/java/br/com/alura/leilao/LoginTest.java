package br.com.alura.leilao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.alura.leilao.LoginPage.URL_LOGIN;
import static org.junit.jupiter.api.Assertions.*;

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

        assertFalse(this.loginPage.isLoginPage());
        assertEquals("fulano",this.loginPage.getLoggedUser());
    }
    @Test
    void should_Not_Be_Sign_In_When_The_Data_Is_Invalid() {
        loginPage.fillTheLoginPage("invalido","123");
        loginPage.logIn();

        Assertions.assertNull(this.loginPage.getLoggedUser());
        assertTrue(this.loginPage.isLoginPageError());
        assertTrue(this.loginPage.getContainsText("Usuário e senha inválidos."));
    }

    @Test
    void should_Not_Access_The_Restricted_Page_Without_Being_Logged_In() {
        this.loginPage.goToLeiloesPage();

        assertTrue(this.loginPage.isLoginPage());
        assertFalse(this.loginPage.getContainsText("Dados do Leilão"));
    }
}
