package application;

public class Visitor 
{
	private static int idCount = 1;
	private int id;
	private String email;
	private Integer zip;
	
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
	
	public Visitor (int id, String email, Integer zip)
	{
		this.id = id;
		this.email = email;
		this.zip = zip;
	}
	
	public int getId()
	{
		return id;
	}
	
	public Integer getZip()
	{
		return zip;
	}
	
	public String getEmail()
	{
		return email;
	}
}
