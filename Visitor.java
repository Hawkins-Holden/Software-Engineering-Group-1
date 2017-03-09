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
	private String latitude;
	private String longitude;
	
	public Visitor (String email)
	{
		id = idCount;
		idCount++;
		this.email = email;
	}
	
	
	
	public Visitor (int id, String email, String fname, String midInit, String lname, boolean hasVisited, String reasonForVisit, 
			String latitude, String longitude)
	{
		this.id = id;
		this.fname = fname;
		this.midInit = midInit;
		this.lname = lname;
		this.email = email;
		this.hasVisited = hasVisited;
		this.reasonForVisit = reasonForVisit;
		this.latitude = latitude;
		this.longitude = longitude; 
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
	
	public String getLat()
	{
		return latitude;
	}
	
	public String getLong()
	{
		return longitude;
	}
	}
