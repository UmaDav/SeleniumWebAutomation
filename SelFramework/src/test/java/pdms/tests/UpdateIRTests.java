package pdms.tests;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.utilities.Base;
import com.utilities.CustomListener;
import com.utilities.excelComponent;

import pdms.repository.HomePage;
import pdms.repository.LeftNavigationPage;
import pdms.repository.UpdateDAPage;
import pdms.repository.UpdateIRPage;

@Listeners(CustomListener.class)
public class UpdateIRTests extends Base{
	private String strIRObjId="";
	
@Test
public void updateIRTest() throws InterruptedException, ParseException, IOException {
	loggerExtentReport = extent.startTest("Update IR Test");
		UpdateIR_SectionHead(2);
		UpdateIR_AuditOfficer(3);
		UpdateIR_DeputyDirector(4);
		UpdateIR_Comissioner(5);
	 	checkIRArrears(2);
	}

	private void UpdateIR_SectionHead(int rowNum) throws InterruptedException, ParseException {

		SoftAssert sftAssert = new SoftAssert();
		loginUser(rowNum,reader.getCellData("UpdateIR", "LoginType", rowNum),reader.getCellData("UpdateIR", "UserName", rowNum), reader.getCellData("UpdateIR", "Password", rowNum));
		//Navigate to Current DA page
		navigateToUpDateIR(reader.getCellData("UpdateIR", "IRObjID", rowNum));
		//select Type Of IR
		selValue(driver,UpdateIRPage.sel_TypeOfIR(driver),10,reader.getCellData("UpdateIR", "TypeOfIR", rowNum));
		//enter current IR%
		UpdateIRPage.txtbx_CurrentIRPercent(driver).sendKeys(Keys.CONTROL + "a");
		Thread.sleep(1000);
		UpdateIRPage.txtbx_CurrentIRPercent(driver).sendKeys(Keys.DELETE);
		Thread.sleep(1000);
		sendkeys(driver,UpdateIRPage.txtbx_CurrentIRPercent(driver),20,reader.getCellData("UpdateIR", "CurrentIR", rowNum));
		//enter From Date
		clickOn(driver,UpdateIRPage.calendar_FromDate(driver),10);
		Thread.sleep(2000);
		DatePicker(reader.getCellData("UpdateIR", "FromDate", rowNum));
		Thread.sleep(2000);
		//enter To Date
		clickOn(driver,UpdateIRPage.calendar_ToDate(driver),10);
		Thread.sleep(2000);
		DatePicker(reader.getCellData("UpdateIR", "ToDate", rowNum));
		Thread.sleep(2000);
		 String strExpToDate = getLastDayOfTheMonth();   
	     int Expectedmnths = getMonthsDifference(UpdateIRPage.calendar_FromDate(driver).getText(), strExpToDate);
	     reader.setCellData("Properties", "CurrentIR", 2,UpdateIRPage.txtbx_CurrentIRPercent(driver).getText());
	     reader.setCellData("Properties", "IRMonths", 2,Integer.toString(Expectedmnths));
		clickOn(driver,UpdateIRPage.btn_Action(driver, "Attach GO"),10);
		Thread.sleep(2000);
		UploadFiles.uploadDocument(reader.getCellData("UpdateIR", "Modal_FileName", rowNum), reader.getCellData("UpdateIR", "Modal_File", rowNum), reader.getCellData("UpdateIR", "Modal_Category", rowNum), sftAssert);
		/*if(bln==true) {
			if(UpdateIRPage.lnk_File(driver, reader.getCellData("UpdateIR", "Modal_FileName", rowNum)).isDisplayed() == true) {
				Assert.assertEquals(true, true);  }
			else {
				Assert.assertEquals(false, true,"Upload unsuccessful");		}
			clickOn(driver,UpdateIRPage.btn_Action(driver, "Submit"),10);
		}	*/			
		clickOn(driver,UpdateIRPage.btn_Action(driver, "Submit"),10);
		Thread.sleep(3000);
		 
		if(UpdateIRPage.span_Confirm(driver).isDisplayed()==true) {
			clickOn(driver,UpdateIRPage.btn_Action(driver, "OK"),10);
		}
		
		/*boolean flg= UpdateIRPage.span_ConfirmMsg(driver,"This case has been routed successfully to the Audit Officer").isDisplayed();
		sftAssert.assertTrue(flg,"Confirmation message was not dispalyed after submission");
		
		flg= UpdateIRPage.span_MsgPendingInfo(driver,"Pending-AuditOfficer").isDisplayed();
		sftAssert.assertTrue(flg,"Confirmation message was not dispalyed after submission");
		*/
		
		getIRObjectId();
		reader.setCellData("UpdateIR", "IRObjID", 2, strIRObjId);
		reader.setCellData("UpdateIR", "IRObjID", 3, strIRObjId);
		reader.setCellData("UpdateIR", "IRObjID", 4, strIRObjId);
		reader.setCellData("UpdateIR", "IRObjID", 5, strIRObjId);
		
		UpdateIRPage.btn_Action(driver, "Close").click();
		//clickOn(driver,UpdateIRPage.btn_Action(driver, "Close"),10);
		Thread.sleep(3000);
				
		LogOut();
		
		sftAssert.assertAll();
		
	}
	private void getIRObjectId() {
		if(UpdateIRPage.span_IRObjectId(driver).isDisplayed()==true) {
			strIRObjId = UpdateIRPage.span_IRObjectId(driver).getText();
			strIRObjId=strIRObjId.substring(1, strIRObjId.length()-1); //Removing First & last characters from string ()
			logfile.info("Fetched DA Work Object Id as : " + strIRObjId);
			}
		
	}

	private void UpdateIR_AuditOfficer(int rowNum) throws InterruptedException, ParseException {
		 
		SoftAssert sftAssert = new SoftAssert();
		loginUser(rowNum,reader.getCellData("UpdateIR", "LoginType", rowNum),reader.getCellData("UpdateIR", "UserName", rowNum), reader.getCellData("UpdateIR", "Password", rowNum));
		//Navigate to Current IR page
		navigateToUpDateIR(reader.getCellData("UpdateIR", "IRObjID", rowNum));
		verifyIRReadOnlyFields(rowNum,sftAssert);
		clickOn(driver,UpdateIRPage.btn_Action(driver, "Approve"),10);
		Thread.sleep(2000);
		if(UpdateIRPage.span_Confirm(driver).isDisplayed()==true) {
				clickOn(driver,UpdateIRPage.btn_Action(driver, "OK"),10);
			}
			Thread.sleep(3000);
			/*boolean flg= UpdateIRPage.span_ConfirmMsg(driver,"This case has been routed successfully to the Deputy Director").isDisplayed();
			sftAssert.assertTrue(flg,"Confirmation message was not displayed after submission");
			flg= UpdateIRPage.span_MsgPendingInfo(driver,"Pending-DeputyDirector").isDisplayed();
			sftAssert.assertTrue(flg,"Confirmation message was not displayed after submission");*/
			UpdateIRPage.btn_Action(driver, "Close");
			Thread.sleep(3000);
			
			LogOut();		
			
			sftAssert.assertAll();
	}
	private void UpdateIR_DeputyDirector(int rowNum) throws InterruptedException, ParseException {
		SoftAssert sftAssert = new SoftAssert();
		loginUser(rowNum,reader.getCellData("UpdateIR", "LoginType", rowNum),reader.getCellData("UpdateIR", "UserName", rowNum), reader.getCellData("UpdateIR", "Password", rowNum));
		//Navigate to Current IR page
		navigateToUpDateIR(reader.getCellData("UpdateIR", "IRObjID", rowNum));
		verifyIRReadOnlyFields(rowNum,sftAssert);
		clickOn(driver,UpdateIRPage.btn_Action(driver, "Approve"),10);
		Thread.sleep(2000);
		if(UpdateIRPage.span_Confirm(driver).isDisplayed()==true) {
			clickOn(driver,UpdateIRPage.btn_Action(driver, "OK"),10);
		}
		Thread.sleep(3000);
		/*boolean flg= UpdateIRPage.span_ConfirmMsg(driver,"This case has been routed successfully to the Commissioner").isDisplayed();
		sftAssert.assertTrue(flg,"Confirmation message was not displayed after submission");
		
		flg= UpdateIRPage.span_MsgPendingInfo(driver,"Pending-Commissioner").isDisplayed();
		sftAssert.assertTrue(flg,"Confirmation message was not disaalyed after submission");*/
		
		//clickOn(driver,UpdateIRPage.btn_Action(driver, "Close"),10);
		UpdateIRPage.btn_Action(driver, "Close").click();
		Thread.sleep(3000);
		
		
		LogOut();
		
		sftAssert.assertAll();
		
	}
	private void UpdateIR_Comissioner(int rowNum) throws InterruptedException, ParseException {
		SoftAssert sftAssert = new SoftAssert();
		loginUser(rowNum,reader.getCellData("UpdateIR", "LoginType", rowNum),reader.getCellData("UpdateIR", "UserName", rowNum), reader.getCellData("UpdateIR", "Password", rowNum));
		
		clickOn(driver,UpdateIRPage.span_PDMSPendingTasks(driver),10);
		Thread.sleep(4000);
		
		//Navigate to Current DA page
		navigateToUpDateIR(reader.getCellData("UpdateIR", "IRObjID", rowNum));
		verifyIRReadOnlyFields(rowNum,sftAssert);
		clickOn(driver,UpdateIRPage.btn_Action(driver, "Approve"),10);
		Thread.sleep(2000);
		if(UpdateIRPage.span_Confirm(driver).isDisplayed()==true) {
			clickOn(driver,UpdateIRPage.btn_Action(driver, "OK"),10);
		}
		Thread.sleep(3000);
		 	
		boolean flg= UpdateIRPage.span_MsgPendingInfo(driver,"Pensioner records are successfully updated with IR.").isDisplayed();
		sftAssert.assertTrue(flg,"Confirmation message was not dispalyed after submission");
		
		//clickOn(driver,UpdateIRPage.btn_Action(driver, "Close"),10);
		UpdateIRPage.btn_Action(driver, "Close").click();
		Thread.sleep(3000);
		
		LogOut();
		sftAssert.assertAll();
		
	}
	private void checkIRArrears(int rowNum) throws IOException, ParseException, InterruptedException {
		String filePath = System.getProperty("user.dir") + "\\testResults\\";
		String timestamp= getCurrentTimeStamp();
		String strFile = "PDMS_IR_Arrears_" + timestamp;
		String[] headers = new String[] { "PPONumber", "BasicAmount", "ActualIRArrears", "ExpectedIRArrears", "Status" };
		createExcelFile(filePath,strFile,"Results",headers);
		excelComponent IRreader  = new excelComponent(filePath + strFile +".xlsx");
			 	 
		String strPPONum ,  strActualIRArrs, strExpIRArrs, strEffDate;
		double dblBasicAmt ,TotalIRArrears=0, dblIR_Months, dblOldIR, dblCurrIR;		 
		dblCurrIR = Double.parseDouble(reader.getCellData("UpdateIR", "CurrentIR", rowNum));		 
		strEffDate = reader.getCellData("UpdateIR", "FromDate", rowNum);
		strEffDate = getConvertodDate(strEffDate);
		strEffDate = ConvertDateMonthInTwoDigitFormat(strEffDate);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    Date EffectDate = formatter.parse(strEffDate);	    
		SoftAssert sftAssert = new SoftAssert();
		loginUser(2,reader.getCellData("UpdateIR", "LoginType", rowNum),reader.getCellData("UpdateIR", "UserName", rowNum), reader.getCellData("UpdateIR", "Password", rowNum));
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
	     String strExpToDate = getLastDayOfTheMonth();   
	     int Expectedmnths = getMonthsDifference(strEffDate, strExpToDate);
	    while(pagination==true) {
	    	currtRow = IRreader.getRowCount("Results") +1;
	    	 int rows =  HomePage.tbl_Report_PensionersRows(driver);         
	    for (row=2;row<=rows;row++) {
	    	strPPONum = HomePage.tbl_Report_PensionersData(driver, row, 4).getText();
	    	strActualIRArrs = HomePage.tbl_Report_PensionersData(driver, row, 27).getText();
	    	strActualIRArrs = strActualIRArrs.substring(2,strActualIRArrs.length());
	    	IRreader.setCellData("Results", "PPONumber", currtRow, strPPONum);
	    	IRreader.setCellData("Results", "ActualIRArrears", currtRow, strActualIRArrs);
	    	Double dblActIR = Double.valueOf(strActualIRArrs.replaceAll(",", "").toString());    	
	    	String strBasicAmt = HomePage.tbl_Report_PensionersData(driver, row, 16).getText();    	
	    	DecimalFormat df = new DecimalFormat("#,###,##0");
	    	strBasicAmt = strBasicAmt.substring(2, strBasicAmt.length());
	    	IRreader.setCellData("Results", "BasicAmount", currtRow, strBasicAmt);
	    	Double dblBasic = Double.valueOf(strBasicAmt.replaceAll(",", "").toString());
	            	TotalIRArrears = (Expectedmnths * dblCurrIR * dblBasic)/100 ;
	            	TotalIRArrears = Math.round(TotalIRArrears);
	            	if(TotalIRArrears  == dblActIR) {
	            		IRreader.setCellData("Results", "Status", currtRow, "Pass");   }
	                	else {
	                		IRreader.setCellData("Results", "Status", currtRow, "Fail"); }            	
	            	String strFinalAmt = df.format(TotalIRArrears);
	            	IRreader.setCellData("Results", "ExpectedIRArrears", currtRow, strFinalAmt);     	
	            	currtRow = currtRow+1;	  	
	    }  //end for loop  	
	    	
	    if(UpdateIRPage.btn_PaginationNext(driver).isDisplayed() == true) {
	    	clickOn(driver,UpdateIRPage.btn_PaginationNext(driver),10);
	    	Thread.sleep(5000);
	    	row=2;
	    	pagination = true; 
	    	}   else {
	    	pagination = false;	    }
	    }//end while loop
	    
	} 
	    

		
	private void navigateToIR() throws InterruptedException {
		
		clickOn(driver,LeftNavigationPage.lnk_UpdateIR(driver),10);
		Thread.sleep(3000);		
	}

	private void navigateToUpDateIR(String strIRID) throws InterruptedException {
		
		if(strIRID.isBlank()) {
			//Navigate to Update IR
			navigateToIR();
		}
		else {
			HomeTests.UpdateIRSearch(strIRID);
			strIRObjId = strIRID;
			}
		
	}
	private void verifyIRReadOnlyFields(int rowNum , SoftAssert asst) throws ParseException, InterruptedException {
		//verify Type Of IR field
		String strActual = UpdateIRPage.span_IRReadonlyField(driver, "Type Of IR").getText();
		String strExpected = reader.getCellData("UpdateIR", "TypeOfIR", rowNum);
		asst.assertEquals(strActual, strExpected, "Type Of IR not matched");
		
		//verify current IR %
		strActual = UpdateIRPage.span_IRReadonlyField(driver, "Current IR %").getText();
		strExpected = reader.getCellData("UpdateIR", "CurrentIR", rowNum);
		asst.assertEquals(Double.parseDouble(strActual), Double.parseDouble(strExpected), "Current IR not matched");
		//verify From Date
		strExpected = getConvertodDate(reader.getCellData("UpdateIR", "FromDate", rowNum));
		strExpected = ConvertDateMonthInTwoDigitFormat(strExpected);
		strActual = UpdateIRPage.span_IRReadonlyField(driver, "From Date").getText();
		asst.assertEquals(strActual, strExpected, "Mismatch in From Date");
		//verify To Date
		strExpected = getConvertodDate(reader.getCellData("UpdateIR", "ToDate", rowNum));
		strExpected = ConvertDateMonthInTwoDigitFormat(strExpected);
		strActual = UpdateIRPage.span_IRReadonlyField(driver, "To Date").getText();
		asst.assertEquals(strActual, strExpected, "Mismatch in To Date");
		if(UpdateDAPage.lnk_File(driver, reader.getCellData("UpdateIR", "Modal_FileName", rowNum)).isDisplayed() == true) {
			asst.assertEquals(true, true);  }
		else {
			asst.assertEquals(false, true,"Upload unsuccessful");	}
		
		Thread.sleep(1000);
			
	}

	
}
