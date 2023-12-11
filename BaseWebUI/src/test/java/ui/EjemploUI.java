package ui;

import org.openqa.selenium.By;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class EjemploUI extends PageObject {

	private static final By TEXT_USER = By.id("user-name");
	private static final By TEXT_PASS = By.id("password");
	private static final By BOTON_LOGIN = By.id("login-button");
	private static final By TEXT_ERROR = By.className("error");
	private static final By TEXT_TITTLE = By.className("title");

	public WebElementFacade getTextUser() {
		return element(TEXT_USER);
	}

	public WebElementFacade getTextPass() {
		return element(TEXT_PASS);
	}

	public WebElementFacade getBotonLogin() {
		return element(BOTON_LOGIN);
	}

	public WebElementFacade getTextError() {
		return element(TEXT_ERROR);
	}

	public WebElementFacade getTextTittle() {
		return element(TEXT_TITTLE);
	}
}
