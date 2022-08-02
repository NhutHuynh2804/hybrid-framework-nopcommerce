package com.hrm.employee;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObject.hrm.AddEmployeePO;
import pageObject.hrm.DashboardPO;
import pageObject.hrm.EmployeeListPO;
import pageObject.hrm.LoginPO;
import pageObject.hrm.PageGenerator;
import pageObject.hrm.MyInfoPO;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_19_Live_Coding extends BaseTest {
	WebDriver driver;
	LoginPO loginPage;
	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage;
	EmployeeListPO employeeListPage;
	MyInfoPO myInfoPage;
	
	String employeeID, statusValue;
	String adminUserName, adminPassword, empFirstName, empLastName, empPassword, empUsername, empFullName;
	String editEmpFirstName, editEmpLasttName, editEmpGender, editEmpMaritalStatus, editEmpNationality;
	String avatarFilePath = GlobalConstants.UPLOAD_FILE+"mocchau.jpg";


	

	@Parameters({"browser","appUrl"})
	
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition:Open browser"+browserName+ "'and navigate to'"+appUrl+"'");
		driver= getBrowserDriver(browserName,appUrl);
		
		statusValue="Enabled";
		adminUserName="Admin";
		adminPassword="admin123";
		empFirstName="Automation";
		empLastName="FX";
		empPassword="automationfx";
		empUsername="automationfx";
		empFullName=empFirstName+" "+empLastName;
		editEmpFirstName ="John"; 
		editEmpLasttName ="Cenna";  
		editEmpGender="Male" ;
		editEmpMaritalStatus ="Single";
		editEmpNationality = "Vietnamese";
		
		loginPage=PageGenerator.getLoginPage(driver);
		
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);

	}
	
	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Add_New_01 - Step 01: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage= PageGenerator.getEmployeeListPage(driver);
		
		log.info("Add_New_01 - Step 02: Click to 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
 		addEmployeePage = PageGenerator.getAddEmployeePage(driver);
 		
		log.info("Add_New_01 - Step 03: Enter valid info to 'First Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "firstName", empFirstName);
		
		log.info("Add_New_01 - Step 04: Enter valid info to 'Last Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "lastName", empLastName);

		log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboxvalueByID(driver, "employeeId");
		
		log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabelHRM(driver, "Create Login Details");
		
		log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_name", empUsername);


		log.info("Add_New_01 - Step 08: Enter valid info to 'Pasword' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_password", empPassword);


		log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Pasword' textbox");
		addEmployeePage.enterToTextboxByID(driver, "re_password", "automationfx");


		log.info("Add_New_01 - Step 10: Select  'Enable'"+statusValue+" value in 'Status' dropdown");
		addEmployeePage.selectItemDropdownByID(driver, "status", statusValue);

		log.info("Add_New_01 - Step 11: Click to 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage =PageGenerator.getMyInfoPage(driver);
		
		log.info("Add_New_01 - Step 12: Open 'Employee List' page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage= PageGenerator.getEmployeeListPage(driver);

		log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
		employeeListPage.sleepInSecond(5);
		employeeListPage.enterToTextboxByID(driver, "empsearch_employee_name_empName", empFullName);
		employeeListPage.sleepInSecond(5);

		log.info("Add_New_01 - Step 14: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		employeeListPage.sleepInSecond(5);


		log.info("Add_New_01 - Step 15: Verify Employee Information displayed at 'Result Table' ");
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), empFirstName);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), empLastName);


		
	}
	
	@Test
	public void Employee_02_Upload_Avatar() {
		
		log.info("Upload_Avatar_02 - Step 01: Login with Employee role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage= loginPage.loginToSystem(driver, empUsername, empPassword);
		
		log.info("Upload_Avatar_02 - Step 02: Open Personal Detail page");
		 dashboardPage.openMenuPage(driver, "My Info");
		 myInfoPage = PageGenerator.getMyInfoPage(driver);
		 
		 log.info("Upload_Avatar_02 - Step 03: Click To Change Photo Image");
		 myInfoPage.clickToChangeImage(driver);
		 
		 log.info("Upload_Avatar_02 - Step 04: Upload New Avatar image");
		 myInfoPage.uploadToChangeImage(driver, avatarFilePath);
		 
		 log.info("Upload_Avatar_02 - Step 05: Click to upload button");
		 myInfoPage.clickToButtonByID(driver, "btnSave");
		 
		 log.info("Upload_Avatar_02 - Step 06: Verify new Avatar is displayed");
		 verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));
		 
		 log.info("Upload_Avatar_02 - Step 07: Verify new Avatar image is displayed");
		 verifyTrue(myInfoPage.isNewAvatarImageDisplayed());



	}
	
	@Test
	public void Employee_03_Edit_Personal_Details() {
		log.info("Personal Details_03 - Step 01: Open 'Personal Details' tab at side bar");
		myInfoPage.openTabSideBarByName("Personal Details");

		log.info("Personal Details_03 - Step 02: Verify all fields at 'Personal Details' tab are disabled ");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtEmpFirstName"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtEmpLastName"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtEmployeeId"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtNICNo"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtSINNo"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_optGender_1"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_optGender_2"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_cmbMarital"));		
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_cmbNation"));		
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_DOB"));		
		
		log.info("Personal Details_03 - Step 03: Click to 'edit' button at 'Personal Details' form ");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		log.info("Personal Details_03 - Step 04: Verify 'Employee Id' textbox is disable ");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtEmployeeId"));

		log.info("Personal Details_03 - Step 05: Verify 'Driver's License Number' textbox is disable ");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtLicenNo"));

		log.info("Personal Details_03 - Step 06: Verify 'SSN Number' textbox is disable ");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtSINNo"));

		log.info("Personal Details_03 - Step 07: Verify 'SIN Number' textbox is disable ");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtNICNo"));

		log.info("Personal Details_03 - Step 08: Verify 'Date of Birth' textbox is disable ");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_DOB"));		

		
		log.info("Personal Details_03 - Step 09: Enter new value to 'First Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "txtEmpFirstName", editEmpFirstName);
		
		log.info("Personal Details_03 - Step 10: Enter new value to 'Last Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "txtEmpLastName", editEmpLasttName);

		log.info("Personal Details_03 - Step 11: Select new value to 'Gender' radio button");
		myInfoPage.clickToRadioByLabelHRM(driver, editEmpGender);

		log.info("Personal Details_03 - Step 12: Select new value to 'Marital Status' dropdown");
		myInfoPage.selectItemDropdownByID(driver, "personal_cmbMarital", editEmpMaritalStatus);

		log.info("Personal Details_03 - Step 13: Select new value to 'Nationality' dropdown");
		myInfoPage.enterToTextboxByID(driver, "personal_cmbNation", editEmpNationality);

		
		
		log.info("Personal Details_03 - Step 14: Click to 'Save' button at 'Personal Details' form ");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Personal Details_03 - Step 15: Verify Success message is displayed ");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Personal Details_03 - Step 16: Verify 'First Name' textbox is update success ");
		verifyEquals(myInfoPage.getTextboxvalueByID(driver, "personal_txtEmpFirstName"),editEmpFirstName);
		
		log.info("Personal Details_03 - Step 17: Verify 'Last Name' textbox is update success ");
		verifyEquals(myInfoPage.getTextboxvalueByID(driver, "personal_txtEmpLastName"),editEmpLasttName);
		

		log.info("Personal Details_03 - Step 18: Verify 'Gender' radio button is updated success ");
		verifyTrue(myInfoPage.isRadioSelectedByLabel(driver, editEmpGender));
		
		log.info("Personal Details_03 - Step 19: Verify 'Marital Status' dropdown is update success ");
		verifyEquals(myInfoPage.getItemDropdownByID(driver, "personal_cmbMarital"), editEmpMaritalStatus);
		
		log.info("Personal Details_03 - Step 20: Verify 'Nationality' dropdown is update success ");
		verifyEquals(myInfoPage.getItemDropdownByID(driver, "personal_cmbNation"),editEmpNationality);

		
		

	}
	
	@Test
	public void Employee_04_Contact_Details() {
		log.info("My Account - Step 01: Navigate to 'My Account' page");


	}
	
	@Test
	public void Employee_05_Emergency_Details() {
		log.info("My Account - Step 01: Navigate to 'My Account' page");


	}
	
	@Test
	public void Employee_06_Assigned_Dependents() {
		log.info("My Account - Step 01: Navigate to 'My Account' page");


	}
	
	@Test
	public void Employee_07_Edit_View_Job() {
		log.info("My Account - Step 01: Navigate to 'My Account' page");


	}
	
	@Test
	public void Employee_08_Edit_View_Salary() {
		log.info("My Account - Step 01: Navigate to 'My Account' page");


	}
	
	@Test
	public void Employee_09_Edit_View_Tax() {
		log.info("My Account - Step 01: Navigate to 'My Account' page");

	}
	
	@Test
	public void Employee_10_Qualifications() {
		log.info("My Account - Step 01: Navigate to 'My Account' page");

	}
	
	@Test
	public void Employee_11_Search_Employee() {
		log.info("My Account - Step 01: Navigate to 'My Account' page");

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int generateNumber() {
		Random rand=new Random();
		return rand.nextInt(9999);
	}
}
