package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import com.toedter.calendar.JDateChooser;

public class DocViewAppointments extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> dfl;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DocViewAppointments frame = new DocViewAppointments();
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
	public DocViewAppointments() {
		final ServerCode sc = new ServerCode();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(41, 16, 126, 20);
		contentPane.add(dateChooser);

		JButton btnCheck = new JButton("Check");
		btnCheck.setBounds(200, 16, 126, 20);
		btnCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//String responseSting = sc.getData("http://localhost:8080/Server/Test");
				JSONParser par = new JSONParser();
				try {
					JSONObject response = (JSONObject) par.parse(
							/* response string */"{\"1300\":\"1\",\"1430\":\"1\",\"1500\":\"1\",\"1600\":\"1\",\"1700\":\"1\",\"1800\":\"1\"}");
					dfl = new DefaultListModel<String>();
					for (Object key : response.keySet()) {
						String keyStr = (String) key;						
						Object keyvalue = response.get(keyStr);
						if (keyvalue.equals("1")) {
							dfl.addElement(keyStr);
						}
						System.out.println("size :"+dfl.size());
					}
					if (dfl.size()> 0) {
						list = new JList<String>(dfl);
						list.setBounds(10, 75, 200, 100);
						setBounds(100, 100, 400, 300);
						contentPane.add(list);
						JButton btnDelete = new JButton("Delete");
						btnDelete.setBounds(26, 180, 171, 41);
						btnDelete.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								System.out.println("selected value" + list.getSelectedIndex());
								dfl.remove(list.getSelectedIndex());
							}
						});
						contentPane.add(btnDelete);

					}
				} catch (ParseException e1) {

					e1.printStackTrace();
				}

			}

		});
		contentPane.add(btnCheck);
		
	

	}
}
