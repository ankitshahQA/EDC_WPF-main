package com.automation.testcases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.TestNG;

public class TestRunner_old  extends ConfigControlNew {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static TestNG testng;
	//static String browser="chrome";
	//static  Xls_Reader xls1 = null;
	public static void callSanity(String browser) {
		testng=new TestNG();
		//testng.addListener(new AnnotationTransformer());
		testng.setUseDefaultListeners(false);
		// Create a list of String 
		List<String> suitefiles=new ArrayList<String>();
		
		try {
			//xls1 = new Xls_Reader("resources/DataSourceFile.xlsx");
			//xls1 = new Xls_Reader(System.getProperty("user.dir")+"\\src\\Data\\DataSourceFile.xlsx");
			//browser = xls1.getCellData("Configuration", "Value",11);
			System.out.println(browser);
			System.out.println(System.getProperty("user.dir"));
			if (browser.equalsIgnoreCase("edge")){
				Path path = Paths.get(System.getProperty("user.dir")+"\\Input\\Edge_TestNG.xml");
		        try {
		            boolean fileExists = Files.exists(path);
		            if(!fileExists) {
		            System.out.println("File exists: " + fileExists);
		            setTextRed("<html>INFO:Exception occurred, not able to find testng file for Edge browser.</html>");}
		        } catch (Exception e) {
					e.printStackTrace();
					setTextRed("<html>INFO:Exception occurred, not able to find testng file for Edge browser.</html>");
					return;
				}
				suitefiles.add(System.getProperty("user.dir")+"\\Input\\Edge_TestNG.xml");
			}
			
			if (browser.equalsIgnoreCase("chrome")){
				Path path = Paths.get(System.getProperty("user.dir")+"\\Input\\Chrome_TestNG.xml");
		        try {
		            boolean fileExists = Files.exists(path);
		            if(!fileExists) {
		            System.out.println("File exists: " + fileExists);
		            setTextRed("<html>INFO:Exception occurred, not able to find testng file for Chrome browser.</html>");}
		        } catch (Exception e) {
					e.printStackTrace();
					setTextRed("<html>INFO:Exception occurred, not able to find testng file for Chrome browser.</html>");
					return;
				}
				suitefiles.add(System.getProperty("user.dir")+"\\Input\\Chrome_TestNG.xml");
				//suitefiles.add(TestRunner2.class.getClassLoader().getResourceAsStream("resources/Chrome_TestNG.xml").toString());
			}
		
		
		/*
		URL in= TestRunner.class.getResource("/images/Chrome_TestNG.xml");
		
		InputStream is = TestRunner.class.getResourceAsStream("/images/Chrome_TestNG.xml");
         File mysuite = new File("testng.xml");
         try {
             OutputStream os = new FileOutputStream(mysuite);
             byte buf[]=new byte[1024];
             int len;
             try {
                 while ((len=is.read(buf))>0) {
                     os.write(buf,0,len);
                     os.close();
                     is.close();
                     System.out.println(mysuite.toPath().toString());
                 }
                 } catch (IOException e) {
                     e.printStackTrace();  //To change body of catch
 
                 }
             } catch (FileNotFoundException e) {
                 e.printStackTrace();  //To change body of catch

             }

		System.out.println(text);
		// Add xml file which you have to execute
		//suitefiles.add("C:\\Automation\\Workspace\\WebProjectSanity\\Chrome_TestNG.xml");
		//suitefiles.add("C:\\Automation\\Seema\\Chrome_TestNG.xml");
		suitefiles.add("testng.xml");
		*/
		//suitefiles.add(System.getProperty("user.dir")+"\\Chrome_TestNG.xml");
		

		// now set xml file for execution
		testng.setTestSuites(suitefiles);

		//testng.setTestClasses(new Class[] {TestSuite.class});
		//testng.setOutputDirectory("/Users/pankaj/temp/testng-output");
		
		testng.run();
		
		}catch(Exception ex) {
			ex.printStackTrace();
			setTextRed(ex.toString());
		}

		}
	
		
}
