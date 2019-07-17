package whms.tests;

import org.testng.annotations.Test;

import com.utilities.Base;

import whms.repository.AMCProfilePage;
import whms.repository.LeftNavigationPage;

public class AMCProfileTests extends Base {
	@Test
	private void createAMCProfileYardTest() throws InterruptedException {
		
		loginUser(2,reader.getCellData("AddAMCProfile", "LoginType", 2),reader.getCellData("AddAMCProfile", "UserName", 2), reader.getCellData("AddAMCProfile", "Password", 2));
		clickOn(driver,LeftNavigationPage.lnk_WarehouseManagement(driver),10);
		selValue(driver,AMCProfilePage.sel_District(driver),10,reader.getCellData("AddAMCProfile", "DistrictName", 2));
		Thread.sleep(1000);
		selValue(driver,AMCProfilePage.sel_AMCYard(driver),10, reader.getCellData("AddAMCProfile", "AMCYard", 2));
		Thread.sleep(1000);
		if(reader.getCellData("AddAMCProfile", "FacilityType", 2).equalsIgnoreCase("Warehouse")) {
			clickOn(driver,AMCProfilePage.rbtn_Warehouse(driver),10);
		}
		else if(reader.getCellData("AddAMCProfile", "FacilityType", 2).equalsIgnoreCase("Godown")) {
			clickOn(driver,AMCProfilePage.rbtn_Godwn(driver),10);
		}	
		sendkeys(driver, AMCProfilePage.txtbx_WhGodwnId(driver), 10, reader.getCellData("AddAMCProfile", "WareHouse_GodownID", 2));
		sendkeys(driver, AMCProfilePage.txtbx_StorageCapacity(driver), 10, reader.getCellData("AddAMCProfile", "StorageCapacity", 2));
		
		//clickOn(driver,AMCProfilePage.btn_AddWarehouse(driver),10);
		//Thread.sleep(3000);
		//String strMessage = AMCProfilePage.span_Msg(driver).getText();
		//System.out.println(strMessage);
	}
	
}
