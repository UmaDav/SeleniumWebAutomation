package pdms.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddPensionerPage {

	private static WebElement element = null;
	
	//**** fetch Work Object Id from Page ***///
	public static WebElement span_AddPenObjectId(WebDriver driver){
    	element = driver.findElement(By.xpath("//div /span[contains(text(),'Add New Pensioner')]/following::span"));
    	return element;
         }
 
	
	//***** Pensioner Details ****////
	/*public static WebElement btnrd_ServiceType(WebDriver driver, String strType){
		
		//String strObject;
		
		if(strType.equals("AMC")) {
			//strObject = "TypeOfServiceAMC";
			//element = driver.findElement(By.id(strObject));			
			element = driver.findElement(By.xpath("//input[@name='$PpyWorkPage$pNewPensioner$pTypeOfService' and @type='radio' and @value='AMC']/following::label"));
			 
		}
		else if(strType.contentEquals("CMF")) {
			//strObject = "TypeOfServiceCMF";
			element = driver.findElement(By.xpath("//input[@name='$PpyWorkPage$pNewPensioner$pTypeOfService' and @type='radio' and @value='CMF']/following::label"));
			//element = driver.findElement(By.id(strObject));
		}     	   	
         return element; 
       }*/
	
	public static WebElement txtbx_PPONumber(WebDriver driver){
    	element = driver.findElement(By.id("PPONumber"));
    	return element;
         }
  
	public static WebElement sel_TypeOfPension(WebDriver driver){
	    	element = driver.findElement(By.id("TypeOfPension"));
	    	return element;
	         }
	 
	 public static WebElement txtbx_PenAadhaarNumber(WebDriver driver){
	     	element = driver.findElement(By.id("AadharNumber"));    
	     	return element; 
	         }
	 
	 public static WebElement txtbx_PensionerName(WebDriver driver){
	     	element = driver.findElement(By.id("EmployeeName"));    
	     	return element; 
	         }
	 public static WebElement txtbx_BeneficiaryName(WebDriver driver){
	     	element = driver.findElement(By.id("PensionerName"));    
	     	return element; 
	         }
	 public static WebElement txtbx_MobileNumber(WebDriver driver){
	     	element = driver.findElement(By.id("MobileNumber"));    
	     	return element; 
	         }
	 
	 public static WebElement sel_DistrictName(WebDriver driver){
	    	element = driver.findElement(By.id("DistrictName"));
	    	return element;
	         }

	 public static WebElement sel_AMCName(WebDriver driver){
	    	element = driver.findElement(By.id("AMC"));
	    	return element;
	         }
	 public static WebElement calendar_PensionerDOB(WebDriver driver){
	        //element = driver.findElement(By.name("$PpyWorkPage$pNewPensioner$pDateOfBirth"));
		 element = driver.findElement(By.xpath("//span[@name=\"$PpyWorkPage$pNewPensioner$pDateOfBirth\"]"));
	        return element;
	          }
	 
	 public static WebElement calendar_PensionerRetirementDate(WebDriver driver){
		 	element = driver.findElement(By.xpath("//span[@name='$PpyWorkPage$pNewPensioner$pRetirementDate']"));
	        return element;
	          }	 
	 public static WebElement calendar_CommutationPaymentDate(WebDriver driver){
		 	element = driver.findElement(By.xpath("//span[@name='$PpyWorkPage$pNewPensioner$pCommutationPaymentDate']"));
	        return element;
	          }
	 public static WebElement calendar_EnhancedFamPenEndtDate(WebDriver driver){
		 	element = driver.findElement(By.xpath("//span[@name='$PpyWorkPage$pNewPensioner$pEnhancedfmlyPnsnrDate']"));
		 	return element;
	          }
	 public static WebElement sel_Relationship(WebDriver driver){
	    	element = driver.findElement(By.id("Relationship"));
	    	return element;
	         }
	 	 
	 public static WebElement btnrd_AdditionalQuantumApplicableYes(WebDriver driver){
	        element = driver.findElement(By.xpath("//input[@id='AdditionalQuantumApplicableYes']/following::label"));
	        return element;
	          }
	 
	 public static WebElement btnrd_AdditionalQuantumApplicableNo(WebDriver driver){
	        //element = driver.findElement(By.id("AdditionalQuantumApplicableNo"));
	        element = driver.findElement(By.xpath("//input[@id='AdditionalQuantumApplicableNo']/following::label"));
	        return element;
	          }
	 
	 public static WebElement btnrd_AdditionalQuantumApplicableYesSelected(WebDriver driver){
	        element = driver.findElement(By.xpath("//input[@id='AdditionalQuantumApplicableYes']"));
	        return element;
	          }
	 
	 public static WebElement btnrd_AdditionalQuantumApplicableNoselected(WebDriver driver){
	        //element = driver.findElement(By.id("AdditionalQuantumApplicableNo"));
	        element = driver.findElement(By.xpath("//input[@id='AdditionalQuantumApplicableNo']"));
	        return element;
	          }
	 
	 public static WebElement btnrd_DAApplicableYes(WebDriver driver){
		 element = driver.findElement(By.xpath("//input[@id='DAApplicableYes']/following::label"));
	        //element = driver.findElement(By.id("DAApplicableYes"));
	        return element;
	          }
	 
	 public static WebElement btnrd_DAApplicableNo(WebDriver driver){
		 element = driver.findElement(By.xpath("//input[@id='DAApplicableNo']/following::label"));
	        //element = driver.findElement(By.id("DAApplicableNo"));
	        return element;
	          }
	 
	 public static WebElement btnrd_DAApplicableYesSelected(WebDriver driver){
		 element = driver.findElement(By.xpath("//input[@id='DAApplicableYes']"));
	        //element = driver.findElement(By.id("DAApplicableYes"));
	        return element;
	          }
	 
	 public static WebElement btnrd_DAApplicableNoSelected(WebDriver driver){
		 element = driver.findElement(By.xpath("//input[@id='DAApplicableNo']"));
	        //element = driver.findElement(By.id("DAApplicableNo"));
	        return element;
	          }
	 
	 //******* Family Details ***////
	 public static WebElement txtbx_FamilySpouseName(WebDriver driver){
	     	element = driver.findElement(By.id("SpouseName"));    
	     	return element; 
	         }
	 
	 public static WebElement txtbx_FamilySpouseAadharNumber(WebDriver driver){
	     	element = driver.findElement(By.id("SpouseAadharNumber"));    
	     	return element; 
	         }
	 public static WebElement txtbx_FamilyChild1Name(WebDriver driver){
	     	element = driver.findElement(By.id("Child1Name"));    
	     	return element; 
	         }
	 
	 public static WebElement txtbx_FamilyChild1AadharNumber(WebDriver driver){
	     	element = driver.findElement(By.id("Child1AadharNumber"));    
	     	return element; 
	         }
	 
	 public static WebElement txtbx_FamilyChild2Name(WebDriver driver){
	     	element = driver.findElement(By.id("Child2Name"));    
	     	return element; 
	         }
	 
	 public static WebElement txtbx_FamilyChild2AadharNumber(WebDriver driver){
	     	element = driver.findElement(By.id("Child2AadharNumber"));    
	     	return element; 
	         }
	 
	 //******** Pension Amount Details *******////
	 public static WebElement chkbx_PenAmtCommPorPen(WebDriver driver){
	     	element = driver.findElement(By.id("pyWorkPageNewPensionerCheckCommutationportionpension"));    
	     	return element; 
	         }
	 public static WebElement chkbx_AddlQurmOfPen(WebDriver driver){
	     	element = driver.findElement(By.id("pyWorkPageNewPensionerCheckAdditionalQuantumofPension"));    
	     	return element; 
	         }
	 
	 public static WebElement txtbx_BasicPension(WebDriver driver){
	     	element = driver.findElement(By.id("BasicPension"));    
	     	return element; 
	         }
	 
	 public static WebElement txtbx_AdditionalQuantumofpension(WebDriver driver){
	     	element = driver.findElement(By.id("AdditionalQuantumofpension"));    
	     	return element; 
	         }
	 public static WebElement txtbx_MedicalAllowance(WebDriver driver){
	     	element = driver.findElement(By.id("MedicalAllowance"));    
	     	return element; 
	         }
	 public static WebElement txtbx_CommutationPortionPension(WebDriver driver){
	     	element = driver.findElement(By.id("CommutationPortionPension"));    
	     	return element; 
	         }
	 public static WebElement txtbx_Arrears(WebDriver driver){
	     	element = driver.findElement(By.id("Arrears"));    
	     	return element; 
	         }
	 public static WebElement txtbx_Deductions(WebDriver driver){
	     	element = driver.findElement(By.id("Deductions"));    
	     	return element; 
	         }
	 public static WebElement txtbx_IRArrears(WebDriver driver){
	     	element = driver.findElement(By.id("IRArrears"));    
	     	return element; 
	         }
	 public static WebElement txtbx_DAArrears(WebDriver driver){
	     	element = driver.findElement(By.id("DAArrears"));    
	     	return element; 
	         }
	 
	 
	 
	 //**** Bank Details ***//////
	 
	 public static WebElement txtbx_IFSCCode(WebDriver driver){
	     	element = driver.findElement(By.id("IFSCCode"));    
	     	return element; 
	         }
	 public static WebElement txtbx_AccountNumber(WebDriver driver){
	     	element = driver.findElement(By.id("AccountNumber"));    
	     	return element; 
	         }
	 
	 //**** Supplementary Bill Details ****////
	 
	 public static WebElement btnrbd_SupplyAmountDisbursedYes(WebDriver driver){
	     	//element = driver.findElement(By.id("SupplyAmountDisbursedYes"));  
		 element = driver.findElement(By.xpath("//input[@id='SupplyAmountDisbursedYes']/following::label"));
	     	
	     	return element; 
	         }
	 public static WebElement btnrbd_SupplyAmountDisbursedNo(WebDriver driver){
	     	//element = driver.findElement(By.id("SupplyAmountDisbursedNo"));   
	     	element = driver.findElement(By.xpath("//input[@id='SupplyAmountDisbursedNo']/following::label"));
	     	return element; 
	         }
	 public static WebElement txtbx_SupplemenaryBillAmount(WebDriver driver){
	     	element = driver.findElement(By.id("SupplementaryBillAmount"));    
	     	return element; 
	         }
	 
	 public static WebElement btn_AttachGo(WebDriver driver){
	     	element = driver.findElement(By.name("ViewSupplementaryBill_pyWorkPage_37"));    
	     	return element; 
	         }
	 
	 public static WebElement lnk_File(WebDriver driver, String strFile){
	     	element = driver.findElement(By.xpath("//a[@title='" + strFile + "']"));    
	     	return element; 
	         }
	 
	 // **** Dates as span items
	 public static WebElement span_PensionStartDate(WebDriver driver){
	     	element = driver.findElement(By.xpath("//span/b[contains(text(),\"Pension Start Date\")]//following::span"));    
	     	return element; 
	         }
	 public static WebElement span_CommutationPaymentEndDate(WebDriver driver){
	     	element = driver.findElement(By.xpath("//span/b[contains(text(),\"Commutation Payment End Date\")]//following::span"));    
	     	return element; 
	         }
	 
	 public static WebElement span_AddlQuntmStartDate(WebDriver driver){
	     	element = driver.findElement(By.xpath("//span/b[contains(text(),\"Additional Quantum Start Date\")]//following::span"));    
	     	return element; 
	         }
	// **** Bank as span items
	 public static WebElement span_BankName(WebDriver driver){
	     	element = driver.findElement(By.xpath("//span/b[contains(text(),\"Bank Name\")]//following::span"));    
	     	return element; 
	         }
	 public static WebElement span_BranchAddress(WebDriver driver){
	     	element = driver.findElement(By.xpath("//span/b[contains(text(),\"Branch Address\")]//following::span"));    
	     	return element; 
	         }
	 
	 
	 //**** Page Buttons ***////
	 
	 public static WebElement btn_Submit(WebDriver driver){
	     	element = driver.findElement(By.name("ViewAddPensionerDetails_pyWorkPage_287"));    
	     	return element; 
	         }
	 public static WebElement btn_Close(WebDriver driver){
	     	//element = driver.findElement(By.name("ViewAddPensionerDetails_pyWorkPage_285"));
	     	element = driver.findElement(By.xpath("//button[contains(.,'Close')]"));   
	     	//textContent
	     	return element; 
	         }
	 
	 public static WebElement btn_Page(WebDriver driver, String strAction){
	     	element = driver.findElement(By.xpath("//button[contains(.,'"+strAction+"')]"));   	     	
	     	return element; 
	         }
	 
	 //***** span Pension Amounts
	 
	 public static WebElement span_TotalAmount(WebDriver driver){
	     	element = driver.findElement(By.xpath("//span/b[contains(text(),'Basic Pension + Add(l) Quantum Of Pension')]/following::div"));    
	     	return element; 
	         }
	 
	 public static WebElement span_DearnessAllowance(WebDriver driver){
	     	element = driver.findElement(By.xpath("//span/b[contains(text(),'Dearness Allowance(DA)')]/following::div"));    
	     	return element; 
	         }
	 public static WebElement span_CommutationPortionPension(WebDriver driver){
	     	element = driver.findElement(By.xpath("//span/b[contains(text(),'Commutation Portion Pension')]/following::div"));    
	     	return element; 
	         }
	 public static WebElement span_InterimRelief(WebDriver driver){
	     	element = driver.findElement(By.xpath("//span/b[contains(text(),'Interim Relief (IR)')]/following::div"));    
	     	return element; 
	         }
	 public static WebElement span_GrossTotal(WebDriver driver){
	     	element = driver.findElement(By.xpath("//span/b[contains(text(),'Gross Total')]/following::div"));    
	     	return element; 
	         }
	 public static WebElement span_NetPension(WebDriver driver){
	     	element = driver.findElement(By.xpath("//span/b[contains(text(),'Net Pension')]/following::div"));    
	     	return element; 
	         }
	 public static WebElement span_ConfirmMsgAO(WebDriver driver){
	     	element = driver.findElement(By.xpath("//div[contains(text(),'This case has been routed successfully to the Audit Officer')]"));    
	     	return element; 
	         }
	 public static WebElement span_MsgAOPending(WebDriver driver){
	     	element = driver.findElement(By.xpath("//span[contains(text(),'Pending-AuditOfficer')]"));    
	     	return element; 
	         }
	 public static WebElement span_PenReadonlyField(WebDriver driver, String strHeader){
	     	element = driver.findElement(By.xpath("//span/b[contains(text(),'"+strHeader+"')]/following::div"));    
	     	return element; 
	         }
	 public static WebElement span_SuppAmtDisb(WebDriver driver){
	     	element = driver.findElement(By.xpath("//span[contains(text(),'Supplementary Amount Disbursed')]//following::div"));    
	     	return element; 
	         }
	 public static WebElement span_PDMSPendingTasks(WebDriver driver){
	     	element = driver.findElement(By.xpath("//a[@title='Click to open PDMS Pending Tasks']"));    
	     	return element; 
	         }
	 
	 
	 
}
