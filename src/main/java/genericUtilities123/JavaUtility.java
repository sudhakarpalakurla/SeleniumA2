package genericUtilities123;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaUtility {

	public String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yy_hh_ss");
		return sdf.format(date);
	}

}
