package com.Repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OP_CitizenPortalPage {
	private static WebElement element = null;
	
	public static WebElement img_RightCorner(WebDriver driver, String strUseFullName){    	
		element = driver.findElement(By.xpath("//span[contains(text(),'" + strUseFullName + "')]//following::div"));
    	return element; 
         }
	public static WebElement span_SwitchRole(WebDriver driver){    	
		element = driver.findElement(By.xpath("//span[contains(text(),'Switch Role')]"));
    	return element; 
         }
	public static WebElement span_RoleName(WebDriver driver, String strRoleName){    	
		element = driver.findElement(By.xpath("//span[contains(text(),'" +strRoleName + "')]"));
    	return element; 
         }
}
