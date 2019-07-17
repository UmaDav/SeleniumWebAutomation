package pdms.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.utilities.Base;
import com.utilities.CustomListener;
import com.utilities.excelComponent;

import pdms.repository.AddPensionerPage;
import pdms.repository.HomePage;
import pdms.repository.LeftNavigationPage;
import pdms.repository.UpdateDAPage;

@Listeners(CustomListener.class)
public class UpdateDATests extends Base {
private String strDAObjId;
	
public void testEMudhra() throws InterruptedException, IOException {
		loginUser(5,reader.getCellData("UpdateDA", "LoginType", 5),reader.getCellData("UpdateDA", "UserName", 5), reader.getCellData("UpdateDA", "Password", 5));
	    Thread.sleep(2000);
	    clickOn(driver,UpdateDAPage.span_PDMSPendingTasks(driver),10);
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[contains(text(),'June 2019')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(.,'Generate Digital Signature')]")).click();
		Thread.sleep(5000);

		
		//String outlookApplicationPath = "C:\\Signer\\Signer\\Signer.exe";
	    //String winiumDriverPath = "C:\\Users\\uma.davuluri\\Downloads\\drk sarma\\Winium.Desktop.exe";
	 
	   // DesktopOptions options = new DesktopOptions(); //Initiate Winium Desktop Options
	    //options.setApplicationPath(outlookApplicationPath); //Set outlook application path
	 
	   // File drivePath = new File(winiumDriverPath); //Set winium driver path
	 
	   // WiniumDriverService service = new WiniumDriverService.Builder().usingDriverExecutable(drivePath).usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
	   // service.start(); //Build and Start a Winium Driver service
	    //WiniumDriver driverwin = new WiniumDriver(options); //Start a winium driver
	    //String str[] = driverwin.getWindowHandles();
  		
		//Thread.sleep(5000);
		//driverwin.findElement(By.xpath("//button[contains(.,'Cancel')]")).click();
		  

	}
@Test
public void updateDATest() throws InterruptedException, ParseException, IOException {
	loggerExtentReport = extent.startTest("Update DA Test");
		UpdateDA_SectionHead(2);
		UpdateDA_AuditOfficer(3);
		UpdateDA_DeputyDirector(4);
		UpdateDA_Comissioner(5);
	 	checkDAArrears(2);
	}
	
public void checkDAArrears(int rowNum) throws InterruptedException, ParseException, IOException {

	String filePath = System.getProperty("user.dir") + "\\testResults\\";
	String timestamp= getCurrentTimeStamp();
	String strFile = "PDMS_DA_Arrears_" + timestamp;
	String[] headers = new String[] { "PPONumber", "TotalAmount", "ActualDRArrears", "ExpectedDRArrears", "Status"};
	createExcelFile(filePath,strFile,"Results",headers);
	excelComponent DAreader  = new excelComponent(filePath + strFile +".xlsx");
		 	 
	String strPPONum , strRtDate , strActualDAArrs, strExpDAArrs, strEffDate;
	double dblTotalAmt ,TotalDAArrears=0, dblDA_Months, dblNewDA, dblCurrDA;
	//dblDA_Months = Double.parseDouble(reader.getCellData("UpdateDA", "DA_Arr_Months", rowNum));
	dblNewDA = Double.parseDouble(reader.getCellData("UpdateDA", "NewDA", rowNum));
	dblCurrDA = Double.parseDouble(reader.getCellData("UpdateDA", "CurrentDA", rowNum));
	dblNewDA = dblNewDA - dblCurrDA;
	strEffDate = reader.getCellData("UpdateDA", "FromDate", rowNum);
	strEffDate = getConvertodDate(strEffDate);
	strEffDate = ConvertDateMonthInTwoDigitFormat(strEffDate);
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date EffectDate = formatter.parse(strEffDate);
    Date PenRtDate;
	SoftAssert sftAssert = new SoftAssert();
	loginUser(2,reader.getCellData("UpdateDA", "LoginType", rowNum),reader.getCellData("UpdateDA", "UserName", rowNum), reader.getCellData("UpdateDA", "Password", rowNum));
    Thread.sleep(2000);
    clickOn(driver,HomePage.menu_DownArrow(driver),10);
    Thread.sleep(2000);
    clickOn(driver,HomePage.span_Reports(driver),10);
    selValue(driver,HomePage.sel_Reports_PensionerStatus(driver),10,"Active");
    Thread.sleep(2000);
    int row=2,currtRow;;
    HomePage.btn_Action(driver, "Search");
    Thread.sleep(5000);
     boolean pagination=true;
        
    while(pagination==true) {
    	currtRow = DAreader.getRowCount("Results") +1;
    	 int rows =  HomePage.tbl_Report_PensionersRows(driver);         
         //System.out.println("ROW COUNT : "+rows);
         
    for (row=2;row<=rows;row++) {
    	strPPONum = HomePage.tbl_Report_PensionersData(driver, row, 4).getText();
    	strRtDate = HomePage.tbl_Report_PensionersData(driver, row, 6).getText();
    	strActualDAArrs = HomePage.tbl_Report_PensionersData(driver, row, 28).getText();
    	strActualDAArrs = strActualDAArrs.substring(2,strActualDAArrs.length());
    	DAreader.setCellData("Results", "PPONumber", currtRow, strPPONum);
    	DAreader.setCellData("Results", "ActualDRArrears", currtRow, strActualDAArrs);
    	Double dblActDA = Double.valueOf(strActualDAArrs.replaceAll(",", "").toString());    	
    	String strTotalAmt = HomePage.tbl_Report_PensionersData(driver, row, 18).getText();    	
    	DecimalFormat df = new DecimalFormat("#,###,##0");
    	strTotalAmt = strTotalAmt.substring(2, strTotalAmt.length());
    	DAreader.setCellData("Results", "TotalAmount", currtRow, strTotalAmt);
    	Double dblTotal = Double.valueOf(strTotalAmt.replaceAll(",", "").toString());
    	PenRtDate = formatter.parse(strRtDate);    	
    	String strExpToDate = getLastDayOfTheMonth();
    	if(!strRtDate.equals("")) {
    		if (PenRtDate.compareTo(EffectDate) > 0) {   
    			int Expectedmnths = getMonthsDifference(strRtDate, strExpToDate);  
    			TotalDAArrears = (Expectedmnths * dblNewDA * dblTotal)/100 ; 
    			//System.out.println("Rt date is greater EffDate");
            }else if (PenRtDate.compareTo(EffectDate) <= 0) {            	
            	int Expectedmnths = getMonthsDifference(strEffDate, strExpToDate);
            	//TotalDAArrears = (dblDA_Months * dblNewDA * dblTotal)/100 ; 
            	TotalDAArrears = (Expectedmnths * dblNewDA * dblTotal)/100 ;
            	}    		
            	TotalDAArrears = Math.round(TotalDAArrears);
            	
            	if(TotalDAArrears  == dblActDA) {
                	DAreader.setCellData("Results", "Status", currtRow, "Pass");   }
                	else {
                		DAreader.setCellData("Results", "Status", currtRow, "Fail"); }       	
            	String strFinalAmt = df.format(TotalDAArrears);
               	DAreader.setCellData("Results", "ExpectedDRArrears", currtRow, strFinalAmt);     	
    	}    	
    	currtRow = currtRow+1;
    }//end for loop
    if(UpdateDAPage.btn_PaginationNext(driver).isDisplayed() == true) {
    	clickOn(driver,UpdateDAPage.btn_PaginationNext(driver),10);
    	Thread.sleep(5000);
    	row=2;
    	pagination = true;
    }   else {
    	pagination = false;
    }
    
}//end while loop
    
    
    
}
private void UpdateDA_SectionHead(int rowNum) throws InterruptedException, ParseException {
	 
		SoftAssert sftAssert = new SoftAssert();
		loginUser(rowNum,reader.getCellData("UpdateDA", "LoginType", rowNum),reader.getCellData("UpdateDA", "UserName", rowNum), reader.getCellData("UpdateDA", "Password", rowNum));
		//Navigate to Current DA page
		navigateToUpDateDA(reader.getCellData("UpdateDA", "DAObjID", rowNum));
		//verify current DA field
		String strActualDA = UpdateDAPage.span_DAPercentage(driver).getText();
		String strExpectedDA = reader.getCellData("UpdateDA", "CurrentDA", rowNum);
		Assert.assertEquals(Double.parseDouble(strActualDA), Double.parseDouble(strExpectedDA), "Current DA not matched");
		//enter New DA field
		UpdateDAPage.txtbx_NewDA(driver).sendKeys(Keys.CONTROL + "a");
		Thread.sleep(1000);
		UpdateDAPage.txtbx_NewDA(driver).sendKeys(Keys.DELETE);
		Thread.sleep(1000);
		sendkeys(driver,UpdateDAPage.txtbx_NewDA(driver),20,reader.getCellData("UpdateDA", "NewDA", rowNum));
		//enter From / Effective Date
		clickOn(driver,UpdateDAPage.calendar_FromDate(driver),10);
		Thread.sleep(2000);
		DatePicker(reader.getCellData("UpdateDA", "FromDate", rowNum));
		Thread.sleep(2000);
		//verify To Date
		String strActualToDate = UpdateDAPage.span_ToDate(driver).getText();
		String strExpToDate = getLastDayOfTheMonth();
		sftAssert.assertEquals(strActualToDate, strExpToDate, "Mismatch in To Date");
		
		//verify DA for Months
		String strFrom = UpdateDAPage.calendar_FromDate(driver).getAttribute("innerText");
		strFrom.trim();
		int Expectedmnths = getMonthsDifference(strFrom, UpdateDAPage.span_ToDate(driver).getText());
		String strMonths = UpdateDAPage.span_DAforMonths(driver).getText() ;
		int intactualMonths = Integer.parseInt(strMonths);
		Assert.assertEquals(intactualMonths, Expectedmnths,"Mismatch in DA for Months");
		reader.setCellData("Properties", "OldDA", 2,strActualDA);
		reader.setCellData("Properties", "CurrentDA", 2, reader.getCellData("UpdateDA", "NewDA", rowNum));
		reader.setCellData("Properties", "DAMonths", 2, strMonths);
		reader.setCellData("Properties", "DAEffDate", 2, UpdateDAPage.calendar_FromDate(driver).getText());
		clickOn(driver,UpdateDAPage.btn_Action(driver, "Attach GO"),10);
		Thread.sleep(2000);
		UploadFiles.uploadDocument(reader.getCellData("UpdateDA", "Modal_FileName", rowNum), reader.getCellData("UpdateDA", "Modal_File", rowNum), reader.getCellData("UpdateDA", "Modal_Category", rowNum), sftAssert);
		/*if(bln==true) {
			if(UpdateDAPage.lnk_File(driver, reader.getCellData("UpdateDA", "Modal_FileName", rowNum)).isDisplayed() == true) {
				Assert.assertEquals(true, true);  }
			else {
				Assert.assertEquals(false, true,"Upload unsuccessful");		}
			clickOn(driver,UpdateDAPage.btn_Action(driver, "Update"),10);
		}*/	
		clickOn(driver,UpdateDAPage.btn_Action(driver, "Update"),10);
		Thread.sleep(3000);
		 
		/*if(UpdateDAPage.span_Confirm(driver).isDisplayed()==true) {
			clickOn(driver,UpdateDAPage.btn_Action(driver, "OK"),10);
		}*/
		clickOn(driver,UpdateDAPage.btn_Action(driver, "OK"),10);
		Thread.sleep(5000);
		boolean flg= UpdateDAPage.span_ConfirmMsg(driver,"This case has been routed successfully to the Audit Officer").isDisplayed();
		sftAssert.assertTrue(flg,"Confirmation message was not dispalyed after submission");
		
		//flg= UpdateDAPage.span_MsgPendingInfo(driver,"Pending-AuditOfficer").isDisplayed();
		//sftAssert.assertTrue(flg,"Confirmation message was not dispalyed after submission");
				
		getDAObjectId();
		reader.setCellData("UpdateDA", "DAObjID", 2, strDAObjId);
		reader.setCellData("UpdateDA", "DAObjID", 3, strDAObjId);
		reader.setCellData("UpdateDA", "DAObjID", 4, strDAObjId);
		reader.setCellData("UpdateDA", "DAObjID", 5, strDAObjId);
		
		clickOn(driver,UpdateDAPage.btn_Action(driver, "Close"),10);
		
		Thread.sleep(3000);
				
		LogOut();
		

		sftAssert.assertAll();	
	}
	
	private void UpdateDA_AuditOfficer(int rowNum) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub
			SoftAssert sftAssert = new SoftAssert();
			loginUser(rowNum,reader.getCellData("UpdateDA", "LoginType", rowNum),reader.getCellData("UpdateDA", "UserName", rowNum), reader.getCellData("UpdateDA", "Password", rowNum));
			//Navigate to Current DA page
			navigateToUpDateDA(reader.getCellData("UpdateDA", "DAObjID", rowNum));
			verifyDAReadOnlyFields(rowNum,sftAssert);
			clickOn(driver,UpdateDAPage.btn_Action(driver, "Approve"),10);
			Thread.sleep(2000);
			if(UpdateDAPage.span_Confirm(driver).isDisplayed()==true) {
				clickOn(driver,UpdateDAPage.btn_Action(driver, "OK"),10);
			}
			Thread.sleep(3000);
			//boolean flg= UpdateDAPage.span_ConfirmMsg(driver,"This case has been routed successfully to the Deputy Director").isDisplayed();
			//sftAssert.assertTrue(flg,"Confirmation message was not dispalyed after submission");
			
			//flg= UpdateDAPage.span_MsgPendingInfo(driver,"Pending-DeputyDirector").isDisplayed();
			//sftAssert.assertTrue(flg,"Confirmation message was not dispalyed after submission");
						
			clickOn(driver,UpdateDAPage.btn_Action(driver, "Close"),10);
			
			Thread.sleep(3000);
		 
			LogOut();

			sftAssert.assertAll();	
	}
	
	private void UpdateDA_DeputyDirector(int rowNum) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub
			SoftAssert sftAssert = new SoftAssert();
			loginUser(rowNum,reader.getCellData("UpdateDA", "LoginType", rowNum),reader.getCellData("UpdateDA", "UserName", rowNum), reader.getCellData("UpdateDA", "Password", rowNum));
			//Navigate to Current DA page
			navigateToUpDateDA(reader.getCellData("UpdateDA", "DAObjID", rowNum));
			verifyDAReadOnlyFields(rowNum,sftAssert);
			clickOn(driver,UpdateDAPage.btn_Action(driver, "Approve"),10);
			Thread.sleep(2000);
			if(UpdateDAPage.span_Confirm(driver).isDisplayed()==true) {
				clickOn(driver,UpdateDAPage.btn_Action(driver, "OK"),10);
			}
			Thread.sleep(3000);
			//boolean flg= UpdateDAPage.span_ConfirmMsg(driver,"This case has been routed successfully to the Commissioner").isDisplayed();
			//sftAssert.assertTrue(flg,"Confirmation message was not dispalyed after submission");
			
			//flg= UpdateDAPage.span_MsgPendingInfo(driver,"Pending-Commissioner").isDisplayed();
			//sftAssert.assertTrue(flg,"Confirmation message was not dispalyed after submission");
			
			clickOn(driver,UpdateDAPage.btn_Action(driver, "Close"),10);
			//Thread.sleep(3000);
			
			
			LogOut();	

			sftAssert.assertAll();	
	}

private void UpdateDA_Comissioner(int rowNum) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub
	SoftAssert sftAssert = new SoftAssert();
	loginUser(rowNum,reader.getCellData("UpdateDA", "LoginType", rowNum),reader.getCellData("UpdateDA", "UserName", rowNum), reader.getCellData("UpdateDA", "Password", rowNum));
	
	clickOn(driver,UpdateDAPage.span_PDMSPendingTasks(driver),10);
	Thread.sleep(4000);
	
	//Navigate to Current DA page
	navigateToUpDateDA(reader.getCellData("UpdateDA", "DAObjID", rowNum));
	verifyDAReadOnlyFields(rowNum,sftAssert);
	clickOn(driver,UpdateDAPage.btn_Action(driver, "Approve"),10);
	Thread.sleep(2000);
	if(UpdateDAPage.span_Confirm(driver).isDisplayed()==true) {
		clickOn(driver,UpdateDAPage.btn_Action(driver, "OK"),10);
	}
	Thread.sleep(3000);
	 	
	/*boolean flg= UpdateDAPage.span_MsgPendingInfo(driver,"Pensioner records are successfully updated with new DA").isDisplayed();
	sftAssert.assertTrue(flg,"Confirmation message was not dispalyed after submission");
	
	clickOn(driver,UpdateDAPage.btn_Action(driver, "Close"),10);
	Thread.sleep(2000);*/
	
	 
	LogOut();		

	sftAssert.assertAll();	
	
		
	}	
private void verifyDAReadOnlyFields(int rowNum , SoftAssert asst) throws ParseException, InterruptedException {
	//verify current DA field
	String strActual = UpdateDAPage.span_DAReadonlyField(driver, "Current DA %").getText();
	String strExpected = reader.getCellData("UpdateDA", "CurrentDA", rowNum);
	asst.assertEquals(Double.parseDouble(strActual), Double.parseDouble(strExpected), "Current DA not matched");
	
	//verify New DA %
	strActual = UpdateDAPage.span_DAReadonlyField(driver, "New DA %").getText();
	strExpected = reader.getCellData("UpdateDA", "NewDA", rowNum);
	asst.assertEquals(Double.parseDouble(strActual), Double.parseDouble(strExpected), "New DA not matched");
	//verify From Date
	strExpected = getConvertodDate(reader.getCellData("UpdateDA", "FromDate", rowNum));
	strExpected = ConvertDateMonthInTwoDigitFormat(strExpected);
	strActual = UpdateDAPage.span_DAReadonlyField(driver, "From Date").getText();
	//verify To Date
	strActual = UpdateDAPage.span_DAReadonlyField(driver, "To Date").getText();
	strExpected = getLastDayOfTheMonth();
	asst.assertEquals(strActual, strExpected, "Mismatch in To Date");
	//verify To Date
	strActual = UpdateDAPage.span_DAReadonlyField(driver, "DA for months").getText();
	int mnths = getMonthsDifference(UpdateDAPage.span_DAReadonlyField(driver, "From Date").getText(), UpdateDAPage.span_DAReadonlyField(driver, "To Date").getText());
	asst.assertEquals(Integer.parseInt(strActual), mnths, "Mismatch in To Date");
	if(UpdateDAPage.lnk_File(driver, reader.getCellData("UpdateDA", "Modal_FileName", rowNum)).isDisplayed() == true) {
		asst.assertEquals(true, true);  }
	else {
		asst.assertEquals(false, true,"Upload unsuccessful");	}
	
	
	//clickOn(driver,UpdateDAPage.btn_Action(driver, "Close"),10);
	Thread.sleep(3000);
		
}
private void navigateToUpDateDA(String strDAID) throws InterruptedException {
		
		if(strDAID.isEmpty()) {
			//Navigate to Add New Pensioner from Left Pane
			navigateToDA();
		}
		else {
			HomeTests.UpdateDASearch(strDAID);
			strDAObjId = strDAID;
			}
		
	}

private void navigateToDA() throws InterruptedException {
	 
	clickOn(driver,LeftNavigationPage.lnk_UpdateDA(driver),10);
	Thread.sleep(3000);		
}


private void getDAObjectId() {	
	if(UpdateDAPage.span_DAObjectId(driver).isDisplayed()==true) {
	strDAObjId = UpdateDAPage.span_DAObjectId(driver).getText();
	strDAObjId=strDAObjId.substring(1, strDAObjId.length()-1); //Removing First & last characters from string ()
	logfile.info("Fetched DA Work Object Id as : " + strDAObjId);
	}
}
}
