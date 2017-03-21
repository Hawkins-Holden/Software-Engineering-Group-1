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
	
	public Visitor (String email)
	{
		id = idCount;
		idCount++;
		this.email = email;
	}
	
	/*
	  This is the 'real' constructor... for now
	*/
	
	public Visitor (int id, String fname, String midInit, String lname, String email, boolean hasVisited, String reasonForVisit, String heard, 
			String latitude, String longitude, String destination)
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
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getFirstName()
	{
		return fname;
	}
	
	public String getMiddleInitial()
	{
		return midInit;
	}
	
	public String getLastName()
	{
		return lname;
	}
	
	public boolean getHasVisited()
	{
		return hasVisited;
	}
	
	public String getReason()
	{
		return reasonForVisit;
	}
	
	public String getHeard()
	{
		return heard;
	}
	
	public String getLat()
	{
		return latitude;
	}
	
	public String getLong()
	{
		return longitude;
	}
	public String getDestination()
	{
		return destination;
	}
}//end class
