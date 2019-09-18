package WB;
// main jpk_mag
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.lang.management.ThreadInfo;
import java.net.SecureCacheResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.eclipse.birt.report.model.elements.Label;
import org.hsqldb.ExpressionArithmetic;
import org.w3c.dom.DOMException;

import XML.Parameters;
import XML.jpk_mag_105;
import XML.jpk_mag_2;

//import com.itextpdf.text.DocumentException;


//import PDF.cashPlan;
import XML.jpk_mag_main;

import javax.swing.event.AncestorListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.swing.event.AncestorEvent;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import javax.swing.JList;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class mainWindowStart extends JFrame {
	
	public static String directoryname;
	private static JPanel contentPane;
	private JLabel lblWprowadOczekiwanLiczb;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static JLabel lblchanges;
	private static JButton analizaGodzin;
	private static Thread thinfo;
	public static JTextArea textArea;
	
	
//---------------------------------------------------------------------------------------------
	public static String info;
	public static void setinfo(String inf){
		info=inf;
	}
	public static String getinfo(){
		return info;
	}
	public static String error="";
	public static void seterror(String err){
		error=error+"\n"+err;
	}
	public static String geterror(){
		return error;
	}
	public static String error_nr="";
	public static void seterror_nr(String errnr){
		error_nr=error_nr+errnr+"\n";
	}
	public static String geterror_nr(){
		return error_nr;
	}

//	-------------------- My thread to show change in programm ----------------------
	public static void informations (){
		thinfo = new Thread(){
			public void run(){
				try {
					
					
					
					while(analizaGodzin.isEnabled()==false)
					{
						lblchanges.setText(info);
						textArea.setText(error+"\n"+geterror_nr());
					sleep(2000);
					}
					lblchanges.setText(info);
					textArea.setText(error+"\n"+geterror_nr());
					
				} catch (Exception e) {
					seterror(e.toString());
					e.printStackTrace();
					
				}
			}
		};
		thinfo.start();
		
	}
	
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
//---------------------------------------------------------------------------------------------
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindowStart frame = new mainWindowStart();
					frame.setVisible(true);
					
				} catch (Exception e) {
					seterror(e.toString());
					e.printStackTrace();
				}
			}
		});
	}
	

	
	
	
	
	
	/**
	 * Create the frame.
	 * @return 
	 */
	public mainWindowStart() {
		setTitle("JPK_MAG              CUB4U");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 549, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//*********************** Program in Gui - you must set date ********************						

		//Image img = new ImageIcon(this.getClass().getResource("/BackgroundImage.jpg")).getImage();
	
		JDateChooser dstart = new JDateChooser();
		dstart.setEnabled(false);
		dstart.setBounds(215, 148, 114, 20);
		dstart.setToolTipText("yyyy-MM-dd");
		contentPane.add(dstart);

		JDateChooser dstop = new JDateChooser();
		dstop.setEnabled(false);
		dstop.setBounds(373, 148, 114, 20);
		contentPane.add(dstop);

		JLabel lblDateStart = new JLabel("Date start");
		lblDateStart.setEnabled(false);
		lblDateStart.setBounds(215, 117, 114, 20);
		lblDateStart.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDateStart);
		
		JLabel lblDateStop = new JLabel("Date stop");
		lblDateStop.setEnabled(false);
		lblDateStop.setBounds(373, 117, 114, 20);
		lblDateStop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDateStop);
		
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.getComboBox().setFont(new Font("Tahoma", Font.PLAIN, 12));
		monthChooser.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 12));
		monthChooser.setBounds(38, 147, 105, 20);
		contentPane.add(monthChooser);
		
		JYearChooser yearChooser = new JYearChooser();
		yearChooser.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 11));
		yearChooser.setBounds(62, 117, 47, 20);
		contentPane.add(yearChooser);
		
		JRadioButton rdbtnCreateXmlFor = new JRadioButton("create xml for a whole month");
		rdbtnCreateXmlFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dstop.setEnabled(false);
				lblDateStop.setEnabled(false);
				dstart.setEnabled(false);
				lblDateStart.setEnabled(false);
				monthChooser.setEnabled(true);
				yearChooser.setEnabled(true);
			}
		});
		rdbtnCreateXmlFor.setSelected(true);
		buttonGroup.add(rdbtnCreateXmlFor);
		rdbtnCreateXmlFor.setBounds(111, 17, 218, 23);
		contentPane.add(rdbtnCreateXmlFor);
		
		JRadioButton rdbtnCreateXmlFrom = new JRadioButton("create xml for one day");
		rdbtnCreateXmlFrom.setBounds(111, 69, 218, 20);
		rdbtnCreateXmlFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dstop.setEnabled(false);
				lblDateStop.setEnabled(false);
				dstart.setEnabled(true);
				lblDateStart.setEnabled(true);
				monthChooser.setEnabled(false);
				yearChooser.setEnabled(false);

			}
		});
		buttonGroup.add(rdbtnCreateXmlFrom);
		contentPane.add(rdbtnCreateXmlFrom);
		
		JRadioButton rdbtnCreataXmlFrom = new JRadioButton("create xml for a few days");
		rdbtnCreataXmlFrom.setBounds(111, 43, 218, 23);
		rdbtnCreataXmlFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dstop.setEnabled(true);
				lblDateStop.setEnabled(true);
				dstart.setEnabled(true);
				lblDateStart.setEnabled(true);
				monthChooser.setEnabled(false);
				yearChooser.setEnabled(false);
			}
		});
		buttonGroup.add(rdbtnCreataXmlFrom);
		contentPane.add(rdbtnCreataXmlFrom);
	
		JLabel lblCreateXmlFrom = new JLabel("Create xml from magazin:");
		lblCreateXmlFrom.setBounds(191, 175, 158, 20);
		lblCreateXmlFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCreateXmlFrom.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCreateXmlFrom);
		
		JCheckBox chckbxMagazinMain = new JCheckBox("Magazin Main");
		chckbxMagazinMain.setBounds(223, 202, 126, 23);
		chckbxMagazinMain.setSelected(true);
		contentPane.add(chckbxMagazinMain);
		
		JCheckBox chckbxMagazin_2 = new JCheckBox("Magazin 2");
		chckbxMagazin_2.setBounds(223, 228, 126, 23);
		chckbxMagazin_2.setSelected(true);
		contentPane.add(chckbxMagazin_2);
		
		JCheckBox chckbxMagazin_105 = new JCheckBox("Magazin 105");
		chckbxMagazin_105.setSelected(true);
		chckbxMagazin_105.setBounds(223, 254, 97, 23);
		contentPane.add(chckbxMagazin_105);
		
		lblchanges = new JLabel("");
		lblchanges.setBounds(38, 352, 449, 14);
		contentPane.add(lblchanges);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 405, 533, 190);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setBackground(SystemColor.control);
		//textArea.setLineWrap(true);
	
	    
		analizaGodzin = new JButton("Start JPK");
		analizaGodzin.setBounds(38, 284, 449, 38);
		analizaGodzin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		analizaGodzin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
			
			System.out.println("Start JPK_MAG");
			analizaGodzin.setText("Start JPK - "+Parameters.time());
			analizaGodzin.setEnabled(false);
				informations ();
				System.out.println(Parameters.time());
				new Thread(new Runnable() {			//Thread to show changes in program in GUI
					
					@Override
					public void run() {
						
					if(rdbtnCreateXmlFor.isSelected())    // if you choose the xml creator for whole month
					{	
						String datum;
						int month=monthChooser.getMonth()+1;
						if(month<10)
						{
							datum=yearChooser.getYear()+"-0"+month;
						}
						else{
							datum=yearChooser.getYear()+"-"+month;
						}
						System.out.println(datum);
						setdatastart_1(datum);
						setdatastop_1(datum);
						
						try {
						if(chckbxMagazinMain.isSelected())
						{
							directoryname = "Magazyn_Zakupy_103";
							jpk_mag_main.main();
						}
						if(chckbxMagazin_2.isSelected())
						{
							directoryname = "Magazyn_MCG_102";
								jpk_mag_2.main();
							
						}
						if(chckbxMagazin_105.isSelected())
						{
							directoryname = "Magazyn_Material_105";
							jpk_mag_105.main();
						}
						
						} catch (SQLException | ParseException e) {
							seterror(e.toString());
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
							
					}
					else					// if you choose the xml creator for your own date
					{
			
			
								if(dstart.getDate()==null)
								{
									JOptionPane.showMessageDialog(contentPane, "You don't set start date");
								}
								else
								{
									
									
									try {
										try {
								
									setdatastart(dstart);
					
									if(dstop.isEnabled()==false||dstop.getDate()==null){
										setdatastop(dstart);
										
									}
									else{	
										setdatastop(dstop);
										
									}
									if(chckbxMagazinMain.isSelected())
									{
										directoryname = "Magazyn_Zakupy_103";
										jpk_mag_main.main();
									}
									if(chckbxMagazin_2.isSelected())
									{
										directoryname = "Magazyn_MCG_102";
										jpk_mag_2.main();	
										
									}
									if(chckbxMagazin_105.isSelected())
									{
										directoryname = "Magazyn_Material_105";
										jpk_mag_105.main();
									}
									}
									
								catch (SQLException e) {
									seterror(e.toString());
									e.printStackTrace();
								} catch (ParseException e) {
									seterror(e.toString());
									e.printStackTrace();
								}
								
							} catch (DOMException e) {
								seterror(e.toString());
								e.printStackTrace();
							}
							System.out.println("JPK_MAG done");
									
								}
				
					}
					
					try {
								Thread.sleep(2001);
							} catch (InterruptedException e) {
								seterror(e.toString());
								e.printStackTrace();
							}
					System.out.println("Kniec: "+Parameters.time());
					analizaGodzin.setText("Start JPK");
					analizaGodzin.setEnabled(true);
					if (error=="")				//if isn't errors close the program
					{		System.exit(0);	}
					}
				}).start();
				
			}
		});
		
		contentPane.add(analizaGodzin);
		
		JLabel lblErrors = new JLabel("Errors:");
		lblErrors.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrors.setBounds(245, 377, 46, 14);
		contentPane.add(lblErrors);
		
		JLabel lblChanges = new JLabel("Changes:");
		lblChanges.setHorizontalAlignment(SwingConstants.CENTER);
		lblChanges.setBounds(233, 333, 71, 14);
		contentPane.add(lblChanges);
		

		
		
		
		
		
		
	}
		
		
			
	private static boolean checkDatePattern(String data) {
	    try {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        format.parse(data);
	        return true;
	    } catch (ParseException e) {
	        return false;
	    }
	}
}
