/*
 * @autor : Chaitanya 
 * 
 * * 
 */
package PageClass_Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Users {
	WebDriver driver;
	public Users(WebDriver driver)
	{
	this.driver=driver;
	}
	//Admin login Elements
	@FindBy(xpath="//input[@name='admin_user[email]']")
	WebElement EnterAdminEmail;
	@FindBy(xpath="//input[@id='admin_user_password']")
	WebElement EnterAdminPassword;
	@FindBy(xpath="//input[@value='Login']")
	WebElement AdminLogin;
	//Users tab elements
	@FindBy(xpath="//a[@href='/admin/users']")
	WebElement Users;
	@FindBy(xpath="//input[@id='q_email']")
	WebElement Emailsearch;
	@FindBy(xpath="//input[@id='q_personal_email']")
	WebElement Personalemailsearch;
	@FindBy(xpath="//input[@value='Filter']")
	WebElement Filter;
	@FindBy(xpath="//a[@class='clear_filters_btn']")
	WebElement ClearFilter;
	@FindBy(xpath="//span[@class='blank_slate']")
	WebElement Nousersfound;
	@FindBy(xpath="//div[@class='pagination_information']")
	WebElement Displayusers;
	@FindBy(xpath="//a[@title='Edit']")
	WebElement Edit;
	@FindBy(xpath="//input[@name='user[password]']")
	WebElement password;
	@FindBy(xpath="//input[@id='user_password_confirmation']")
	WebElement confirmpassword;
	@FindBy(xpath="//input[@value='Update User']")
	WebElement Updateuser;
	
	//New user create elements
	@FindBy(xpath="//a[contains(text(),'New User')]")
	WebElement Newuser;
	@FindBy(xpath="//input[@id='user_title']")
	WebElement usertitle;
	@FindBy(xpath="//input[@id='user_first_name']")
	WebElement user_first_name;
	@FindBy(xpath="//input[@id='user_last_name']")
	WebElement user_last_name;
	@FindBy(xpath="//input[@id='user_password']")
	WebElement user_password;
	@FindBy(xpath="//input[@id='user_password_confirmation']")
	WebElement user_password_confirmation;
	@FindBy(xpath="//input[@id='user_email']")
	WebElement user_email;
	@FindBy(xpath="//input[@id='user_personal_email']")
	WebElement user_personal_email;
	@FindBy(xpath="//select[@id='user_onboard_email']")
	WebElement user_onboard_email;
	@FindBy(xpath="//select[@id='user_company_id']")
	WebElement user_company_id;
	@FindBy(xpath="//select[@id='user_role']")
	WebElement user_role;
	@FindBy(xpath="//input[@id='user_start_date']")
	WebElement user_start_date;
	@FindBy(xpath="//select[@id='user_current_stage']")
	WebElement user_current_stage;
	@FindBy(xpath="//input[@value='Create User']")
	WebElement CreateUser;
	

	public void Clickonusers() {
		this.Users.click();	
	}	
	public void Movetoelement(WebElement element) {
	//This will scroll until the element is in view
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public void selectbytext(WebElement elementname ,String text)
	{
		Select se=new Select(elementname);
		se.selectByVisibleText(text);
	}
	public void finduser(String email) throws InterruptedException {
		Movetoelement(Emailsearch);
		this.Emailsearch.clear();
		this.Emailsearch.sendKeys(email);
		this.Filter.click();		
	}
	public void updateuserpassword(String password) {
		this.Edit.click();
		this.password.click();
		this.password.sendKeys(password);
		this.confirmpassword.click();
		this.confirmpassword.sendKeys(password);
		Movetoelement(Updateuser);
		this.Updateuser.click();
		
	}
	public void CreateUser(String title,String firstname,String lastname,String password,String email,String personalemail,String onboardemail,String comapny,String role,String startdate,String currentstage) {
		this.Newuser.click();
		this.usertitle.click();
		this.usertitle.sendKeys(title);
		this.user_first_name.click();
		this.user_first_name.sendKeys(firstname);
		this.user_last_name.click();
		this.user_last_name.sendKeys(lastname);
		this.password.click();
		this.password.sendKeys(password);
		this.confirmpassword.click();
		this.confirmpassword.sendKeys(password);
		this.user_email.click();
		this.user_email.sendKeys(email);
		this.user_personal_email.click();
		this.user_personal_email.sendKeys(personalemail);
		selectbytext(user_onboard_email , onboardemail );
		selectbytext(user_company_id, comapny);
		selectbytext(user_role, role);
		this.user_start_date.sendKeys(startdate);
		this.user_start_date.sendKeys(Keys.ESCAPE);
		selectbytext(user_current_stage, currentstage);
		this.CreateUser.click();
	}
}