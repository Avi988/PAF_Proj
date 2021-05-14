
package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item {

	 
	 
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafDB", "root",  "");
	 //For testing

	 //	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	
	
	
	
	
	public String insertIncompleteResearch(String ID, String researcher_name, String email, String start_date, String research_category, String price)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 // create a prepared statement
	 String query = " insert into incompleteResearch(`ID`,`researcher_name`,`email`,`start_date`,`research_category`,`price`)"
	 + " values (?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, researcher_name);
	 preparedStmt.setString(3, email);
	 preparedStmt.setString(4, start_date);
	 preparedStmt.setString(5, research_category);
	 preparedStmt.setDouble(6, Double.parseDouble(price));

	
	 
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	catch (Exception e)
	 {
	 output = "Error while inserting";
	 System.err.println(e.getMessage());
	 }
	return output;
	}

	
	public String readItems()//
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	 // Prepare the HTML table to be displayed
	 
//	 output = "<table border='1'><tr><th>researcher_name</th>"
//	 +"<th>email</th><th>start_date</th>"
//	 + "<th>research_category</th>"
//   + "<th>price</th>"
//	 + "<th>Update</th><th>Remove</th></tr>";
	 
	 output = "<table class=\"table table-bordered\">\r\n"
	 		+ "  <thead>\r\n"
	 		+ "    <tr>\r\n"
	 		+ "      <th scope=\"col\">ID</th>\r\n"
	 		+ "      <th scope=\"col\">researcher_name</th>\r\n"
	 		+ "      <th scope=\"col\">email</th>\r\n"
	 		+ "      <th scope=\"col\">start_date</th>\r\n"
			+ "      <th scope=\"col\">research_category</th>\r\n"
			+ "      <th scope=\"col\">price</th>\r\n"
	 		+ "      <th scope=\"col\" colspan=\"2\">Upadate/Delete</th>\r\n"
	 		+ "    </tr>\r\n"
	 		+ "  </thead>\r\n"
	 		+ "</table";
	 String query = "select * from incompleteResearch";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String ID = Integer.toString(rs.getInt("ID"));
		 String researcher_name= rs.getString("researcher_name");
		 String email= rs.getString("email");
		 String start_date= rs.getString("start_date");
		 String research_category= rs.getString("research_category");
		 String price= Double.toString(rs.getDouble("price"));
	 
	 // Add a row into the HTML table
		 output += "<tr><td><input id='hidIncompleteIDUpdate'name='hidIncompleteIDUpdate'type='hidden' value='" + ID + "'>"
		 		  + researcher_name + "</td>";
		 output += "<td>" + email + "</td>";
		 output += "<td>" + start_date + "</td>"; 
		 output += "<td>" + research_category + "</td>";
		 output += "<td>" + price + "</td>";

	 // buttons
		 output += "<td>"
		 + "<input name='btnUpdate' "
	     + " type='button' class='btnUpdate btn btn-outline-dark' value='Update'>"
		 + "<input name='ID' type='hidden' "
		 + " value='" + ID + "'>" + "</td>"
		 + "<td><form method='post' action='items.jsp'>" //
		 + "<input name='btnRemove' "
		 + " type='submit' class='btn btn-outline-danger' value='Remove'>"
		 + "<input name='hidIncompleteIDUpdate' type='hidden' "
		 + " value='" + ID + "'>" + "</form></td></tr>";

	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	return output;
	}
	
	
	public String removeItem(String ID)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	
	 Statement st=con.createStatement();
	// int i=
	 st.executeUpdate("DELETE FROM incompleteResearch WHERE ID="+ID);
	 con.close();
	 output = "deleted successfully";

	 
	 }
	catch (Exception e)
	 {
	 output = "Error while deleting";
	 System.err.println(e.getMessage());
	 }
	return output;
	}

	public String updateIncompleteResearch(String id,String researcher_name, String email, String start_date, String research_category, String price)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 // create a prepared statement
	 String query = "update incompleteResearch set researcher_name=?,  email=?, start_date=?, research_category=?, price=? where ID='"+id+"'";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 
	 preparedStmt.setString(1, researcher_name);
	 preparedStmt.setString(2, email);
	 preparedStmt.setString(3, start_date);
	 preparedStmt.setString(4, research_category);
	 preparedStmt.setDouble(5, Double.parseDouble(price));
	
	 
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	catch (Exception e)
	 {
	 output = "Error while updating";
	 System.err.println(e.getMessage());
	 e.printStackTrace();
	 }
	return output;
	}
	
	
	public String deleteIncompleteResearch(String ID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for deleting.";
	 }
	 // create a prepared statement
	 String query = "delete from incompleteResearch where ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newItems = readItems();
	 output = "{\"status\":\"success\", \"data\": \"" +
	 newItems + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	


}

