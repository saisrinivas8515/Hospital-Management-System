package UI;

public class Client {

	static String authToken="..";
	public static void main(String[] args) {
		Edit_appointment e=new Edit_appointment();
		e.setVisible(true);
		/*
		UI.Login l = new UI.Login();
		l.setVisible(true);*/
		
		
		/*
		 * 
							 * Login l=new Login(); l.setVisible(true);
							 */

		/*
		 * //Doctors_info frame = new Doctors_info(); //frame.setVisible(true);
		 */ System.out.println(":) :) :) :) :)");
	}
     static String getToken(){
    	return authToken;
    }
}
