package nrp_pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import nrp_base.BaseNRP;

public class SetupPage_NRP extends BaseNRP {
	
	HomePage_NRP hp; 

	public SetupPage_NRP(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 100);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//a[contains(text(),'Reports Categories')])[1]")
	WebElement link_ReportsCategory;

	@FindBy(xpath = "(//a[contains(text(),'Reports Setup')])[1]")
	WebElement link_ReportsSetup;

	@FindBy(xpath = "(//div[contains(text(),'Report Builder')])[1]")
	WebElement link_ReportBuilder;

	public void clickOnReportBuilder() throws InterruptedException {

		Thread.sleep(1000);
		scrollToElement(link_ReportBuilder);
		log.info("Report builder link has been clicked");

	}

	public void clickOnReportCategories() throws IOException, InterruptedException {

		justscrollToElement(link_ReportsCategory);
		link_ReportsCategory.click();;
		waitForProgressbar();
		log.info("Report Categories link has been clicked");
		getScreenshot("ListOfCategoriesScreen");
	}

	public void clickOnReportsSetup() throws IOException, InterruptedException {
		
		Thread.sleep(1000);
		scrollToElement(link_ReportsSetup);
		log.info("Report setup link has been clicked and user is on list of report screen.");
		waitForProgressbar();
		Thread.sleep(5000);
		getScreenshot("ListOfReportsScreen");
	}

	public void navigateToReportsPage() throws InterruptedException, IOException {

		hp= new HomePage_NRP(driver);
		hp.navigateToLeftBar();
		hp.clickOnSetup();
		clickOnReportBuilder();
		clickOnReportsSetup();
		
	}

	public void navigateToReportsCategoryPage() throws InterruptedException, IOException {

		hp= new HomePage_NRP(driver);
		hp.navigateToLeftBar();
		hp.clickOnSetup();
		waitForProgressbar();
		clickOnReportBuilder();
		clickOnReportCategories();
		
		
	}
	
	
	
	

}
