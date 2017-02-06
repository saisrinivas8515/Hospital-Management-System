package UI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import org.json.simple.JSONObject;

import com.toedter.calendar.JDateChooser;

import UI.ServerCode;
import UI.email_validator;

import java.awt.Font;
import java.awt.TextArea;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Object;

public class PatientInfo extends JFrame {

	private JPanel contentPane;
	private JTextField txtFN;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JTextField txtLN;
	private JLabel lblBirthDate;
	private JLabel lblEmailId;
	private JTextField txtEid;
	private JLabel lblAddress;
	private JTextArea txtaAdd1, textInfo1, textInfo2;
	private JTextField txtH;
	private JTextField txtW;
	private JTextField txtO;
	private JLabel lblRefered;
	private JTextField txtRef;
	public String age;
	JDateChooser dateChooser = new JDateChooser();
	private email_validator eval;
	private JTextField textMed1;
	private JTextField textUse1;
	private JTextField textMed2;
	private JTextField textUse2;
	public File xray;
	public String imageDataString;
	
	public void getStringFromBitmap() {
		try {			
			// Reading a Image file from file system
			FileInputStream imageInFile = new FileInputStream(xray);
			byte imageData[] = new byte[(int) xray.length()];
			imageInFile.read(imageData);

			// Converting Image byte array into Base64 String
			imageDataString = encodeImage(imageData);

			// Converting a Base64 String into Image byte array
			//byte[] imageByteArray = decodeImage(imageDataString);

			/////////////

			// Write a image byte array into file system
			//FileOutputStream imageOutFile = new FileOutputStream(
					//"/Users/jeeva/Pictures/wallpapers/water-drop-after-convert.jpg");

			//imageOutFile.write(imageByteArray);

			imageInFile.close();
			//imageOutFile.close();
			
			System.out.println("Image Successfully Manipulated!");
			
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
	}

	/**
	 * Encodes the byte array into base64 string
	 *
	 * @param imageByteArray - byte array
	 * @return String a {@link java.lang.String}
	 */
	public static String encodeImage(byte[] imageByteArray) {
		return Base64.encodeBase64URLSafeString(imageByteArray);
	}

	/**
	 * Decodes the base64 string into byte array
	 *
	 * @param imageDataString - a {@link java.lang.String}
	 * @return byte array
	 */
	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}
		 
	
	
	public PatientInfo() {
		final ServerCode sc = new ServerCode();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 647);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtFN = new JTextField();
		txtFN.setText(" ");
		txtFN.setBounds(122, 14, 154, 27);
		contentPane.add(txtFN);
		txtFN.setColumns(10);

		// prevent numerical values
		txtFN.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < 'A') || (c > 'Z')) && ((c < 'a') || (c > 'z'))
						&& (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
					getToolkit().beep();
				}
			}
		});

		
final FileFilter imageFilter = new FileNameExtensionFilter(
			    		    "Image files", ImageIO.getReaderFileSuffixes());
			
		JButton btnFile = new JButton("X-RAY");
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    JFileChooser chooser = new JFileChooser();
			    chooser.setAcceptAllFileFilterUsed(false);
			    chooser.setFileFilter(imageFilter);
			    int returnVal = chooser.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose this file: " +
			            chooser.getSelectedFile().getName());
   
			       xray = chooser.getSelectedFile();
			       getStringFromBitmap();
			    }
			}
		});
		btnFile.setBounds(196, 574, 126, 23);
		contentPane.add(btnFile);
	   

		
		
	 
		JButton btnSend = new JButton("Submit");
		btnSend.setBounds(497, 574, 106, 22);
		contentPane.add(btnSend);

		lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(26, 11, 186, 33);
		contentPane.add(lblFirstName);

		lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(26, 52, 186, 33);
		contentPane.add(lblLastName);

		txtLN = new JTextField();
		txtLN.setText(" ");
		txtLN.setColumns(10);
		txtLN.setBounds(122, 58, 154, 27);
		contentPane.add(txtLN);
		txtLN.setTransferHandler(null); // disable copy&paste actions

		// prevent numerical values
		txtLN.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < 'A') || (c > 'Z')) && ((c < 'a') || (c > 'z'))
						&& (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
					getToolkit().beep();
				}
			}
		});

		lblBirthDate = new JLabel("Birth Date");
		lblBirthDate.setBounds(26, 96, 154, 33);
		contentPane.add(lblBirthDate);

		lblEmailId = new JLabel("E-mail ID");
		lblEmailId.setBounds(356, 52, 115, 33);
		contentPane.add(lblEmailId);

		txtEid = new JTextField();
		txtEid.setText(" ");
		txtEid.setColumns(10);
		txtEid.setBounds(449, 58, 154, 27);
		contentPane.add(txtEid);
		txtEid.setTransferHandler(null); // disable copy&paste actions

		lblAddress = new JLabel("Address");
		lblAddress.setBounds(26, 229, 39, 33);
		contentPane.add(lblAddress);

		txtaAdd1 = new JTextArea();
		txtaAdd1.setBounds(122, 233, 154, 111);
		contentPane.add(txtaAdd1);

		ButtonGroup bg = new ButtonGroup();
		final JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setSelected(true);
		rdbtnMale.setActionCommand("Male");
		rdbtnMale.setBounds(452, 84, 50, 41);
		contentPane.add(rdbtnMale);
		rdbtnMale.doClick();

		final JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setActionCommand("Female");
		rdbtnFemale.setBounds(511, 84, 136, 41);
		contentPane.add(rdbtnFemale);
		bg.add(rdbtnMale);
		bg.add(rdbtnFemale);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(356, 88, 115, 33);
		contentPane.add(lblGender);

		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(356, 121, 115, 33);
		contentPane.add(lblHeight);

		txtH = new JTextField();
		txtH.setBounds(449, 127, 64, 22);
		contentPane.add(txtH);
		txtH.setColumns(10);
		txtH.setTransferHandler(null); // disable copy&paste actions

		// prevent text values
		txtH.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
					getToolkit().beep();
				}
			}
		});

		txtW = new JTextField();
		txtW.setColumns(10);
		txtW.setBounds(449, 158, 64, 22);
		contentPane.add(txtW);
		txtW.setTransferHandler(null); // disable copy&paste actions

		// prevent text values
		txtW.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
					getToolkit().beep();
				}
			}
		});

		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(356, 152, 115, 33);
		contentPane.add(lblWeight);

		JLabel lblBloodGroup = new JLabel("Blood Group");
		lblBloodGroup.setBounds(26, 134, 71, 33);
		contentPane.add(lblBloodGroup);

		JLabel lblCms = new JLabel("Cm");
		lblCms.setBounds(526, 121, 64, 33);
		contentPane.add(lblCms);

		JLabel lblKgs = new JLabel("Kg");
		lblKgs.setBounds(526, 153, 50, 33);
		contentPane.add(lblKgs);

		final JComboBox comboBldGrp = new JComboBox(
				new String[] { "O +ve", "O -ve", "A +ve", "A -ve", "B +ve",
						"B -ve", "AB +ve", "AB -ve" });
		comboBldGrp.setBounds(122, 140, 71, 20);
		contentPane.add(comboBldGrp);

		JLabel lblOccupation = new JLabel("Occupation");
		lblOccupation.setBounds(356, 190, 145, 33);
		contentPane.add(lblOccupation);

		txtO = new JTextField();
		txtO.setText(" ");
		txtO.setColumns(10);
		txtO.setBounds(449, 190, 154, 27);
		contentPane.add(txtO);
		txtO.setTransferHandler(null); // disable copy&paste actions

		// prevent numerical values
		txtO.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < 'A') || (c > 'Z')) && ((c < 'a') || (c > 'z'))
						&& (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
					getToolkit().beep();
				}
			}
		});

		JLabel lblMaritalStatus = new JLabel("Marital Status");
		lblMaritalStatus.setBounds(356, 11, 121, 33);
		contentPane.add(lblMaritalStatus);

		final JComboBox comboMarSts = new JComboBox(new String[] { "Married",
				"Divorced", "Widowed" });
		comboMarSts.setBounds(449, 17, 121, 22);
		comboMarSts.setEnabled(true);
		contentPane.add(comboMarSts);

		lblRefered = new JLabel("Refered");
		lblRefered.setBounds(26, 176, 57, 33);
		contentPane.add(lblRefered);

		txtRef = new JTextField();
		txtRef.setText(" ");
		txtRef.setColumns(10);
		txtRef.setBounds(122, 176, 154, 27);
		contentPane.add(txtRef);
		txtRef.setTransferHandler(null); // disable copy&paste actions

		// prevent numerical values
		txtRef.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < 'A') || (c > 'Z')) && ((c < 'a') || (c > 'z'))
						&& (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
					getToolkit().beep();
				}
			}
		});

		final JTextArea txtaAdd2 = new JTextArea();
		txtaAdd2.setBounds(449, 233, 154, 111);
		contentPane.add(txtaAdd2);

		JLabel label = new JLabel("Address");
		label.setBounds(356, 229, 39, 33);
		contentPane.add(label);

		dateChooser.setBounds(122, 96, 154, 27);
		contentPane.add(dateChooser);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtFN.setText("");
				txtLN.setText("");
				txtH.setText("");
				txtW.setText("");
				txtRef.setText("");
				txtEid.setText("");
				txtO.setText("");
				txtaAdd1.setText("");
				txtaAdd2.setText("");
				textMed1.setText("");
				textMed2.setText("");
				textUse1.setText("");
				textUse2.setText("");
				textInfo1.setText("");
				textInfo2.setText("");
				rdbtnMale.doClick();
				dateChooser.setDate(null);
				comboBldGrp.setSelectedIndex(1);
			}
		});
		btnReset.setBounds(26, 574, 106, 23);
		contentPane.add(btnReset);

		JLabel lblPrescriptions = new JLabel("PRESCRIPTIONS");
		lblPrescriptions.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrescriptions.setBounds(26, 369, 106, 14);
		contentPane.add(lblPrescriptions);

		JLabel lblMedication = new JLabel("Medication:");
		lblMedication.setBounds(26, 394, 64, 14);
		contentPane.add(lblMedication);

		textMed1 = new JTextField();
		textMed1.setBounds(122, 386, 154, 30);
		contentPane.add(textMed1);
		textMed1.setColumns(10);

		JLabel lblUsage = new JLabel("Usage:");
		lblUsage.setBounds(26, 433, 46, 14);
		contentPane.add(lblUsage);

		textUse1 = new JTextField();
		textUse1.setBounds(122, 427, 50, 27);
		contentPane.add(textUse1);
		textUse1.setColumns(10);

		JLabel lblPerDay = new JLabel("per day");
		lblPerDay.setBounds(177, 438, 46, 14);
		contentPane.add(lblPerDay);

		JLabel lblInformation = new JLabel("Information:");
		lblInformation.setBounds(26, 470, 71, 14);
		contentPane.add(lblInformation);

		textInfo1 = new JTextArea();
		textInfo1.setBounds(122, 465, 154, 83);
		contentPane.add(textInfo1);

		textMed2 = new JTextField();
		textMed2.setColumns(10);
		textMed2.setBounds(452, 386, 154, 30);
		contentPane.add(textMed2);

		JLabel label_1 = new JLabel("Medication:");
		label_1.setBounds(356, 394, 64, 14);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Usage:");
		label_2.setBounds(356, 433, 46, 14);
		contentPane.add(label_2);

		textUse2 = new JTextField();
		textUse2.setColumns(10);
		textUse2.setBounds(452, 427, 50, 27);
		contentPane.add(textUse2);

		JLabel label_3 = new JLabel("per day");
		label_3.setBounds(507, 438, 46, 14);
		contentPane.add(label_3);

		textInfo2 = new JTextArea();
		textInfo2.setBounds(452, 470, 154, 83);
		contentPane.add(textInfo2);

		JLabel label_4 = new JLabel("Information:");
		label_4.setBounds(356, 475, 71, 14);
		contentPane.add(label_4);

		btnSend.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					eval = new email_validator();

					SimpleDateFormat dateformat = new SimpleDateFormat(
							"dd/MM/yyyy");
					Date d = dateChooser.getDate();

					// If date is not given throw an exception to the user
					if (d == null || txtFN.getText().equals("")
							|| txtLN.getText().equals("")
							|| txtH.getText().equals("")
							|| txtW.getText().equals("")
							|| txtO.getText().equals("")
							|| txtaAdd1.getText().equals("")
							|| txtRef.getText().equals("")
							|| txtEid.getText().equals(""))
						throw new RuntimeException();

					else {

						boolean valid = eval.validate(txtEid.getText());

						if (valid) {

							age = dateformat.format(d);
							//System.out.println(age);

							JSONObject obj = new JSONObject();
							obj.put("Firstname", txtFN.getText());
							obj.put("Lastname", txtLN.getText());
							obj.put("Bdate", age);
							if (rdbtnMale.isSelected())
								obj.put("Gender", rdbtnMale.getActionCommand());
							else if (rdbtnFemale.isSelected())
								obj.put("Gender",
										rdbtnFemale.getActionCommand());
							obj.put("Height", txtH.getText());
							obj.put("Weight", txtW.getText());
							obj.put("BloodGrp", comboBldGrp.getSelectedItem()
									.toString());
							obj.put("RefBy", txtRef.getText());
							obj.put("MStatus", comboMarSts.getSelectedItem()
									.toString());
							obj.put("Eid", txtEid.getText());
							obj.put("Occupation", txtO.getText());
							obj.put("Address", txtaAdd1.getText());

							obj.put("Medication1", textMed1.getText());
							obj.put("Medication2", textMed2.getText());
							obj.put("Usage1", textUse1.getText());
							obj.put("Usage2", textUse2.getText());
							obj.put("Info1", textInfo1.getText());
							obj.put("Info2", textInfo2.getText());

							obj.put("xray", imageDataString);
							
							//http://mobile.cs.fsu.edu/converting-images-to-json-objects/
							//http://myjeeva.com/convert-image-to-string-and-string-to-image-in-java.html
							
							//JSONObject jsonObj = new JSONObject("{\"image\":\" + imageDataString + \"}");
							
							System.out.println(obj);
							//sc.PostData(obj, "http://localhost:8080/Server/Test");
						} else {
							// throw new
							// RuntimeException("E-mail address is not right!");
							JOptionPane.showMessageDialog(getParent(),
									"Invalid e-mail address!");
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

	}
}
