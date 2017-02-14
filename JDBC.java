import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.*;

public class JDBC
{
   static Integer zip;
   public static void insertIntoDB(int newZip) 
   {
      Connection con = null;
      Statement stmt;
      
      String url = "jdbc:mysql://localhost:3306/test";
      String user = "root";
      String password = "";
   
      try
      {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         con = DriverManager.getConnection(url, user, password);
      
         if(!con.isClosed())
         {  
            System.out.println("Successfully connected to " +
               "MySQL server using TCP/IP...");
            stmt = con.createStatement();
            
            java.util.Date today = new java.util.Date();
            System.out.println(new java.sql.Timestamp(today.getTime()));//used for testing date
            java.sql.Timestamp ts = new java.sql.Timestamp(today.getTime());
            
            SimpleDateFormat df = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            String now = df.format(today);
            System.out.println(now);//testing
            
            
            
            //create and select db
            stmt.execute("USE VisitorDB");
            stmt.execute("INSERT INTO visitor_info (VisitorID, Fname, MiddleInitial, Lname, Email, Country, Address, City, State, Zip, Party, Heard, Hotel, Destination, RepeatVisit, TravelingFor, Visiting_Day) VALUES" + 
                    "(" + Math.ceil((Math.random()*10000)) + ", 'Bobby'" + ", 'D'" + ", 'Williams'" + ", 'bobby@gmail.com'" + ", 'United States'" + ", '2355 Something Road'" + ", 'Pollock'" + ", 'Louisiana', " + newZip + ", " + 3 + ", 'Billboard', " + 0 + ", 'Monroe', " + 0 + ", 'Pleasure', " + " '" + ts + "'" + ")");
         }
      } 
      catch(Exception e) 
      {
         System.err.println("Exception: " + e.getMessage());
      } 
      finally
      {
         try
         {
            if(con != null)
               con.close();
         } 
         catch(SQLException e) {}
      }
   }   
   
   
   
   
   public static int readFromDB(int newZip) 
   {
      Connection con = null;
      Statement stmt;
      
      String url = "jdbc:mysql://localhost:3306/test";
      String user = "root";
      String password = "";
   
      try
      {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         con = DriverManager.getConnection(url, user, password);
      
         if(!con.isClosed())
         {  
            System.out.println("Successfully connected to " +
               "MySQL server using TCP/IP...");
            stmt = con.createStatement();
            
            //create and select db
            
            stmt.execute("CREATE DATABASE IF NOT EXISTS VisitorsDB");
            stmt.execute("USE VisitorDB");
            
            
            /**
             * Query entries with the Zip '71467'
             */
            
            ResultSet res = stmt.executeQuery("SELECT * FROM visitor_info WHERE Zip = " + newZip);
            
            /**
             * Iterate over the result set from the above query
             */
            
            while (res.next())
            {
                System.out.println(res.getInt("VisitorID") + " " + res.getString("Email"));
                zip = res.getInt("Zip");
            }
         }
      } 
      catch(Exception e) 
      {
         System.err.println("Exception: " + e.getMessage());
      } 
      finally
      {
         try
         {
            if(con != null)
               con.close();
         } 
         catch(SQLException e) {}
      }
	return zip;
   }   
   
   
}