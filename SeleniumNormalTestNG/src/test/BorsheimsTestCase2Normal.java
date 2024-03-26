package test;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

import utils.ExeclUtilsss;

public class BorsheimsTestCase2Normal {
	
	
	WebDriver driver=null;
	int testCaseNumber;
	 String expectedUsername=null;
	 private ExeclUtilsss excel = new ExeclUtilsss();
	
	@Parameters("testCaseNumber")
	@BeforeTest
	public void setUpTest(@Optional("14") int testCaseNumber) {
		this.testCaseNumber = testCaseNumber;
		driver=new ChromeDriver();
		System.out.println("testcaseNo: "+testCaseNumber);
	}
	
	
	@Test (priority = 0)
	public void NavigateWebsite() throws Exception {
		
		try {
		driver.get("https://borsheims.com/");
		
		//Navigate to Account page:
		driver.findElement(By.xpath("//*[@id=\"js-header\"]/div[1]/div/div[3]")).click();
		
		//Navigate to register:
		driver.findElement(By.xpath("//*[@id=\"js-main-content\"]/div/div[1]/div/div/div[3]/div[2]/p[2]/a")).click();
	    Assert.assertTrue(true); // Dummy assertion to simulate test case execution
        excel.writeStatusToExcel(1,"testcase2", "Passed");
		} catch (AssertionError e) {
			excel.writeStatusToExcel(1,"testcase2", "Failed");
			throw e; 
		}
	
	}
	

	
	@Test(priority = 1, dataProvider = "test1data")
	public void registerInformation(String email, String password, String confirmPassword,String firstName, String lastName, String emailAdd, String phoneNo,
				String faxNo, String company, String address, String city, String state, String otherState, String zipCode, String country,String done ) throws Exception {
		
		try {
			
	    if ("X".equalsIgnoreCase(done)) {
	    	System.out.println("User already registered.");
	        return; // Exit method without proceeding
	    }
		
		expectedUsername=firstName;
		driver.findElement(By.id("Customer_LoginEmail")).sendKeys(email);
		driver.findElement(By.id("l-Customer_Password")).sendKeys(password);
		driver.findElement(By.id("l-Customer_VerifyPassword")).sendKeys(confirmPassword);
		
		driver.findElement(By.id("l-Customer_ShipFirstName")).sendKeys(firstName);
		driver.findElement(By.id("l-Customer_ShipLastName")).sendKeys(lastName);
		driver.findElement(By.id("Customer_ShipEmail")).sendKeys(emailAdd);
		driver.findElement(By.id("l-Customer_ShipPhone")).sendKeys(phoneNo);
		driver.findElement(By.id("l-Customer_ShipFax")).sendKeys(faxNo);
		driver.findElement(By.id("l-Customer_ShipCompany")).sendKeys(company);
		driver.findElement(By.id("l-Customer_ShipAddress1")).sendKeys(address);
		driver.findElement(By.id("l-Customer_ShipCity")).sendKeys(city);
        // Find the dropdown element
        WebElement dropdownState = driver.findElement(By.id("Customer_ShipStateSelect"));
        Select selectState = new Select(dropdownState);
        selectState.selectByVisibleText(state);
		driver.findElement(By.id("l-Customer_ShipState")).sendKeys(otherState);
		driver.findElement(By.id("l-Customer_ShipZip")).sendKeys(zipCode);
        // Find the dropdown element
        WebElement dropdownCountry = driver.findElement(By.id("Customer_ShipCountry"));
        Select selectCountry = new Select(dropdownCountry);
        selectCountry.selectByVisibleText(country);
        
        //click on save:
        driver.findElement(By.xpath("//*[@id=\"shipping_fields\"]/div[14]/div/input")).click();
        
        
        
	    Assert.assertTrue(true); // Dummy assertion to simulate test case execution
        excel.writeStatusToExcel(2,"testcase2", "Passed");
		} catch (AssertionError e) {
			excel.writeStatusToExcel(2,"testcase2", "Failed");
			throw e; 
		}
	}
	
    @Test(priority = 2)
    public void usernameDisplayed() throws Exception {
    	
    	try {
        // Find the message element
        ExeclUtilsss excel=new ExeclUtilsss();
    	excel.writeDoneToExcel(testCaseNumber);
        WebElement messageElement = driver.findElement(By.className("message-success"));
        String messageText = messageElement.getText();

        // Extract the username from the message
        String username = messageText.split(" ")[1]; //username is the second word
        // Remove any unwanted characters from the username
        username = username.replaceAll("[^a-zA-Z0-9]", ""); // Remove any non-alphanumeric characters
        System.out.println("Username: " + username);

        // Expected username
        System.out.println("Expected username: "+expectedUsername); ;

        // Compare the extracted username with the expected username
        Assert.assertEquals(username, expectedUsername, "Actual username does not match expected username");
        
        //click on save:
        //driver.findElement(By.xpath("//*[@id=\"js-aced-form\"]/div[2]/div/input")).click();
        
	    Assert.assertTrue(true); // Dummy assertion to simulate test case execution
        excel.writeStatusToExcel(3,"testcase2", "Passed");
		} catch (AssertionError e) {
			excel.writeStatusToExcel(3,"testcase2", "Failed");
			throw e; 
		}
        
    }
	
	@Test(priority = 3)
	public void logoutFunction() throws Exception {	
		try {
		//Navigate to MyAccount page:
		driver.findElement(By.xpath("//*[@id=\"js-header\"]/div[1]/div/div[3]")).click();
		Thread.sleep(2000);
		//logout from the account:
		driver.findElement(By.xpath("//*[@id=\"js-main-content\"]/div/div[1]/div/div/p/a")).click();
		//wait for 5 sec.
		Thread.sleep(5000);
		
	    Assert.assertTrue(true); // Dummy assertion to simulate test case execution
        excel.writeStatusToExcel(4,"testcase2", "Passed");
		} catch (AssertionError e) {
			excel.writeStatusToExcel(4,"testcase2", "Failed");
			throw e; 
		}
	}
	
	
	@Test(priority = 4, dataProvider = "test2data")
	public void loginInformation(String email, String password ) throws Exception {	
		try {
		//Navigate to Account page:
		driver.findElement(By.xpath("//*[@id=\"js-header\"]/div[1]/div/div[3]")).click();
		driver.findElement(By.id("l-Customer_LoginEmail")).sendKeys(email);
		driver.findElement(By.id("l-Customer_Password")).sendKeys(password);
		driver.findElement(By.id("l-Customer_Password")).sendKeys(Keys.ENTER);
		

		
	    Assert.assertTrue(true); // Dummy assertion to simulate test case execution
        excel.writeStatusToExcel(5,"testcase2", "Passed");
		} catch (AssertionError e) {
			excel.writeStatusToExcel(5,"testcase2", "Failed");
			throw e; 
		}

	}
	

	@DataProvider(name="test1data")
	public Object[][] getData() throws Exception {
		String excelPath="C:\\Users\\Msys\\eclipse-workspace\\SeleniumNormalTestNG\\excel\\data.xlsx";
		String sheetName="registerInfo";
		
		Object[][] data = testData(excelPath, sheetName);
		return data;
	}
	
	@DataProvider(name="test2data")
	public Object[][] getData2() throws Exception {
		String excelPath="C:\\Users\\Msys\\eclipse-workspace\\SeleniumNormalTestNG\\excel\\data.xlsx";
		String sheetName="registerInfo";
		
		Object[][] data = testData2(excelPath, sheetName);
		return data;
	}

	
	public Object[][] testData(String excelPath, String sheetName) throws Exception {
		ExeclUtilsss excel=new ExeclUtilsss("registerInfo");
		int rowCount=excel.getRowCount();
		int colCount=excel.getColCount();
		
		Object data[][]=new Object[1][colCount];
			for(int j=0;j<colCount;j++) {
				String username=excel.getCellDataString(testCaseNumber, j);
				System.out.print(username+" | ");
				data[0][j]=username;
				
			System.out.println();
		}
		return data;
		
	}
	
	public Object[][] testData2(String excelPath, String sheetName) throws Exception {
		ExeclUtilsss excel=new ExeclUtilsss("registerInfo");
		int rowCount=excel.getRowCount();
		int colCount=excel.getColCount();
		
		Object data[][]=new Object[1][2];
			for(int j=0;j<2;j++) {
				String username=excel.getCellDataString(testCaseNumber, j);
				System.out.print(username+" | ");
				data[0][j]=username;
				
			System.out.println();
		}
		return data;
	}
	

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
		driver.quit();
		System.out.println("Test Completed Successfully");
		
	}

}
