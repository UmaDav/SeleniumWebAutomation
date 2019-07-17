package pdms.tests;

import org.testng.asserts.SoftAssert;

import com.Repository.UploadFilesPage;
import com.utilities.Base;

public class UploadFiles extends Base{
	
	public static void uploadDocument(String strUploadFile,String strFile, String strCategory, SoftAssert asst) throws InterruptedException {
		UploadFilesPage.btn_selectFiles(driver).sendKeys(strCurrentDir + "\\testdata\\" +strFile);	
		Thread.sleep(5000);
		String strFileName = UploadFilesPage.tbl_Modal(driver, 2, 1).getText();
		String strFileActual = UploadFilesPage.tbl_Modal(driver, 2, 2).getText();
		String strCategoryActual = UploadFilesPage.tbl_Modal(driver, 2, 3).getText();
		//asst.assertEquals(strFileName,strUploadFile,"File Name Not Matched");
		//asst.assertEquals(strFileActual,strFile,"File  Not Matched");
		//asst.assertEquals(strCategoryActual,strCategory,"File  Not Matched");
		logfile.info("Uploaded file successfully");
		clickOn(driver,UploadFilesPage.btn_ModalSubmit(driver),10);
		Thread.sleep(2000);		
		//return true;
		
	}
 	 
}
