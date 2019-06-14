/*
 * @autor : Chaitanya 
 * 
 * * 
 */
package PageClass_User;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class User_Login {
	static WebDriver driver;
	public User_Login(WebDriver driver)
	{
	this.driver=driver;
	}
	@FindBy(xpath="//div[@class='sign-in-with-email ng-scope']")
	WebElement Signinwithemail;
	@FindBy(xpath="//input[@id='input_0']")
	WebElement EnterEmail;
	@FindBy(xpath="//input[@id='input_1']")
	WebElement EnterPassword;
	@FindBy(xpath="//button[@type='submit']")
	WebElement Signin;
	@FindBy(xpath="//a[@class='h6 forgot-password md-accent-color ng-binding md-default-theme']")
	WebElement forgotpassword;
	
	public void Loginuser(String email,String password) {
	boolean el = isClickable(Signinwithemail);
	if (el != true) {
		this.EnterEmail.clear();
		this.EnterEmail.sendKeys(email);
		this.EnterPassword.clear();
		this.EnterPassword.sendKeys(password);
		this.Signin.click();	
		}
	else {
		Signinwithemail.click();
		this.EnterEmail.clear();
		this.EnterEmail.sendKeys(email);
		this.EnterPassword.clear();
		this.EnterPassword.sendKeys(password);
		this.Signin.click();		
		}
	}
	public static boolean isClickable(WebElement name)      
	{
	try
	{
	   WebDriverWait wait = new WebDriverWait(driver, 5);
	   wait.until(ExpectedConditions.elementToBeClickable(name));
	   return true;
	}
	catch (Exception e)
	{
	  return false;
	}
	}
}