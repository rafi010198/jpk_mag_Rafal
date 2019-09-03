package WB;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import XML.jpk_mag_2;
import XML.jpk_mag_main;

public class loop_to_jpk {
	
	public static String directoryname;
	private static JPanel contentPane;
	private JLabel lblWprowadOczekiwanLiczb;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel PaneInfo;

	public static  String datastart;
	 /**
		 * @param datastop	create xml between datastart and datastop 
	*/
	public static  String datastop;

	public static void setdatastart_1(String dat){
		datastart = dat+"-29";
		System.out.println("ustawiono date: "+ datastart);
	}
	
	public static String getdatastart(){
		return datastart;
	}

	public static void setdatastop_1(String dat){
		int year = Integer.parseInt(dat.substring(0, 4));
		int mount = Integer.parseInt(dat.substring(6, 7))-1;
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {

			for(int j=2019;j<2020;j++)
			{
				for(int i=1;i<13;i++)
				{	String dates;
					if(i<10)
					{
						dates=j+"-0"+i;
					}
					else
					{
						dates=j+"-"+i;
					}
					setdatastart_1(dates);
					setdatastop_1(dates);
					
					directoryname = "Magazyn_Zakupy_103";
		//			jpk_mag_main.main();

					directoryname = "Magazyn_MCG_102";
		//			jpk_mag_2.main();
				}
			}	
			
			
		} catch (Exception e) {				// usun potem
		
//		} catch (SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

	}

}