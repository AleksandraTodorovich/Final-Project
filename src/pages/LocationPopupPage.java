package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LocationPopupPage extends BasicPage {

	public JavascriptExecutor js;
//		Location Popup Page:
//		get metoda za element koji prikazuje lokaciju u hederu 
//		get metodu za close element
//		get metode potrebne za implementaciju metode u kojoj se postavlja lokacija
//		getKeyword()
//		//*[@id='locality_keyword']
//		getLocationItem(String locationName)
//		//li/a[contains(text(), '" + locationName + "')]/..
//		i nije greska na kraju postoje dve tacke!
//		getLocationInput()
//		//*[@id='location_id']
//		getSubmit()
//		//*[@name='btn_submit']
//		metodu koja otvara iskačući dijalog
//		klikom na lokaciju iz hedera
//		metodu koja postavlja lokaciju - naziv lokacije (locationName) se prosleđuje kao parametar
//		metode
//		metoda prvo klikne na element keyword element
//		čita vrednost data-value atributa location item elementa
//		postavlja lokaciju izvršavajući JavaScript kod
//		Skripta: arguments[0].value=arguments[1]
//		prvi argument skripte je location input
//		drugi argument skripte je vrednost pročitanog atributa iz drugog koraka.
//		Klikće na submit element preko skripte arguments[0].click();
//		metodu koja zatvara iskačući dijalog, klikom na X dugme

	public LocationPopupPage(WebDriver driver, JavascriptExecutor js) {
		super(driver);
		this.js = js;
	}

	public WebElement getSelectLocation() {
		return driver.findElement(By.className("location-selector"));
	}

	public WebElement getCloseLocationBox() {
		return driver.findElement(By.xpath("//*[@id=\"location-popup\"]/div/div/div/div/a"));
	}

	public WebElement getKeyword() {
		return driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}

	public WebElement getLocationItem(String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

	public void openSelectLocation() {
		driver.findElement(By.xpath("//*[@class = 'location-selector']/a")).click();
	}

	public void setLocation(String locationName) throws InterruptedException {
		getKeyword().click();
		String dataValue = this.getLocationItem(locationName).getAttribute("data-value");
		Thread.sleep(2000);
		js.executeScript("arguments[0].value=arguments[1]", getLocationInput(), dataValue);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", getSubmit());
		// getCloseLocationBox().click();
	}

}
