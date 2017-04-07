package adminApplication;

import java.sql.Date;

public class VisitorDetails {
	private int id;
	private String fname;
	private String lname;
	private String email;
	private String latitude;
	private String longitude;
	private String city;
	private String state;
	private String country;
	private Integer party;
	private String heard;
	private String hotel;
	private String destination;
	private Boolean repeatVisit;
	private String travelingFor;
	private Date visitingDay;
	
	public VisitorDetails(int id, String fname, String lname, String email, String latitude, String longitude,
			String city, String state, String country, Integer party, String heard, String hotel, String destination,
			Boolean repeatVisit, String travelingFor, Date visitingDay) {
		this.setId(id);
		this.setFname(fname);
		this.setLname(lname);
		this.setEmail(email);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setCity(city);
		this.setState(state);
		this.setCountry(country);
		this.setParty(party);
		this.setHeard(heard);
		this.setHotel(hotel);
		this.setDestination(destination);
		this.setRepeatVisit(repeatVisit);
		this.setTravelingFor(travelingFor);
		this.setVisitingDay(visitingDay);
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getParty() {
		return party;
	}

	public void setParty(Integer party) {
		this.party = party;
	}

	public String getHeard() {
		return heard;
	}

	public void setHeard(String heard) {
		this.heard = heard;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Boolean getRepeatVisit() {
		return repeatVisit;
	}

	public void setRepeatVisit(Boolean repeatVisit) {
		this.repeatVisit = repeatVisit;
	}

	public String getTravelingFor() {
		return travelingFor;
	}

	public void setTravelingFor(String travelingFor) {
		this.travelingFor = travelingFor;
	}

	public Date getVisitingDay() {
		return visitingDay;
	}

	public void setVisitingDay(Date visitingDay) {
		this.visitingDay = visitingDay;
	}
	
}