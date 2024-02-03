package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericUtilities.BaseClass;
import genericUtilities123.IConstantPath;

public class AddCourseTest  extends BaseClass {
	@Test
	
	public void addCourseTest() {
		SoftAssert soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCourseListLink();
		soft. assertTrue(courseList.getPageHeader().contains("Course List"));
		courseList.clickNewButton();
		soft.assertEquals(addCourse.getPageHeader(),"Add New Course");
		
		Map<String,String> map = excel.readFromExcel("Add Course");
		addCourse.setName(map.get("Name"));
		addCourse.selectCategory(web, map.get("Category"));
		addCourse.setPrice(map.get("Price"));
		addCourse.uploadphoto(map.get("photo"));
		addCourse.setDescription(map.get("Description"), web);
		addCourse.clickSave();
		
		soft.assertEquals(courseList.getSuccessMessage(),"Success!");
		courseList.deleteCource(web, map.get("Name"));
		soft.assertEquals(courseList.getSuccessMessage(), "Success!");
		
		if(courseList.getSuccessMessage().equals("Success!"))
			excel.updateTestStatus("Add Course", "Pass", IConstantPath.EXCEL_PATH);
		else
			excel.updateTestStatus("Add Course", "fail", IConstantPath.EXCEL_PATH);	
		
	}

}
