package source;

public class App {
	public static void main(String[] args) {
		try {
			/*
			YemaeFrame lf=new YemaeFrame("회원1","상영1");
			lf.setVisible(true);
			*/
			LoginFrame lf=new LoginFrame();
			lf.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
