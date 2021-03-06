package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.sql.Date;

public class Appointment {

	// Establishing database connection
	private Connection connect() {

		Connection conn = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcarems", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return conn;
	}

	// Inserting appointment
	public String setAppointment(String hospitalID, String doctorID, String patientID, String description,
			String datetime) {

		String output = "";

		try {

			Connection conn = connect();

			if (conn == null) {

				return "Connection failed!!";

			}

			String query = "INSERT INTO appointmentsTbl (`hospitalID`, `doctorID`, `patientID`, `description`, `datetime`)"
					+ " VALUES (?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, hospitalID);
			ps.setString(2, doctorID);
			ps.setString(3, patientID);
			ps.setString(4, description);
			ps.setDate(5, Date.valueOf(datetime));

			
			  ps.execute(); 
			  conn.close();
			 
			
			String newAppointment = readAppointment();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";


		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\":\"Error while inserting\"}";
			System.err.println(e.getMessage());

		}

		return output;

	}

	// retrieve all the details
	public String readAppointment() {

		String output = "";

		try {

			Connection conn = connect();

			if (conn == null) {

				return "Connection failed";

			}

			output = "<table border = '1'><tr><th>hospitalID</th><th>doctorID</th><th>patientID</th><th>description</th><th>datetime</th><th>Update</th><th>Delete</th></tr>";

			String query = "SELECT * FROM appointmentsTbl";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				
				String appointmentID = Integer.toString(rs.getInt("appointmentID"));
				String hospitalID = rs.getString("hospitalID");
				String doctorID = rs.getString("doctorID");
				String patientID = rs.getString("patientID");
				String description = rs.getString("description");
				String datetime = rs.getString("datetime");

				output += "<tr><td><input id='hidAppoiIDUpdate' name='hidAppoiIDUpdate' type='hidden' value= '"+ appointmentID + "'>" + hospitalID + "</td>";
				output += "<td>" + doctorID + "</td>";
				output += "<td>" + patientID + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + datetime + "</td>";

				output += "<td><input name = 'btnUpdate' type = 'button' value='Update' class='btnUpdate btn btn-secondary'> </td>"
						+ "<td><input name = 'btnRemove' type = 'button' value = 'Delete' class = 'btnRemove btn btn-danger' data-intemid='"+ appointmentID + "'>" + "</td></tr>";

			}

			conn.close();

			output += "</table>";

		} catch (Exception e) {

			output = "Error while retrieving";
			System.err.println(e.getMessage());

		}

		return output;

	}

	// Update records
	public String updateAppointment(String appointmentID, String hospitalID, String doctorID, String patientID,
			String description, String datetime) {

		String output = "";

		try {

			Connection conn = connect();

			if (conn == null) {

				return "Connection error";

			}

			String query = "UPDATE appointmentsTbl SET hospitalID = ?, doctorID = ?, patientID = ?, description = ?, datetime = ? WHERE appointmentID = ?";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, hospitalID);
			ps.setString(2, doctorID);
			ps.setString(3, patientID);
			ps.setString(4, description);
			ps.setDate(5, Date.valueOf(datetime));
			ps.setInt(6, Integer.parseInt(appointmentID));

			ps.execute();
			conn.close();

			String newAppointment = readAppointment();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";

		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\":\"Error while updating\"}";
			System.err.println(e.getMessage());

		}

		return output;

	}

	// Delete records
	public String deleteAppointment(String appointmentID) {

		String output = "";

		try {

			Connection conn = connect();

			if (conn == null) {

				return "Error while connecting to the database for deleting.";

			}

			String query = "DELETE FROM appointmentsTbl WHERE appointmentID = ?";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, Integer.parseInt(appointmentID));

			ps.execute();
			conn.close();

			String newAppointment = readAppointment();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";

		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\":\"Error while deleting\"}";
			System.err.println(e.getMessage());

		}

		return output;

	}

}
