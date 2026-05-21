package com.automation.testcases;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestRunner  extends EDCMainFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Exposed (package-private would be enough, but main() lives in the same
	// package) so EDCMainFrame.computeHeadlessExitCode can read TestNG's
	// post-run status bit-mask for a CI-friendly process exit code.
	public static TestNG testng;
	//static String browser="chrome";
	//static  Xls_Reader xls1 = null;
	public static void callSanity() {
		testng=new TestNG();
		//testng.addListener(new AnnotationTransformer());
		testng.setUseDefaultListeners(false);

		// Wire the live listener so ResultsLogPanel counters update per test case
		testng.addListener(new EDCTestListener());
		
		// Create a list of String 
		List<String> suitefiles=new ArrayList<String>();
		
		try {
			//xls1 = new Xls_Reader("resources/DataSourceFile.xlsx");
			//xls1 = new Xls_Reader(System.getProperty("user.dir")+"\\src\\Data\\DataSourceFile.xlsx");
			//browser = xls1.getCellData("Configuration", "Value",11);
			//System.out.println(browser);
			System.out.println(System.getProperty("user.dir"));
			
				Path path = Paths.get(System.getProperty("user.dir")+"\\Input\\TestNG.xml");
		        try {
		            boolean fileExists = Files.exists(path);
		            if(!fileExists) {
		            System.out.println("File exists: " + fileExists);
		            setTextRed("<html>INFO:Exception occurred, not able to find testng file .</html>");}
		        } catch (Exception e) {
					e.printStackTrace();
					setTextRed("<html>INFO:Exception occurred, not able to find testng file.</html>");
					return;
				}
				suitefiles.add(System.getProperty("user.dir")+"\\Input\\TestNG.xml");
			
		
		
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
