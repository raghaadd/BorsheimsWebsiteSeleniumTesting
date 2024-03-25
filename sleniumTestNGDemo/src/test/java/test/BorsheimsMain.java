package test;

import java.util.List;

import utils.ExeclUtilsss;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BorsheimsMain {
    ExeclUtilsss excel = new ExeclUtilsss("testCases");

    @Test(priority = 1)
    public void readExecutionColumn() throws Exception {
        int executionColumnIndex = 1;
        int rowCount = excel.getRowCount();

        for (int i = 1; i <= rowCount; i++) { // Start from row 1 to skip the header row
            // Read the execution status for the current row
            String executionStatus = excel.getCellDataString(i, executionColumnIndex);
            System.out.println("Test Case #" + i + " Execution Status: " + executionStatus);
            // Perform further actions based on the execution status
            // For example, execute the test case if it's marked as "X"
            if ("X".equalsIgnoreCase(executionStatus)) {
                executeTestCase(i); // Pass the row index to execute the corresponding test case
            }
        }
    }

    @Test (priority = 2)
    public void executeTestCase(int rowIndex) throws Exception {
        switch (rowIndex) {
            case 1:
                BorsheimsTestCase1 tc1 = new BorsheimsTestCase1();
                tc1.setUpTest(); 
                Object[][] brandData = tc1.brandNames();
                String brandName = (String) brandData[0][0]; 
                System.out.println("band Name: "+brandName);
                tc1.NavigateWebsite(); 
                tc1.searchRandomBrand(brandName); 
                tc1.addItemBag(); 
                tc1.BagPageDisplay(); 
                tc1.tearDown(); 
                break;
            case 2:
                BorsheimsTestCase2 tc2 = new BorsheimsTestCase2();
                tc2.setUpTest(15); 
                tc2.NavigateWebsite(); 
                Object[][] testData = tc2.getData();
                
                // Extracting the data
                String email = (String) testData[0][0];
                String password = (String) testData[0][1];
                String confirmPassword = (String) testData[0][2];
                String firstName = (String) testData[0][3];
                String lastName = (String) testData[0][4];
                String emailAdd = (String) testData[0][5];
                String phoneNo = (String) testData[0][6];
                String faxNo = (String) testData[0][7];
                String company = (String) testData[0][8];
                String address = (String) testData[0][9];
                String city = (String) testData[0][10];
                String state = (String) testData[0][11];
                String otherState = (String) testData[0][12];
                String zipCode = (String) testData[0][13];
                String country = (String) testData[0][14];
                String done = (String) testData[0][15];
                tc2.registerInformation(email, password, confirmPassword, firstName, lastName,
                		emailAdd, phoneNo, faxNo, company, address, city, state, otherState, zipCode, country, done);
                tc2.usernameDisplayed();
                tc2.logoutFunction();
                tc2.loginInformation(email,password);
                tc2.tearDown(); 
                break;
            default:
                System.out.println("Invalid test case number: " + rowIndex);
                break;
        }
    }

//    public static void main(String[] args) throws Exception {
//        BorsheimsMain main = new BorsheimsMain();
//        main.readExecutionColumn();
//    }
}
