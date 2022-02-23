package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasicPage{

//	Login Page:
//	get metode za sve potrebne elemente
//	metodu koja prijavljuje korisnika na sistem - kao parametri se prosleđuju imejl i lozinka

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getLogin() {
		return this.driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div[2]/div[2]/ul/li[2]/a"));
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.name("username"));
	}

	public WebElement getPassword() {
		return this.driver.findElement(By.name("password"));
	}

	public WebElement getClickOnLogIn() {
		return this.driver.findElement(By.xpath("//*[@title = 'Login']"));
	}

	public void loginDemo(String email, String password) {
		getEmail().sendKeys(email);
		getPassword().sendKeys(password);
		getClickOnLogIn().click();
	}

}
