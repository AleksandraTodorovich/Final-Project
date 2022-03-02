package pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProfilePage extends BasicPage{
//  Profile Page:
//	get metode za sve potrebne elemente
//	sve elemente za izmenu osnovnih informacija kao i 
//	sve elemente potrebne za rad sa profilnom slikom
//	da bi se na stranici pojavio element input type="file" potrebno je da se prvo izvrši JavaScript 
//	kod koji vrši akciju klik na Upload dugme 
//	Skripta: arguments[0].click();
//	metodu koja otprema profilnu sliku - kao parametar se prosleđuje putanja do fajla, tj. u ovom 
//	slučaju do slike
//	metodu koja briše profilnu sliku
//	klikom na Remove dugme 
//	Preporuka: izvršite JavaScript kod arguments[0].click(); nad tim dugmetom
//	metodu koja menja sve osnovne informacije korisnika - kao parametri se prosleđuju sve potrebne 
//	informacije
	
	private JavascriptExecutor js;

	public ProfilePage(WebDriver driver, JavascriptExecutor js) {
		super(driver);
		this.js = js;
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));

	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));

	}

	public WebElement getEmail() {
		return this.driver.findElement(By.name("user_email"));

	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));

	}

	public WebElement getPhone() {
		return this.driver.findElement(By.name("user_phone"));

	}

	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));

	}

	public WebElement getCountry() {
		return this.driver.findElement(By.id("user_country_id"));

	}

	public WebElement getState() {
		return this.driver.findElement(By.id("user_state_id"));

	}

	public WebElement getCity() {
		return this.driver.findElement(By.id("user_city"));

	}

	public WebElement getCurrentPassword() {
		return this.driver.findElement(By.name("current_password"));

	}

	public WebElement getNewPassword() {
		return this.driver.findElement(By.name("new_password"));
	}

	public WebElement getConfirmMessage() {
		return this.driver.findElement(By.name("conf_new_password"));
	}

	public WebElement getSave() {
		return this.driver.findElement(By.name("btn_submit"));
	}

	public WebElement getUpoloadButton() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a"));

	}

	public void photoUpload(String photo) throws IOException {
		getUpoloadButton();
		WebElement upload = driver.findElement(By.xpath("//input[@type = 'file']"));
		File file = new File(photo);
		driver.findElement(By.xpath("//input[@type = 'file']")).sendKeys(file.getCanonicalPath());
	}

	public WebElement getRemovePhoto() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a[2]"));
		// WebElement e = this.driver.findElement(By.xpath("//*[@title = 'Remove']/i"));
		// js.executeScript("arguments[0].click();", e);
	}

	public void changeAllAccountData(String firstName, String lastName, String address, String phone, String zipCode,
			String country, String state, String city) throws InterruptedException {
		Select select = new Select(null);
		Thread.sleep(2000);
		this.getFirstName().clear();
		this.getLastName().clear();
		this.getAddress().clear();
		this.getPhone().clear();
		this.getZipCode().clear();
		this.getFirstName().sendKeys(firstName);
		this.getLastName().sendKeys(lastName);
		this.getAddress().sendKeys(address);
		this.getPhone().sendKeys(phone);
		this.getZipCode().sendKeys(zipCode);
		select = new Select(getCountry());
		select.selectByVisibleText(country);
		select = new Select(getState());
		select.selectByVisibleText(state);
		select = new Select(getCity());
		select.selectByVisibleText(city);

	}

}
