package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
  
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.Repository.OP_CitizenPortalPage;
import com.Repository.OP_HomePage;
import com.Repository.OP_LoginPage;
import com.utilities.excelComponent;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
//import org.apache.commons.mail.HtmlEmail;
/*
 * Base Class has following Reusable Methods:
 * Initialization() - To initialize the Browser
 * loginByEmailId() - to login e-pragati by email id
 * Termination() - to end the browser session and quit
 * failed() - In case of failed test cases to capture screenshot
 * sendkeys() - Used for text field inputs with explicit waits implemented
 * clickOn() - Used for Button clicks with explicit waits implemented
 * selValue() - Used for select field inputs with explicit waits implemented
 * DatePicker() - Used for Pega Calendar Object to select required Date
 * setExtent() - Used for Extent Report file creation with current time stamp
 * getCurrentTimeStamp() - Method used to convert current date time to String format 
 * getConvertodDate() - Converts Date from one format(dd-MMM-yyyy) to another format (dd/mm/yyyy)
 * setUp() - Executes for every test method from each class using TestNG framework
 * 
 * */
public class Base{
	public static ExtentReports extent;
	public static ExtentTest loggerExtentReport;
	public static WebDriver driver = null;
	//public static Logger logfile;
	public static String strCurrentDir=null;
	public static Properties property = new Properties();
	public static excelComponent reader;
	
	
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss a");
	     System.setProperty("current.date.time", dateFormat.format(new Date()));
	     SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
	     System.setProperty("current.date", dateFormat1.format(new Date()));
	}
	public static Logger logfile =Logger.getLogger(Base.class);
	
	//Configure log file to track execution log
	 
			
/*
 * Method:readPropertiesFile
 * Purpose: This method reads configuration file from root directory and created property instance
 * Author: Uma
 * Created On : May-30-2019
 */
	public static void readPropertiesFile()  {
		 try {
			InputStream input = new FileInputStream(strCurrentDir +"\\configs\\config.properties");
			property.load(input);
		} catch (Exception e) {
			System.out.println("Entered into properties file reading... Exception occurred");
			e.printStackTrace(); }
	 }
	 
	public static void initialization() throws InterruptedException {
		
		PropertyConfigurator.configure("log4j.properties");			
		
	     //defines excel component into reader object
		reader  = new excelComponent(strCurrentDir +"\\testdata\\" + property.getProperty("application") + ".xlsx"); 
		
		// sets browser property
		
		if(property.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", strCurrentDir + "\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(property.getProperty("browser").equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", strCurrentDir+"\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (property.getProperty("browser").equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", strCurrentDir + "\\IEDriverServer.exe");
            //Initialize InternetExplorerDriver Instance.
            driver = new InternetExplorerDriver();	}				
		//Maximize the browser and mention page load with timeout options
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS); 
	    driver.get(property.getProperty("url")); //get URL to execute from config file
	    logfile.info("Launched " + property.getProperty("browser")+" browser with e-pragati URL");	    
	     //clickOn(driver,OP_HomePage.lnk_SignIn(driver),10);	     
	     //logfile.info("Sign in has been successful");	 
	     //loginUser(reader.getCellData("SetUp", "LoginType", 2),reader.getCellData("SetUp", "UserName", 2), reader.getCellData("SetUp", "Password", 2));
	    }
	
	public static void loginUser(int rowNum, String strLoginType, String strUserName, String strPassword) throws InterruptedException {
		 clickOn(driver,OP_HomePage.lnk_SignIn(driver),10);	     
	     logfile.info("Sign in has been successful");	
		selValue(driver, OP_LoginPage.sel_LoginType(driver),10,strLoginType);
		if(strLoginType.equals("Login with Email ID")) {
		sendkeys(driver,OP_LoginPage.txtbx_EmailId(driver),10,strUserName);
		sendkeys(driver,OP_LoginPage.txtbx_PasswordEmail(driver),5,strPassword);
		OP_LoginPage.btn_Login(driver).click();
		}
		
		if(strLoginType.equals("Login with Mobile Number")) {
			sendkeys(driver,OP_LoginPage.txtbx_MobileNumber(driver),10,strUserName);
			sendkeys(driver,OP_LoginPage.txtbx_PasswordMobile(driver),5,strPassword);
			OP_LoginPage.btn_LoginMobile(driver).click();
			}
		logfile.info("Login is successful with " + strLoginType + " Option");
		Thread.sleep(2000);
		if(driver.getTitle().equals("Citizen Portal")) {			
			clickOn(driver,OP_CitizenPortalPage.img_RightCorner(driver, reader.getCellData("AddAMCProfile", "FullName", rowNum)),20);			
			Thread.sleep(2000);
			WebElement eleSwithcRole = OP_CitizenPortalPage.span_SwitchRole(driver);
			Actions action = new Actions(driver);			 
	        action.moveToElement(eleSwithcRole).build().perform();
			Thread.sleep(2000);
			clickOn(driver,OP_CitizenPortalPage.span_RoleName(driver,reader.getCellData("AddAMCProfile", "Role", rowNum)),10);
		}
		
	}
		
	 public static void Termination() throws InterruptedException {
		
		 //clickOn(driver,OP_LoginPage.lnk_UserName(driver),10);
		//Wait for 5 Sec
			//Thread.sleep(5000);
		 //clickOn(driver,OP_LoginPage.lnk_LogOut(driver),10);
			//Wait for 5 Sec
			//Thread.sleep(5000);
			driver.quit();
		} 
	 public static void LogOut() throws InterruptedException {
			
		 clickOn(driver,OP_LoginPage.lnk_UserName(driver),10);
		//Wait for 5 Sec
			Thread.sleep(5000);
		 clickOn(driver,OP_LoginPage.lnk_LogOut(driver),10);
		 Thread.sleep(4000); 
		}
	 public void failed(String testMethodName) {
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 try {
			FileUtils.copyFile(scrFile, new File("F:\\ePragati\\RBZ\\RBZ\\screenshots\\"+testMethodName+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 public static void sendkeys(WebDriver driver, WebElement element, int timeout, String value) {
		 new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		 element.sendKeys(value);
	 }
	 
	 public static void clickOn(WebDriver driver, WebElement element, int timeout) {
		 new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		 element.click();
	 }
	 
	 public static void selValue(WebDriver driver, WebElement element, int timeout, String value) {
		 new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		 Select selElement = new Select(element);
		 selElement.selectByVisibleText(value);
	 }
	 
	 public static void DatePicker(String strDate) {
		 String date[] = strDate.split("-");
		 String day = date[0];
		 String month = date[1];
		 String year = date[2];
		 //driver.switchTo().frame("$PFarmerDetails$pCropStartDate");
		 driver.findElement(By.cssSelector("#monthSpinner > .spinner-input")).sendKeys(month);
		 driver.findElement(By.cssSelector("#yearSpinner > .spinner-input")).sendKeys(year);
		 driver.findElement(By.linkText(day)).click();
		 
	 }
	 
	 public static String getCurrentDate() {
		 Date objDate = new Date(); // Current System Date and time is assigned to objDate
		  
		  String strDateFormat = "dd-MMM-yyyy"; //Date format is Specified
		  SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); //Date format string is passed as an argument to the Date format object
		  String strDate= objSDF.format(objDate).toString(); //Date formatting is applied to the current date
		  return strDate;
		}
	 
	 public static String getCurrentTimeStamp() {
		 Date objDate = new Date(); // Current System Date and time is assigned to objDate
		  
		  String strDateFormat = "dd-MMM-yyyy hhmmss a"; //Date format is Specified
		  SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); //Date format string is passed as an argument to the Date format object
		  String strDate= objSDF.format(objDate).toString(); //Date formatting is applied to the current date
		  return strDate;
		}
	 
	 /* Method getConvertedDate
	  * Input is Date in String format with dd-MMM-yyyy
	  * Purpose: Function converts above date format to dd/MM/yyyy
	  * 		  Function returns converted date format as String
	  * Created On : May-20-2019
	  * author - Uma
	  * */
	 public static String getConvertodDate(String strDate) {		    
		    try
		    {
		      //create SimpleDateFormat object with source string date format
		      SimpleDateFormat sdfSource = new SimpleDateFormat("dd-MMM-yyyy");
		      
		      //parse the string into Date object
		      Date date = sdfSource.parse(strDate);
		      
		      //create SimpleDateFormat object with desired date format
		      SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy");
		      
		      //parse the date into another format
		      strDate = sdfDestination.format(date); }
		    catch(ParseException pe)   { 
		      System.out.println("Parse Exception : " + pe);   }
		    return strDate;
	 }
	 /*
	  * Method: ConvertDateMonthInTwoDigitFormat
	  * Purpose: Converts given date in two digits of day, month
	  * input Date as String in dd/MM/yyyy format
	  * output: converted date in two digit format
	  * Created On: 05/Jun/2019
	  * Author:Uma
	  * 
	  */
	 public static String ConvertDateMonthInTwoDigitFormat(String strInputDate) throws ParseException {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		     Date date = formatter.parse(strInputDate);
		      
		     //formatting day in dd format like 01, 02 etc.
		     String strDateFormat = "dd";
		     SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		     String strDate = sdf.format(date);
		   		     
		     //formatting Month in MM format like 01, 02 etc.
		     strDateFormat = "MM";
		     sdf = new SimpleDateFormat(strDateFormat);
		     String strMonth = sdf.format(date);
		     
		 	   //formatting Year in YYYY format like 01, 02 etc.
		     strDateFormat = "yyyy";
		     sdf = new SimpleDateFormat(strDateFormat);
		     String strYear = sdf.format(date);
		      		     
		     String strFormattedDate = strDate + "/" + strMonth + "/" + strYear;
		     return strFormattedDate;
		  
		}
	 /* 
	  * Method : getAge
	  * input : date Of Birth
	  * Output : age
	  * Purpose: Get age when DOB is in format of dd/MM/yyyy
	  * Created On : 08-Jun-2019
	  * Author:Uma
	  */
	 public static int getAge(String strDOB) {
			
			try
		    {
		      //create SimpleDateFormat object with source string date format
		      SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
		      
		      //System.out.println("Input Date Format is : " + strDate);
		      //parse the string into Date object
		      Date date = sdfSource.parse(strDOB);
		      
		      //create SimpleDateFormat object with desired date format
		      SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");
		      
		      //parse the date into another format
		      strDOB = sdfDestination.format(date); 
		      //System.out.println("Converted Date Format is : " + strDate);  
		    }
		    catch(ParseException pe)   { 
		      System.out.println("Parse Exception : " + pe);   }		     

			//Age Format in 'YYYY-MM-DD
			LocalDate dob = LocalDate.parse(strDOB);
	        LocalDate curDate = LocalDate.now();
	        return Period.between(dob, curDate).getYears();
	    }
	 
	 /* 
	  * Method : getLastDayOfTheMonth
	  * input : Last date Of Month for current Date
	  * Output : Last Date of the month
	  * Purpose: Used in PDMS under Update DA Page
	  * Created On : 13-Jun-2019
	  * Author:Uma
	  */	 
public static String getLastDayOfTheMonth() throws ParseException {
	Calendar calendar = Calendar.getInstance();
    int lastDate = calendar.getActualMaximum(Calendar.DATE);

    calendar.set(Calendar.DATE, lastDate);    
    String strToDate = (calendar.get(Calendar.DATE)) + "/" + (calendar.get(Calendar.MONTH) + 1) +  "/" + calendar.get(Calendar.YEAR);
    String strFinalDate = ConvertDateMonthInTwoDigitFormat(strToDate);
        
    return strFinalDate;
}
/* 
 * Method : getLastDayOfTheMonth
 * input : From Date & To date
 * Output : Number of months between two dates
 * Purpose: Used in PDMS under Update DA Page
 * Created On : 13-Jun-2019
 * Author:Uma
 */	 
public static int getMonthsDifference(String strFromDate, String strToDate ) throws ParseException {
	 
	SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	
	Date dFrom = dateformat.parse(strFromDate);
	Date dTo = dateformat.parse(strToDate);
	
	int numberOfMonths = 0;
    Calendar cal_ToDate = Calendar.getInstance();
    int year = 0; 
    int months = 0;
  
          Calendar cal_FromDate = Calendar.getInstance();
          cal_FromDate.setTime(dFrom);
          cal_ToDate.setTime(dTo);
          year = cal_ToDate.get(Calendar.YEAR) - cal_FromDate.get(Calendar.YEAR);
          months = (cal_ToDate.get(Calendar.MONTH)+1) - ((cal_FromDate.get(Calendar.MONTH) +1) );
                if(cal_ToDate.get(Calendar.DAY_OF_MONTH) < (cal_FromDate.get(Calendar.DAY_OF_MONTH)) )     
                {
                      months--;
                }
          numberOfMonths = months + (year * 12);
    
	return numberOfMonths;
}

	 /*
	  * Method - getScreenshot()
	  * Purpose - Captures screenshots and creates File in specified location
	  * Author - Uma
	  * Created On - May-25-2019
	  */
	 	 public String getScreenshot(WebDriver driver,String screenShotName) throws IOException {
	 			String  dateName = getCurrentTimeStamp();
	 			TakesScreenshot ts = (TakesScreenshot) driver;
	 			File source = ts.getScreenshotAs(OutputType.FILE);
	 			String destination = System.getProperty("user.dir") + "/FailTestScreenshots/" + screenShotName + dateName +".png";
	 			File finaldestination = new File(destination);
	 			FileUtils.copyFile(source, finaldestination);
	 			return destination;	
	 			}		 
	 
	public static void createExcelFile(String strPath, String strFileName, String strSheetName,String[] headers) throws IOException {
		
		String timestamp= getCurrentTimeStamp();
        // Creating Workbook instances 
		XSSFWorkbook wb = new XSSFWorkbook(); 

        //Workbook wb = new HSSFWorkbook(); 
  
        // An output stream accepts output bytes and sends them to sink. 
        OutputStream fileOut = new FileOutputStream(strPath+strFileName+".xlsx"); 
          
        // Creating Sheets using sheet object 
        Sheet sheet1 = wb.createSheet(strSheetName); 
       Row header = sheet1.createRow(0);
        for (int rn=0; rn<headers.length; rn++) {
        	header.createCell(rn).setCellValue(headers[rn]);   
        }
        wb.write(fileOut);  
                
	}
// *********   Before & After Suite functions   *********
/*
  	  * Method - setExtent() - 
	  * Purpose: Invokes once for the entire test suite executed thru testng.xml and creates an Extents Report
	  * Author - Uma
	  * Created On : May-14-2019
	  * 
*/
	 @BeforeSuite
	public void setExtent() throws IOException  {
		 strCurrentDir = System.getProperty("user.dir"); // fetching root/current directory to the frame work
		
		
	     readPropertiesFile(); // reads config file		
		
		 String timestamp= getCurrentTimeStamp();
		//Creates Application directory based on config file
		 String strAppDirBase = strCurrentDir + "\\testResults\\";
		 String strAppDir = property.getProperty("application");
		 String strAppDirPath = strAppDirBase + strAppDir;
	        
		 Path appPath = Paths.get(strAppDirPath);

	        if (!Files.exists(appPath)) {
	        	Files.createDirectories(appPath);
	            System.out.println(" App Directory created");
	        }	 
		 
	    String strDateDirBase = strCurrentDir + "\\testResults\\" +strAppDir + "\\" ;
		String strDateDir = getCurrentDate();
		String strDateDirPath = strDateDirBase + strDateDir;
		 
		Path datePath = Paths.get(strDateDirPath);

        if (!Files.exists(datePath)) {
        	Files.createDirectories(datePath);
            System.out.println(" Date Directory created");
        }	 
				
        extent = new ExtentReports(strDateDirPath + "\\" + property.getProperty("application") + "_TestReport_" 
    			+ timestamp +".html",true);
    			System.out.println("Extent Report created" );
    			
    			extent.addSystemInfo("Application", property.getProperty("application"));
    			extent.addSystemInfo("Environment", property.getProperty("environment"));
        		
		}
	 
	 /*
	  * Method - endReport()
	  * Purpose - Invokes this method after entire suite has been completed 
	  * 			Flushes and ends the generated Extent Report
	  * Author- Uma
	  * Created On - May-24-2019	
	  */
	@AfterSuite
  public void endReport() throws EmailException, InterruptedException {
		//sendEmailTestReport();
	 			//extent.flush();
	 			//extent.close();
	 			//Termination();
	 		}

	// *********   Before & After Methods   *********
	/*
	 * Method - setUp()
	 * Purpose - This method executed for every test case from each class mentioned in the testng.xml/all test cases written in class
	 * Created On - 18-May-2019
	 */
		 @BeforeMethod
			public void setUp() throws InterruptedException, IOException {
				initialization();
			}
		
	
/*
 * Method - setResult
 * Purpose - Invokes this method after every test case to capture test result with Pass/Fail status and desription
 * Author - Uma
 * Created On - 19-May-2019
 */
	 @AfterMethod
		public void setResult(ITestResult result) throws InterruptedException, IOException {
			if(result.getStatus() == ITestResult.FAILURE) {
				loggerExtentReport.log(LogStatus.FAIL,"Test Case Failed Is "+result.getName()); //to add name in extent report
				loggerExtentReport.log(LogStatus.FAIL,"Test Case Failed Is "+result.getThrowable()); //to add error/exception in extent report
				String screenshotPath = getScreenshot(driver,result.getName());
				loggerExtentReport.log(LogStatus.FAIL, loggerExtentReport.addScreenCapture(screenshotPath)); //to attach screenshot in extent report
				logfile.error("Test Case Failed");
			}
			else if (result.getStatus() == ITestResult.SKIP){
				loggerExtentReport.log(LogStatus.SKIP, "Test Case Skipped Is : " + result.getName());
				logfile.debug("Test Case Skipped");
			}
			else if (result.getStatus() == ITestResult.SUCCESS){
				loggerExtentReport.log(LogStatus.PASS, "Test Case Passed Is : " + result.getName());
				logfile.info("Test Case Passed");
			}		
			extent.endTest(loggerExtentReport); //ending test and ends the current test and preapre to create html report.		
			Termination();
		}

	 

		public void sendEmailTestReport() throws EmailException {
			// load your HTML email template
			 String htmlEmailTemplate = "F:\\Selenium.Automation\\SelFramework\\testResults\\RBZ\\31-May-2019\\RBZ_TestReport_31-May-2019 042558 PM.html";

			  // define you base URL to resolve relative resource locations
			  //URL url = new URL("http://www.apache.org");

			  // create the email message
			 //ImageHtmlEmail email = new ImageHtmlEmail();
			 Email email = new SimpleEmail();
			  //email.setDataSourceResolver(new DataSourceUrlResolver(url));
			  email.setHostName("smtp.gmail.com");
			  email.setSslSmtpPort("465");
			  email.setSSLOnConnect(true);
			  email.setAuthenticator(new DefaultAuthenticator("umasundari2010@gmail.com", "Rithvik2006"));
				email.setDebug(true);
				email.getMailSession().getProperties().put("mail.smtps.auth", "true");
				email.getMailSession().getProperties().put("mail.debug", "true");
				email.getMailSession().getProperties().put("mail.smtps.port", "587");
				email.getMailSession().getProperties().put("mail.smtps.socketFactory.port", "587");
				email.getMailSession().getProperties().put("mail.smtps.socketFactory.class",   "javax.net.ssl.SSLSocketFactory");
				email.getMailSession().getProperties().put("mail.smtps.socketFactory.fallback", "false");
				email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");
			  email.addTo("umasundari2010@gmail.com", "Uma");
			  email.setFrom("umasundari2010@gmail.com", "Me");
			  email.setSubject("Test Results Mail");
			  
			  // set the html message
			  //email.setHtmlMsg(htmlEmailTemplate);

			  // set the alternative message
			  //email.setTextMsg("Your email client does not support HTML messages");

			  // send the email
			  email.send();
		}
}
