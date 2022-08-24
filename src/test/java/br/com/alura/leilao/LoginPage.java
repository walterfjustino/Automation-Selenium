package br.com.alura.leilao;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObject{

    public static final String URL_LOGIN = "http://localhost:8080/login";

    public static final String URL_LEILOES = "http://localhost:8080/leiloes/2";

    public LoginPage() {
        super(null);
        this.browser.navigate().to(URL_LOGIN);
    }

    public void fillTheLoginPage(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }
    public void logIn() {
        browser.findElement(By.id("login-form")).submit();
    }
    public boolean isLoginPage() {
        return browser.getCurrentUrl().equals(URL_LOGIN);
    }
    public boolean isLoginPageError() {
        return browser.getCurrentUrl().equals(URL_LOGIN+"?error");
    }
    public String getLoggedUser() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
    public void goToLeiloesPage(){
        browser.navigate().to(URL_LEILOES);
    }

    public boolean getContainsText(String text) {
        return  browser.getPageSource().contains(text);
    }
}
