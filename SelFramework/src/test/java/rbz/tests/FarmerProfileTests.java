package rbz.tests;

import com.utilities.Base;
import com.utilities.CustomListener;

import rbz.repository.AddFarmerPage;
import rbz.repository.FarmerProfilePage;
import rbz.repository.LeftNavigationPage;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListener.class)
public class FarmerProfileTests extends Base {

	//*****************Test functions********************************************
		//@Test(priority=1, groups={"Sanity","Regression"})
		public void addActiveFarmerTest() throws InterruptedException, java.text.ParseException {
			loggerExtentReport = extent.startTest("Add Active Farmer");
			navigateToFarmerProfile();	
			int TotalFarmers = Integer.parseInt(FarmerProfilePage.lbl_TotalFarmersCnt(driver).getText());
			int ActiveFarmers = Integer.parseInt(FarmerProfilePage.lbl_ActiveFarmersCnt(driver).getText());
			int InActiveFarmers = Integer.parseInt(FarmerProfilePage.lbl_InactiveFarmersCnt(driver).getText());
			
			addFarmerData(4);
			
			int TotalFarmersAfterAddFarmer = Integer.parseInt(FarmerProfilePage.lbl_TotalFarmersCnt(driver).getText());
			int ActiveFarmersAfterAddFarmer = Integer.parseInt(FarmerProfilePage.lbl_ActiveFarmersCnt(driver).getText());
			int InActiveFarmersAfterAddFarmer = Integer.parseInt(FarmerProfilePage.lbl_InactiveFarmersCnt(driver).getText());
			
			Assert.assertEquals(TotalFarmersAfterAddFarmer, TotalFarmers+1, "Total Farmers Count is not increased");
			Assert.assertEquals(ActiveFarmersAfterAddFarmer, ActiveFarmers+1, "Total active Farmers are not increased");
			Assert.assertEquals(InActiveFarmersAfterAddFarmer, InActiveFarmers, "Total Inactive Farmers are not increased");
			
			chkPagination(4);
			
			verifyFarmerDetailsinProfileTable(4);
		}
		//@Test(priority=2, groups={"Sanity","Regression"})
		public void addInactiveFarmerTest() throws InterruptedException, java.text.ParseException {
			loggerExtentReport = extent.startTest("Add Inactive Farmer");
			navigateToFarmerProfile();	
			int TotalFarmers = Integer.parseInt(FarmerProfilePage.lbl_TotalFarmersCnt(driver).getText());
			int InActiveFarmers = Integer.parseInt(FarmerProfilePage.lbl_InactiveFarmersCnt(driver).getText());
			int ActiveFarmers = Integer.parseInt(FarmerProfilePage.lbl_ActiveFarmersCnt(driver).getText());
			
			addFarmerData(5);
			
			int TotalFarmersAfterAddFarmer = Integer.parseInt(FarmerProfilePage.lbl_TotalFarmersCnt(driver).getText());
			int InActiveFarmersAfterAddFarmer = Integer.parseInt(FarmerProfilePage.lbl_InactiveFarmersCnt(driver).getText());
			int ActiveFarmersAfterAddFarmer = Integer.parseInt(FarmerProfilePage.lbl_ActiveFarmersCnt(driver).getText());
			
			Assert.assertEquals(TotalFarmersAfterAddFarmer, TotalFarmers+1, "Total Farmers Count is not increased");
			Assert.assertEquals(InActiveFarmersAfterAddFarmer, InActiveFarmers+1, "Total Inactive Farmers are not increased");
			Assert.assertEquals(ActiveFarmersAfterAddFarmer, ActiveFarmers, "Total active Farmers are increased");
			
			chkPagination(5);
			verifyFarmerDetailsinProfileTable(5);
			
		}
		@Test(priority=3, groups={"Regression"})	
		public void verifyErrMessageTest() throws InterruptedException {
			logfile.info("Test Case  verifyErrMessageTest - Started");
			loggerExtentReport = extent.startTest("AddFarmers:verifyErrMessage");
			navigateToFarmerProfile();
			logfile.info("*** Navigated to Farmer Profile window ****");
			clickOn(driver,FarmerProfilePage.btn_AddFarmer(driver),10);
			//Wait for 5 Sec
			Thread.sleep(5000);
			logfile.info("**** add Farmer window opened ****");
			clickOn(driver,AddFarmerPage.btn_AddFarmer(driver),10); //Click on Add button in Add Farmer window
			//Wait for 5 Sec
			Thread.sleep(5000);
			logfile.info("**** Clicked on Add button to validate error messages of each control****");
			for(int colNum=0; colNum<=15; colNum++) {
				String str2 = new String("N/A");
				if((reader.getCellData("AddFarmer", colNum, 2)).equalsIgnoreCase(str2)) {
					//System.out.println("Hey Obj Name is : " + reader.getCellData("AddFarmer", colNum, 3));
				}
				else {
				String  strActualErrMessage = AddFarmerPage.div_ObjErr(driver,reader.getCellData("AddFarmer", colNum, 3)).getText();
				String strExpectedErrMessage = reader.getCellData("AddFarmer", colNum, 2);								
				Assert.assertEquals(strActualErrMessage, strExpectedErrMessage);
				logfile.info("Validated Message : " + strActualErrMessage);
				}
			}
			
			//Verify Crop details error message
			String  strActualErrMessage = AddFarmerPage.div_CropDetErr(driver).getText();
			String strExpectedErrMessage = reader.getCellData("AddFarmer", 16, 2);
			Assert.assertEquals(strActualErrMessage, strExpectedErrMessage);
			logfile.info("Validated Message : " + strActualErrMessage);
			
			AddFarmerPage.btn_PageClose(driver).click();
			Thread.sleep(2000);
			logfile.info("Clicked on Close button on Add Farmer Pop up window");
			logfile.info("Test Case  verifyErrMessageTest - Completed");		
		}
		
		//*****************Reusable functions********************************************
		public void addFarmerData(int rowNum) throws InterruptedException {
				
				clickOn(driver,FarmerProfilePage.btn_AddFarmer(driver),10);
				//Wait for 5 Sec
				Thread.sleep(5000);
				sendkeys(driver,AddFarmerPage.txtbx_CardNumber(driver),20,reader.getCellData("AddFarmer", "CardNumber", rowNum));
				Thread.sleep(2000);
				
				AddFarmerPage.txtbx_FarmerName(driver).click();
				sendkeys(driver,AddFarmerPage.txtbx_FarmerName(driver),10,reader.getCellData("AddFarmer", "FarmerName", rowNum));
				sendkeys(driver,AddFarmerPage.txtbx_FarmerMobile(driver),10,reader.getCellData("AddFarmer", "FarmerMobileNumber", rowNum));
				selValue(driver,AddFarmerPage.sel_District(driver),10,reader.getCellData("AddFarmer", "DistrictName", rowNum));
				selValue(driver,AddFarmerPage.sel_Mandal(driver),10,reader.getCellData("AddFarmer", "MandalName", rowNum));
				selValue(driver,AddFarmerPage.sel_Village(driver),10,reader.getCellData("AddFarmer", "VillageName", rowNum));
				selValue(driver,AddFarmerPage.sel_NomineeRelationship(driver),10,reader.getCellData("AddFarmer", "RepresentativeRelationship", rowNum));
				sendkeys(driver,AddFarmerPage.txtbx_FarmerNominee(driver),10,reader.getCellData("AddFarmer", "RepresentativeName", rowNum));
				sendkeys(driver,AddFarmerPage.txtbx_NomineeMobile(driver),10,reader.getCellData("AddFarmer", "RepresentativeMobileNumber", rowNum));
				
				sendkeys(driver,AddFarmerPage.txtbx_PassbookNumber(driver),10,reader.getCellData("AddFarmer", "PassbookNumber", rowNum));
				sendkeys(driver,AddFarmerPage.txtbx_AccountNumber(driver),10,reader.getCellData("AddFarmer", "AccountNumber", rowNum));
				sendkeys(driver,AddFarmerPage.txtbx_Acres(driver),10,reader.getCellData("AddFarmer", "Acres", rowNum));
				sendkeys(driver,AddFarmerPage.txtbx_Cents(driver),10,reader.getCellData("AddFarmer", "Cents", rowNum));
				clickOn(driver,AddFarmerPage.chbx_Vegetables(driver),10);
				
				clickOn(driver,AddFarmerPage.calendar_Cropstartdate(driver),10);
				Thread.sleep(2000);
				DatePicker(reader.getCellData("AddFarmer", "CropStartDate", rowNum));
				
				clickOn(driver,AddFarmerPage.calendar_EOappdate(driver),10);
				Thread.sleep(2000);
				DatePicker(reader.getCellData("AddFarmer", "EOApprovalDate", rowNum));
				
				//Select Stall Type
				selValue(driver,AddFarmerPage.sel_StallType(driver),10,reader.getCellData("AddFarmer", "TypeofStall", rowNum));
				
				clickOn(driver,AddFarmerPage.btn_AddFarmer(driver),10); //Click on Add button in Add Farmer window
				//Wait for 5 Sec
				Thread.sleep(5000);
				
				//Capture Actual Vs. Expected Farmer added messages
				String strActual = AddFarmerPage.lbl_AddFarmerConfirmation(driver).getText(); 
				String strExpected = reader.getCellData("AddFarmer", "FarmerName", rowNum) 
						+ "-" + reader.getCellData("AddFarmer", "CardNumber", rowNum) 
				+ " added Successfully !";
				
				Assert.assertEquals(strActual, strExpected); //verify farmer added message
				
				clickOn(driver,AddFarmerPage.btn_Close(driver),10);	 //Close pop-up window
				//Wait for 5 Sec
				Thread.sleep(5000);
				
				FarmerProfilePage.frm_FarmerPage(driver).click();
				Thread.sleep(2000);
				
				//check for added farmer in web table
				//FarmerProfilePage.tbl_FarmersList(driver, reader.getCellData("AddFarmer", "CardNumber", rowNum)).click();
			
		}
			
		public void navigateToFarmerProfile() throws InterruptedException {
			clickOn(driver,LeftNavigationPage.lnk_RythuBazaar(driver),10);
			clickOn(driver,LeftNavigationPage.lnk_FarmerProfile(driver),10);
			//Wait for 5 Sec
			Thread.sleep(5000);
		}

		/*
		 * Function:chkPagination
		 * Purpose: checks whether the given Card Number exists in current page or not
		 * If Card Number doesn't exists in current page, clicks on Pagination button till it finds required record
		 * Input: Excel Row number 
		 * Output : Clicks the Row
		 * */
		public void chkPagination(int tblRow) throws InterruptedException {
			Boolean flag = true;
			
			while(flag == true) {
			if (chkCardNumber(reader.getCellData("AddFarmer", "CardNumber", tblRow)) == true) {
				FarmerProfilePage.tbl_FarmersList(driver, reader.getCellData("AddFarmer", "CardNumber", tblRow)).click();
				flag = false;}
			else {
				FarmerProfilePage.btn_PaginationNext(driver).click();
				Thread.sleep(5000);
				flag = true; }
			} //end while loop
		}
		
		public void verifyFarmerDetailsinProfileTable(int excelrowNum) throws InterruptedException, java.text.ParseException {
			String stractualValue=null;
			String strExpectedValue=null;
				
			
				//Validate Card Number in Farmer list table for the required Row
				stractualValue = FarmerProfilePage.tbl_FarmersList_tdValue(driver,reader.getCellData("AddFarmer", "CardNumber", 
						excelrowNum),2).getText();
				strExpectedValue = reader.getCellData("AddFarmer", "CardNumber", excelrowNum);
				Assert.assertEquals(stractualValue, strExpectedValue,"Card Number mismatch in Farmers list table");
				
				//Validate Farmer Name in Farmer list table for the required Row
				stractualValue = FarmerProfilePage.tbl_FarmersList_tdValue(driver,reader.getCellData("AddFarmer", "FarmerName", 
						excelrowNum),3).getText();
				strExpectedValue = reader.getCellData("AddFarmer", "FarmerName", excelrowNum);
				Assert.assertEquals(stractualValue, strExpectedValue,"Farmer Name mismatch in Farmers list table");
				
				
				//Validate Farmer Mobile Number in Farmer list table for the required Row
				stractualValue = FarmerProfilePage.tbl_FarmersList_tdValue(driver,reader.getCellData("AddFarmer", "FarmerMobileNumber", 
						excelrowNum),4).getText();
				strExpectedValue = reader.getCellData("AddFarmer", "FarmerMobileNumber", excelrowNum);
				Assert.assertEquals(stractualValue, strExpectedValue,"Farmer Mobile Number mismatch in Farmers list table");
			
				
				//Validate Representative Name in Farmer list table for the required Row
				stractualValue = FarmerProfilePage.tbl_FarmersList_tdValue(driver,reader.getCellData("AddFarmer", "RepresentativeName", 
						excelrowNum),5).getText();
				strExpectedValue = reader.getCellData("AddFarmer", "RepresentativeName", excelrowNum);
				Assert.assertEquals(stractualValue, strExpectedValue,"Representative Name mismatch in Farmers list table");
				
				
				//Validate Representative Mobile Number in Farmer list table for the required Row
				stractualValue = FarmerProfilePage.tbl_FarmersList_tdValue(driver,reader.getCellData("AddFarmer", "RepresentativeMobileNumber", 
						excelrowNum),6).getText();
				strExpectedValue = reader.getCellData("AddFarmer", "RepresentativeMobileNumber", excelrowNum);
				Assert.assertEquals(stractualValue, strExpectedValue,"Representative Mobile Number mismatch in Farmers list table");
			
				//Converts Date format from dd-MMM-yyyy to dd/MM/yyyy to compare with table grid value			
				String strupdateDate = getConvertodDate(reader.getCellData("AddFarmer", "EOApprovalDate",excelrowNum));			
				//Validate EO Approval Date in Farmer list table for the required Row
				stractualValue = FarmerProfilePage.tbl_FarmersList_tdValue(driver,strupdateDate,7).getText();
				strExpectedValue = strupdateDate;
				Assert.assertEquals(stractualValue, strExpectedValue,"EO Approval Date mismatch in Farmers list table");
				
				//Converts Date format from dd-MMM-yyyy to dd/MM/yyyy to compare with table grid value
				strupdateDate = getConvertodDate(reader.getCellData("AddFarmer", "CropStartDate",excelrowNum));			
				//Validate Crop Start Date in Farmer list table for the required Row
				stractualValue = FarmerProfilePage.tbl_FarmersList_tdValue(driver,strupdateDate,8).getText();
				strExpectedValue = strupdateDate;
				Assert.assertEquals(stractualValue, strExpectedValue,"Crop Start Date mismatch in Farmers list table");
				
				String strCropStartDate = stractualValue;
				Date dtCropStartDate =new SimpleDateFormat("dd/MM/yyyy").parse(strCropStartDate);
				String strCropEndDate = getCropEndDate(dtCropStartDate);
				System.out.println("Crop End Date fetched from Crop Start Date is: " + strCropEndDate);
				
				//Validate Crop End Date in Farmer list table for the required Row
				stractualValue = FarmerProfilePage.tbl_FarmersList_tdValue(driver,strCropEndDate,9).getText();
				strExpectedValue = strCropEndDate;
				Assert.assertEquals(stractualValue, strExpectedValue,"Crop End Date mismatch in Farmers list table");
				
				
				//Validate Status in Farmer list table for the required Row
				stractualValue = FarmerProfilePage.tbl_FarmersList_tdValue(driver,reader.getCellData("AddFarmer", "Status", 
						excelrowNum),10).getText();
				strExpectedValue = reader.getCellData("AddFarmer", "Status", excelrowNum);
				Assert.assertEquals(stractualValue, strExpectedValue,"Status mismatch in Farmers list table");
					
			//System.out.println("row index is : "+FarmerProfilePage.tbl_FarmersList(driver, "CardAB").getAttribute("pl_index"));
		}

		public static Boolean chkCardNumber(String strCardNumber) {
			for(int rowNum=2 ; rowNum<=10 ; rowNum++) {
				WebElement tdElement = driver.findElement(By.xpath("//*[@id=\"bodyTbl_right\"]/tbody/tr[" +rowNum + "]/td[2]"));
				if (strCardNumber.equalsIgnoreCase(tdElement.getText())) {
					return true;
				}
			}		
			return false;
		}
		public static String getCropEndDate(Date dtCropStartDate) {		 
			 int monthsToAdd = 6;
		     
		     Calendar c = Calendar.getInstance();
		     
		     c.setTime(dtCropStartDate);
		     // add months to current date
		     c.add(Calendar.MONTH, monthsToAdd);
		     
		     String strDate = (c.get(Calendar.DATE)-1) + "/" + (c.get(Calendar.MONTH) + 1) +  "/" + c.get(Calendar.YEAR);
		     return strDate;
		     
		 }
		
		public void addFarmerTestoldforloop() throws InterruptedException {
			loggerExtentReport = extent.startTest("AddFarmers");
			navigateToFarmerProfile();
			int rowCount = reader.getRowCount("AddFarmer");
			for(int rowNum=4; rowNum<=rowCount; rowNum++) {
				clickOn(driver,FarmerProfilePage.btn_AddFarmer(driver),10);
				//Wait for 5 Sec
				Thread.sleep(5000);
				sendkeys(driver,AddFarmerPage.txtbx_CardNumber(driver),20,reader.getCellData("AddFarmer", "CardNumber", rowNum));
				Thread.sleep(2000);
				
				AddFarmerPage.txtbx_FarmerName(driver).click();
				sendkeys(driver,AddFarmerPage.txtbx_FarmerName(driver),10,reader.getCellData("AddFarmer", "FarmerName", rowNum));
				sendkeys(driver,AddFarmerPage.txtbx_FarmerMobile(driver),10,reader.getCellData("AddFarmer", "FarmerMobileNumber", rowNum));
				selValue(driver,AddFarmerPage.sel_District(driver),10,reader.getCellData("AddFarmer", "DistrictName", rowNum));
				selValue(driver,AddFarmerPage.sel_Mandal(driver),10,reader.getCellData("AddFarmer", "MandalName", rowNum));
				selValue(driver,AddFarmerPage.sel_Village(driver),10,reader.getCellData("AddFarmer", "VillageName", rowNum));
				selValue(driver,AddFarmerPage.sel_NomineeRelationship(driver),10,reader.getCellData("AddFarmer", "RepresentativeRelationship", rowNum));
				sendkeys(driver,AddFarmerPage.txtbx_FarmerNominee(driver),10,reader.getCellData("AddFarmer", "RepresentativeName", rowNum));
				sendkeys(driver,AddFarmerPage.txtbx_NomineeMobile(driver),10,reader.getCellData("AddFarmer", "RepresentativeMobileNumber", rowNum));
				
				sendkeys(driver,AddFarmerPage.txtbx_PassbookNumber(driver),10,reader.getCellData("AddFarmer", "PassbookNumber", rowNum));
				sendkeys(driver,AddFarmerPage.txtbx_AccountNumber(driver),10,reader.getCellData("AddFarmer", "AccountNumber", rowNum));
				sendkeys(driver,AddFarmerPage.txtbx_Acres(driver),10,reader.getCellData("AddFarmer", "Acres", rowNum));
				sendkeys(driver,AddFarmerPage.txtbx_Cents(driver),10,reader.getCellData("AddFarmer", "Cents", rowNum));
				clickOn(driver,AddFarmerPage.chbx_Vegetables(driver),10);
				
				clickOn(driver,AddFarmerPage.calendar_Cropstartdate(driver),10);
				Thread.sleep(2000);
				DatePicker(reader.getCellData("AddFarmer", "CropStartDate", rowNum));
				
				
				clickOn(driver,AddFarmerPage.calendar_EOappdate(driver),10);
				Thread.sleep(2000);
				DatePicker(reader.getCellData("AddFarmer", "EOApprovalDate", rowNum));
				
				//Select Stall Type
				selValue(driver,AddFarmerPage.sel_StallType(driver),10,reader.getCellData("AddFarmer", "TypeofStall", rowNum));
				
				clickOn(driver,AddFarmerPage.btn_AddFarmer(driver),10); //Click on Add button in Add Farmer window
				//Wait for 5 Sec
				Thread.sleep(5000);
				
				//Capture Actual Vs. Expected Farmer added messages
				String strActual = AddFarmerPage.lbl_AddFarmerConfirmation(driver).getText(); 
				String strExpected = reader.getCellData("AddFarmer", "FarmerName", rowNum) 
						+ "-" + reader.getCellData("AddFarmer", "CardNumber", rowNum) 
				+ " added Successfully !";
				
				Assert.assertEquals(strActual, strExpected); //verify farmer added message
				
				clickOn(driver,AddFarmerPage.btn_Close(driver),10);	 //Cloce popup window
				//Wait for 5 Sec
				Thread.sleep(5000);
				
				FarmerProfilePage.frm_FarmerPage(driver).click();
				Thread.sleep(2000);
				
				//check for added farmer in web table
				FarmerProfilePage.tbl_FarmersList(driver, reader.getCellData("AddFarmer", "CardNumber", rowNum)).click();
				
			}//end for loop
			
		}
		

}
