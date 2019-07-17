package com.Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class OP_HomePage {

	
	private static WebElement element = null;
	 
    public static WebElement lnk_SignIn(WebDriver driver){
 
         element = driver.findElement(By.linkText("SIGN IN"));
 
         return element;
 
         }
}
