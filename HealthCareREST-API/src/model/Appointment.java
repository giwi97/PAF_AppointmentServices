package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.sql.Date;

public class Appointment {

	//Establishing database connection
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

	//Inserting appointment
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

			output = "Insertion successful";

		} catch (Exception e) {

			output = "Insertion failed!!";
			System.err.println(e.getMessage());

		}

		return output;

	}

	//retrieve all the details
	public String readAppointment() {

		String output = "";

		try {

			Connection conn = connect();

			if (conn == null) {

				return "Connection failed";

			}

			output = "<table border = \"1\"><tr><th>hospitalID</th><th>doctorID</th><th>patientID</th><th>description</th><th>datetime</th><th>Update</th><th>Delete</th></tr>";

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

				output += "<tr><td>" + hospitalID + "</td>";
				output += "<td>" + doctorID + "</td>";
				output += "<td>" + patientID + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + datetime + "</td>";

				output += "<td><input name = \"btnUpdate\" type = \"button\" value=\"Update\" class=\"btn btn-secondary\"> </td>"
						+ "<td><form method = \"post\" action = \"appointments.jsp\">"
						+ "<input name = \"btnRemove\" type = \"submit\" value = \"Delete\" class = \"btn btn-danger\">"
						+ "<input name = \"appointmentID\" type = \"hidden\" value = \"" + appointmentID + "\">"
						+ "</form></td></tr>";

			}

			conn.close();

			output += "</table>";

		} catch (Exception e) {

			output = "Error while retrieving";
			System.err.println(e.getMessage());

		}

		return output;

	}

	//retrieving details according to appointment ID
	
	public String readAppointmentByID(String appointmentID) {

		String output = "";

		try {

			Connection conn = connect();

			if (conn == null) {

				return "Connection failed";

			}

			output = "<table border = \"1\"><tr><th>hospitalID</th><th>doctorID</th><th>patientID</th><th>description</th><th>datetime</th><th>Update</th><th>Delete</th></tr>";

			String query = "SELECT * FROM appointmentsTbl WHERE appointmentID = '" + appointmentID + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				String appointmentID1 = Integer.toString(rs.getInt("appointmentID"));
				String hospitalID = rs.getString("hospitalID");
				String doctorID = rs.getString("doctorID");
				String patientID = rs.getString("patientID");
				String description = rs.getString("description");
				String datetime = rs.getString("datetime");

				output += "<tr><td>" + hospitalID + "</td>";
				output += "<td>" + doctorID + "</td>";
				output += "<td>" + patientID + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + datetime + "</td>";

				output += "<td><input name = \"btnUpdate\" type = \"button\" value=\"Update\" class=\"btn btn-secondary\"> </td>"
						+ "<td><form method = \"post\" action = \"appointments.jsp\">"
						+ "<input name = \"btnRemove\" type = \"submit\" value = \"Delete\" class = \"btn btn-danger\">"
						+ "<input name = \"appointmentID\" type = \"hidden\" value = \"" + appointmentID1 + "\">"
						+ "</form></td></tr>";

			}

			conn.close();

			output += "</table>";

		} catch (Exception e) {

			output = "Error while retrieving";
			System.err.println(e.getMessage());

		}

		return output;

	}
	
	//retrieving details according to Doctor ID
	
	public String readAppointmentByDocID(String doctorID) {
		
		String output = "";

		try {

			Connection conn = connect();

			if (conn == null) {

				return "Connection failed";

			}

			output = "<table border = \"1\"><tr><th>hospitalID</th><th>patientID</th><th>description</th><th>datetime</th><th>Update</th><th>Delete</th></tr>";

			String query = "SELECT * FROM appointmentsTbl WHERE doctorID = '" + doctorID + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				String doctorID1 = rs.getString("doctorID");
				String hospitalID = rs.getString("hospitalID");
				String patientID = rs.getString("patientID");
				String description = rs.getString("description");
				String datetime = rs.getString("datetime");

				output += "<tr><td>" + hospitalID + "</td>";
				output += "<td>" + patientID + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + datetime + "</td>";

				output += "<td><input name = \"btnUpdate\" type = \"button\" value=\"Update\" class=\"btn btn-secondary\"> </td>"
						+ "<td><form method = \"post\" action = \"appointments.jsp\">"
						+ "<input name = \"btnRemove\" type = \"submit\" value = \"Delete\" class = \"btn btn-danger\">"
						+ "<input name = \"appointmentID\" type = \"hidden\" value = \"" + doctorID1 + "\">"
						+ "</form></td></tr>";

			}

			conn.close();

			output += "</table>";

		} catch (Exception e) {

			output = "Error while retrieving";
			System.err.println(e.getMessage());

		}

		return output;
		
	}
	
	//retrieving details according to hospital ID
	
	public String readAppointmentByHosID(String hospitalID) {
		
		String output = "";

		try {

			Connection conn = connect();

			if (conn == null) {

				return "Connection failed";

			}

			output = "<table border = \"1\"><tr><th>doctorID</th><th>patientID</th><th>description</th><th>datetime</th><th>Update</th><th>Delete</th></tr>";

			String query = "SELECT * FROM appointmentsTbl WHERE hospitalID = '" + hospitalID + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				
				String hospitalID1 = rs.getString("hospitalID");
				String doctorID = rs.getString("doctorID");
				String patientID = rs.getString("patientID");
				String description = rs.getString("description");
				String datetime = rs.getString("datetime");

				output += "<tr><td>" + doctorID + "</td>";
				output += "<td>" + patientID + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + datetime + "</td>";

				output += "<td><input name = \"btnUpdate\" type = \"button\" value=\"Update\" class=\"btn btn-secondary\"> </td>"
						+ "<td><form method = \"post\" action = \"appointments.jsp\">"
						+ "<input name = \"btnRemove\" type = \"submit\" value = \"Delete\" class = \"btn btn-danger\">"
						+ "<input name = \"appointmentID\" type = \"hidden\" value = \"" + hospitalID1 + "\">"
						+ "</form></td></tr>";

			}

			conn.close();

			output += "</table>";

		} catch (Exception e) {

			output = "Error while retrieving";
			System.err.println(e.getMessage());

		}

		return output;
		
	}
	
	//retrieving details according to Patient ID
	
	public String readAppointmentByPatientID(String patientID) {
		
		String output = "";

		try {

			Connection conn = connect();

			if (conn == null) {

				return "Connection failed";

			}

			output = "<table border = \"1\"><tr><th>hospitalID</th><th>doctorID</th><th>description</th><th>datetime</th><th>Update</th><th>Delete</th></tr>";

			String query = "SELECT * FROM appointmentsTbl WHERE patientID = '" + patientID + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				
				String patientID1 = rs.getString("patientID");
				String hospitalID = rs.getString("hospitalID");
				String doctorID = rs.getString("doctorID");
				String description = rs.getString("description");
				String datetime = rs.getString("datetime");

				output += "<tr><td>" + hospitalID + "</td>";
				output += "<td>" + doctorID + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + datetime + "</td>";

				output += "<td><input name = \"btnUpdate\" type = \"button\" value=\"Update\" class=\"btn btn-secondary\"> </td>"
						+ "<td><form method = \"post\" action = \"appointments.jsp\">"
						+ "<input name = \"btnRemove\" type = \"submit\" value = \"Delete\" class = \"btn btn-danger\">"
						+ "<input name = \"appointmentID\" type = \"hidden\" value = \"" + patientID1 + "\">"
						+ "</form></td></tr>";

			}

			conn.close();

			output += "</table>";

		} catch (Exception e) {

			output = "Error while retrieving";
			System.err.println(e.getMessage());

		}

		return output;
		
	}

	//Update records
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

			output = "Updated successfully";

		} catch (Exception e) {

			output = "Error while updating..";
			System.err.println(e.getMessage());

		}

		return output;

	}

	//Delete records
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

			output = "Deleted successfully";

		} catch (Exception e) {

			output = "Error while deleting";
			System.err.println(e.getMessage());

		}

		return output;

	}

}
