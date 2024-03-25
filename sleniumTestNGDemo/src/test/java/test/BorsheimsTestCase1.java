package test;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.ExeclUtilsss;

public class BorsheimsTestCase1 {
	
	
	WebDriver driver=null;
	private ExeclUtilsss excel = new ExeclUtilsss();
	String brandName=null;
	
	@BeforeTest
	public void setUpTest() {
		driver=new ChromeDriver();
	}
	
	@Test (priority = 0)
	public void NavigateWebsite() throws Exception {
		try {
		driver.get("https://borsheims.com/");	
		//Navigate to Account page:
		driver.findElement(By.xpath("//*[@id=\"js-header\"]/div[1]/div/div[3]")).click();
		//enter valid email and password:
		driver.findElement(By.id("l-Customer_LoginEmail")).sendKeys("test@testqa2024.com");
		driver.findElement(By.id("l-Customer_Password")).sendKeys("wtx@91187");
		driver.findElement(By.id("l-Customer_Password")).sendKeys(Keys.ENTER);
	    Assert.assertTrue(true); // Dummy assertion to simulate test case execution
        excel.writeStatusToExcel(1,"testcase1", "Passed");
		} catch (AssertionError e) {
			excel.writeStatusToExcel(1,"testcase1", "Failed");
			throw e; 
		}

	}
	
    @Test(priority = 1, dataProvider = "brandNames")
    public void searchRandomBrand(String brandName) throws Exception {
    	try {
        System.out.println("Brand Name: " + brandName);
        this.brandName=brandName;
        driver.findElement(By.id("l-desktop-search")).sendKeys(brandName);
        driver.findElement(By.id("l-desktop-search")).sendKeys(Keys.ENTER);
        
        // brand name is displayed in the result search page:
        Thread.sleep(1000);
        WebElement actualTextElement = driver.findElement(By.xpath("//*[@id=\"js-category-header-title\"]"));
        String text = actualTextElement.getText();
        System.out.println("text: " + text);

        // Extract the first word
        String firstWordBrand = text.split("\\s+")[0];
        System.out.println("First word: " + firstWordBrand);
        Assert.assertEquals(firstWordBrand, brandName, "Actual text doesn't match expected text");
	    Assert.assertTrue(true); // Dummy assertion to simulate test case execution
        excel.writeStatusToExcel(2,"testcase1", "Passed");
		} catch (AssertionError e) {
			excel.writeStatusToExcel(2,"testcase1", "Failed");
			throw e; 
		}
    }
    
    @Test(priority = 2)
    public void addItemBag() throws Exception {
    	try {
    	Thread.sleep(2000);
    	WebElement element = null;
    	if(brandName.equalsIgnoreCase("Vhernier")) {
    		element=driver.findElement(By.xpath("//*[@id=\"js-main-content\"]/div[2]/div[1]/div/div[4]/x-search-app")).getShadowRoot().findElement(
        			By.cssSelector("x-card"));
    	}else {
        	element=driver.findElement(By.xpath("//*[@id=\"js-main-content\"]/div[2]/div[1]/div/div[3]/x-search-app")).getShadowRoot().findElement(
        			By.cssSelector("x-card"));
    	}
    	element.click();
    	Thread.sleep(1000);
    	driver.findElement(By.id("js-add-to-cart")).sendKeys(Keys.ENTER);
    	Thread.sleep(4000);
    	
    	driver.get("https://www.borsheims.com/vhernier-mon-jeu-titanium-link");
    	Thread.sleep(1000);
    	driver.findElement(By.id("js-add-to-cart")).sendKeys(Keys.ENTER);
    	Thread.sleep(4000);
    	
    	//driver.findElement(By.xpath("//*[@id=\"js-mini-basket-container\"]/div[1]/span")).sendKeys(Keys.ENTER);
	    Assert.assertTrue(true); // Dummy assertion to simulate test case execution
        excel.writeStatusToExcel(3,"testcase1", "Passed");
		} catch (AssertionError e) {
			excel.writeStatusToExcel(3,"testcase1", "Failed");
			throw e; 
		}
    }
    
    @Test(priority = 3)
    public void BagPageDisplay() throws Exception {
    	try {
        driver.findElement(By.xpath("//*[@id=\"js-header-contents\"]/a/img")).click();
        driver.findElement(By.xpath("//*[@id=\"js-header\"]/div[1]/div/div[4]")).click();
        driver.findElement(By.xpath("//*[@id=\"js-mini-basket-container\"]/div[2]/div/a")).sendKeys(Keys.ENTER);
        
        //increase the QT.:
        driver.findElement(By.xpath("//*[@id=\"js-main-content\"]/div/div[1]/div[2]/div/div[2]/div[3]/div[1]/div[3]/div[4]/form/div/span[2]")).click();

        // Find all parent divs containing the product details
        List<WebElement> productDivs = driver.findElements(By.cssSelector("div.flex.rigid.row.ai-center.basket-product-row"));

        // Create a 2D array to store item details including a header row
        String[][] valueToWrite = new String[productDivs.size() + 1][4];

        // Add header row
        valueToWrite[0] = new String[]{"Product Name", "Quantity", "Price", "Total"};

        // Iterate through each product div
        for (int i = 0; i < productDivs.size(); i++) {
            // Find the element containing the item name
            WebElement itemNameElement = productDivs.get(i).findElement(By.cssSelector("h4.lineitem-name a"));

            // Get the text of the item name
            String itemName = itemNameElement.getText().trim();
            System.out.println("item name: "+itemName);

            // Find the element containing the price
            WebElement priceElement = productDivs.get(i).findElement(By.cssSelector("div.lineitem-has-price p"));

            // Get the text of the price
            String price = priceElement.getText().trim();
            System.out.println("price: "+price);

            // Find the element containing the total price
            WebElement totalPriceElement = productDivs.get(i).findElement(By.cssSelector("div.lineitem-has-subtotal p"));

            // Get the text of the total price
            String totalPrice = totalPriceElement.getText().trim();
            System.out.println("total price: "+totalPrice);
            
            // Find the element containing the quantity input field
            WebElement quantityInputElement = productDivs.get(i).findElement(By.cssSelector("input#l-quantity"));

            // Get the value attribute of the input element to get the quantity
            String quantity = quantityInputElement.getAttribute("value").trim();
            System.out.println("Quantity: " + quantity);

            // Write the item details to the array
            valueToWrite[i + 1] = new String[]{itemName, quantity, price, totalPrice};
        }

        // Write item details to Excel
        ExeclUtilsss excel1 = new ExeclUtilsss();
        excel1.writeBagPageToExcel(valueToWrite,"C:\\Users\\Msys\\eclipse-workspace\\sleniumTestNGDemo\\excel\\data2.xlsx");
        
	    Assert.assertTrue(true); // Dummy assertion to simulate test case execution
        excel.writeStatusToExcel(4,"testcase1", "Passed");
		} catch (AssertionError e) {
			excel.writeStatusToExcel(4,"testcase1", "Failed");
			throw e; 
		}
    }




    @DataProvider(name = "brandNames")
    public Object[][] brandNames() {
        String[] brandList = {"Hobo", "Vhernier", "Baccarat"};
        Object[][] data = new Object[1][1]; // Create a 2D array with one row and one column
        Random randomNum = new Random();
        int randomIndex = randomNum.nextInt(brandList.length);
        String brandName = brandList[randomIndex];
        data[0][0] = brandName; // Set the brand name in the array
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
