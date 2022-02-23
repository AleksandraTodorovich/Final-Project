package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BasicTest{

//	(Bonus deo)
//	Search Test:
//	U okviru search results testa potrebno je izvršiti sledeće korake:
//	učitajte stranicu http://demo.yo-meals.com/meals
//	postavite lokaciju na "City Center - Albany"
//	čitate podatke iz xlsx fajla > Meals Search Results Sheet
//	izvršite otvaranje svakog linka i postavljanje svake lokacije
//	i za svaku stranicu proverite rezultate pretrage 
//	verifikujte da je broj rezultata na stranici isti kao u fajlu
//	verifikujte da je svaki rezultat na stranici redom prikazan kao u fajlu
//
//	Na kraju kreirajte testng.xml fajl u kom ćete pobrojati sve testove na izvršenje.

	@Test
	public void searchResult() throws IOException, InterruptedException {
		driver.navigate().to(baseUrl + "meals");
		locationPopupPage.getKeyword().click();
		locationPopupPage.getLocationInput();
		locationPopupPage.getLocationItem("City Center - Albany").click();
		locationPopupPage.getSubmit().click();
		Thread.sleep(1000);
		file = new File("data/Data.xlsx");
		System.out.println(file.getAbsolutePath());
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");

		for (int i = 1; i <= 6; i++) {
			String url = sheet.getRow(i).getCell(1).getStringCellValue();
			driver.navigate().to(url);
			String location = sheet.getRow(i).getCell(0).getStringCellValue();
			Thread.sleep(500);
			locationPopupPage.getSelectLocation().click();
			locationPopupPage.setLocation(location);
			double numberOfResults = sheet.getRow(i).getCell(2).getNumericCellValue();
			Thread.sleep(1000);
			sa.assertEquals(searchResultPage.getAllResults(), numberOfResults, "Result missmatch");
			for (int j = 0; j < numberOfResults; j++) {
				sa.assertTrue(searchResultPage.getAllSearchResults().get(j).getText()
						.contains(sheet.getRow(i).getCell(3 + j).getStringCellValue()), "Result missmatch");
			}
		}
		sa.assertAll();
	}

}
