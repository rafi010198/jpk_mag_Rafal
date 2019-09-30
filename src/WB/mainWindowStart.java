package WB;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.management.ThreadInfo;
import java.net.SecureCacheResponse;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
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

import com.itextpdf.text.log.SysoLogger;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class mainWindowStart extends JFrame {
	
	public static String directoryname;
	private static JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static JLabel lblchanges;
	private static JButton analizaGodzin;
	private static Thread thinfo;
	private static JLabel lblTime;
	private static long starttime;
	public static JTextArea textArea;
	public static JScrollPane scrollPane_1 ;
	
	
//---------------------------------------------------------------------------------------------
	public static String info="";
	/**this method set information and after show in window in field changes*/
	public static void setinfo(String inf){
		info=inf;
	}
	public static String getinfo(){
		return info;
	}
	public static String saveinfo="";
	/**this method set information and after show in window in field changes*/
	public static void setsaveinfo(String savinf){
		saveinfo=saveinfo+"<br>"+savinf;
	}
	public static String getsaveinfo(){
		return info;
	}
	public static String error="";
	/**this method set errors information and after show in window in field errors*/
	public static void seterror(String err){
		error=error+"\n"+err;
	}
	public static String geterror(){
		return error;
	}
	/**this method set errors information and after show in window in field errors*/
	public static String error_nr="";
	public static void seterror_nr(String errnr){
		error_nr=error_nr+errnr+"\n";
	}
	public static String geterror_nr(){
		return error_nr;
	}
//	-------------------- Thread to clock -------------------------------------------
	public static void clock(){
		thinfo = new Thread(){
			public void run(){
				try {
					
					
					
					while(analizaGodzin.isEnabled()==false)
					{

						lblTime.setText(Parameters.time());
						
					sleep(1000);
					}
					
					
				} catch (Exception e) {
					seterror(e.toString());
					e.printStackTrace();
					
				}
			}
		};
		thinfo.start();
		
	}
	
//	-------------------- My thread to show change in programm ----------------------
	public static void informations (){
		thinfo = new Thread(){
			public void run(){
				try {
					
					int value=0;
					while(analizaGodzin.isEnabled()==false)
					{
						
						lblchanges.setText("<html>"+saveinfo+"<br>- "+info+"</html>");
						textArea.setText(error+"\n"+geterror_nr());
						JScrollBar sb = scrollPane_1.getVerticalScrollBar();	//go to bottom in scroll panel in changes if you don`t scroll
						if(value==sb.getValue())
						{
						sb.setValue( sb.getMaximum());
						}
						value=sb.getValue();
					sleep(3000);
					}
					lblchanges.setText("<html>"+saveinfo+"<br>"+info+"<br><center>Done JPK_MAG</center></html>");
					textArea.setText(error+"\n"+geterror_nr());
					JScrollBar sb = scrollPane_1.getVerticalScrollBar();	//go to bottom in scroll panel in changes
					sb.setValue( sb.getMaximum());
					
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
		System.out.println("ustawiono date start: "+ datastart);
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
					//this print "system.out.pintline" to text file
					PrintStream o = new PrintStream(new File("//192.168.90.203/Common/Programy/JPK/log/log-mianWindowStart.txt")); 
	//				System.setOut(o);
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
		setResizable(false);
		setTitle("JPK_MAG              CUB4U");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 671);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//*********************** Program in Gui - you must set date ********************						

		//Image img = new ImageIcon(this.getClass().getResource("/BackgroundImage.jpg")).getImage();
	
		lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTime.setBounds(431, 21, 79, 23);
		contentPane.add(lblTime);
		
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
		
		JRadioButton rdbtnCreateXmlFor = new JRadioButton("create JPK for a whole month");
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
		rdbtnCreateXmlFor.setBounds(137, 21, 218, 23);
		contentPane.add(rdbtnCreateXmlFor);
		
		JRadioButton rdbtnCreateXmlFrom = new JRadioButton("create JPK for one day");
		rdbtnCreateXmlFrom.setBounds(137, 73, 218, 20);
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
		
		JRadioButton rdbtnCreataXmlFrom = new JRadioButton("create JPK for a few days");
		rdbtnCreataXmlFrom.setBounds(137, 47, 218, 23);
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
	
		JLabel lblCreateXmlFrom = new JLabel("Create JPK from magazin:");
		lblCreateXmlFrom.setBounds(191, 175, 158, 20);
		lblCreateXmlFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCreateXmlFrom.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCreateXmlFrom);
		
		JCheckBox chckbxMagazinMain = new JCheckBox("Magazyn Zakupy (103)");
		chckbxMagazinMain.setBounds(168, 202, 218, 23);
		chckbxMagazinMain.setSelected(true);
		contentPane.add(chckbxMagazinMain);
		
		JCheckBox chckbxMagazin_2 = new JCheckBox("Magazyn cz\u0119\u015Bci gotowych (102)");
		chckbxMagazin_2.setBounds(168, 228, 218, 23);
		chckbxMagazin_2.setSelected(true);
		contentPane.add(chckbxMagazin_2);
		
		JCheckBox chckbxMagazin_105 = new JCheckBox("Magazyn material (105)");
		chckbxMagazin_105.setSelected(true);
		chckbxMagazin_105.setBounds(168, 254, 218, 23);
		contentPane.add(chckbxMagazin_105);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(10, 358, 515, 66);
		
		contentPane.add(scrollPane_1);
		
		lblchanges = new JLabel("");
		scrollPane_1.setViewportView(lblchanges);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 450, 533, 190);
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
			starttime = new Date().getTime();
			System.out.println("Start JPK_MAG");
			analizaGodzin.setText("Start JPK - "+Parameters.time());
			analizaGodzin.setEnabled(false);
				informations ();
				clock();
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
							setinfo("JPK_MAG done");		
								}
				
					}
					
					try {
								Thread.sleep(3001);
							} catch (InterruptedException e) {
								seterror(e.toString());
								e.printStackTrace();
							}
					System.out.println("Kniec: "+Parameters.time());
					analizaGodzin.setText("Start JPK");
					analizaGodzin.setEnabled(true);
					if (error=="" && error_nr=="")				//if isn't errors close the program
					{		System.exit(0);	}
					}
				}).start();
				
			}
		});
		
		contentPane.add(analizaGodzin);
		
		JLabel lblErrors = new JLabel("Errors:");
		lblErrors.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrors.setBounds(246, 435, 46, 14);
		contentPane.add(lblErrors);
		
		JLabel lblChanges = new JLabel("Changes:");
		lblChanges.setHorizontalAlignment(SwingConstants.CENTER);
		lblChanges.setBounds(233, 333, 71, 14);
		contentPane.add(lblChanges);
		
		
		JButton btnOpenDirectory = new JButton("");
		ImageIcon imagei = new ImageIcon(this.getClass().getResource("/folder.png"));
		Image img = imagei.getImage();
		Image newimg = img.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ImageIcon imageIcon = new ImageIcon(newimg);
		btnOpenDirectory.setIcon(imageIcon);
		btnOpenDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().open(new File(Parameters.getPathToSave()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnOpenDirectory.setBounds(453, 206, 40, 45);
		contentPane.add(btnOpenDirectory);
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
