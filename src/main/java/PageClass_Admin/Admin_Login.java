/*
 * @autor : Chaitanya 
 * 
 * * 
 */
package PageClass_Admin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Admin_Login {
	WebDriver driver;
	public Admin_Login(WebDriver driver)
	{
	this.driver=driver;
	}
	@FindBy(xpath="//*[@name='admin_user[email]']")
	WebElement EnterAdminEmail;
	@FindBy(xpath="//*[@name='admin_user[password]']")
	WebElement EnterAdminPassword;
	@FindBy(xpath="//input[@value='Login']")
	WebElement AdminLogin;
	
	public void Loginadmin(String email,String password) {
		
		//this.EnterAdminEmail.clear();
		this.EnterAdminEmail.sendKeys(email);
		//this.EnterAdminPassword.clear();
		this.EnterAdminPassword.sendKeys(password);
		this.AdminLogin.click();	
		
	}
}
