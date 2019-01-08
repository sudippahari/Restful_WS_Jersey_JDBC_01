package com.sudip.restful;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/display")
public class DisplayProduct {

	@Path("{id}")
	@GET
	public String show(@PathParam("id") int id) {
		String name = null;
		String date = null;
		String author = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from book where book_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString(2);
				date = rs.getString(3);
				author = rs.getString(4);
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.toString(id) + "\t" + name + "\t" + date + "\t" + author;
	}
}
