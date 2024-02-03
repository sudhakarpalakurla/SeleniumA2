package genericUtilities;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import genericUtilities123.IConstantPath;
import genericUtilities123.JavaUtility;
import genericUtilities123.PropertiesUtility;
import pomPages.AddNewCategoryPage;
import pomPages.AddNewCoursePage;
import pomPages.AddNewUserPage;
import pomPages.CategoryPage;
import pomPages.CourseList;
import pomPages.HomePage;
import pomPages.UsersPage;
import pomPages.loginPage;

public class BaseClass {
	protected WebDriver driver;
	protected PropertiesUtility property;
	protected JavaUtility jutil;
	protected WebDriverUtility web;
	protected ExcelUtility excel;

	protected loginPage login;
	protected HomePage home;
	protected UsersPage users;
	protected CourseList courseList;
	protected CategoryPage category;
	protected AddNewUserPage addUser;
	protected AddNewCoursePage addCourse;
	protected AddNewCategoryPage addCategory;
	
	public static WebDriver sdriver;
	public static JavaUtility sjUtili;

	// @BeforeSuite
	// @BeforeTest

	@BeforeClass
	public void classSetup() {
		web = new WebDriverUtility();
		jutil = new JavaUtility();
		property = new PropertiesUtility();
		excel = new ExcelUtility();

		property.PropertiesInit(IConstantPath.PROPERTIES_FILE_PATH);

		driver = web.launchBrowserAndMaximize(property.readFromProperties("browser"));
		web.waitTillElementFound(Long.parseLong(property.readFromProperties("timeouts")));
        sdriver = driver;
        sjUtili = jutil;
	}

	@BeforeMethod
	public void methodSetup() {
		login = new loginPage(driver);
		home = new HomePage(driver);
		users = new UsersPage(driver);
		courseList = new CourseList(driver);
		category = new CategoryPage(driver);
		addUser = new AddNewUserPage(driver);
		addCourse = new AddNewCoursePage(driver);
		addCategory = new AddNewCategoryPage(driver);

		excel.excelInIt(IConstantPath.EXCEL_PATH, "Sheet1");

		web.navigateToApp(property.readFromProperties("url"));
		Assert.assertEquals(login.getPageHeader(),"Login");
		login.loginToApp(property.readFromProperties("username"), property.readFromProperties("password"));
		Assert.assertEquals(home.getPageHeader(), "Home");

	}

	@AfterMethod
	public void methodTearDown() {
		excel.closeExcel();

	}

	@AfterClass
	public void classTearDown() {
		web.quitAllWindows();
	}
	// @AfterTest
	// @AfterSuite

}


	
	
