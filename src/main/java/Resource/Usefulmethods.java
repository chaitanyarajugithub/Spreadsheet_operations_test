package Resource;

import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Usefulmethods {
WebDriver driver;	
	public Boolean IsElementPresent(WebElement element)
    {
        try
        {
            return element.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

	public String Role(String name) {
	    switch (name) {
	        case "Super User":
	            return "Account owner";
	        case "Super Admin":
	            return "Account owner";
	        case "Admin":
	            return "Admin";
	        case "Manager":
	            return "Employee";
	        case "Employee":
	            return "Employee";
	        default:
	            return "Account owner";
	    }
	}  
	
	public String Company(String url) {
	    switch (url) {
	        case "https://ace.shr-eng.com/admin/login":
	            return "Ace";
	        case "https://buzz.shr-eng.com/admin/login":
	            return "Rocketship";
	        case "https://rock.shr-eng.com/admin/login":
	            return "Rocketship";
	        case "https://spades.shr-eng.com/admin/emails":
	            return "Rocketship";
	        case "https://prime.shr-eng.com/admin/login":
	            return "rocketship";
	        default:
	            return "Rocketship";
	    }
	}  
	
	/*public static void main(String args[]) {
		String data = Role("Employee");
		System.out.println(data);
	}
*/
}
