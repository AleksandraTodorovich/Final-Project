package tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MealItemTest extends BasicTest {
	
//		Meal Item Test:
//		U okviru add meal to cart testa potrebno je izvršiti sledeće korake:
//		učitajte stranicu http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo
//		ugasite lokacioni iskačući dijalog
//		dodajte jelo u korpu, količina je proizvoljna verifikujte da je prikazana poruka sa tekstom 
//		"The Following Errors Occurred:	Please Select Location"
//		sačekajte da obaveštenje nestane
//		postavite lokaciju na "City Center - Albany"
//		dodajte jelo u korpu, količina je proizvoljna
//		verifikujte da je prikazana poruka sa tekstom "Meal Added To Cart"
//
//		U okviru add meal to favorite testa potrebno je izvršiti sledeće korake:
//		učitajte stranicu http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo
//		ugasite lokacioni iskačući dijalog
//		dodajte jelo u omiljena jela
//		verifikujte da je prikazana poruka sa tekstom "Please login first!"
//		učitajte stranicu za prijavu
//		prijavite se na aplikaciju preko demo naloga
//		učitajte stranicu http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo
//		dodajte jelo u omiljena jela
//		verifikujte da je prikazana poruka sa tekstom "Product has been added to your favorites."
//
//		U okviru clear cart testa potrebno je izvršiti sledeće korake:
//		učitajte stranicu http://demo.yo-meals.com/meals
//		postavite lokaciju na "City Center - Albany"
//		čitate podatke iz xlsx fajla > Meals Sheet 
//		u korpu dodajte svaki proizvod sa određenom količinom
//		za svako dodavanje proizvioda verifikujte da je prikazana poruka sa tekstom "Meal Added To Cart"
//		koristite SoftAssert za ovu proveru
//		obrišite sve stavke iz korpe
//		verifikujte da je prikazana poruka sa tekstom "All meals removed from Cart successfully"
//		*(enabled = false)	

	@Test(priority = 1)
	public void addMealToCart() throws InterruptedException {
		driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.getCloseLocationBox().click();
		Thread.sleep(2000);
		mealPage.addMeal("4");
		Assert.assertTrue(notificationSystemPage.getMessage().contains("The Following Errors Occurred:"),
				"Message Error 1");
		Assert.assertTrue(notificationSystemPage.getMessage().contains("Please Select Location"), "Message Error 2");
		notificationSystemPage.waitForElementToDissapire();
		locationPopupPage.getSelectLocation().click();
		Thread.sleep(2000);
		// locationPopupPage.setLocation("City Center - Albany"); //klik na "City Center
		// - Albany" nije hteo da radi preko poziva ove metode, pa sam opet morala da
		// idem peške
		locationPopupPage.getKeyword().click();
		locationPopupPage.getLocationInput();
		locationPopupPage.getLocationItem("City Center - Albany").click();
		locationPopupPage.getSubmit().click();
		Thread.sleep(1000);
		mealPage.addMeal("2");
		Assert.assertTrue(notificationSystemPage.getMessage().contains("Meal Added To Cart"), "Couldn't add meal to cart");
	}

	@Test(priority = 2)
	public void addMealToFavorite() throws InterruptedException {
		driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.getCloseLocationBox().click();
		Thread.sleep(2000);
		mealPage.addMealToFavorite().click();
		Assert.assertEquals(notificationSystemPage.getMessage(), "Please login first!", "Error message!");
		loginPage.getLogin().click();
		loginPage.getEmail().clear();
		loginPage.getPassword().clear();
		loginPage.loginDemo(email, password);
		driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.addMealToFavorite().click();
		Assert.assertEquals(notificationSystemPage.getMessage(), "Product has been added to your favorites.",
				"Error occurred.");
	}

	@Test(priority = 3)
	public void clearCart() throws IOException, InterruptedException {
		driver.navigate().to(baseUrl + "meals");		
		locationPopupPage.getKeyword().click();
		locationPopupPage.getLocationInput();
		locationPopupPage.getLocationItem("City Center - Albany").click();
		locationPopupPage.getSubmit().click();
		file = new File("data/Data.xlsx");
		System.out.println(file.getAbsolutePath());
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");
		for (int i = 1; i <= 5; i++) {
			String mealUrl = sheet.getRow(i).getCell(0).getStringCellValue();
			driver.navigate().to(mealUrl);
			Thread.sleep(2000);
			mealPage.addMeal("1");
			sa.assertTrue(notificationSystemPage.getMessage().contains("Meal Added To Cart"),
					"An error occured while adding meals");
		}
		sa.assertAll();
		Thread.sleep(700);
		cartSummaryPage.clickOnClearAll();
		Assert.assertTrue(notificationSystemPage.getMessage().contains("All meals removed from Cart successfully"),
				"An error accured while removing meals from Cart");
	}
}
