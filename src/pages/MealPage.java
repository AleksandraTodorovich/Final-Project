package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MealPage extends BasicPage{
	//	Meal Page:
	//	get metode za sve potrebne elemente
	//	metodu koja dodaje jelo u korpu - kao parametar se prosleđuje količina
	//	metodu koja jelo dodaje u omiljena jela, klikom na dugme Favorite 
	
	public MealPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public WebElement getAddMealToYourCart() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'btn--primary')]"));

	}

	public WebElement getQuantity() {
		return this.driver.findElement(By.name("product_qty"));

	}

	public void addMeal(String quantity) {
		this.getQuantity().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		this.getQuantity().sendKeys(quantity);
		getAddMealToYourCart().click();
	}

	public WebElement addMealToFavorite() {

		return this.driver.findElement(By.id("item_119"));
	}

}
