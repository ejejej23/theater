package source;

public class PayDTO {
	private String myCode;
	private String myYemaeCode;
	private String myPayCode;
	private String myScreenCode;
	private String phone;
	private String ticketingdate;
	private String playdate;

	private int ticketPrice;
	private int totalPrice = ticketPrice;
	private int mileage;
	private int useM = 0;
	private int adultN;
	private int teenagerN;
	private int perN;
	private String myseats[] = { "A3", "B3" };

	private String mTitle;
	private String mstartTime;
	private String screenName;

	private String cardCom;
	private String bankCom;

	public int getPerN() {
		return perN;
	}

	public void setPerN(int perN) {
		this.perN = perN;
	}

	public String getTicketingdate() {
		return ticketingdate;
	}

	public void setTicketingdate(String ticketingdate) {
		this.ticketingdate = ticketingdate;
	}

	public String getPlaydate() {
		return playdate;
	}

	public void setPlaydate(String playdate) {
		this.playdate = playdate;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getMstartTime() {
		return mstartTime;
	}

	public void setMstartTime(String mstartTime) {
		this.mstartTime = mstartTime;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getMyCode() {
		return myCode;
	}

	public void setMyCode(String myCode) {
		this.myCode = myCode;
	}

	public String getMyYemaeCode() {
		return myYemaeCode;
	}

	public void setMyYemaeCode(String myYemaeCode) {
		this.myYemaeCode = myYemaeCode;
	}

	public String getMyPayCode() {
		return myPayCode;
	}

	public void setMyPayCode(String myPayCode) {
		this.myPayCode = myPayCode;
	}

	public String getMyScreenCode() {
		return myScreenCode;
	}

	public void setMyScreenCode(String myScreenCode) {
		this.myScreenCode = myScreenCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getUseM() {
		return useM;
	}

	public void setUseM(int useM) {
		this.useM = useM;
	}

	public int getAdultN() {
		return adultN;
	}

	public void setAdultN(int adultN) {
		this.adultN = adultN;
	}

	public int getTeenagerN() {
		return teenagerN;
	}

	public void setTeenagerN(int teenagerN) {
		this.teenagerN = teenagerN;
	}

	public String[] getMyseats() {
		return myseats;
	}

	public String getMyseats(int num) {
		return this.myseats[num];
	}

	public void setMyseats(String[] myseats) {
		this.myseats = myseats;
	}

	public void setMyseats(int num, String s) {
		this.myseats[num] = s;
	}

	public String getCardCom() {
		return cardCom;
	}

	public void setCardCom(String cardCom) {
		this.cardCom = cardCom;
	}

	public String getBankCom() {
		return bankCom;
	}

	public void setBankCom(String bankCom) {
		this.bankCom = bankCom;
	}

}
