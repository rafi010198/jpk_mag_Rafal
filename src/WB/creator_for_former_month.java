package WB;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.toedter.calendar.JDateChooser;

import XML.jpk_mag_2;
import XML.jpk_mag_main;

public class creator_for_former_month {
	
	public static String directoryname;

	//**************** method for date stop and start ************************
		/**
		 	* @param datastart	create xml between datastart and datastop 
		 */
		
		public static  String datastart;
		 /**
			 * @param datastop	create xml between datastart and datastop 
		*/
		public static  String datastop;

		public static void setdatastart_1(String dat){
			datastart = dat+"-01";
			System.out.println("ustawiono date: "+ datastart);
		}
		
		public static String getdatastart(){
			return datastart;
		}

		public static void setdatastop_1(String dat){
			int year = Integer.parseInt(dat.substring(0, 4));
			int mount = Integer.parseInt(dat.substring(5, 7))-1;
			Calendar kalendarz = new GregorianCalendar(year,mount,1);
			int numberday= kalendarz.getActualMaximum(Calendar.DAY_OF_MONTH);	
			datastop = dat+"-"+numberday;
			System.out.println("ustawiono date stop:"+ datastop);
		}
		
		public static String getdatastop(){
			return datastop;
		}

		public void setdatastart(JDateChooser dstart){

			SimpleDateFormat doNazwy = new SimpleDateFormat("yyyy-MM-dd");
			datastart = doNazwy.format(dstart.getDate()).toString();
		}

		public void setdatastop(JDateChooser dstop){
			SimpleDateFormat doNazwy = new SimpleDateFormat("yyyy-MM-dd");
			datastop = doNazwy.format(dstop.getDate()).toString();
		}
	//*******************************************************************************
	
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String todaydate = date.format(calendar.getTime());
		int yeartoxml;
		String monthtoxml;
		int year = Integer.parseInt(todaydate.substring(0,4));
		int month = Integer.parseInt(todaydate.substring(5, 7));
		
		
		if(month<10)					//if to set number former month
		{
			if(month==01)
			{yeartoxml=year-1;
			monthtoxml="12";
			}
			else{
				yeartoxml=year;
				monthtoxml="0"+Integer.toString(month-1);
			}
		}
		else
		{
			yeartoxml=year;
			monthtoxml=Integer.toString(month-1);
		}
		
		System.out.println(todaydate);
		setdatastart_1(yeartoxml+"-"+monthtoxml);
		setdatastop_1(yeartoxml+"-"+monthtoxml);
		
		try {
			directoryname = "Magazyn_Zakupy_103";				//create xml to magazine main
			jpk_mag_main.main();
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			directoryname = "Magazyn_MCG_102";					//create xml to magazine 2
			jpk_mag_2.main();
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
