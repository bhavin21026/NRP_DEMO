package nrp_pageobjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nrp_base.BaseNRP;

public class ReportsPage_NRP extends BaseNRP {

	public ReportsPage_NRP(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 100);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//button[contains(text(),'Create New Report')])[1]")
	WebElement btn_createReport;
	
	@FindBy(xpath = "//input[@placeholder='Search Your Reports']")
	WebElement txt_searchReport;
	
	@FindBy(xpath = "//div[@id='toast-container']")
	WebElement tosterMessage;
	
	@FindBy(css = "#main-field > div > div > div > app-reports > div > app-report-list > div > table > tbody > tr > td:nth-child(1)")
	WebElement searchedReport;
	
	

	public void clickOncreateReportLink() throws InterruptedException, IOException {

		scrollToElement(btn_createReport);
		//waitForProgressbar();
		log.info("Welcome to report setup page, now start creating report.");
		getScreenshot("CrossLocationReportBuilderSetup");

	}
	
	public void searchAndValidatecreatedReport(String ReportName) throws InterruptedException, IOException {
				
		wait.until(ExpectedConditions.visibilityOf(tosterMessage));
		wait.until(ExpectedConditions.invisibilityOf(tosterMessage));
		Thread.sleep(2000);
		waitUntilClickable(txt_searchReport);
		txt_searchReport.click();
		txt_searchReport.sendKeys(ReportName);
		Thread.sleep(2000);
		String Report=searchedReport.getText();
		
		if(Report.equalsIgnoreCase(ReportName))
		{
			sa.assertTrue(true);
			log.info("Report is created and user is able to search same report in list of reports");
			getScreenshot("Success_earchedCreatedReport");

			
		}
		else
		{
			getScreenshot("Failed_SearchedCreatedReport");

			
		}

	}

}
