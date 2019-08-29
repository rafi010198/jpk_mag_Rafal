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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.eclipse.birt.report.model.elements.Label;
import org.hsqldb.ExpressionArithmetic;
import org.w3c.dom.DOMException;

import XML.jpk_mag_2;

//import com.itextpdf.text.DocumentException;


//import PDF.cashPlan;
import XML.jpk_mag_main;
import com.toedter.calendar.JDateChooser;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import javax.swing.JList;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

public class mainWindowStart extends JFrame {
	
	public static String directoryname;
	private JPanel contentPane;
	private JLabel lblWprowadOczekiwanLiczb;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel LabelInformation;
	
//---------------------------------------------------------------------------------------------
	public static String info="bnm";
	public static void setinfo(String inf){
		info=inf;
	}
	public void information (){
		Thread th = new Thread(){
			public void run(){
				try {
					for(;;){
						Calendar cal = new GregorianCalendar();
						int sec = cal.get(Calendar.SECOND);

					LabelInformation.setText(sec+"  "+info);
					sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		th.start();
		
	}
	
	/**
	 	* @param datastart	create xml between datastart and datastop 
	 */
	
	public static  String datastart;
	 /**
		 * @param datastop	create xml between datastart and datastop 
	*/
	public static  String datastop;
//******************method for loop*********************************************
/*
	public static void setdatastart(String dat){
		datastart = dat+"-29";
		System.out.println("ustawiono date: "+ datastart);
	}
	
	public static String getdatastart(){
		return datastart;
	}

	public static void setdatastop(String dat){
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
*/
//*****************method for GUI *******************************************
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 549, 420);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//*********************** Program in Gui - you must set date ********************						

		//Image img = new ImageIcon(this.getClass().getResource("/BackgroundImage.jpg")).getImage();
	
		JDateChooser dstart = new JDateChooser();
		dstart.setBounds(48, 131, 114, 20);
		dstart.setToolTipText("yyyy-MM-dd");
		contentPane.add(dstart);

		JDateChooser dstop = new JDateChooser();
		dstop.setBounds(215, 131, 114, 20);
		contentPane.add(dstop);

		JLabel lblDateStart = new JLabel("Date start");
		lblDateStart.setBounds(48, 100, 114, 20);
		lblDateStart.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDateStart);
		
		JLabel lblDateStop = new JLabel("Date stop");
		lblDateStop.setBounds(215, 100, 114, 20);
		lblDateStop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDateStop);
		
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.getComboBox().setFont(new Font("Tahoma", Font.PLAIN, 11));
		monthChooser.setBounds(393, 158, 105, 20);
		monthChooser.setEnabled(false);
		
		JYearChooser yearChooser = new JYearChooser();
		yearChooser.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 12));
		yearChooser.setBounds(426, 127, 47, 20);
		yearChooser.setEnabled(false);
		contentPane.add(yearChooser);
		
		

		JRadioButton rdbtnCreateXmlFrom = new JRadioButton("create xml for one day");
		rdbtnCreateXmlFrom.setBounds(111, 43, 218, 20);
		rdbtnCreateXmlFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dstop.setEnabled(false);
				lblDateStop.setEnabled(false);
				

			}
		});
		buttonGroup.add(rdbtnCreateXmlFrom);
		contentPane.add(rdbtnCreateXmlFrom);
		
		JRadioButton rdbtnCreataXmlFrom = new JRadioButton("create xml for a few days");
		rdbtnCreataXmlFrom.setBounds(111, 17, 218, 23);
		rdbtnCreataXmlFrom.setSelected(true);
		rdbtnCreataXmlFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dstop.setEnabled(true);
				lblDateStop.setEnabled(true);
			}
		});
		buttonGroup.add(rdbtnCreataXmlFrom);
		contentPane.add(rdbtnCreataXmlFrom);
/*		
		JRadioButton rdbtnCreateXmlFor = new JRadioButton("create xml for a whole month ");
		rdbtnCreateXmlFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dstop.setEnabled(false);
				lblDateStop.setEnabled(false);
				dstart.setEnabled(false);
				lblDateStart.setEnabled(false);
				monthChooser.setEnabled(true);
				yearChooser.setEnabled(true);
			}
		});
		buttonGroup.add(rdbtnCreateXmlFor);
		rdbtnCreateXmlFor.setBounds(111, 66, 218, 23);
		contentPane.add(rdbtnCreateXmlFor);
*/		
		JLabel lblCreateXmlFrom = new JLabel("Create xml from magazin:");
		lblCreateXmlFrom.setBounds(124, 173, 158, 20);
		lblCreateXmlFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCreateXmlFrom.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCreateXmlFrom);
		
		JCheckBox chckbxMagazinMain = new JCheckBox("Magazin Main");
		chckbxMagazinMain.setBounds(156, 200, 126, 23);
		chckbxMagazinMain.setSelected(true);
		contentPane.add(chckbxMagazinMain);
		
		JCheckBox chckbxMagazin_2 = new JCheckBox("Magazin 2");
		chckbxMagazin_2.setBounds(156, 226, 126, 23);
		chckbxMagazin_2.setSelected(true);
		contentPane.add(chckbxMagazin_2);
		
		JButton analizaGodzin = new JButton("Start JPK");
		analizaGodzin.setBounds(89, 287, 232, 38);
		analizaGodzin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		analizaGodzin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
			
			System.out.println("Start JPK_MAG");
								
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
									}
									
								catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							} catch (DOMException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("JPK_MAG done");
							System.exit(0);	
									
								}
				
							
			}
		});
		contentPane.add(analizaGodzin);
		
		LabelInformation = new JLabel("cos tam");
		LabelInformation.setBounds(10, 344, 152, 37);
		contentPane.add(LabelInformation);
		
		information();
		

//********************* Program with loop for year and mounth ******************

	
		
//*******************************************************************************
		
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
