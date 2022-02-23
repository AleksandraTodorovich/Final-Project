package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSistemPage extends BasicPage {

	private WebDriverWait wait;
//		Notification Sistem Page:
//		get metodu za element koji prikazuje poruku
//		//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]
//		metodu koja vraća poruku koja se nalazi u obaveštenju
//		metodu koja čeka da obaveštenje nestane
//		čeka se da element //*[contains(@class, 'system_message')]
//		za atribut style dobije vrednost  "display: none;"

	public NotificationSistemPage(WebDriver driver, WebDriverWait wait) {
		super(driver);
		this.wait = wait;
	}

	public WebElement getElementThatShowsMessage() {
		return this.driver.findElement(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

	public String getMessage() {
		return getElementThatShowsMessage().getText();
	}

	public void waitForElementToDissapire() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.attributeToBe(By.xpath("//*[contains(@class, 'system_message')]"), "style",
				"display: none;"));
	}

}
