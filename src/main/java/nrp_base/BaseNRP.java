package nrp_base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseNRP {

	public static WebDriver driver;

	public static boolean browserAlreadyOpen = false;
	public static Properties setup = new Properties();
	public static Properties daily = new Properties();
	public static Properties weekly = new Properties();
	public static Properties monthly = new Properties();
	public static Properties er = new Properties();

	public static FileInputStream fisnew;
	public static WebDriverWait wait;
	public static WebDriverWait waitMore;

	public boolean isAlreadyLogIn = false;
	public String username;
	public String password;
	public SoftAssert sa = new SoftAssert();
	public static Logger log;

	@BeforeSuite
	public void setUpNRP() throws InterruptedException {

		System.out.println("Setup method invoked");
		log = Logger.getLogger("NRP_CRL");
		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "/src/test/resources/Properties/log4j.properties");

		try {

			fisnew = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\setup.properties");

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			setup.load(fisnew);

			// log.info("Config property file loaded");
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {

			fisnew = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\Daily_Live.properties");

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			daily.load(fisnew);

			// log.info("Config property file loaded");
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {

			fisnew = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\Weekly_Live.properties");

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			weekly.load(fisnew);

			// log.info("Config property file loaded");
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {

			fisnew = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\Monthly_Live.properties");

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			monthly.load(fisnew);

			// log.info("Config property file loaded");
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {

			fisnew = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\ER_Live.properties");

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			er.load(fisnew);

			// log.info("Config property file loaded");
		} catch (IOException e) {

			e.printStackTrace();
		}
try {

			System.out.println(setup.getProperty("browser").equalsIgnoreCase("chrome"));

			if (setup.getProperty("browser").equals("firefox")) {
				// System.setProperty("webdriver.gecko.driver",
				// System.getProperty("user.dir") +
				// "\\src\\test\\resources\\executables\\geckodriver.exe");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Firefox Browser Launched");
			} else if (setup.getProperty("browser").equals("chrome")) {

				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
				// System.setProperty("webdriver.chrome.driver",
				// System.getProperty("user.dir") +
				// "\\src\\test\\resources\\executables\\chromedriver.exe");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
				log.info("Chrome Browser Launched");

			} else if (setup.getProperty("browser").equals("ie")) {
				// System.setProperty("webdriver.chrome.driver",
				// System.getProperty("user.dir") +
				// "\\src\\test\\resources\\executables\\msedgedriver.exe");
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				log.info("IE Browser Launched");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 50);

		driver.get(setup.getProperty("testsiteurl"));
		waitForProgressbar();

		log.info("Website has been opened" + setup.getProperty("testsiteurl"));
		
	}

	public void waitForProgressbar() throws InterruptedException {

		WebElement progressbar = driver.findElement(By.xpath("(//div[@class='spinner'])[1]"));
		if(wait.until(ExpectedConditions.visibilityOf(progressbar)) != null) {
		log.info("Progressbar enabled");
		wait.until(ExpectedConditions.invisibilityOf(progressbar));
		log.info("Progressbar disabled");
		}
		//Thread.sleep(3000);
	}
	
	public void waitForProgressbarForDataGroup() throws InterruptedException {

		WebElement progressbar = driver.findElement(By.xpath("(//div[@class='spinner'])[2]"));
		wait.until(ExpectedConditions.visibilityOf(progressbar));
		log.info("Progressbar enabled");
		wait.until(ExpectedConditions.invisibilityOf(progressbar));
		log.info("Progressbar disabled");
		
		//Thread.sleep(3000);
	}

	public void waitForPageTorefresh() throws InterruptedException {

		driver.navigate().refresh();
		waitForProgressbar();

	}

	public void waitForVisibility(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitUntilClickable(WebElement element) {

		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void scrollToElement(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].click();", element);

	}

	public void justscrollToElement(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);

	}
	
	public void divType(WebElement element, String ReportName) throws InterruptedException {

		
		Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        
   
        Thread.sleep(1000);
        actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        actions.sendKeys(Keys.chord(Keys.BACK_SPACE));

        actions.sendKeys(ReportName);
        
        actions.build().perform();
		
		//WebElement ele=driver.findElement(By.cssSelector("#main-field > div > div > div > app-reports > div > app-report-details > section > div.newreportbuilder-section-top-part.pl-40 > editable-label > div > div > div"));
		//ele.click();
		//ele.sendKeys("dssgDSG");
		///JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].click()", ele);
		//element.click();
		//js.executeScript("arguments[0].value='rhddfhhfh';", element);
		//js.executeScript("document.evaluate(//*[@id=\"main-field\"]/div/div/div/app-reports/div/app-report-details/section/div[1]/editable-label/div/div/div, document, null, 9, null).singleNodeValue.innerHTML="+ReportName);

		
	}

	public void isProgressbarDisplayed() {

		WebElement progressbar = driver.findElement(By.xpath("(//div[@class='spinner'])[1]"));

		if (progressbar.isDisplayed()) {

			log.info("Progressbar displayed ");

			wait.until(ExpectedConditions.invisibilityOf(progressbar));

			log.info("Progressbar gone ");

		}

	}

	public void getScreenshot(String result) throws IOException {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileHandler.copy(src,
				new File(System.getProperty("user.dir") + "/target/Screenshots/" + result + "Screenshot.png"));
	}

	public double removeCurrencyFormat(String CurrencyValue) throws ParseException {

		String NewCurrency = CurrencyValue.replace("$", "");
		NumberFormat format = NumberFormat.getInstance();
		Number NewCurrency2 = format.parse(NewCurrency);
		double numbers = NewCurrency2.doubleValue();

		return numbers;
	}

	public double setNumberFormat(String CurrencyValue) throws ParseException {

		NumberFormat format = NumberFormat.getNumberInstance();
		Number number = format.parse(CurrencyValue);
		double numbers = number.doubleValue();
		return numbers;
	}

}

/*
 * @AfterSuite
 * 
 * public void tearDown2() {
 * 
 * //EmailUtility eu=new EmailUtility(); //eu.doSendEmail();
 * 
 * driver.quit();
 * System.out.println("Test Suit execution completed! and driver closed");
 * 
 * 
 * 
 * }
 */
