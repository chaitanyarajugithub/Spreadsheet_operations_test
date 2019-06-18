package Googlesheets.googlesheetsdata;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;

import jdk.nashorn.internal.parser.JSONParser;

import com.google.api.services.sheets.v4.Sheets;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
 
public class Quickstart {
/** Application name. */
private static final String APPLICATION_NAME ="Google Sheets API Java Quickstart";
 
/** Directory to store user credentials for this application. */
private static final java.io.File DATA_STORE_DIR = new java.io.File(
System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-quickstart");
 
/** Global instance of the {@link FileDataStoreFactory}. */
private static FileDataStoreFactory DATA_STORE_FACTORY;
 
/** Global instance of the JSON factory. */
private static final JsonFactory JSON_FACTORY =
JacksonFactory.getDefaultInstance();
 
/** Global instance of the HTTP transport. */
private static HttpTransport HTTP_TRANSPORT;
 
/** Global instance of the scopes required by this quickstart.
*
* If modifying these scopes, delete your previously saved credentials
* at ~/.credentials/sheets.googleapis.com-java-quickstart
*/
private static final List<String> SCOPES =
Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);
 
static {
try {
HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
} catch (Throwable t) {
t.printStackTrace();
System.exit(1);
}
}
 
/**
* Creates an authorized Credential object.
* @return an authorized Credential object.
* @throws IOException
*/
public static Credential authorize() throws IOException {
// Load client secrets.
InputStream in =new FileInputStream("D:\\CHAITU\\Chaitanya_Work\\SaplingData\\workspacesapling\\googlesheetsdata\\client_secret.json");
GoogleClientSecrets clientSecrets =
GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
 
// Build flow and trigger user authorization request.
GoogleAuthorizationCodeFlow flow =
new GoogleAuthorizationCodeFlow.Builder(
HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
.setDataStoreFactory(DATA_STORE_FACTORY)
.setAccessType("offline")
.build();
Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
System.out.println(
"Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
return credential;
}
 
/**
* Build and return an authorized Sheets API client service.
* @return an authorized Sheets API client service
* @throws IOException
*/
public static Sheets getSheetsService() throws IOException {
Credential credential = authorize();
return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
.setApplicationName(APPLICATION_NAME)
.build();
}
 
public static void main(String[] args) throws IOException, ParseException {
// Build a new authorized API client service.
Sheets service = getSheetsService();
 
// Prints the names and majors of students in a sample spreadsheet:
// https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
// https://docs.google.com/spreadsheets/d/1V_HYul_k-QKTS4oYg3SM8xNemCM5ElBp/edit?usp=drive_web&ouid=112583343300784901667&dls=true
String spreadsheetId = "1C8B0AISY2VzKGawhXwX95bUNCROsEaysl_2e6ZmGTlk";
//1sILuxZUnyl_7-MlNThjt765oWshN3Xs-PPLfqYe4DhI
//1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms
String range = "Ace!B13:E";
ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
List<List<Object>> values = response.getValues();
System.out.println(values.size());
System.out.println(values);
if (values == null || values.size() == 0) {
System.out.println("No data found.");
} else {
System.out.println("Name, Major");
for (List row : values) {
	try {
// Print columns A and E, which correspond to indices 0 and 4.
System.out.printf("%s,%s,%s\n", row.get(0),row.get(1), row.get(2));
	} catch (NullPointerException | IndexOutOfBoundsException e) {
	    e.printStackTrace();
	}
}
Quickstart a = new Quickstart();
String Print = a.Getcelldata("Chaitanya!E4", "13lWxNZlwlAY498DMX4r5KLTpmCYAGRiicVmloLR5YpY");
System.out.println(Print);
a.getsheet();

List<String> ranges = Arrays.asList("C9","C10");
BatchGetValuesResponse readResult = getSheetsService().spreadsheets().values()
  .batchGet("1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms")
  .setRanges(ranges)
  .execute();
         
ValueRange januaryTotal = readResult.getValueRanges().get(0);
//String total = (String) januaryTotal.getValues().get(0).get(0);
System.out.println((januaryTotal.getValues().get(0).get(0)));

ValueRange febTotal = readResult.getValueRanges().get(1);
System.out.println((febTotal.getValues().get(0).get(0)));
}
}

public String Getcelldata(String Cellnumber,String Sheetid) throws IOException {
	try {
	List<String> ranges = Arrays.asList(Cellnumber);
	BatchGetValuesResponse readResult = getSheetsService().spreadsheets().values()
	  .batchGet(Sheetid)
	  .setRanges(ranges)
	  .execute();  
	ValueRange data = readResult.getValueRanges().get(0);
	String Celldata = (String) (data.getValues().get(0).get(0));
	return Celldata;
   }catch(NullPointerException e) 
     { 
         System.out.print("NullPointerException Caught"); 
     }
	return "Null"; 
  }
public void getsheet() throws IOException, ParseException {
	String spreadsheetId = "1C8B0AISY2VzKGawhXwX95bUNCROsEaysl_2e6ZmGTlk";
	Sheets service = getSheetsService();
	Spreadsheet sp = service.spreadsheets().get(spreadsheetId).execute();
	String sheets = sp.getProperties().getTitle();
	System.out.println(sheets);	
	
	List<Sheet> sheets1 = sp.getSheets();
	System.out.println(sheets1.size());
	for (int i = 0; i < sheets1.size(); i++) {
		Sheet responseString = sheets1.get(i);
		//System.out.println(responseString);
		String data=responseString.toString();
		org.json.simple.parser.JSONParser parse = new org.json.simple.parser.JSONParser();
		JSONObject jobj = (JSONObject) parse.parse(data);
		jobj = (JSONObject) parse.parse(jobj.get("properties")+"");
		System.out.println(jobj.get("title"));
	    System.out.println(sheets1.get(i));
	    }
/*	Iterator<Sheet> iterator = sheets1.iterator();
	System.out.println(iterator.next());*/
	
}
public String getsheetname(String SpreadsheetId,int sheetid) throws IOException, ParseException {
	String spreadsheetId = SpreadsheetId;
	Sheets service = getSheetsService();
	Spreadsheet sp = service.spreadsheets().get(spreadsheetId).execute();
	List<Sheet> sheets1 = sp.getSheets();
	Sheet responseString = sheets1.get(sheetid);
		String data=responseString.toString();
		org.json.simple.parser.JSONParser parse = new org.json.simple.parser.JSONParser();
		JSONObject jobj = (JSONObject) parse.parse(data);
		jobj = (JSONObject) parse.parse(jobj.get("properties")+"");
		String Sheetname = (String) jobj.get("title");
		//System.out.println(jobj.get("title"));
		return Sheetname;
	   }
	
}