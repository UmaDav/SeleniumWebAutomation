package com.Repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class OP_LoginPage {

	private static WebElement element = null;
	 
    
	public static WebElement sel_LoginType(WebDriver driver){
    	 element = driver.findElement(By.id("Authentication"));
    	//selAadhar.selectByVisibleText(strLoginType);
    	//element = driver.findElement(By.id("emailId"));
    	
         return element; 
         }
	
	public static WebElement txtbx_EmailId(WebDriver driver){
    	element = driver.findElement(By.id("emailId"));    	
         return element; 
         }
	
	public static WebElement txtbx_MobileNumber(WebDriver driver){
    	element = driver.findElement(By.id("phoneNum"));    	
         return element; 
         }
    
    public static WebElement txtbx_PasswordEmail(WebDriver driver){    	
    	element = driver.findElement(By.id("emailOTP"));    	
         return element; 
         }
    public static WebElement txtbx_PasswordMobile(WebDriver driver){    	
    	element = driver.findElement(By.id("phoneOTP"));    	
         return element; 
         }

    public static WebElement btn_Login(WebDriver driver){    	
    	element = driver.findElement(By.xpath("//span[contains(.,'Login')]"));
    	return element; 
         }
    public static WebElement btn_LoginMobile(WebDriver driver){    	
    	element = driver.findElement(By.id("sub_1"));
    	return element; 
         }
    
 public static WebElement lnk_UserName(WebDriver driver){
    	
    	element = driver.findElement(By.cssSelector(".workarea_header_id"));
    	
         return element;
 
         }
 public static WebElement lnk_LogOut(WebDriver driver){
 	
 	//element = driver.findElement(By.cssSelector("#pyNavigation1555580552360 > .menu-item:nth-child(2) .menu-item-title"));
	 element = driver.findElement(By.xpath("//span[contains(text(),'Log off')]"));
	 
      return element;

      }
}
