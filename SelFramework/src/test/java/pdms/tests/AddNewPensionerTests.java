package pdms.tests;

import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Repository.UploadFilesPage;
import com.utilities.Base;
import com.utilities.CustomListener;

import pdms.repository.LeftNavigationPage;
import pdms.repository.UpdateDAPage;
import rbz.repository.AddFarmerPage;
import rbz.repository.FarmerProfilePage;
import pdms.repository.AddPensionerPage;

@Listeners(CustomListener.class)
public class AddNewPensionerTests extends Base {

	public String strPenObjId;
	public double currDA;
	public double currIR;
	public int age;
	public static SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void ServicePensionTest() throws InterruptedException, ParseException {
		loggerExtentReport = extent.startTest("Add ServicePensionTest Test");
		addNewPesioner_SectionHead(9,reader.getCellData("AddPensioner", "TypeOfPension", 9));
		addNewPesioner_AuditOfficer(10,reader.getCellData("AddPensioner", "TypeOfPension", 10));
		addNewPesioner_DeputyDirector(11,reader.getCellData("AddPensioner", "TypeOfPension", 11));
		addNewPesioner_Comissioner(12,reader.getCellData("AddPensioner", "TypeOfPension", 12));
	}
	
	
	public void addNewPesioner_SectionHead(int rowNum, String strTypeOfPension) throws InterruptedException, ParseException {
		softAssert= new SoftAssert();
		loginUser(rowNum,reader.getCellData("AddPensioner", "LoginType", rowNum),reader.getCellData("AddPensioner", "UserName", rowNum), reader.getCellData("AddPensioner", "Password", rowNum));
		currDA = Double.parseDouble(reader.getCellData("Properties", "CurrentDA", 2));
		currIR = Double.parseDouble(reader.getCellData("Properties", "CurrentIR", 2));
		//currDA=Double.parseDouble(property.getProperty("DA"));
		//currIR=Double.parseDouble(property.getProperty("IR"));
		performNavigationforNewPensioner(reader.getCellData("AddPensioner", "ObjectID", rowNum));			
		enterBasicPensionerDetails(rowNum,reader.getCellData("AddPensioner", "TypeOfPension", rowNum));
		enterPensionAmountDetails(rowNum);
		age = getAge(AddPensionerPage.calendar_PensionerDOB(driver).getText());
		validatePensionAmounts(rowNum);
		enterPensionerBankDetails(rowNum);
		enterSupplementaryBillDetails(rowNum);
		//*** Validate Pension Start Date & Commutation End Dates
		validatePensionStartDate(reader.getCellData("AddPensioner", "RetirementDate", rowNum),AddPensionerPage.span_PensionStartDate(driver).getText());
		validateCommutationPaymentEndDate(reader.getCellData("AddPensioner", "CommutationPaymentDate", rowNum),AddPensionerPage.span_CommutationPaymentEndDate(driver).getText());
		
		//Validate Commutation Portion Pension Checkbox
          validateChkCommPortionPension();
          Boolean blnActual;
          if (age >= 70) {      	   
        	  validateAddlQntmStartDate(reader.getCellData("AddPensioner", "PenDateOfBirth", rowNum),AddPensionerPage.span_AddlQuntmStartDate(driver).getText());
        	  blnActual = AddPensionerPage.btnrd_AdditionalQuantumApplicableYesSelected(driver).isSelected();
  			 softAssert.assertTrue(blnActual, "Additional Quantum Applicable is not selected as Yes when age is more than 70 Years");	
  			 }
          else if (age < 70) {
        	 //System.out.println("addl Qntm start Date is : " + AddPensionerPage.span_AddlQuntmStartDate(driver).getText());
        	  blnActual = AddPensionerPage.btnrd_AdditionalQuantumApplicableNoselected(driver).isSelected();
        	  softAssert.assertTrue(blnActual, "Additional Quantum Applicable is not selected as No when age is more than 70 Years");
          }
          
         blnActual = AddPensionerPage.btnrd_DAApplicableYesSelected(driver).isSelected();
         softAssert.assertTrue(blnActual, "DA Applicable is not selected as Yes when age is more than 70 Years");
		
  		validateChkAddlQtmPension(age);
		reader.setCellData("AddPensioner", "ObjectID", 3, strPenObjId);
		logfile.info("written Pension Object Id as " +  strPenObjId); 
		
		clickOn(driver,AddPensionerPage.btn_Close(driver),10);
		Thread.sleep(3000);
		softAssert.assertAll();
		LogOut();
		
		/*clickOn(driver,AddPensionerPage.btn_Submit(driver),10);
		Thread.sleep(6000);
		boolean flg= AddPensionerPage.span_ConfirmMsgAO(driver).isDisplayed();
		Assert.assertTrue(flg,"Confirmation message was not dispalyed after submission");
		
		flg= AddPensionerPage.span_MsgAOPending(driver).isDisplayed();
		Assert.assertTrue(flg,"Confirmation message was not dispalyed after submission");
		
		LogOut();*/
		 
	}
	public void addNewPesioner_AuditOfficer(int rowNum, String strTypeOfPension) throws InterruptedException, ParseException {
		softAssert= new SoftAssert();
		loginUser(rowNum,reader.getCellData("AddPensioner", "LoginType", rowNum),reader.getCellData("AddPensioner", "UserName", rowNum), reader.getCellData("AddPensioner", "Password", rowNum));
		//currDA=Double.parseDouble(property.getProperty("DA"));
		//currIR=Double.parseDouble(property.getProperty("IR"));
		currDA = Double.parseDouble(reader.getCellData("Properties", "CurrentDA", 2));
		currIR = Double.parseDouble(reader.getCellData("Properties", "CurrentIR", 2));
		performNavigationforNewPensioner(reader.getCellData("AddPensioner", "ObjectID", rowNum));	
		verifyAllPensionDetaisReadOnly(rowNum); 
		softAssert.assertAll();		
		LogOut();
	}
	public void addNewPesioner_DeputyDirector(int rowNum, String strTypeOfPension) throws InterruptedException, ParseException {
		softAssert= new SoftAssert();
		loginUser(rowNum,reader.getCellData("AddPensioner", "LoginType", rowNum),reader.getCellData("AddPensioner", "UserName", rowNum), reader.getCellData("AddPensioner", "Password", rowNum));
		//currDA=Double.parseDouble(property.getProperty("DA"));
		//currIR=Double.parseDouble(property.getProperty("IR"));
		currDA = Double.parseDouble(reader.getCellData("Properties", "CurrentDA", 2));
		currIR = Double.parseDouble(reader.getCellData("Properties", "CurrentIR", 2));
		performNavigationforNewPensioner(reader.getCellData("AddPensioner", "ObjectID", rowNum));	
		verifyAllPensionDetaisReadOnly(rowNum); 
		softAssert.assertAll();
		LogOut();
	}
	public void addNewPesioner_Comissioner(int rowNum,String strTypeOfPension) throws InterruptedException, ParseException {
		softAssert= new SoftAssert();
		loginUser(rowNum,reader.getCellData("AddPensioner", "LoginType", rowNum),reader.getCellData("AddPensioner", "UserName", rowNum), reader.getCellData("AddPensioner", "Password", rowNum));
		//currDA=Double.parseDouble(property.getProperty("DA"));
		//currIR=Double.parseDouble(property.getProperty("IR"));
		currDA = Double.parseDouble(reader.getCellData("Properties", "CurrentDA", 2));
		currIR = Double.parseDouble(reader.getCellData("Properties", "CurrentIR", 2));
		clickOn(driver,AddPensionerPage.span_PDMSPendingTasks(driver),10);
		Thread.sleep(4000);
		performNavigationforNewPensioner(reader.getCellData("AddPensioner", "ObjectID", rowNum));	
		verifyAllPensionDetaisReadOnly(rowNum);		
		softAssert.assertAll();
		LogOut();
	}
		
	public void performNavigationforNewPensioner(String strObjIDN) throws InterruptedException {
		
		if(strObjIDN.equals(null)) {
			//Navigate to Add New Pensioner from Left Pane
			navigateToAddnewPensioner();
		}
		else {
			HomeTests.AddPensionerSearchTest(strObjIDN);
			strPenObjId = strObjIDN;
			}
		
	}
	public void navigateToAddnewPensioner() throws InterruptedException {
		clickOn(driver,LeftNavigationPage.lnk_AddNewPensioner(driver),10);
		Thread.sleep(3000);	
		logfile.info("Navigated to Add New Pensioner Page");
	}
	
	public void enterBasicPensionerDetails(int rowNum, String strTypeOfPension) throws InterruptedException, ParseException {
		Thread.sleep(8000);	
		sendkeys(driver,AddPensionerPage.txtbx_PPONumber(driver),10,reader.getCellData("AddPensioner", "PPONumber", rowNum));
		logfile.info("Entered PPO Number as " + reader.getCellData("AddPensioner", "PPONumber", rowNum));
		Thread.sleep(1000);
		selValue(driver,AddPensionerPage.sel_TypeOfPension(driver),10,reader.getCellData("AddPensioner", "TypeOfPension", rowNum));
		logfile.info("selected Type Of Pension as " + reader.getCellData("AddPensioner", "TypeOfPension", rowNum));
		Thread.sleep(2000);		
		AddPensionerPage.txtbx_PenAadhaarNumber(driver).click();
		Thread.sleep(1000);		
		//sendkeys(driver,AddPensionerPage.txtbx_PenAadhaarNumber(driver),10,reader.getCellData("AddPensioner", "PensionerAadhaarNumber", rowNum));
		
		selValue(driver,AddPensionerPage.sel_DistrictName(driver),10,reader.getCellData("AddPensioner", "DistrictName", rowNum));
		logfile.info("selected District Name as " + reader.getCellData("AddPensioner", "DistrictName", rowNum));
		Thread.sleep(2000);
		selValue(driver,AddPensionerPage.sel_AMCName(driver),10,reader.getCellData("AddPensioner", "AMC", rowNum));
		logfile.info("selected AMC as " + reader.getCellData("AddPensioner", "AMC", rowNum));
		Thread.sleep(2000);
		sendkeys(driver,AddPensionerPage.txtbx_PensionerName(driver),10,reader.getCellData("AddPensioner", "PensionerName", rowNum));
		logfile.info("Entered Pensioner Name as " + reader.getCellData("AddPensioner", "PensionerName", rowNum));
		Thread.sleep(2000);
		sendkeys(driver,AddPensionerPage.txtbx_MobileNumber(driver),10,reader.getCellData("AddPensioner", "MobileNumber", rowNum));	
		logfile.info("Entered Mobile Number as " + reader.getCellData("AddPensioner", "MobileNumber", rowNum));
		Thread.sleep(2000);
		clickOn(driver,AddPensionerPage.calendar_PensionerDOB(driver),10);
		Thread.sleep(2000);
		DatePicker(reader.getCellData("AddPensioner", "PenDateOfBirth", rowNum));
		logfile.info("Entered Date Of Birth as " + reader.getCellData("AddPensioner", "PenDateOfBirth", rowNum));		
		Thread.sleep(2000);
		clickOn(driver,AddPensionerPage.calendar_PensionerRetirementDate(driver),10);
		Thread.sleep(2000);
		DatePicker(reader.getCellData("AddPensioner", "RetirementDate", rowNum));
		logfile.info("Entered Retirement Date as " + reader.getCellData("AddPensioner", "RetirementDate", rowNum));
		Thread.sleep(2000);
	 	clickOn(driver,AddPensionerPage.calendar_CommutationPaymentDate(driver),10);
		Thread.sleep(2000);
		DatePicker(reader.getCellData("AddPensioner", "CommutationPaymentDate", rowNum));
		logfile.info("Entered Commutation Date as " + reader.getCellData("AddPensioner", "CommutationPaymentDate", rowNum));
		Thread.sleep(3000);		
	
		switch(strTypeOfPension) {
		case "Anticipatory Pension" :
			selValue(driver,AddPensionerPage.sel_Relationship(driver),10,reader.getCellData("AddPensioner", "Relationship", rowNum));
			logfile.info("Selected Relationship as " + reader.getCellData("AddPensioner", "Relationship", rowNum));
			break;
		case "Enhanced Family Pension" :
			sendkeys(driver,AddPensionerPage.txtbx_BeneficiaryName(driver),10,reader.getCellData("AddPensioner", "BeneficiaryName", rowNum));
			Thread.sleep(1000);
			clickOn(driver,AddPensionerPage.calendar_EnhancedFamPenEndtDate(driver),10);
			Thread.sleep(2000);
			DatePicker(reader.getCellData("AddPensioner", "EnhancedFamilyPensionerEndDate", rowNum));
			logfile.info("Entered EnhancedFamily Pensioner End Date as " + reader.getCellData("AddPensioner", "EnhancedFamilyPensionerEndDate", rowNum));
			Thread.sleep(3000);	
			selValue(driver,AddPensionerPage.sel_Relationship(driver),10,reader.getCellData("AddPensioner", "Relationship", rowNum));
			logfile.info("Selected Relationship as " + reader.getCellData("AddPensioner", "Relationship", rowNum));
			break;
		case "Family Pension" :
			sendkeys(driver,AddPensionerPage.txtbx_BeneficiaryName(driver),10,reader.getCellData("AddPensioner", "BeneficiaryName", rowNum));
			Thread.sleep(1000);
			selValue(driver,AddPensionerPage.sel_Relationship(driver),10,reader.getCellData("AddPensioner", "Relationship", rowNum));
			logfile.info("Selected Relationship as " + reader.getCellData("AddPensioner", "Relationship", rowNum));
			break;
		case "Provisional Pension" :
			sendkeys(driver,AddPensionerPage.txtbx_BeneficiaryName(driver),10,reader.getCellData("AddPensioner", "BeneficiaryName", rowNum));
			Thread.sleep(1000);
			selValue(driver,AddPensionerPage.sel_Relationship(driver),10,reader.getCellData("AddPensioner", "Relationship", rowNum));
			logfile.info("Selected Relationship as " + reader.getCellData("AddPensioner", "Relationship", rowNum));
			break;
		case "Service Pension" :			
			break;
			
		}//Switch ends					
	}
	
	public void validatePensionAmounts(int rowNum) {
		double Basic = Double.parseDouble(AddPensionerPage.txtbx_BasicPension(driver).getAttribute("value"));
		//Validating Addl Quantum  amount
		Double dblExpected = getAddlQutmOfPension(age,Basic);
		String strActual= AddPensionerPage.txtbx_AdditionalQuantumofpension(driver).getAttribute("value");
		softAssert.assertEquals(Double.parseDouble(strActual), dblExpected, "Additional Quantum is displayed as wrong");
		logfile.info("Validated Additional Quantum Amount");
		
		//Validating Total amount
		dblExpected = getTotalAmount(Basic, dblExpected);
		strActual= AddPensionerPage.span_TotalAmount(driver).getText();
		softAssert.assertEquals(Double.parseDouble(strActual), dblExpected, "Total Amount is displayed as wrong");
		logfile.info("Validated Total Amount");
		
		//Validating Dearness Allowance
		if (reader.getCellData("AddPensioner", "DAApplicable", rowNum).equals("Yes")){
			dblExpected = getDearnessAllowance(dblExpected);
			strActual= AddPensionerPage.span_DearnessAllowance(driver).getText();
			softAssert.assertEquals(Double.parseDouble(strActual), dblExpected, "Dearness Allowance is displayed as wrong");
			logfile.info("Validated Dearness Allowance");
		}
		else if (reader.getCellData("AddPensioner", "DAApplicable", rowNum).equals("No")) {
			strActual= AddPensionerPage.span_DearnessAllowance(driver).getText();
			softAssert.assertEquals(strActual, "0.00", "Dearness Allowance is displayed as wrong");
			logfile.info("Validated Dearness Allowance as 0.00 when DA is No");
		}
		
		
		//Validating Interim Relief
		dblExpected = getInterimRelief(Double.parseDouble(AddPensionerPage.txtbx_BasicPension(driver).getAttribute("value")));
		strActual= AddPensionerPage.span_InterimRelief(driver).getText();
		softAssert.assertEquals(Double.parseDouble(strActual), dblExpected, "Interim Relief is displayed as wrong");
		logfile.info("Validated Interim Relief Amount");
		
		//Validating Gross Total
		dblExpected = getGrossTotal("Edit");
		strActual= AddPensionerPage.span_GrossTotal(driver).getText();
		softAssert.assertEquals(Double.parseDouble(strActual), dblExpected, "Gross Total is displayed as wrong");
		logfile.info("Validated Gross Total Amount");
		
		//Validating Net Pension (Net Pension = Gross Total-CommutationPortionPension-Deductions)
		dblExpected = getNetpension("Edit");
		strActual= AddPensionerPage.span_NetPension(driver).getText();
		softAssert.assertEquals(Double.parseDouble(strActual), dblExpected, "Net Pension is displayed as wrong");
		logfile.info("Validated Net Pension Amount");
	}
	public static String getPensionStartDate(String strRetrDate) throws ParseException {	
		Date dtRettDate =new SimpleDateFormat("dd/MM/yyyy").parse(strRetrDate);
		 	     
	     Calendar c = Calendar.getInstance();
	     c.setTime(dtRettDate);	     
	     String strPensionStartDate = (c.get(Calendar.DATE)+1) + "/" + (c.get(Calendar.MONTH) + 1) +  "/" + c.get(Calendar.YEAR);
	     return strPensionStartDate;
	     
	 }
	
	public static String getCommuEndDate(String strCommuStDate) throws ParseException {	
		Date dtCommuStDate =new SimpleDateFormat("dd/MM/yyyy").parse(strCommuStDate);
		 int monthsToAdd = 180;
	     
	     Calendar c = Calendar.getInstance();
	     
	     c.setTime(dtCommuStDate);
	     // add months to current date
	     c.add(Calendar.MONTH, monthsToAdd);
	     
	     String strCommuEndtDate = (c.get(Calendar.DATE)-1) + "/" + (c.get(Calendar.MONTH) + 1) +  "/" + c.get(Calendar.YEAR);
	     return strCommuEndtDate;
	     
	 }
	public static String getQuantumStartDate(String strDOB) throws ParseException {	
		Date dtCommuStDate =new SimpleDateFormat("dd/MM/yyyy").parse(strDOB);
		 int yearsToAdd = 70;
	     
	     Calendar c = Calendar.getInstance();
	     
	     c.setTime(dtCommuStDate);
	     // add months to current date
	     c.add(Calendar.YEAR, yearsToAdd);
	     
	     String strQntmStarttDate = (c.get(Calendar.DATE)) + "/" + (c.get(Calendar.MONTH) + 1) +  "/" + c.get(Calendar.YEAR);
	     return strQntmStarttDate;
	     
	 }
	public void validatePensionStartDate(String strRetrDate,String stractualValue ) throws ParseException {		
		//Converts Date format from dd-MMM-yyyy to dd/MM/yyyy to compare with table grid value
		strRetrDate = getConvertodDate(strRetrDate);	
		String strPenStDate = getPensionStartDate(strRetrDate);
		strPenStDate = ConvertDateMonthInTwoDigitFormat(strPenStDate);
		
		//Validate Crop Start Date in Farmer list table for the required Row
		String strExpectedValue = strPenStDate;
		softAssert.assertEquals(stractualValue, strExpectedValue,"Pension Start Date mismatch in add New Pensioner Page");
		logfile.info("Validated Pension Start Date as " +  strExpectedValue);
	}
	
	public void validateCommutationPaymentEndDate(String strCommStDate, String stractualValue) throws ParseException {		
				
		//Converts Date format from dd-MMM-yyyy to dd/MM/yyyy to compare with table grid value
		strCommStDate = getConvertodDate(strCommStDate);	
		String strCommEndDate = getCommuEndDate(strCommStDate);
		strCommEndDate = ConvertDateMonthInTwoDigitFormat(strCommEndDate);
		String strExpectedValue = strCommEndDate;
		softAssert.assertEquals(stractualValue, strExpectedValue,"Commutation Payment End Date mismatch in add New Pensioner Page");
		logfile.info("Validated Commutation Payment End Date as " +  strExpectedValue);
	}
	
	public void validateAddlQntmStartDate(String DOB, String stractualValue) throws ParseException {		
		//Converts Date format from dd-MMM-yyyy to dd/MM/yyyy to compare with table grid value
		DOB = getConvertodDate(DOB);	
		String strAddlQntmStDate = getQuantumStartDate(DOB);
		strAddlQntmStDate = ConvertDateMonthInTwoDigitFormat(strAddlQntmStDate); 
		//String stractualValue = AddPensionerPage.span_AddlQuntmStartDate(driver).getText();
		String strExpectedValue = strAddlQntmStDate;
		softAssert.assertEquals(stractualValue, strExpectedValue,"Additional Quantum Start Date mismatch in add New Pensioner Page");
		logfile.info("Validated Additional Quantum Start Date as " +  strExpectedValue);
	}
	
	public void getPenObjectId() {	
		if(AddPensionerPage.span_AddPenObjectId(driver).isDisplayed()==true) {
		strPenObjId = AddPensionerPage.span_AddPenObjectId(driver).getText();
		strPenObjId=strPenObjId.substring(1, strPenObjId.length()-1); //Removing First & last characters from string ()
		logfile.info("Fetched Pensioner Work Object Id as : " + strPenObjId);
		}
	}

	public static void validateChkCommPortionPension() {
		boolean chkStatus;
		boolean expStatus=false;
		String strDate = AddPensionerPage.calendar_CommutationPaymentDate(driver).getText();
		chkStatus = AddPensionerPage.chkbx_PenAmtCommPorPen(driver).isSelected();
		if(!strDate.isBlank()) {
			expStatus=true;
			softAssert.assertEquals(chkStatus, true, "Coomutation Portion Pension is Not Checked");
			//logfile.info("Coomutation Portion Pension is Checked");
		}
		  
	}
	
	public static void validateChkAddlQtmPension(int penAge) {
		boolean chkStatus;
		boolean expStatus=false;
		String strDate = AddPensionerPage.calendar_CommutationPaymentDate(driver).getText();
		chkStatus = AddPensionerPage.chkbx_AddlQurmOfPen(driver).isSelected();
		if(penAge>=70) {
			expStatus=true;
			softAssert.assertEquals(chkStatus, true, "Additional Quantum Of pension is Not Checked");
			//logfile.info("Coomutation Portion Pension is Checked");
		}
	}
	public void PensionerFamilyDetails(int rowNum) throws InterruptedException {
		//** enter family details
				sendkeys(driver,AddPensionerPage.txtbx_FamilySpouseName(driver),10,reader.getCellData("AddPensioner", "SpouseName", rowNum));
				logfile.info("Entered Family Spouse Name as " +  reader.getCellData("AddPensioner", "SpouseName", rowNum));
				Thread.sleep(2000);
				sendkeys(driver,AddPensionerPage.txtbx_FamilySpouseAadharNumber(driver),10,reader.getCellData("AddPensioner", "SpouseAadhaarNumber", rowNum));
				logfile.info("Entered Spouse Aadhaar Number as " +  reader.getCellData("AddPensioner", "SpouseAadhaarNumber", rowNum));
				Thread.sleep(2000);
				sendkeys(driver,AddPensionerPage.txtbx_FamilyChild1Name(driver),10,reader.getCellData("AddPensioner", "NameoftheChild1", rowNum));
				logfile.info("Entered Child1 Name as " +  reader.getCellData("AddPensioner", "NameoftheChild1", rowNum));
				Thread.sleep(2000);
				sendkeys(driver,AddPensionerPage.txtbx_FamilyChild1AadharNumber(driver),10,reader.getCellData("AddPensioner", "Child1AadhaarNumber", rowNum));
				logfile.info("Entered Child1 Aadhaar Number as " +  reader.getCellData("AddPensioner", "Child1AadhaarNumber", rowNum));
				Thread.sleep(2000);
				sendkeys(driver,AddPensionerPage.txtbx_FamilyChild2Name(driver),10,reader.getCellData("AddPensioner", "NameoftheChild2", rowNum));
				logfile.info("Entered Child2 Name as " +  reader.getCellData("AddPensioner", "NameoftheChild2", rowNum));
				Thread.sleep(2000);
				sendkeys(driver,AddPensionerPage.txtbx_FamilyChild2AadharNumber(driver),10,reader.getCellData("AddPensioner", "Child2AadhaarNumber", rowNum));
				logfile.info("Entered Child2 Aadhaar Number as " +  reader.getCellData("AddPensioner", "Child2AadhaarNumber", rowNum));
				Thread.sleep(2000);
	}
	public void enterPensionAmountDetails(int rowNum) throws InterruptedException {
		//*********** Pension Amount Details *****************///
				//AddPensionerPage.txtbx_BasicPension(driver).sendKeys("");
				AddPensionerPage.txtbx_BasicPension(driver).sendKeys(Keys.CONTROL + "a");
				Thread.sleep(1000);
				AddPensionerPage.txtbx_BasicPension(driver).sendKeys(Keys.DELETE);
				Thread.sleep(1000);
				sendkeys(driver,AddPensionerPage.txtbx_BasicPension(driver),10,reader.getCellData("AddPensioner", "BasicPension", rowNum));
				logfile.info("Entered Basic Pension as " +  reader.getCellData("AddPensioner", "BasicPension", rowNum));
				Thread.sleep(2000);
				
				if(reader.getCellData("AddPensioner", "DAApplicable", rowNum).equals("Yes")) {
					AddPensionerPage.txtbx_MedicalAllowance(driver).sendKeys(Keys.CONTROL + "a");
					Thread.sleep(1000);
					AddPensionerPage.txtbx_MedicalAllowance(driver).sendKeys(Keys.DELETE);
					Thread.sleep(1000);
					sendkeys(driver,AddPensionerPage.txtbx_MedicalAllowance(driver),10,reader.getCellData("AddPensioner", "MedicalAllowance", rowNum));
					logfile.info("Entered Medical Allowance as " +  reader.getCellData("AddPensioner", "MedicalAllowance", rowNum));
					Thread.sleep(2000);
				}
				//AddPensionerPage.txtbx_CommutationPortionPension(driver).sendKeys("");
				AddPensionerPage.txtbx_CommutationPortionPension(driver).sendKeys(Keys.CONTROL + "a");
				Thread.sleep(1000);
				AddPensionerPage.txtbx_CommutationPortionPension(driver).sendKeys(Keys.DELETE);
				Thread.sleep(1000);
				sendkeys(driver,AddPensionerPage.txtbx_CommutationPortionPension(driver),10,reader.getCellData("AddPensioner", "CommutationPortionPension", rowNum));
				logfile.info("Entered Commutation Portion Pension as " +  reader.getCellData("AddPensioner", "CommutationPortionPension", rowNum));
				Thread.sleep(2000);
				//AddPensionerPage.txtbx_Arrears(driver).sendKeys("");
				AddPensionerPage.txtbx_Arrears(driver).click();
				AddPensionerPage.txtbx_Arrears(driver).sendKeys(Keys.CONTROL + "a");
				Thread.sleep(1000);
				AddPensionerPage.txtbx_Arrears(driver).sendKeys(Keys.DELETE);
				Thread.sleep(1000);
				sendkeys(driver,AddPensionerPage.txtbx_Arrears(driver),10,reader.getCellData("AddPensioner", "Arrears", rowNum));
				logfile.info("Entered Arrears as " +  reader.getCellData("AddPensioner", "Arrears", rowNum));
				Thread.sleep(2000);				
				AddPensionerPage.txtbx_Deductions(driver).click();
				AddPensionerPage.txtbx_Deductions(driver).sendKeys(Keys.CONTROL + "a");
				Thread.sleep(1000);
				AddPensionerPage.txtbx_Deductions(driver).sendKeys(Keys.DELETE);
				Thread.sleep(1000);
				sendkeys(driver,AddPensionerPage.txtbx_Deductions(driver),10,reader.getCellData("AddPensioner", "Deductions", rowNum));
				logfile.info("Entered Deductions as " +  reader.getCellData("AddPensioner", "Deductions", rowNum));
				Thread.sleep(2000);
				
				//AddPensionerPage.txtbx_IRArrears(driver).sendKeys("");
				AddPensionerPage.txtbx_IRArrears(driver).click();
				AddPensionerPage.txtbx_IRArrears(driver).sendKeys(Keys.CONTROL + "a");
				Thread.sleep(1000);
				AddPensionerPage.txtbx_IRArrears(driver).sendKeys(Keys.DELETE);
				Thread.sleep(1000);
				sendkeys(driver,AddPensionerPage.txtbx_IRArrears(driver),10,reader.getCellData("AddPensioner", "IRArrears", rowNum));
				logfile.info("Entered IR Arrears as " +  reader.getCellData("AddPensioner", "IRArrears", rowNum));
				Thread.sleep(2000);
				//AddPensionerPage.txtbx_DAArrears(driver).sendKeys("");
				AddPensionerPage.txtbx_DAArrears(driver).click();
				AddPensionerPage.txtbx_DAArrears(driver).sendKeys(Keys.CONTROL + "a");
				Thread.sleep(1000);
				AddPensionerPage.txtbx_DAArrears(driver).sendKeys(Keys.DELETE);
				Thread.sleep(1000);
				sendkeys(driver,AddPensionerPage.txtbx_DAArrears(driver),10,reader.getCellData("AddPensioner", "DAArrears", rowNum));
				Thread.sleep(1000);
				AddPensionerPage.txtbx_DAArrears(driver).sendKeys(Keys.TAB);
				Thread.sleep(1000);
				logfile.info("Entered DA Arrears as " +  reader.getCellData("AddPensioner", "DAArrears", rowNum));
				Thread.sleep(2000);		
	}
	public void enterPensionerBankDetails(int rowNum) throws InterruptedException {
		//*********** Bank Details *****************///
				sendkeys(driver,AddPensionerPage.txtbx_IFSCCode(driver),10,reader.getCellData("AddPensioner", "IFSCCode", rowNum));
				//logfile.info("Entered IFSCCode as " +  reader.getCellData("AddPensioner", "IFSCCode", rowNum));
				Thread.sleep(2000);
				AddPensionerPage.txtbx_AccountNumber(driver).click();
				Thread.sleep(1000);
				sendkeys(driver,AddPensionerPage.txtbx_AccountNumber(driver),10,reader.getCellData("AddPensioner", "AccountNumber", rowNum));
				//logfile.info("Entered AccountNumber as " +  reader.getCellData("AddPensioner", "AccountNumber", rowNum));
				Thread.sleep(2000);
				//Verifying Bank Name displayed on the page
				String strExpected = reader.getCellData("AddPensioner", "BankName", rowNum);
				String strActual= AddPensionerPage.span_BankName(driver).getText();
				softAssert.assertEquals(strActual, strExpected,"Bank Name is mismatched");
				//logfile.info("Verified Bank Name as " +  reader.getCellData("AddPensioner", "BankName", rowNum));
				
				//Verifying Branch Address displayed on the page
				strExpected = reader.getCellData("AddPensioner", "BranchAddress", rowNum);
				strActual= AddPensionerPage.span_BranchAddress(driver).getText();
				softAssert.assertEquals(strActual, strExpected, "Branch Address is mismatched");
				//logfile.info("Verified Branch Address as " +  reader.getCellData("AddPensioner", "BranchAddress", rowNum));		
	}

public void enterSupplementaryBillDetails(int rowNum) throws InterruptedException {
	if(reader.getCellData("AddPensioner", "SupplementaryAmountDisbursed", rowNum).equals("Yes")) {	
		clickOn(driver,AddPensionerPage.btnrbd_SupplyAmountDisbursedYes(driver),10);
		Thread.sleep(2000);
		AddPensionerPage.txtbx_SupplemenaryBillAmount(driver).sendKeys("");
		sendkeys(driver,AddPensionerPage.txtbx_SupplemenaryBillAmount(driver),10,reader.getCellData("AddPensioner", "SupplemenaryBillAmount", rowNum));	
		logfile.info("Entered SupplemenaryBillAmount as " + reader.getCellData("AddPensioner", "SupplemenaryBillAmount", rowNum));
		Thread.sleep(2000);
		clickOn(driver,AddPensionerPage.btn_AttachGo(driver),10);
		Thread.sleep(2000);
		logfile.info("Clicked on Attch Go button to upload files" );
		UploadFilesPage.btn_selectFiles(driver).sendKeys(strCurrentDir + "\\testdata\\" + reader.getCellData("AddPensioner", "UploadFile", rowNum));	
		Thread.sleep(2000);
		String strFileName = UploadFilesPage.tbl_Modal(driver, 2, 1).getText();
		String strFile = UploadFilesPage.tbl_Modal(driver, 2, 2).getText();
		String strCategory = UploadFilesPage.tbl_Modal(driver, 2, 3).getText();
		softAssert.assertEquals(strFileName, reader.getCellData("AddPensioner", "Modal_FileName", rowNum),"File Name Not Matched");
		softAssert.assertEquals(strFile, reader.getCellData("AddPensioner", "Modal_File", rowNum),"File  Not Matched");
		softAssert.assertEquals(strCategory, reader.getCellData("AddPensioner", "Modal_Category", rowNum),"File  Not Matched");
		logfile.info("Uploaded file successfully");
		clickOn(driver,UploadFilesPage.btn_ModalSubmit(driver),10);
		Thread.sleep(2000);
		if(AddPensionerPage.lnk_File(driver, strFileName).isDisplayed() == true) {
			logfile.info("File displayed in Pensioner Page");
		}		
	}	
	
	//SupplementaryAmountDisbursed
}

public double getAddlQutmOfPension(int PenAge, double Basic) {
	int QuantumPercent=0;
	double AddlQuantumOfPension;	
	if(PenAge >=70 && PenAge <75) { QuantumPercent = 10; }
	if(PenAge >=75 && PenAge <80) {  QuantumPercent = 15; }
	if(PenAge >=80 && PenAge <85) {  QuantumPercent = 20; }
	if(PenAge >=85 && PenAge <90) { QuantumPercent = 25; }
	if(PenAge >=90 && PenAge <95) {  QuantumPercent = 30; }
	if(PenAge >=95 && PenAge <100) {  QuantumPercent = 35; }
	if(PenAge >=100 && PenAge <150) {  QuantumPercent = 50; }
	 
	AddlQuantumOfPension = (Basic * QuantumPercent)/100;	
	return AddlQuantumOfPension;
}
public double getTotalAmount(double Basic ,double AddlQuantumOfPension ) {

	double TotalAmt = Basic + AddlQuantumOfPension;
	
	return TotalAmt;
}
public double getDearnessAllowance(double TotalAmt) {
	double DA;
	DA= (TotalAmt * currDA)/100;
	return DA;
}

public double getInterimRelief(double Basic) {
	//Interim Relief (IR) = (Basic * Current IR)/100
	double IR;
	double currIR = Double.parseDouble(property.getProperty("IR"));
	IR= (Basic * currIR)/100;
	return IR;
}

public static double getGrossTotal(String strType) {
	//Gross Total = Total+MedicalAllowance+DA+Arrears+InterimRelief+IRArrears+DAArrears
	Double GrossTotal=0.00;
	
	if(strType.equals("Edit")) {
	 GrossTotal = Double.parseDouble(AddPensionerPage.span_TotalAmount(driver).getText()) +
			Double.parseDouble(AddPensionerPage.txtbx_MedicalAllowance(driver).getAttribute("value")) + 
			Double.parseDouble(AddPensionerPage.span_DearnessAllowance(driver).getText()) + 
			Double.parseDouble(AddPensionerPage.txtbx_Arrears(driver).getAttribute("value")) + 
			Double.parseDouble(AddPensionerPage.span_InterimRelief(driver).getText()) + 
			Double.parseDouble(AddPensionerPage.txtbx_IRArrears(driver).getAttribute("value")) + 
			Double.parseDouble(AddPensionerPage.txtbx_DAArrears(driver).getAttribute("value"));
	 return GrossTotal;
	}
	else if(strType.equals("Readonly")) {
		GrossTotal = Double.parseDouble(AddPensionerPage.span_PenReadonlyField(driver, "Basic Pension + Add(l) Quantum Of Pension").getText()) +
				Double.parseDouble(AddPensionerPage.span_PenReadonlyField(driver,"Dearness Allowance(DA)").getText()) + 
				Double.parseDouble(AddPensionerPage.span_PenReadonlyField(driver,"Arrears").getText()) + 
				Double.parseDouble(AddPensionerPage.span_PenReadonlyField(driver,"Interim Relief (IR)").getText()) + 
				Double.parseDouble(AddPensionerPage.span_PenReadonlyField(driver,"IR Arrears").getText()) + 
				Double.parseDouble(AddPensionerPage.span_PenReadonlyField(driver,"DA Arrears").getText());
				
		
		System.out.println("Medical Allowance is : " + AddPensionerPage.span_PenReadonlyField(driver,"Medical Allowance").getText());
				if(!AddPensionerPage.span_PenReadonlyField(driver,"Medical Allowance").getText().equals("")) {
					GrossTotal = GrossTotal +Double.parseDouble(AddPensionerPage.span_PenReadonlyField(driver,"Medical Allowance").getText());
					}
		return GrossTotal;
	}	
	return GrossTotal;
}
public static double getNetpension(String strType) {
	Double NetPension=0.00;
	if(strType.equals("Edit")) {
	NetPension = Double.parseDouble(AddPensionerPage.span_GrossTotal(driver).getText()) - 
			(Double.parseDouble(AddPensionerPage.txtbx_CommutationPortionPension(driver).getAttribute("value")) + 
					Double.parseDouble(AddPensionerPage.txtbx_Deductions(driver).getAttribute("value")));
	return NetPension;
	}
	else if (strType.equals("Readonly")) {
		NetPension = Double.parseDouble(AddPensionerPage.span_PenReadonlyField(driver, "Gross Total").getText()) - 
					Double.parseDouble(AddPensionerPage.span_PenReadonlyField(driver,"Deductions").getText());
		
		if(!AddPensionerPage.span_PenReadonlyField(driver,"Commutation Portion Pension").getText().equals("")) {
			NetPension = NetPension - Double.parseDouble(AddPensionerPage.span_PenReadonlyField(driver,"Commutation Portion Pension").getText());
		}
	}
	return NetPension;
}

public void verifyPensionAmountDetails( int agePen, double Basic) {
	
	double addlqntm=0;
	double totalAmt =0;
	double dearnessAllowance;
	double dblActualValue;
	addlqntm =getAddlQutmOfPension(agePen,Basic);
	
	//Validating additional quantum pension
	dblActualValue = Double.parseDouble(AddPensionerPage.txtbx_AdditionalQuantumofpension(driver).getText());
	softAssert.assertEquals(dblActualValue, addlqntm,"Additional Quantum Pension mismatch");
	
	//validating total amount (Basic + addl quantum
	totalAmt = getTotalAmount(Basic,addlqntm);
	dblActualValue = Double.parseDouble(AddPensionerPage.span_TotalAmount(driver).getText());
	softAssert.assertEquals(dblActualValue, totalAmt, "Total Amount is mismatched");

	//validating dearnessAllowance	
	dearnessAllowance = getDearnessAllowance(totalAmt);
	dblActualValue = Double.parseDouble(AddPensionerPage.span_DearnessAllowance(driver).getText());
	softAssert.assertEquals(dblActualValue, dearnessAllowance,"Mismatch in DA");
		
}

public void getCurrentDA() throws InterruptedException {	
	LeftNavigationPage.lnk_UpdateDA(driver).click();
	Thread.sleep(5000);
	currDA = Double.parseDouble(UpdateDAPage.span_DAPercentage(driver).getText());	
	logfile.info("Current DA percentage is : " + currDA);
	
}

public void verifyAllPensionDetaisReadOnly(int rowNum) throws ParseException, InterruptedException {
	
	String strActual;
	String strExpected;
	verifyBasicPenDetailsReadOnly(rowNum);
	verifyFamilyDetailsReadOnly(rowNum);		
	verifyPensionAmountDetailsReadOnly(rowNum);
	
	verifyBankDetailsReadOnly(rowNum);
	
	strActual = AddPensionerPage.span_SuppAmtDisb(driver).getText();
	strExpected = reader.getCellData("AddPensioner", "SupplementaryAmountDisbursed", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Supplementary Amount disbursed");
	clickOn(driver,AddPensionerPage.btn_Page(driver, "Close"),10);
	Thread.sleep(2000);
	
}


public void verifyBankDetailsReadOnly(int rowNum) {
	String strActual;
	String strExpected;
	//Bank Details:
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "IFSC Code").getText();
	strExpected = reader.getCellData("AddPensioner", "IFSCCode", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in IFSC Code");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Bank Name").getText();
	strExpected = reader.getCellData("AddPensioner", "BankName", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Bank Name");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Branch Address").getText();
	strExpected = reader.getCellData("AddPensioner", "BranchAddress", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Branch Address");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Account Number").getText();
	strExpected = reader.getCellData("AddPensioner", "AccountNumber", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Account Number");
}


public void verifyPensionAmountDetailsReadOnly(int rowNum) {
	String strActual;
	String strExpected;
	//Pension Amount Details:
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Basic Pension").getText();
	strExpected = reader.getCellData("AddPensioner", "BasicPension", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Basic Pension");
	age = getAge(AddPensionerPage.span_PenReadonlyField(driver,"Date Of Birth").getText());
		
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Add(l) Quantum Of Pension").getText();
	Double dblExpected = getAddlQutmOfPension(age,Double.parseDouble(strExpected));
	//String strActual= AddPensionerPage.txtbx_AdditionalQuantumofpension(driver).getAttribute("value");
	softAssert.assertEquals(Double.parseDouble(strActual), dblExpected, "Additional Quantum is displayed as wrong");
	logfile.info("Validated Additional Quantum Amount");
	
	//Validating Total amount
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Basic Pension + Add(l) Quantum Of Pension").getText();
	dblExpected = getTotalAmount(Double.parseDouble(reader.getCellData("AddPensioner", "BasicPension", rowNum)), dblExpected);
	softAssert.assertEquals(Double.parseDouble(strActual), dblExpected, "Total Amount is displayed as wrong");
	logfile.info("Validated Total Amount");
			
			//Validating Dearness Allowance
			strActual = AddPensionerPage.span_PenReadonlyField(driver, "Dearness Allowance(DA)").getText();
			if (reader.getCellData("AddPensioner", "DAApplicable", rowNum).equals("Yes")){
				dblExpected = getDearnessAllowance(dblExpected);
				softAssert.assertEquals(Double.parseDouble(strActual), dblExpected, "Dearness Allowance is displayed as wrong");
				logfile.info("Validated Dearness Allowance");
			}
			else if (reader.getCellData("AddPensioner", "DAApplicable", rowNum).equals("No")) {
				softAssert.assertEquals(strActual, "0.00", "Dearness Allowance is displayed as wrong");
				logfile.info("Validated Dearness Allowance as 0.00 when DA is No");
			}
			//Validating Interim Relief
			strActual = AddPensionerPage.span_PenReadonlyField(driver, "Interim Relief (IR)").getText();
			dblExpected = getInterimRelief(Double.parseDouble(AddPensionerPage.span_PenReadonlyField(driver,"Basic Pension").getText()));
			softAssert.assertEquals(Double.parseDouble(strActual), dblExpected, "Interim Relief is displayed as wrong");
			logfile.info("Validated Interim Relief Amount");
			
			//Validating Gross Total
			dblExpected = getGrossTotal("Readonly");
			strActual = AddPensionerPage.span_PenReadonlyField(driver, "Gross Total").getText();
			softAssert.assertEquals(Double.parseDouble(strActual), dblExpected, "Gross Total is displayed as wrong");
			logfile.info("Validated Gross Total Amount");
			
			//Validating Net Pension (Net Pension = Gross Total-CommutationPortionPension-Deductions)
			dblExpected = getNetpension("Readonly");
			strActual = AddPensionerPage.span_PenReadonlyField(driver, "Net Pension").getText();
			softAssert.assertEquals(Double.parseDouble(strActual), dblExpected, "Net Pension is displayed as wrong");
			logfile.info("Validated Net Pension Amount");	
				
			
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Medical Allowance").getText();
	strExpected = reader.getCellData("AddPensioner", "MedicalAllowance", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Medical Allowance");
	
		 
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Commutation Portion Pension").getText();
	strExpected = reader.getCellData("AddPensioner", "CommutationPortionPension", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Commutation Portion Pension");
	
	if(reader.getCellData("AddPensioner", "Arrears", rowNum).equals("")) {
		strActual = AddPensionerPage.span_PenReadonlyField(driver, "Arrears").getText();
		softAssert.assertEquals(strActual, "0.0");
	}
	else {
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Arrears").getText();
	strExpected = reader.getCellData("AddPensioner", "Arrears", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Arrears");
	}
	
	if(reader.getCellData("AddPensioner", "Deductions", rowNum).equals("")) {
		strActual = AddPensionerPage.span_PenReadonlyField(driver, "Deductions").getText();
		softAssert.assertEquals(strActual, "0.0");
	}
	else {
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Deductions").getText();
	strExpected = reader.getCellData("AddPensioner", "Deductions", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Deductions");
	}
	
	if(reader.getCellData("AddPensioner", "IRArrears", rowNum).equals("")) {
		strActual = AddPensionerPage.span_PenReadonlyField(driver, "IR Arrears").getText();
		softAssert.assertEquals(strActual, "0");
	}
	else {
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "IR Arrears").getText();
	strExpected = reader.getCellData("AddPensioner", "IRArrears", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in IR Arrears");
	}
	
	if(reader.getCellData("AddPensioner", "DAArrears", rowNum).equals("")) {
		strActual = AddPensionerPage.span_PenReadonlyField(driver, "DA Arrears").getText();
		softAssert.assertEquals(strActual, "0.00");
	}
	else {
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "DA Arrears").getText();
	strExpected = reader.getCellData("AddPensioner", "DAArrears", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in DA Arrears");
	}	 
}


public void verifyFamilyDetailsReadOnly(int rowNum) {
	String strActual;
	String strExpected;
	//family Details
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Spouse Name").getText();
	strExpected = reader.getCellData("AddPensioner", "SpouseName", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Spouse Name");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Spouse Aadhaar Number").getText();
	strExpected = reader.getCellData("AddPensioner", "SpouseAadhaarNumber", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Spouse Aadhaar Number");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Name of the Child 1").getText();
	strExpected = reader.getCellData("AddPensioner", "NameoftheChild1", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Name of the Child 1");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Child 1 Aadhaar Number").getText();
	strExpected = reader.getCellData("AddPensioner", "Child1AadhaarNumber", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Child 1 Aadhaar Number");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Name of the Child 2").getText();
	strExpected = reader.getCellData("AddPensioner", "NameoftheChild2", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Name of the Child 2");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Child 2 Aadhaar Number").getText();
	strExpected = reader.getCellData("AddPensioner", "Child2AadhaarNumber", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Child 2 Aadhaar Number");
}


public void verifyBasicPenDetailsReadOnly(int rowNum) throws ParseException {
	
	String strActual = AddPensionerPage.span_PenReadonlyField(driver, "PPO Number").getText();
	String strExpected = reader.getCellData("AddPensioner", "PPONumber", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in PPO Number");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Type Of Pension").getText();
	strExpected = reader.getCellData("AddPensioner", "TypeOfPension", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Type Of Pension");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Pensioner Aadhaar Number").getText();
	strExpected = reader.getCellData("AddPensioner", "PensionerAadhaarNumber", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Pensioner Aadhaar Number");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "District Name").getText();
	strExpected = reader.getCellData("AddPensioner", "DistrictName", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in District Name");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "AMC").getText();
	strExpected = reader.getCellData("AddPensioner", "AMC", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in AMC");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Pensioner Name").getText();
	strExpected = reader.getCellData("AddPensioner", "PensionerName", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Pensioner Name");
	if(!reader.getCellData("AddPensioner", "TypeOfPension", rowNum).equals("Service Pension") && 
			!reader.getCellData("AddPensioner", "TypeOfPension", rowNum).equals("Anticipatory Pension") ) {
		strActual = AddPensionerPage.span_PenReadonlyField(driver, "Beneficiary Name").getText();
		strExpected = reader.getCellData("AddPensioner", "BeneficiaryName", rowNum);
		softAssert.assertEquals(strActual, strExpected,"Mismatch in Pensioner's Beneficiary Name");
	}
	if(!reader.getCellData("AddPensioner", "TypeOfPension", rowNum).equals("Service Pension") && 
			!reader.getCellData("AddPensioner", "TypeOfPension", rowNum).equals("Anticipatory Pension") && 
	!reader.getCellData("AddPensioner", "TypeOfPension", rowNum).equals("Provisional Pension"))
		{
		strActual = AddPensionerPage.span_PenReadonlyField(driver, "Relationship").getText();
		strExpected = reader.getCellData("AddPensioner", "Relationship", rowNum);
		softAssert.assertEquals(strActual, strExpected,"Mismatch in Relationship");
		
	}
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Mobile Number").getText();
	strExpected = reader.getCellData("AddPensioner", "MobileNumber", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Mobile Number");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Date Of Birth").getText();
	strExpected=getConvertodDate(reader.getCellData("AddPensioner", "PenDateOfBirth", rowNum));
	strExpected = ConvertDateMonthInTwoDigitFormat(strExpected);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Date Of Birth");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Retirement Date").getText();
	strExpected=getConvertodDate(reader.getCellData("AddPensioner", "RetirementDate", rowNum));
	strExpected = ConvertDateMonthInTwoDigitFormat(strExpected);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Retirement Date");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Pension Start Date").getText();
	validatePensionStartDate(reader.getCellData("AddPensioner", "RetirementDate", rowNum),strActual);
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Commutation Payment Date").getText();
	if(reader.getCellData("AddPensioner", "CommutationPaymentDate", rowNum).equals(Cell.CELL_TYPE_BLANK)) {
		validateCommutationPaymentEndDate(reader.getCellData("AddPensioner", "CommutationPaymentDate", rowNum),strActual);
	}
	 
	//strExpected = reader.getCellData("AddPensioner", "CommutationPaymentDate", rowNum);
	//Assert.assertEquals(strActual, strExpected,"Mismatch in Commutation Payment Date");
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Additional Quantum Applicable").getText();
	strExpected = reader.getCellData("AddPensioner", "AdditionalQuantumApplicable", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in Additional Quantum Applicable");
	if(reader.getCellData("AddPensioner", "AdditionalQuantumApplicable", rowNum).equals("Yes")) {
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "Additional Quantum Start Date").getText();
	validateAddlQntmStartDate(reader.getCellData("AddPensioner", "PenDateOfBirth", rowNum),strActual);
	//strExpected = reader.getCellData("AddPensioner", "AdditionalQuantumApplicable", rowNum);
	//Assert.assertEquals(strActual, strExpected,"Mismatch in Additional Quantum Start Date");
	}
	
	strActual = AddPensionerPage.span_PenReadonlyField(driver, "DA Applicable").getText();
	strExpected = reader.getCellData("AddPensioner", "DAApplicable", rowNum);
	softAssert.assertEquals(strActual, strExpected,"Mismatch in DA Applicable");
}

}
