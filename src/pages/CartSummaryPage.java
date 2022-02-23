package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartSummaryPage extends BasicPage {
	// get metodu za Clear All dugme
	// metodu koja briše sve stavke iz korpe klikom na Clear All dugme

	public CartSummaryPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getClearAll() {
		return this.driver.findElement(By.xpath("//*[@id='cartSummary']/div/div[1]/a[2]"));
	}

	public void clickOnClearAll() {
		getClearAll().click();
	}
}
