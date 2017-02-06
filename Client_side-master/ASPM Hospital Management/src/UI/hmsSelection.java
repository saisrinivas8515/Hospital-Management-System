package UI;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;

public class hmsSelection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public hmsSelection(String s) {

		System.out.println("You are " + s);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 355, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		if(s == "Manager"){
		final JComboBox comboBox = new JComboBox(
				new String[] { "Book an appointment", "Add a patient", "Add a doctor", "Discharge a patient", "See/Edit/Delete doctor", "See/Edit/Delete patient", "See/Edit/Delete appointment"});
		comboBox.setBounds(164, 29, 155, 23);
		contentPane.add(comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selcVal = comboBox.getSelectedIndex();

				switch (selcVal) {
				case 0:
					
					UI.Book_app frame = new UI.Book_app();
					frame.setVisible(true);
					break;
				case 1:
					UI.PatientInfo frame2 = new UI.PatientInfo();
					frame2.setVisible(true);
					break;
				case 2:
					UI.Doctors_info frame3 = new UI.Doctors_info();
					frame3.setVisible(true);
					break;
				case 3:
					UI.discharge frame4 = new UI.discharge();
					frame4.setVisible(true);
					break;
					
				case 4:
					UI.Edit_doctors_info frame5 = new UI.Edit_doctors_info();
					frame5.setVisible(true);
					break;
					
				case 5:
					UI.Edit_patientInfo frame6 = new UI.Edit_patientInfo();
					frame6.setVisible(true);
					break;
					
				case 6:
					UI.Edit_appointment frame7 = new UI.Edit_appointment();
					frame7.setVisible(true);
					break;
					
				default:
					System.out.println("Error message");
				}
				dispose();
			}
		

		});
		
		btnSubmit.setBounds(20, 68, 84, 23);
		contentPane.add(btnSubmit);
		}
		
		else if(s == "Doctor"){
		final JComboBox comboBox = new JComboBox(
				new String[] { "See/Edit/Delete patient", "See/Edit/Delete appointment" });
		comboBox.setBounds(164, 29, 155, 23);
		contentPane.add(comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selcVal = comboBox.getSelectedIndex();

				switch (selcVal) {
				case 0:
					UI.Edit_patientInfo frame6 = new UI.Edit_patientInfo();
					frame6.setVisible(true);
					break;
				case 1:
					DocViewAppointments frame7=new DocViewAppointments();
					frame7.setVisible(true);
					break;

				default:
					System.out.println("Error message");
				}
				dispose();
			}
		

		});
		
		btnSubmit.setBounds(20, 68, 84, 23);
		contentPane.add(btnSubmit);
		}
		
		
		else if(s == "Patient"){
		final JComboBox comboBox = new JComboBox(
				new String[] { "Book an appointment", "See/Edit/Delete appointment" });
		comboBox.setBounds(164, 29, 155, 23);
		contentPane.add(comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selcVal = comboBox.getSelectedIndex();

				switch (selcVal) {
				case 0:
					UI.Book_app frame = new UI.Book_app();
					frame.setVisible(true);
					break;
				case 1:
					UI.Edit_appointment frame7 = new UI.Edit_appointment();
					frame7.setVisible(true);
					break;
				default:
					System.out.println("Error message");
				}
				dispose();
			}
		

		});
		
		btnSubmit.setBounds(20, 68, 84, 23);
		contentPane.add(btnSubmit);
		}

		JLabel lblSelectAnOption = new JLabel("Please select an option");
		lblSelectAnOption.setBounds(20, 24, 135, 33);
		contentPane.add(lblSelectAnOption);
	}
}
