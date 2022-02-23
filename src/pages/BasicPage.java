package pages;

import org.openqa.selenium.WebDriver;

public abstract class BasicPage {
//		Od page klasa je potrebno implementirati:
//		Basic Page:
//		apstraktna klasa koja sadrži sve zajedničke funkcionalnosti page klasa
//		sve ostale page klase nasleđuju ovu klasu

	protected WebDriver driver;

	public BasicPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

}
