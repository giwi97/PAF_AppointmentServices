package com;

import model.Appointment;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import javax.ws.rs.*;

import com.google.gson.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Appointments")
public class AppointmentService {

	Appointment appointmentObj = new Appointment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)

	public String readAppointment() {

		return appointmentObj.readAppointment();

	}

	@GET
	@Path("Appointment/{appointmentID}")
	@Produces(MediaType.APPLICATION_JSON)

	public String readAppointmentByID(@PathParam("appointmentID") String appointmentID) {

		return appointmentObj.readAppointmentByID(appointmentID);

	}

	@GET
	@Path("AppointmentDoc/{doctorID}")
	@Produces(MediaType.APPLICATION_JSON)

	public String readAppointmentByDocID(@PathParam("doctorID") String doctorID) {

		return appointmentObj.readAppointmentByDocID(doctorID);

	}
	
	@GET
	@Path("AppointmentHos/{hospitalID}")
	@Produces(MediaType.APPLICATION_JSON)

	public String readAppointmentByHosID(@PathParam("hospitalID") String hospitalID) {

		return appointmentObj.readAppointmentByHosID(hospitalID);

	}
	
	@GET
	@Path("AppointmentPat/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)

	public String readAppointmentByPatientID(@PathParam("patientID") String patientID) {

		return appointmentObj.readAppointmentByPatientID(patientID);

	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public String setAppointment(@FormParam("hospitalID") String hospitalID, @FormParam("doctorID") String doctorID,
			@FormParam("patientID") String patientID, @FormParam("description") String description,
			@FormParam("datetime") String datetime) {

		String output = appointmentObj.setAppointment(hospitalID, doctorID, patientID, description, datetime);

		return output;

	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateAppointment(String applicationData) {

		// Convert the input string to a JSON object

		JsonObject appointmentObject = new JsonParser().parse(applicationData).getAsJsonObject();

		// Read the values from the JSON object

		String appointmentID = appointmentObject.get("appointmentID").getAsString();
		String hospitalID = appointmentObject.get("hospitalID").getAsString();
		String doctorID = appointmentObject.get("doctorID").getAsString();
		String patientID = appointmentObject.get("patientID").getAsString();
		String description = appointmentObject.get("description").getAsString();
		String datetime = appointmentObject.get("datetime").getAsString();

		String output = appointmentObj.updateAppointment(appointmentID, hospitalID, doctorID, patientID, description,
				datetime);

		return output;

	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)

	public String deleteAppointment(String appointmentData) {

		// Convert the input string to an XML document

		Document doc = Jsoup.parse(appointmentData, "", Parser.xmlParser());

		// Read the value from the element <itemID>

		String appointmentID = doc.select("appointmentID").text();

		String output = appointmentObj.deleteAppointment(appointmentID);

		return output;
	}

}
