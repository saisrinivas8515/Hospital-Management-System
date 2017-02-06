package UI;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import javax.swing.JRadioButton;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Book_app extends JFrame{
	private JTextField docID;
	private JTextField patID;
	private JTextField desc;
	private JPanel contentPane;
	public JDateChooser dateChooser;
	public JRadioButton radioButton_3;
	public String doctorID, patientID, description, day;
	final JRadioButton radioButton[]=new JRadioButton[10];
	public Book_app() {
		final ServerCode sc = new ServerCode();
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Doctor ID");
		lblNewLabel.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNewLabel);
		
		docID = new JTextField();
		docID.setBounds(73, 8, 126, 20);
		getContentPane().add(docID);
		docID.setColumns(10);
		
		JLabel lblPatientId = new JLabel("Patient ID");
		lblPatientId.setBounds(10, 40, 64, 14);
		getContentPane().add(lblPatientId);
		
		patID = new JTextField();
		patID.setColumns(10);
		patID.setBounds(73, 37, 126, 20);
		getContentPane().add(patID);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 65, 46, 14);
		getContentPane().add(lblDate);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(73, 65, 126, 20);
		getContentPane().add(dateChooser);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 96, 75, 14);
		getContentPane().add(lblDescription);
		
		desc = new JTextField();
		desc.setBounds(73, 96, 126, 85);
		getContentPane().add(desc);
		desc.setColumns(10);
		
     		
		
		final JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 198, 89, 23);
		contentPane.add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					doctorID = docID.getText();
					patientID = patID.getText();
					description = desc.getText();
					SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
					Date d = dateChooser.getDate();
                    //System.out.println("info: \n"+" did= "+doctorID+" pid= "+patientID+" dsec: "+description+" date: "+d);
					// If date is not given throw an exception to the user
					if (doctorID.equals("") || patientID.equals("")
							|| description.equals("")
							|| d == null)
						throw new RuntimeException();

					else {
						day = dateformat.format(d);
						JSONObject obj = new JSONObject();
						obj.put("doctorid", doctorID);
						obj.put("patientid", patientID);
						obj.put("day", day);
						for(JRadioButton rb: radioButton)
						{
							if(rb.isSelected())
								obj.put("datetimeslot", rb.getActionCommand());
						}
						int res=sc.PostData(obj, "http://localhost:8080/Server/Test","Book_app","Manager");
						if(res!=0)
						{JOptionPane.showMessageDialog(getParent(),
								"sucessfull");
						dispose();
						UI.Book_app frame = new UI.Book_app();
						frame.setVisible(true);
						}
					}
				}

				catch (RuntimeException e5) {
					// JOptionPane.showMessageDialog(null, e5, "Error",
					// JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(getParent(),
							"Enter the missing informations!");

				} catch (Exception e1) {
					// System.out.println("Exception caught:" + e1);
					JOptionPane.showMessageDialog(getParent(),
							"Exception caught: " + e1);
				}
			
			}
		});
		
		
		
		final JButton btnReset = new JButton("Reset");
		btnReset.setBounds(100, 198, 89, 23);
		contentPane.add(btnReset);
		
		
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				docID.setText("");
				patID.setText("");
				desc.setText("");
				radioButton_3.doClick();
				dateChooser.setDate(null);
			}
		});
		
		final ButtonGroup bg2 = new ButtonGroup();
		JButton btnCheck = new JButton("check");
		btnCheck.setBounds(205, 65, 89, 23);
		btnCheck.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String responseSting=sc.getData("http://localhost:8080/Server/Test");
				JSONParser par = new JSONParser();
				
				JSONObject jsonChildObject;
				try {
					JSONObject response = (JSONObject) par.parse(/*response string*/"{\"1300\":\"1\",\"1430\":\"1\",\"1500\":\"1\",\"1600\":\"1\",\"1700\":\"1\",\"1800\":\"1\"}");
					int i=0,x=10,y=198;
					for (Object key : response.keySet()) {
				        String keyStr = (String)key;
				        Object keyvalue = response.get(keyStr);
					    if(keyvalue.equals("1"))
					    {
					    	radioButton[i] = new JRadioButton(keyStr);					    		
					    	if(i>4)
						    	{System.out.println("nfjennjanefjwnaiwnfajrnhbvyrbajvgwdv");
					    		y=224;x=10;}
					    	radioButton[i].setBounds(x, y, 64, 23);
					    	getContentPane().add(radioButton[i]);
					    	bg2.add(radioButton[i]);
							x=x+59;
					    	i++;
					    }
 					} 
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				btnSubmit.setBounds(10, 277, 89, 23);
				btnReset.setBounds(246, 277, 89, 23);
			}
			
		});
		contentPane.add(btnCheck);
		
		
	}
}
