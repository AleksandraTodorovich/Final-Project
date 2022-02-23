package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;
import pages.SearchResultPage;

public abstract class BasicTest {
//		Od test klasa je potrebno implementirati:
//		Basic Test:
//		apstraktna klasa koja sadrži sve zajedničke funkcionalnosti za sve test klase
//		od dodatnih atributa ima:
//		baseUrl 
//		imejl i lozinku demo korisnika "customer@dummyid.com"/12345678a
//		BeforeMethod metoda koja konfiguriše Selenium drajver
//		AfterMethod metoda koja u slučaju pada testa kreira screenshot stranice i te slike čuva u okviru
//		screenshots direktorijuma i zatvara sesiju drajvera
//		sve ostale test klase nasleđuju ovu klasu
	
	protected String baseUrl;
	protected String email;
	protected String password;
	protected WebDriver driver;
	protected JavascriptExecutor js;
	protected WebDriverWait wait;
	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSistemPage notificationSystemPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected CartSummaryPage cartSummaryPage;
	protected MealPage mealPage;
	protected SearchResultPage searchResultPage;
	protected Select select;
	protected File file;
	protected XSSFWorkbook wb;
	protected FileInputStream fis;
	protected SoftAssert sa;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "driver-lib/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wb = new XSSFWorkbook();
		sa = new SoftAssert();
		locationPopupPage = new LocationPopupPage(driver, js);
		loginPage = new LoginPage(driver);
		notificationSystemPage = new NotificationSistemPage(driver, wait);
		profilePage = new ProfilePage(driver, js);
		authPage = new AuthPage(driver);
		mealPage = new MealPage(driver);
		cartSummaryPage = new CartSummaryPage(driver);
		searchResultPage = new SearchResultPage(driver);
		baseUrl = "https://demo.yo-meals.com/";
		email = "customer@dummyid.com";
		password = "12345678a";

	}

	@AfterMethod
	public void afterMethod(ITestResult testResult) throws IOException, InterruptedException {

		Date d = new Date();
		String FileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		if (testResult.getStatus() == ITestResult.FAILURE) {
			System.out.println(testResult.getStatus());
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("./screenshots/test/" + testResult.getName() + "-" + FileName));
			Thread.sleep(1000);
			driver.quit();
		}
	}
}