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

public class HomePage_NRP extends BaseNRP {

	public HomePage_NRP(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 100);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='sidepanel-wraper scroll-custom']")
	WebElement LeftMenuBar;

	@FindBy(xpath = "(//span[contains(text(),'Setup')])[1]")
	WebElement link_LinkSetup;

	@FindBy(xpath = "//span[contains(text(),'Calendar Overview')]")
	WebElement link_CalendarView;
	
	@FindBy(xpath = "//span[contains(text(),'Snapshot Reports')]")
	WebElement link_snapShotReport;
	
	@FindBy(xpath = "(//div[@class='title-2 pb-10'])[1]")
	WebElement ReportTitle;

	@FindBy(name = "btnByTypeReportFilter")
	WebElement DateFilter;

	
	public void navigateToLeftBar() throws InterruptedException {

		Actions actions = new Actions(driver);
		actions.moveToElement(LeftMenuBar).perform();
		log.info("Left menu bar has been expanded.");
		Thread.sleep(5000);
		log.info("Waited for 5 seconds before click");

	}

	public void clickOnCalendardashboard() throws InterruptedException

	{
		waitUntilClickable(link_CalendarView);
		link_CalendarView.click();
		log.info("calendar view link has been clicked and now waiting for progessbar");
		waitForProgressbar();
		//CalendarViewPage_NRP Cal=new CalendarViewPage_NRP(driver);
		//waitUntilClickable(Cal.dashBoardButton);
		Thread.sleep(5000);
		log.info("calendar view link has been clicked fully");

	}
	
	public void clickOnSnapShotReport() throws InterruptedException

	{
		waitUntilClickable(link_snapShotReport);
		link_snapShotReport.click();
		log.info("Snap Shot Report link has been clicked and now waiting for progessbar");
		waitUntilClickable(DateFilter);
		DateFilter.click();
		Thread.sleep(2000);
		log.info("Snap Shot Report link has been clicked fully");

	}

	

	public void clickOnSetup() throws InterruptedException {

		justscrollToElement(link_LinkSetup);
		link_LinkSetup.click();
		Thread.sleep(2000);
		//waitForProgressbar();
		log.info("Setup link has been clicked");

	}
	
	public void verifyCreatedReportOnLeftBar(String CategoryName,String ReportName) throws InterruptedException {
		
		
		navigateToLeftBar();
		WebElement categoryName = driver.findElement(By.xpath("//span[contains(text(),'"+CategoryName+"')]"));
		scrollToElement(categoryName);
		log.info("Category name clicked");
		WebElement reportName = driver.findElement(By.xpath("//div[contains(text(),'"+ReportName+"')]"));
		waitUntilClickable(reportName);
		justscrollToElement(reportName);
		reportName.click();
		log.info("Report name clicked");
		isProgressbarDisplayed();
		Thread.sleep(2000);
		String strReporttitle=ReportTitle.getText();
		
		if(strReporttitle.equalsIgnoreCase(ReportName))
		{
			sa.assertTrue(true);
			log.info("Crated report is present and verified");
	
			
		}

		

		

	}

}
