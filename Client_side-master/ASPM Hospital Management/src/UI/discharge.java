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
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;

import org.json.simple.JSONObject;

public class discharge extends JFrame{
	private JTextField desc;
	private JTextField patID;
	private JTextField docID;
	private JPanel contentPane;
	public JDateChooser inDate, outDate, disDate;
	public String ind, outd, disd;
	private JTextField billing;
	
	public discharge() {
		final ServerCode sc = new ServerCode();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);
		
		JLabel lblInDate = new JLabel("In date");
		lblInDate.setBounds(10, 20, 46, 14);
		getContentPane().add(lblInDate);
		
		inDate = new JDateChooser();
		inDate.setBounds(99, 14, 112, 20);
		getContentPane().add(inDate);
		
		JLabel lblOutDate = new JLabel("Out date");
		lblOutDate.setBounds(10, 57, 46, 14);
		getContentPane().add(lblOutDate);
		
		outDate = new JDateChooser();
		outDate.setBounds(99, 51, 112, 20);
		getContentPane().add(outDate);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 137, 67, 14);
		getContentPane().add(lblDescription);
		
		desc = new JTextField();
		desc.setBounds(99, 137, 142, 95);
		getContentPane().add(desc);
		desc.setColumns(10);
		
		JLabel lblPatientId = new JLabel("Patient ID");
		lblPatientId.setBounds(264, 17, 67, 14);
		getContentPane().add(lblPatientId);
		
		patID = new JTextField();
		patID.setBounds(327, 14, 86, 20);
		getContentPane().add(patID);
		patID.setColumns(10);
		
		JLabel lblDoctorId = new JLabel("Doctor ID");
		lblDoctorId.setBounds(264, 51, 67, 14);
		getContentPane().add(lblDoctorId);
		
		docID = new JTextField();
		docID.setColumns(10);
		docID.setBounds(327, 48, 86, 20);
		getContentPane().add(docID);
		
		disDate = new JDateChooser();
		disDate.setBounds(99, 94, 112, 20);
		getContentPane().add(disDate);
		
		JLabel lblDischargeDate = new JLabel("Discharge date");
		lblDischargeDate.setBounds(10, 100, 86, 14);
		getContentPane().add(lblDischargeDate);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 243, 89, 23);
		getContentPane().add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					SimpleDateFormat dateformat = new SimpleDateFormat(
							"dd/MM/yyyy");
					Date in = inDate.getDate();
					Date out = outDate.getDate();
					Date dis = disDate.getDate();

					// If date is not given throw an exception to the user
					if (in == null || out == null || dis == null || desc.getText().equals("")
							|| patID.getText().equals("")
							|| docID.getText().equals(""))
						throw new RuntimeException();

					else {
							ind = dateformat.format(in);
							outd = dateformat.format(out);
							disd = dateformat.format(dis);
							//System.out.println(age);

							JSONObject obj = new JSONObject();
							obj.put("indate", ind);
							obj.put("outdate", outd);
							obj.put("description", desc.getText());
							obj.put("datetime", disd);
							obj.put("patientid", patID.getText());
							obj.put("doctorid", docID.getText());
							obj.put("billing", billing.getText());
							
							System.out.println(obj);
							sc.PostData(obj,
									"http://localhost:8080/Server/Test","discharge","Manager");
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
		btnReset.setBounds(324, 243, 89, 23);
		getContentPane().add(btnReset);
		
		JLabel lblBilling = new JLabel("Billing");
		lblBilling.setBounds(264, 100, 46, 14);
		contentPane.add(lblBilling);
		
		billing = new JTextField();
		billing.setBounds(327, 94, 86, 20);
		contentPane.add(billing);
		billing.setColumns(10);
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desc.setText("");
				patID.setText("");
				docID.setText("");
				billing.setText("");
				inDate.setDate(null);
				outDate.setDate(null);
				disDate.setDate(null);
			}
		});
	}
}
