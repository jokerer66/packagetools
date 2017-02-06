package helper.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTime {

	private static MyTime mytime;
	public static MyTime getInstance() {
		if (mytime == null) {
			synchronized (MyTime.class) {
				if (mytime == null) {
					mytime = new MyTime();
				}
			}
		}
		return mytime;
	}

	public MyTime() {
	}

	//output year+month+date
	public String getDate(){
		Date date1 = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd");
		String date = sf.format(date1);

		return date;
	}

	//output year+month+date
	public String getDate_nopoint(){
		Date date1 = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String date = sf.format(date1);

		return date;
	}

	//output year+month+date
	public String getDateNum(){
		Date date1 = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
		String date = sf.format(date1);

		return date;
	}
	
	//output month+date
	public String getYueri(){
		Date date1 = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("MMdd");
		String date = sf.format(date1);

		return date;
	}
	
	//output changed hour numbers as string
	public String getHour(){
		Date date1 = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("HH");
		String date = sf.format(date1);

		int date_ap = Integer.parseInt(date);
		
		if(date_ap<10){

		date = "0"+date_ap+"";
	}else{

		date = date_ap+"";
	}


	return date;
	}

	public String getMinite(){
		Date date1 = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("mm");
		String date = sf.format(date1);

		int date_ap = Integer.parseInt(date);

		if(date_ap<10){

			date = "0"+date_ap+"";
		}else{

			date = date_ap+"";
		}

		return date;
	}
	
	//output commen time
	public String getTime(){
		
		String time = null;
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
		time = sf.format(date);
		
		return time;
	}

	public String getTime_iosPack(){

		String time = null;
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd-HH/mm");
		time = sf.format(date);

		return time;
	}

	public String getTime_buildtxt() {

		String time = null;
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd-HH:mm");
		time = sf.format(date);

		return time;
	}

	public String getTime_database(){

		String time = null;
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = sf.format(date);

		return time;
	}
	public String getSimpleTime_database(){

		String time = null;
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
		time = sf.format(date);

		return time;
	}



}
