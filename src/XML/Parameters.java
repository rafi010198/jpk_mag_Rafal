package XML;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import WB.mainWindowStart;

//import PDF.Parameters;

public class Parameters extends mainWindowStart {
	
	
	
	/**
	 * @param datastart 	start date in string version yyyy-mm-dd
	*/
	/**
	* @param datastop 		stop date in string version yyyy-mm-dd
	*/
//	public static String datastart="2019-07-01";		--datastart inicjalize in mainWindowStart
//	public static String datastop="2019-07-01";			--datastop inicjalize in mainWindowStart
	

	public static final String xmlFilePath = "//192.168.90.203/Logistyka/Raporty godzin/jpk.XML";
	static SimpleDateFormat godz = new SimpleDateFormat("HH;mm");	
	static SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");

	public static String pzNumber 		= null;
	public static String pzDatum 		= null;
	public static String pzAmount 		= null;
	public static String pzLeveringsdatum = null;
	public static String pzLeverancier 	= null;
	
	
	private static String PathToSave= "C:/Users/pl01.FIN/Desktop/RD/baza_do_zapisu_lokalnie";
	private static String PathToSaveHours= "C:/Users/pl01.FIN/Desktop/RD/baza_do_zapisu_lokalnie";
	private static String PathToDB = "C:/Users/pl01.FIN/Desktop/RD/baza_do_zapisu_lokalnie";
	
	public static String getPathToSave(){
		return PathToSave;
	}
	
	public static String getPathToSaveHours(){
		return PathToSaveHours;
	}
	
	public static void setPathToSave (String s){
		PathToSave = s;
	}

	public static String getPathToDB() {
		return PathToDB;
	}

	public static void setPathToDB(String pathToDB) {
		PathToDB = pathToDB;
	}
	
	public static void createDirectory(){
		String path = getPath();
		File theDir = new File(path);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    try{
		        theDir.mkdir();
		    } 
		    catch(SecurityException se){
		        //handle it
		    }
		}
	}

public static File createFile(String name){
	String path = getPath();
	SimpleDateFormat godz = new SimpleDateFormat("HH;mm");
	Calendar date = Calendar.getInstance();
	
	File f = new File(path+name);
	if(f.exists() && !f.isDirectory()){
		f = new File(path+ godz.format(date.getTime())+" "+name);
	}
	
	return f;
	
}

public static String getPath(){
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat doNazwy = new SimpleDateFormat("yyyy.MM.dd");
//	String path = Parameters.getPathToSaveHours()+"/"+doNazwy.format(calendar.getTime())+"/";			// create directory with today date in name
//-----------------------------------------------------------------------------------------------------
/*		Date first = null;																		// create directory with date first day in xml in name
		try {
			first = new SimpleDateFormat("yyyy-MM-dd").parse(datastart);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	String path = Parameters.getPathToSaveHours()+"/"+doNazwy.format(first)+"/";	
*/	
//---------------------------------------------------------------------------------------------------	
	Date first = null;																	// create directory - name magazin > date 
	try {
		first = new SimpleDateFormat("yyyy-MM-dd").parse(datastart);
	} catch (ParseException e) {
		e.printStackTrace();
	}
String path1 = Parameters.getPathToSaveHours()+"/"+directoryname+"/";
File theDir = new File(path1);
// if the directory does not exist, create it
if (!theDir.exists()) {
    try{
        theDir.mkdir();
    } 
    catch(SecurityException se){
        //handle it
    }
}
String path2 = Parameters.getPathToSaveHours()+"/"+directoryname+"/RAPORT_"+setNameMounth(datastart)+"_"+datastart.substring(0,4)+"/";
	return path2;
}

//****************************************************************************
public static String setNameMounth(String sdat)
{
	String mount[]={"Styczen","Luty","Marzec","Kwiecieñ","Maj","Czerwiec","Lipiec","Sierpien","Wrzesien","Pazdziernik","Listopad","Grudzien"};
	int num = Integer.parseInt(sdat.substring(5, 7))-1;
	return mount[num];
}


public static String today(){
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	String todaydate = date.format(calendar.getTime());
	return todaydate;
}

public static String time(){
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
	String nowtime = date.format(calendar.getTime());
	return nowtime;
}



}
