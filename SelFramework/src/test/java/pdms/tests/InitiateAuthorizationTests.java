package pdms.tests;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

 
import com.utilities.Base;
import com.utilities.CustomListener;
import com.utilities.excelComponent;

import pdms.repository.HomePage;
import pdms.repository.InitiateAuthorizationPage;
import pdms.repository.LeftNavigationPage;
import pdms.repository.UpdateIRPage;

@Listeners(CustomListener.class)
public class InitiateAuthorizationTests extends Base{
	
	@Test
	public void initiateauthorizationTest() throws InterruptedException, ParseException, IOException {
		loggerExtentReport = extent.startTest("InitiateauthorizationTest Test");
				
		loginUser(2,reader.getCellData("InitiateAuthorization", "LoginType", 2),reader.getCellData("InitiateAuthorization", "UserName", 2), reader.getCellData("InitiateAuthorization", "Password", 2));	
		clickOn(driver,LeftNavigationPage.lnk_InitiateAuthorization(driver),10);
		Thread.sleep(3000);
		selValue(driver,InitiateAuthorizationPage.sel_PensionFor(driver),20,"All Pensioners");
		Thread.sleep(3000);
		
	checkeachPensionerData();
		//getPensAmounts();
		
		/*rNum = getPenRow("Service Pension");
		strPensioners = InitiateAuthorizationPage.tbl_PensionersSummary(driver, rNum, 2).getText();
		strTotalAmt = InitiateAuthorizationPage.tbl_PensionersSummary(driver, rNum, 3).getText();
		strTotalAmt = strTotalAmt.substring(2, strTotalAmt.length());
		strTotalAmt=strTotalAmt.replaceAll(",", "");
		double dblAmt = Double.parseDouble(strTotalAmt);
    	//System.out.println("Total Amount is : ");
		System.out.println("Service Pension Pensioners : " + strPensioners);
		System.out.println("Service Pension Total Amount : " + dblAmt);*/
		
	}

	private static int getPenRow(String strTypeOfPension) {		
		boolean flg = false;
		int row=2;		
		while(flg == false) {			
		if(InitiateAuthorizationPage.tbl_PensionersSummary(driver, row, 1).getText().equals(strTypeOfPension) == true)		{
			flg = true;	}
		else {	
			row++; 	}
	} //while loop ends
		return row;
}
	
	private static void getPensAmounts() throws InterruptedException {
		 boolean pagination=true;	
		  double dblAmt=0;
		 String strTypeOfPension , strAmt;
		 int row=2, PageNum=1; 
		 int cntServPens=0 , cntAntPens=0 , cntProvPens=0, cntEnhPens=0, cntFamPens=0, cntNorPens=0;
		 double dblServPens=0 , dblAntPens=0 , dblProvPens=0, dblEnhPens=0, dblFamPens=0, dblNorPens=0;
		 PageNum=1;
		 List<WebElement> alinks = driver.findElements(By.xpath("//div[@class='field-item dataValueWrite']/a"));
			int pgcount = alinks.size();
		 while(PageNum<=pgcount) {			 	
		    	 int rows =  HomePage.tbl_Report_PensionersRows(driver);         
		    for (row=2;row<=rows;row++) {
		    	strTypeOfPension = InitiateAuthorizationPage.tbl_PensionersData(driver, row, 10).getText();
		    	strAmt = InitiateAuthorizationPage.tbl_PensionersData(driver, row, 23).getText();
		    	strAmt = strAmt.substring(2, strAmt.length());
		    	strAmt=strAmt.replaceAll(",", "");
		    	dblAmt = Double.parseDouble(strAmt);
		    	switch (strTypeOfPension)
				{
				case "Service Pension" :
					cntServPens++;
					dblServPens = dblServPens + dblAmt;
					break;
				case "Anticipatory Pension" :
					cntAntPens++;
					dblAntPens = dblAntPens + dblAmt;
					break;
				case "Provisional Pension" :
					cntProvPens++;
					dblProvPens = dblProvPens + dblAmt;
					break;
				case "Enhanced Family Pension" :
					cntEnhPens++;
					dblEnhPens = dblEnhPens + dblAmt;
					break;
				case "Family Pension" :
					cntFamPens++;
					dblFamPens = dblFamPens + dblAmt;
					break;
				case "Normal Pension" :
					cntNorPens++;
					dblNorPens = dblNorPens + dblAmt;
					break;
				} //ends switch		
		    	
		    }// end for loop		    
		    System.out.println("Service Pensioners Upto Page " + PageNum + " : " + cntServPens);
	    	System.out.println("Service Pensioners Total Amount Upto Page " + PageNum + " : " + dblServPens);
	    	
	    	System.out.println("Anticipatory Pensioners Upto Page " + PageNum + " : " + cntAntPens);
	    	System.out.println("Anticipatory Pensioners Total Amount Upto Page " + PageNum + " : " + dblAntPens);
	    	
	    	System.out.println("Provisional Pensioners Upto Page " + PageNum + " : " + cntProvPens);
	    	System.out.println("Provisional Pensioners Total Amount Upto Page " + PageNum + " : " + dblProvPens);
	    	
	    	System.out.println("Enhanced Family Pensioners Upto Page " + PageNum + " : " + cntEnhPens);
	    	System.out.println("Enhanced Family Pensioners Total Amount Upto Page " + PageNum + " : " + dblEnhPens);
	    	
	    	System.out.println("Family Pensioners Upto Page " + PageNum + " : " + cntFamPens);
	    	System.out.println("Family Pensioners Total Amount Upto Page " + PageNum + " : " + dblFamPens);
	    	
	    	System.out.println("Normal Pensioners Upto Page " + PageNum + " : " + cntNorPens);
	    	System.out.println("Normal Pensioners Total Amount Upto Page " + PageNum + " : " + dblNorPens);
		    		    		    	
		    	if(PageNum!=pgcount) {clickOn(driver,InitiateAuthorizationPage.btn_PaginationNext(driver),10);}
		    	Thread.sleep(5000);
		    	row=2;
		    	PageNum++;
		 } // end while loop
		System.out.println("Total Pensioners Count is" + (cntServPens+cntAntPens+cntProvPens+cntEnhPens+cntFamPens+cntNorPens));
	    System.out.println("Total Pensioners Amount is : " + (dblServPens+dblAntPens+dblProvPens+dblEnhPens+dblFamPens+dblNorPens));    
		}
	
	private static void checkeachPensionerData() throws InterruptedException, ParseException, IOException {
		 double dblAmt=0;
		 String strTypeOfPension , strAmt, strRtDate;
		 int row=2, PageNum=1, age=0, currtRow;
		 
		 	String filePath = System.getProperty("user.dir") + "\\testResults\\";
			String timestamp= getCurrentTimeStamp();
			String strFile = "PDMS_AllPensioners_" + timestamp;
			String[] headers = new String[] { "PPONumber", "ActualGrossTotal", "ExpectedGrossTotal", "Status" };
			createExcelFile(filePath,strFile,"Results",headers);
			excelComponent IAReader  = new excelComponent(filePath + strFile +".xlsx");
			
		 
		 double dblBasic=0 , dblAddlQuantum=0 , dblTotal=0, dblDA=0, dblIR=0 , dblMedical=0, dblArrears=0;
		 double dblDAArr=0, dblIRArr=0, dblGross=0;
		 PageNum=1;
		 List<WebElement> alinks = driver.findElements(By.xpath("//div[@class='field-item dataValueWrite']/a"));
			int pgcount = alinks.size();
		 while(PageNum<=pgcount) {			 	
			 	currtRow = IAReader.getRowCount("Results") +1;
		    	 int rows =  HomePage.tbl_Report_PensionersRows(driver);         
		    for (row=2;row<=rows;row++) {
		    				//	Check DOB is not null
		    	if(!InitiateAuthorizationPage.tbl_PensionersData(driver, row, 5).getText().equals(""))  
		    	{
		    		//Get Gross Total as string from Pensioners table and convert to Double value
			    	String strActualGross = InitiateAuthorizationPage.tbl_PensionersData(driver, row, 21).getText();
			    	strActualGross = strActualGross.substring(2,strActualGross.length());
			    	//Write PPO Number & Gross Total from Pensioners table to Excel file
			    	System.out.println("PPO Number : " + InitiateAuthorizationPage.tbl_PensionersData(driver, row, 4).getText());
			    	IAReader.setCellData("Results", "PPONumber", currtRow, InitiateAuthorizationPage.tbl_PensionersData(driver, row, 4).getText());
			    	IAReader.setCellData("Results", "ActualGrossTotal", currtRow, strActualGross);
			    	System.out.println("Actual Gross Total : " + strActualGross);
			    	
			    	//Get pensioner age to calculate DA : Pass DOB of pensioner to getAge method
			    	age=getAge(InitiateAuthorizationPage.tbl_PensionersData(driver, row, 5).getText());
			    	//strTypeOfPension = InitiateAuthorizationPage.tbl_PensionersData(driver, row, 10).getText();
			    	//get Basic Pension from table
			    	strAmt = InitiateAuthorizationPage.tbl_PensionersData(driver, row, 16).getText();
			    	if(!strAmt.equals("")){	dblBasic =stringToDoubleAmount(strAmt);}
			    	else {	dblBasic=0; }
			    	/*dblAddlQuantum = getAddlQutmOfPension(age,dblBasic);
			    	dblTotal = dblBasic + dblAddlQuantum;*/	
			    	// get Total from Pensioner table
			    	strAmt = InitiateAuthorizationPage.tbl_PensionersData(driver, row, 18).getText();
			    	if(!strAmt.equals("")){	dblTotal =stringToDoubleAmount(strAmt);}
			    	else {	dblTotal=0; }
			    	System.out.println("Pensioner Total : " + dblTotal);
			    	dblDA = getDearnessAllowance(dblTotal);
			    	System.out.println("Pensioner DR : " + dblDA);
			    	dblIR = getInterimRelief(dblBasic);
			    	System.out.println("Pensioner IR : " + dblIR);
			    	//get Medical Allowance from Pensioner table
			    	strAmt = InitiateAuthorizationPage.tbl_PensionersData(driver, row, 20).getText();
			    	if(!strAmt.equals("")){	dblMedical = stringToDoubleAmount(strAmt);}
			    	else {	dblMedical=0; 	}
			    	System.out.println("Pensioner Medical Allowance : " + dblMedical);
			    	//get Arrears from Pensioner table
			    	strAmt = InitiateAuthorizationPage.tbl_PensionersData(driver, row, 24).getText();
			    	if(!strAmt.equals("")){dblArrears = stringToDoubleAmount(strAmt);}
			    	else {	dblArrears=0; 	}
			    	System.out.println("Pensioner Arrears: " + dblArrears);
			    	//Pass Date of Retirement Date to the method
			    	dblDAArr = getDAArrears(InitiateAuthorizationPage.tbl_PensionersData(driver, row, 6).getText(),dblTotal); 
			    	System.out.println("Pensioner DA Arrears: " + dblDAArr);
			    	dblIRArr = getIRArrears(dblBasic);
			    	System.out.println("Pensioner IR Arrears: " + dblIRArr);
			    	dblGross = dblTotal + dblDA + dblIR + dblMedical + dblArrears + dblDAArr + dblIRArr;
			    	// BigDecimal bd = new BigDecimal(dblGross).setScale(2, RoundingMode.HALF_UP);
			    	 //dblGross = bd.doubleValue();
			    	DecimalFormat df = new DecimalFormat("#,###,##0.##");
			    	String strGross = df.format(dblGross);
			    	IAReader.setCellData("Results", "ExpectedGrossTotal", currtRow, strGross);
			    	
			    	System.out.println("Expected Gross Total : " + strGross);
			    	Double dblExpGross= Double.valueOf(strGross.replaceAll(",", "").toString());
			    	Double dblActGross= Double.valueOf(strActualGross.replaceAll(",", "").toString());
			    	if(Double.compare(dblActGross,dblExpGross)==0) {
			    		IAReader.setCellData("Results", "Status", currtRow, "Pass");
			    	}
			    	else {
			    		IAReader.setCellData("Results", "Status", currtRow, "Fail");
			    	}
			    	currtRow++;	
			    	
		    	} //if ends
		    	 
		    }// end for loop		    
		     		    		    	
		    	if(PageNum!=pgcount) {clickOn(driver,InitiateAuthorizationPage.btn_PaginationNext(driver),10);}
		    	Thread.sleep(5000);
		    	row=2;
		    	PageNum++;
		 } // end while loop
		
		}

	private static double getIRArrears(double dblBasic) {
		 double dblCurreIR, dblIRMonths, dblIRArrTotal;
		 dblCurreIR = Double.parseDouble(reader.getCellData("Properties", "CurrentIR", 2));
		 dblIRMonths = Double.parseDouble(reader.getCellData("Properties", "IRMonths", 2));
		 dblIRArrTotal = (dblCurreIR * dblIRMonths * dblBasic)/100;
		 
		return dblIRArrTotal;
	}

	private static double getDAArrears(String strRtDate, double dblTotal) throws ParseException {	
		Date PenRtDate;
		double TotalDAArrears=0, dblNewDA=0;
		String strEffDate= reader.getCellData("Properties", "DAEffDate", 2);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    Date EffectDate = formatter.parse(strEffDate);
	    String strExpToDate = getLastDayOfTheMonth();
	    dblNewDA = Double.parseDouble(reader.getCellData("Properties", "CurrentDA", 2)) - Double.parseDouble(reader.getCellData("Properties", "OldDA", 2));
		if(!strRtDate.equals("")) {
			PenRtDate = formatter.parse(strRtDate);  			
    		if (PenRtDate.compareTo(EffectDate) > 0) {   
    			int Expectedmnths = getMonthsDifference(strRtDate, strExpToDate);  
    			TotalDAArrears = (Expectedmnths * dblNewDA * dblTotal)/100 ; 
    			//System.out.println("Rt date is greater EffDate");
            }else if (PenRtDate.compareTo(EffectDate) <= 0) {            	
            	int Expectedmnths = getMonthsDifference(strEffDate, strExpToDate);
            	TotalDAArrears = (Expectedmnths * dblNewDA * dblTotal)/100 ;
            	}
            	//TotalDAArrears = Math.round(TotalDAArrears);     	
    	}    
		return TotalDAArrears;
	}

	private static double stringToDoubleAmount(String strAmount) {
		strAmount = strAmount.substring(2, strAmount.length());
		strAmount=strAmount.replaceAll(",", "");
    	double dblAmtount = Double.parseDouble(strAmount);
    	return dblAmtount;		
	}
	
	private static double getAddlQutmOfPension(int PenAge, double Basic) {
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

	private static double getDearnessAllowance(double TotalAmt) {
		double DA;
		String strCurrDA=reader.getCellData("Properties", "CurrentDA", 2);
		DA = (TotalAmt * Double.parseDouble(strCurrDA))/100;
		//System.out.println("DR before: " + DA);
		String strDA = Double.toString(DA);
		int idx= strDA.indexOf(".");
		//strDA = strDA.substring(idx+1);
		 char cOne = strDA.charAt(idx+1);
		//int iDA = Integer.parseInt(cOne);
		 int iDA = Character.getNumericValue(cOne);
		if (iDA>=2 && iDA <=4) {
			DA = Math.round(DA);
			DA=DA+1;
		}
		else if (iDA>=5) {
			DA = Math.round(DA);
		}	
		 		
		//System.out.println("DR after: " + DA);
		return DA;
	}

	private static double getInterimRelief(double Basic) {
		//Interim Relief (IR) = (Basic * Current IR)/100
		double IR;
		double currIR = Double.parseDouble(reader.getCellData("Properties", "CurrentIR", 2));
		IR= (Basic * currIR)/100;
		return IR;
	}
	

}
	
