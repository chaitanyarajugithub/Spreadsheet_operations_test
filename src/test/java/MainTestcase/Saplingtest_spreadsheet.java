/*
 * @autor : Chaitanya 
 * 
 * * 
 */
package MainTestcase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import Googlesheets.googlesheetsdata.Quickstart;
import PageClass_Admin.Admin_Login;
import PageClass_Admin.Users;
import PageClass_User.User_Login;
import Resource.ExcelUtilMethods;
import Resource.Usefulmethods;

public class Saplingtest_spreadsheet {
  private static final boolean flase = false;
  WebDriver driver;
  ExtentReports report;
  ExtentTest logger;
  Properties p;
  FileInputStream fi;
  String inputpath = "./Data/Input/Sapling Server Credentials.xlsx"; 
  
 @BeforeTest(enabled = flase)
  public void setUp() throws Exception {
	//phantom JS windows
	//System.setProperty("phantomjs.binary.path","phantomjs.exe");
	//phantom JS Linux
/*	System.setProperty("phantomjs.binary.path","/usr/bin/phantomjs");
    driver = new PhantomJSDriver();*/	  
	driver = new ChromeDriver();
    Thread.sleep(5000); 
    report = new ExtentReports("./Reports/PomReport.html");
    p =new Properties();
	fi =new FileInputStream("./src/main/java/Resource/Data.properties");
	p.load(fi);
	driver.get(p.getProperty("objurl"));
	driver.manage().window().maximize();
    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
 
  @Test(description="ExcelRead",enabled = true)
  public void Test() throws InterruptedException, IOException, ParseException {
	  try {
	  Quickstart Gc = new Quickstart();
	  p = new Properties();
	  fi = new FileInputStream("./Data/Input/Resources.properties");
	  p.load(fi);
	  ExcelUtilMethods excel= new ExcelUtilMethods(inputpath);
	  String spreadsheetid = p.getProperty("spreadsheetid");
	  //String sheetname = p.getProperty("Sheetname");
	  for (int i=0; i<5; i++) {
		  String sheetname = Gc.getsheetname(spreadsheetid, i);
		  String Url=Gc.Getcelldata(sheetname+"!C9", spreadsheetid);
		  String UrlAdmin=Gc.Getcelldata(sheetname+"!C10", spreadsheetid);
		  String adminemail1=Gc.Getcelldata(sheetname+"!C13", spreadsheetid);
		  String adminpass1=Gc.Getcelldata(sheetname+"!D13", spreadsheetid);
		  String adminemail2=Gc.Getcelldata(sheetname+"!C21", spreadsheetid);
		  String adminpass2=Gc.Getcelldata(sheetname+"!D21", spreadsheetid);
		  Reporter.log("URL is = "+ Url,true);
		  
		 // System.out.println(Url);
		  
		    for (int j=13; j<27; j++) {
			  String Email=Gc.Getcelldata(sheetname+"!C"+j, spreadsheetid);
			  String Password=Gc.Getcelldata(sheetname+"!D"+j, spreadsheetid);
			  String usertype=Gc.Getcelldata(sheetname+"!B"+j, spreadsheetid);
			  System.out.println(usertype.trim());
			  if((Email.contains("@")) && !(usertype.trim().equalsIgnoreCase("Active Admin")) && (Email != "empty")) {
			  Reporter.log("Email and password is = "+ Email +"  "+ Password,true);
			  driver = new ChromeDriver();
			  driver.get(Url);
			  Thread.sleep(3000);
			  User_Login userlogin=PageFactory.initElements(driver, User_Login.class);
			  Usefulmethods Methods = new Usefulmethods();
			  String companyname = Methods.Company(UrlAdmin);
			  try {
			  userlogin.Loginuser(Email, Password);
			  Thread.sleep(15000);
			  if(driver.getCurrentUrl().contains("updates")) {
				  System.out.println("user login sucsess");
			  }
			  else {
				  System.out.println("user login failed");
				  driver.get(UrlAdmin);
				  driver.manage().window().maximize();
				  Admin_Login admin=PageFactory.initElements(driver, Admin_Login.class);
				  Users users=PageFactory.initElements(driver, Users.class);
				  Thread.sleep(3000);
				  admin.Loginadmin(adminemail1, adminpass1);
				  Thread.sleep(7000);
				  if(driver.getCurrentUrl().contains("dashboard")){
					  Thread.sleep(3000);
					  users.Clickonusers();
					  Thread.sleep(3000);
					  users.finduser(Email);
					  /*//unused code
					  Boolean isPresent = driver.findElements(By.xpath("//a[@title='Edit']")).size()<0;
					  System.out.println(isPresent);*/
					  //WebElement pre = driver.findElement(By.xpath("//a[@title='Edit']"));
					  
					  if(driver.getPageSource().contains("edit")) {
					  Thread.sleep(4000);
					  users.updateuserpassword(Password);
					  }else {
						  String role=Methods.Role(usertype);
						  users.Clickonusers();
						  users.CreateUser("Mr", "AUTO", usertype , Password, Email, Email, "Personal", companyname, role, "2019-03-27", "active");
					  }
				  }
				  else {
					  System.out.println("Active Admin login 1 failed");
					  driver.get(UrlAdmin);
					  Thread.sleep(4000);
					  admin.Loginadmin(adminemail2, adminpass2);
					  Thread.sleep(7000);
					  if(driver.getCurrentUrl().contains("dashboard")){
						  Thread.sleep(3000);
						  users.Clickonusers();
						  Thread.sleep(3000);
						  users.finduser(Email);
						  Thread.sleep(3000);
						 // WebElement pre = driver.findElement(By.linkText("Edit"));
						  //Usefulmethods Methods = new Usefulmethods();
						  System.out.println(driver.getPageSource().contains("edit"));
						  //System.out.println(Methods.Textpresent("edit"));
						  
						  if(driver.getPageSource().contains("edit")) {
						  Thread.sleep(4000);
						  users.updateuserpassword(Password);
						  }else {
							  String role=Methods.Role(usertype);
							  users.Clickonusers();
							  users.CreateUser("Mr", "AUTO", usertype , Password, Email, Email, "Personal", companyname, role, "2019-03-27", "active");
						  }
						 }
					  else {
						  System.out.println("Active Admin login 2 failed");
					  }
					  
				  }
			  }
			  } catch (org.openqa.selenium.NoSuchElementException e)  {
		            System.out.println("NoSuchElementException!!");
		           
		        }
			  Thread.sleep(2000);
			  driver.close();
			  }
			  else {
				  System.out.println("Not correct Formats");
			  }
		  }	}		  
	  
	  } catch (NullPointerException | IndexOutOfBoundsException e) {
		    e.printStackTrace();
		}
  }
	  
 /* @AfterTest
  public void tearDown() throws Exception {
	report.flush();
	report.endTest(logger);  
    //driver.quit();
   }*/
}