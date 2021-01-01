package nrp_pageobjects;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nrp_base.BaseNRP;

public class CompanySelectionPage__NRP extends BaseNRP {

	public CompanySelectionPage__NRP(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//span[text()='Select client']")
	WebElement drp_SelectClient;
	
	@FindBy(xpath = "//input[@type='search' and @placeholder='Search by name...']")
	WebElement searchClient;

	//li[contains(text(),'Seven Restaurants')]


	public void selectClient() throws InterruptedException {

		//waitForVisibility(drp_SelectClient);
		drp_SelectClient.click();
		log.info("Select client dropdown clicked");
		waitUntilClickable(searchClient);
		searchClient.click();
		searchClient.sendKeys("Seven Restaurants");
		WebElement client=driver.findElement(By.xpath("//li[contains(text(),'Seven Restaurants')]"));
		waitUntilClickable(client);
		client.click();
		log.info("Client selection done");
		Thread.sleep(1500);
		waitForProgressbar();

	}

	

}
