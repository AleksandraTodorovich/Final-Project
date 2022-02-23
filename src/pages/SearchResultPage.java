package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends BasicPage{
//		Search Result Page:
//		get metodu za sve rezultate pretrage //*[@class='product-name']/a
//		metodu koja vraća nazive svih jela dobijenih pretragom
//		metodu koja vraća broj rezultata pretrage


	public SearchResultPage(WebDriver driver) {
		super(driver);
	}

	public List<WebElement> getAllSearchResults() {
		return this.driver.findElements(By.xpath("//*[@class='product-name']/a"));
	}

	public String allMenuItems() {
		List<WebElement> menuItems = this.driver.findElements(By.xpath("//*[@class='product-name']/a"));
		String menuItem = null;
		for (int i = 0; i < menuItems.size(); i++) {
			menuItem = menuItems.get(i).getText();
		}
		return menuItem;
	}

	public double getAllResults() {
		List<WebElement> name = this.driver.findElements(By.xpath("//*[@class='product-name']/a"));
		return name.size();
	}
}
