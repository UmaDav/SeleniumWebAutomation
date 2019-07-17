package com.Repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UploadFilesPage {

	private static WebElement element = null;
	
	public static WebElement btn_selectFiles(WebDriver driver){
     	element = driver.findElement(By.id("$PpyAttachmentPage$ppxAttachName")); 
     	return element; 
         }
 
 public static WebElement btn_ModalSubmit(WebDriver driver){
     	element = driver.findElement(By.id("ModalButtonSubmit"));    
     	return element; 	     	
         }
 public static WebElement btn_ModalCancel(WebDriver driver){
  	element = driver.findElement(By.id("ModalButtonCancel"));    
  	return element; 	     	
      }
 public static WebElement tbl_Modal(WebDriver driver, int trow, int tdata){
	  	element = driver.findElement(By.xpath("//*[@id=\"bodyTbl_right\"]/tbody/tr[" + trow+ "]/td[" + tdata + "]"));    
	  	return element; 	     	
	      }

}
