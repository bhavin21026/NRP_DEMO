package nrp_pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nrp_base.BaseNRP;

public class LoginPage_NRP extends BaseNRP {

	public LoginPage_NRP(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 100);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@id='Email']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//button[text()='Login']")
	WebElement btnLogin;

	//@FindBy(xpath = "(//div[@class='spinner'])[1]")
	//WebElement loginProgressbar;

	public void doLogin(String emailid, String passwordValue) throws InterruptedException, IOException {

		
		//waitForProgressbar();
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
		getScreenshot("LoginPage");
		UserName.sendKeys(emailid);
		Password.sendKeys(passwordValue);
		wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
		btnLogin.click();
		log.info("Waiting for Home page to load");
		waitForProgressbar();
		log.info("Login Successfull and welcome to home page of NRP");
		getScreenshot("HomePage");


	}

}
