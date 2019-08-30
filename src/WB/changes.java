package WB;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class changes extends JFrame {

	private JPanel contentPane;
	public static JLabel lblZmianyWProgramie;
	
	public static void information (){
		Thread th = new Thread(){
			public void run(){
				try {
					
		//			changes.main();
					
					for(;;){
						Calendar cal = new GregorianCalendar();
						int sec = cal.get(Calendar.SECOND);
						System.out.println("W¹tek czas "+sec);
						
						lblZmianyWProgramie.setText("khbvl");
						try {
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					
					sleep(5000);
					}


				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		th.start();
		
	}
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					changes frame = new changes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public changes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblZmianyWProgramie = new JLabel("zmiany w programie");
		lblZmianyWProgramie.setHorizontalAlignment(SwingConstants.CENTER);
		lblZmianyWProgramie.setBounds(58, 84, 305, 77);
		contentPane.add(lblZmianyWProgramie);
	}

}
