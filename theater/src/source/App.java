package source;

public class App {
	public static void main(String[] args) {
		try {
			/*
			YemaeFrame lf=new YemaeFrame("ȸ��1","��1");
			lf.setVisible(true);
			*/
			LoginFrame lf=new LoginFrame();
			lf.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
