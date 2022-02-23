package tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest{
	//	Profile Test klasa:
	//
	//		U okviru edit profile testa potrebno je izvršiti sledeće korake:
	//		učitajte stranicu http://demo.yo-meals.com/guest-user/login-form
	//		ugasite lokacioni iskačući dijalog
	//		prijavite se na aplikaciju preko demo naloga
	//		verifikujte da je prikazana poruka sa tekstom "Login Successfull"
	//		učitajte stranicu http://demo.yo-meals.com/member/profile
	//		zamenite sve osnovne informacije korisnika
	//		verifikujte da je prikazana poruka sa tekstom "Setup Successful"
	//		odjavite se sa sajta
	//		verifikujte da je prikazana poruka sa tekstom "Logout Successfull!"
	//
	//		U okviru change profile image testa potrebno je izvršiti sledeće korake:
	//		učitajte stranicu http://demo.yo-meals.com/guest-user/login-form
	//		ugasite lokacioni iskačući dijalog
	//		prijavite se na aplikaciju preko demo naloga
	//		verifikujte da je prikazana poruka sa tekstom "Login Successfull"
	//		učitajte stranicu http://demo.yo-meals.com/member/profile
	//		otpremite profilnu sliku
	//		sliku iz images foldera
	//		s obzirom na to da se za otpremanje šalje apsolutna putanja do slike, a mi koristimo 
	//	relativnu,
	//		moramo da pribavimo putanju na sledeći način
	//		String imgPath = new File("imagеs/slika.png").getCanonicalPath();
	//		Koristan link
	//		verifikujte da je prikazana poruka sa tekstom "Profile Image Uploaded Successfully"
	//		sačekajte da nestane obaveštenje
	//		obrišite profilnu sliku
	//		verifikujte da je prikazana poruka sa tekstom "Profile Image Deleted Successfully"
	//		sačekajte da nestane obaveštenje
	//		odjavite se sa sajta
	//		verifikujte da je prikazana poruka sa tekstom "Logout Successfull!"
	
	
	//    	Drugi test
	//		U okviru change profile image testa potrebno je izvršiti sledeće korake:
	//		učitajte stranicu http://demo.yo-meals.com/guest-user/login-form
	//		ugasite lokacioni iskačući dijalog
	//		prijavite se na aplikaciju preko demo naloga
	//		verifikujte da je prikazana poruka sa tekstom "Login Successfull"
	//		učitajte stranicu http://demo.yo-meals.com/member/profile
	//		otpremite profilnu sliku
	//		sliku iz images foldera
	//		s obzirom na to da se za otpremanje šalje apsolutna putanja do slike, a mi koristimo relativnu,
	//		moramo da pribavimo putanju na sledeći način
	//		String imgPath = new File("imagеs/slika.png").getCanonicalPath();
	//		Koristan link
	//		verifikujte da je prikazana poruka sa tekstom "Profile Image Uploaded Successfully"
	//		sačekajte da nestane obaveštenje
	//		obrišite profilnu sliku
	//		verifikujte da je prikazana poruka sa tekstom "Profile Image Deleted Successfully"
	//		sačekajte da nestane obaveštenje
	//		odjavite se sa sajta
	//		verifikujte da je prikazana poruka sa tekstom "Logout Successfull!"

	@Test(priority = 1)
	public void editProfile() throws InterruptedException {
		driver.navigate().to(baseUrl + "guest-user/login-form");
		locationPopupPage.getCloseLocationBox().click();
		loginPage.getEmail().clear();
		loginPage.getPassword().clear();
		loginPage.loginDemo(email, password);
		Assert.assertEquals(notificationSystemPage.getMessage(), "Login Successfull", "Login Unsuccessfull");
		driver.navigate().to(baseUrl + "member/profile");
		Thread.sleep(2000);
		profilePage.getFirstName().clear();
		profilePage.getLastName().clear();
		profilePage.getAddress().clear();
		profilePage.getPhone().clear();
		profilePage.getZipCode().clear();
		Thread.sleep(2000);
		profilePage.getFirstName().sendKeys("Maureen");
		profilePage.getLastName().sendKeys("McCartney");
		profilePage.getAddress().sendKeys("Denton 123/4");
		profilePage.getPhone().sendKeys("+447700900100");
		profilePage.getZipCode().sendKeys("08015");
		select = new Select(profilePage.getCountry());
		select.selectByVisibleText("United Kingdom");
		select = new Select(profilePage.getState());
		Thread.sleep(2000);
		select.selectByVisibleText("Manchester");
		select = new Select(profilePage.getCity());
		select.selectByVisibleText("Worsley");
		profilePage.getSave().click();
//		Vlado kao što vidiš išla sam peške, nije uopšte hteo poziv metode iz Profile Page-a da radi, ne znam zašto. Menjala sam
//		lokatore nista nije pomoglo, i na kraju samo ovako je htelo da radi. Ipak sam ostavila tu metodu i ovaj zakomentarisani deo, čisto da vidiš.
//		profilePage.changeAllAccountData("Maureen", "McCartney", "Denton 123/4", " +447700900100", "08015", "United Kingdom", "Manchester", "Worsley");
		Assert.assertEquals(notificationSystemPage.getMessage(), "Setup Successful", "Setup Unsuccessfull");
		authPage.getMyAccountDropdown().click();
		authPage.logOut();
		Assert.assertEquals(notificationSystemPage.getMessage(), "Logout Successfull!", "Unable to Logout");
	}

	@Test(priority = 2)
	public void changeProfileImage() throws IOException, InterruptedException {
		driver.navigate().to(baseUrl + "guest-user/login-form");
		locationPopupPage.getCloseLocationBox().click();
		loginPage.getEmail().clear();
		loginPage.getPassword().clear();
		loginPage.loginDemo(email, password);
		Assert.assertEquals(notificationSystemPage.getMessage(), "Login Successfull", "Login Unsuccessfull");
		driver.navigate().to(baseUrl + "member/profile");
		Thread.sleep(2000);
		WebElement e = profilePage.getUpoloadButton();
		js.executeScript("arguments[0].click();", e);
		profilePage.photoUpload("img/th.jpg");
		Assert.assertEquals(notificationSystemPage.getMessage(), "Profile Image Uploaded Successfully",
				"Image was not uploaded");
		notificationSystemPage.waitForElementToDissapire();
		e = profilePage.getRemovePhoto();
		js.executeScript("arguments[0].click();", e);
		Assert.assertEquals(notificationSystemPage.getMessage(), "Profile Image Deleted Successfully",
				"Photo could not be removed");
		notificationSystemPage.waitForElementToDissapire();
		authPage.getMyAccountDropdown().click();
		authPage.logOut();
		Assert.assertEquals(notificationSystemPage.getMessage(), "Logout Successfull!", "Logout Error!");
	}
}