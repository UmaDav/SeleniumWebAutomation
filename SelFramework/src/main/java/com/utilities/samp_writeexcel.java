package com.utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
  

public class samp_writeexcel {

	public static void main(String[] args) throws IOException   {
					 
		    String line;
		    Process p = Runtime.getRuntime().exec("ps -e");
		    BufferedReader input =   new BufferedReader(new InputStreamReader(p.getInputStream()));
		    while ((line = input.readLine()) != null) {
		        System.out.println(line); //<-- Parse data here.
		    }
		    input.close();
	
	}
		 
	 
	 }
