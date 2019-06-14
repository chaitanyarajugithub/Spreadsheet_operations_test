/*
 * @autor : Chaitanya 
 * 
 * * 
 */
package Resource;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
public class Taking_Screenshot {
public static DateFormat df;
public static String datef;
public static Date d; 
public static void takescreenshot(WebDriver driver, String sname)
{
try{
df=new SimpleDateFormat("dd_MM_yyyy hh_mm_ss");
d=new Date();
datef=df.format(d);
String spath="D:\\CHAITU\\Chaitanya_Work\\SaplingData\\workspacesapling\\Exceloperations_test\\Screens\\"+datef+" "+sname+".png";
File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(src, new File(spath));
	}catch(IOException t)
	{
System.out.println(t.getMessage());
	}
	}
	}

