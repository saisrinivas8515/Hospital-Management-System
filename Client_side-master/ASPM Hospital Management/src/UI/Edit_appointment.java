package UI;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

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


public class Edit_appointment extends JFrame{
	private JTextField docID;
	private JTextField patID;
	private JTextField desc;
	private JPanel contentPane;
	public JDateChooser dateChooser;
	public JRadioButton radioButton_3;
	public String doctorID, patientID, description, day;
	public String[] app_lists = {  };
	
	public Edit_appointment() {
		final ServerCode sc = new ServerCode();
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 350);
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
		
		ButtonGroup bg2 = new ButtonGroup();
		
		final JRadioButton radioButton = new JRadioButton("09:00");
		radioButton.setBounds(10, 198, 64, 23);
		getContentPane().add(radioButton);
		radioButton.setActionCommand("09:00");
		
		final JRadioButton radioButton_1 = new JRadioButton("10:00");
		radioButton_1.setBounds(73, 198, 64, 23);
		getContentPane().add(radioButton_1);
		radioButton_1.setActionCommand("10:00");
		
		final JRadioButton radioButton_2 = new JRadioButton("11:00");
		radioButton_2.setBounds(139, 198, 64, 23);
		getContentPane().add(radioButton_2);
		radioButton_2.setActionCommand("11:00");
		
		final JRadioButton radioButton_3 = new JRadioButton("12:00");
		radioButton_3.setSelected(true);
		radioButton_3.setBounds(205, 198, 64, 23);
		getContentPane().add(radioButton_3);
		radioButton_3.setActionCommand("12:00");
		
		final JRadioButton radioButton_4 = new JRadioButton("13:00");
		radioButton_4.setBounds(271, 198, 64, 23);
		getContentPane().add(radioButton_4);
		radioButton_4.setActionCommand("13:00");
		
		final JRadioButton radioButton_5 = new JRadioButton("17:00");
		radioButton_5.setBounds(10, 224, 64, 23);
		getContentPane().add(radioButton_5);
		radioButton_5.setActionCommand("17:00");
		
		final JRadioButton radioButton_6 = new JRadioButton("18:00");
		radioButton_6.setBounds(73, 224, 64, 23);
		getContentPane().add(radioButton_6);
		radioButton_6.setActionCommand("18:00");
		
		final JRadioButton radioButton_7 = new JRadioButton("19:00");
		radioButton_7.setBounds(139, 224, 64, 23);
		getContentPane().add(radioButton_7);
		radioButton_7.setActionCommand("19:00");
		
		final JRadioButton radioButton_8 = new JRadioButton("20:00");
		radioButton_8.setBounds(205, 224, 64, 23);
		getContentPane().add(radioButton_8);
		radioButton_8.setActionCommand("20:00");
		
		final JRadioButton radioButton_9 = new JRadioButton("21:00");
		radioButton_9.setBounds(271, 224, 64, 23);
		getContentPane().add(radioButton_9);
		radioButton_9.setActionCommand("21:00");
		
		bg2.add(radioButton);
		bg2.add(radioButton_1);
		bg2.add(radioButton_2);
		bg2.add(radioButton_3);
		bg2.add(radioButton_4);
		bg2.add(radioButton_5);
		bg2.add(radioButton_6);
		bg2.add(radioButton_7);
		bg2.add(radioButton_8);
		bg2.add(radioButton_9);
		
		JButton btnSubmit = new JButton("Edit");
		btnSubmit.setBounds(10, 277, 89, 23);
		contentPane.add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// Getting the values written in text fields
					doctorID = docID.getText();
					patientID = patID.getText();
					description = desc.getText();
					

					SimpleDateFormat dateformat = new SimpleDateFormat(
							"dd/MM/yyyy");
					Date d = dateChooser.getDate();

					// If date is not given throw an exception to the user
					if (doctorID.equals("") || patientID.equals("")
							|| description.equals("")
							|| d == null)
						throw new RuntimeException();

					else {
						day = dateformat.format(d);
						//System.out.println(age);

						// Encoding values into a JSON object
						JSONObject obj = new JSONObject();

						obj.put("doctorid", doctorID);
						obj.put("patientid", patientID);
						obj.put("day", day);
						if (radioButton.isSelected())
							obj.put("datetimeslot", radioButton.getActionCommand());
						else if (radioButton_1.isSelected())
							obj.put("datetimeslot",
									radioButton_1.getActionCommand());
						else if (radioButton_2.isSelected())
							obj.put("datetimeslot",
									radioButton_2.getActionCommand());
						else if (radioButton_3.isSelected())
							obj.put("datetimeslot",
									radioButton_3.getActionCommand());
						else if (radioButton_4.isSelected())
							obj.put("datetimeslot",
									radioButton_4.getActionCommand());
						else if (radioButton_5.isSelected())
							obj.put("datetimeslot",
									radioButton_5.getActionCommand());
						else if (radioButton_6.isSelected())
							obj.put("datetimeslot",
									radioButton_6.getActionCommand());
						else if (radioButton_7.isSelected())
							obj.put("datetimeslot",
									radioButton_7.getActionCommand());
						else if (radioButton_8.isSelected())
							obj.put("datetimeslot",
									radioButton_8.getActionCommand());
						else if (radioButton_9.isSelected())
							obj.put("datetimeslot",
									radioButton_9.getActionCommand());

						// Printing JSON object in string format for testing
						System.out.println(obj);
						sc.PostData(obj, "http://localhost:8080/Server/Test","Edit_appointment","Manager");
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
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(274, 277, 89, 23);
		contentPane.add(btnReset);
		
		JComboBox appList = new JComboBox(app_lists);
		appList.setBounds(238, 11, 125, 20);
		contentPane.add(appList);
		
		JButton btnNewButton = new JButton("Show appointment");
		btnNewButton.setBounds(238, 36, 126, 23);
		contentPane.add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(143, 277, 89, 23);
		contentPane.add(btnDelete);
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				docID.setText("");
				patID.setText("");
				desc.setText("");
				radioButton_3.doClick();
				dateChooser.setDate(null);
			}
		});
		
		
		
	}
}
