package application;

/***********************
JDBC just adds in some random things that I made up at the moment.   	
*/
 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.*;


public class JDBC
{
   static Integer id;
   public static void insertIntoDB(Visitor visitor) 
   {
      Connection con = null;
      Statement stmt;
      
      String url = "jdbc:mysql://localhost:3306/test";//will replace 'localhost' with proper ip address when time comes
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
                        
            /*
              Inserts data into database.
              NOTE: uses temporary values for now. Will change when we 
              can get this information using variables from the other classes.
             */
             
           // int visitorID = (int) Math.ceil((Math.random()*10000));
            
            //Main x = new Main();
            
            int v = 0;
            if (visitor.getHasVisited() == true)
            {
            	v = 1;
            }
            
            stmt.execute("USE VisitorDB");
            
            stmt.execute("INSERT INTO visitors (VisitorID, Fname, MiddleInitial, Lname, Email) VALUES" + 
            		"(" + visitor.getId() + ", '" + visitor.getFirstName() + "', " + "'" + visitor.getMiddleInitial() + 
            		"'" + ", '" + visitor.getLastName() + "', " + "'" + visitor.getEmail() + "')");
            
            stmt.execute("INSERT INTO visitorlocations (VisitorID, Latitude, Longitude) VALUES" +
            		"(" + visitor.getId() + ", '" + visitor.getLat() + "', " + "'" + visitor.getLong() + "')");
            
            stmt.execute("INSERT INTO visits (VisitorID, Party, Heard, Hotel, Destination, RepeatVisit, TravelingFor, Visiting_Day) VALUES" +
            		"(" + visitor.getId() + ", '1', " + "'" + visitor.getHeard() + "'" + ", '1'" + ", 'Monroe', " + "'" + v + "'" 
            		+ ", 'Pleasure', " + "'" + getCurrentTimeStamp() + "'" + ")");            
         }//end if
      } //end try
      
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
   }//end insertIntoDB method   
   
   /*
     This method allows timestamp to be inserted properly into the database
   */
   
   private static java.sql.Timestamp getCurrentTimeStamp()
   {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
   }
   
   /*
    * Reads from the database
    */
   
   public static int readFromDB(int id) 
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
            
            ResultSet res = stmt.executeQuery("SELECT * FROM visitors WHERE VisitorID = " + id);
            
            /**
             * Iterate over the result set from the above query
             */
            
            while (res.next())
            {
                System.out.println(res.getInt("VisitorID") + " " + res.getString("Email"));
               // id = res.getInt("Zip");
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
	return id;
   }   
   
   
}