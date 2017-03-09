package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
            
            //create and select db
            stmt.execute("USE VisitorDB");
            stmt.execute("INSERT INTO visitor_info (VisitorID, Email, Zip) VALUES" + 
                    "(" + Math.ceil((Math.random()*10000)) + ", 'somethingElse@gmail.com', " + newZip + ")");
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