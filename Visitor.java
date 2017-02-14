public class Visitor 
{
	private static int idCount = 1;
	private int id;
	private String fname;
	private String midInit;
	private String lname;
	private String email;
	private String country;
	private String address;
	private String state;
	private String city;
	private Integer zip;
	private boolean hasVisited;
	private String reasonForVisit;
	
	public Visitor (String email, Integer zip)
	{
		id = idCount;
		idCount++;
		this.email = email;
		this.zip = zip;
	}
	
	public Visitor (Integer zip)
	{
		id = idCount;
		idCount++;
		this.zip = zip;
		email = null;
	}
	
	public Visitor (int id, String email, String country, String address, String state, String city,
			Integer zip, String fname, String midInit, String lname, boolean hasVisited, String reasonForVisit)
	{
		this.id = id;
		this.fname = fname;
		this.midInit = midInit;
		this.lname = lname;
		this.email = email;
		this.country = country;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.hasVisited = hasVisited;
		this.reasonForVisit = reasonForVisit;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getState()
	{
		return state;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public Integer getZip()
	{
		return zip;
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
}
