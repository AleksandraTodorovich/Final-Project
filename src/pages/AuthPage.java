package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthPage extends BasicPage {

	// get metode za sve potrebne elemente sa stranice
	// metodu koja odjavljuje korisnika sa sistema

	public AuthPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getMyAccountDropdown() {
		return this.driver.findElement(By.className("user-trigger-js"));
	}

	public WebElement getMyAccount() {
		return this.driver.findElement(By.xpath("//*[@class = 'my-account-dropdown']/ul/li"));
	}

	public WebElement getLogout() {
		return this.driver.findElement(By.xpath("//*[@class = 'my-account-dropdown']/ul/li[2]"));

	}

	public void logOut() {
		getLogout().click();
	}
}
