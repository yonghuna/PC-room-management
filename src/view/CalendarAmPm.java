package view;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarAmPm {

	public void testName() throws Exception {
		
		int i = Calendar.getInstance().get(Calendar.AM_PM);
		String[] ampm = {"AM", "PM"};
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String time = sdf.format(new Date());
		
		System.out.println("지금 오번 오후의 값 : " + i +"," + ampm[i]);
		System.out.println("지금 시간은 :"+ time+"입니다.");
	}

}
