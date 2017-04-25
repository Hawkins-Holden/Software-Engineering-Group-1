package application;

public class Visitor 
{
	private static int idCount = 1;
	private int id;
	private String fname;
	private String midInit;
	private String lname;
	private String email;
	private boolean hasVisited;
	private String reasonForVisit;
	private String heard;
	private String latitude;
	private String longitude;
	private String destination;
	private String hotel;
	private String city;
	private String state;
	private String metro;
	private String zip;
	private String country;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMetro() {
		return metro;
	}

	public void setMetro(String metro) {
		this.metro = metro;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	private int party;
	
	public Visitor (String email)
	{
		//id = JDBC.generateID();
		this.email = email;
	}
	
	/*
	  This is the 'real' constructor... for now
	*/
	
	public Visitor (int id, String fname, String midInit, String lname, String email, boolean hasVisited, String reasonForVisit, String heard, 
			String latitude, String longitude, String destination, String hotel, int party)
	{
		this.id = id;
		this.fname = fname;
		this.midInit = midInit;
		this.lname = lname;
		this.email = email;
		this.hasVisited = hasVisited;
		this.reasonForVisit = reasonForVisit;
		this.heard = heard;
		this.latitude = latitude;
		this.longitude = longitude; 
		this.destination = destination;
		this.hotel = hotel;
		this.party = party;
	}
	
	public Visitor() {
		// TODO Auto-generated constructor stub
	}

	public static int getIdCount() {
		return idCount;
	}

	public static void setIdCount(int idCount) {
		Visitor.idCount = idCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMidInit() {
		return midInit;
	}

	public void setMidInit(String midInit) {
		this.midInit = midInit;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isHasVisited() {
		return hasVisited;
	}

	public void setHasVisited(boolean hasVisited) {
		this.hasVisited = hasVisited;
	}

	public String getReasonForVisit() {
		return reasonForVisit;
	}

	public void setReasonForVisit(String reasonForVisit) {
		this.reasonForVisit = reasonForVisit;
	}

	public String getHeard() {
		return heard;
	}

	public void setHeard(String heard) {
		this.heard = heard;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public int getParty() {
		return party;
	}

	public void setParty(int party) {
		this.party = party;
	}


}//end class
