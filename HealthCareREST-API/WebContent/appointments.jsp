<%@page import="com.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	//Save---------------------------------
	if (request.getParameter("hospitalID") != null)
	{
		Appointment appObj = new Appointment();
		String stsMsg = "";
		//Insert--------------------------
		if (request.getParameter("hidAppoiIDSave") == "")
		{
			stsMsg = appObj.setAppointment(request.getParameter("hospitalID"),
			request.getParameter("doctorID"),
			request.getParameter("patientID"),
			request.getParameter("description"),
			request.getParameter("datetime"));
		}
		else//Update----------------------
		{
			stsMsg = appObj.updateAppointment(request.getParameter("appointmentID"),
			request.getParameter("hospitalID"),
			request.getParameter("doctorID"),
			request.getParameter("patientID"),
			request.getParameter("description"),
			request.getParameter("datetime"));
		}
	
		session.setAttribute("statusMsg", stsMsg);

	}

	//Delete-----------------------------
	if (request.getParameter("hidItemIDDelete") != null)
	{
		Appointment appObj = new Appointment();
		String stsMsg =
		appObj.deleteAppointment(request.getParameter("hidItemIDDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment Service</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/appointments.js"></script>
</head>
<body>

	<div class = "container">
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Appointment Service</h1>
					<form id="formAppointment" name="formAppointment" method="post" action="appointments.jsp">
						Hospital ID:  
						<input id="hospitalID" name="hospitalID" type="text" class="form-control form-control-sm">  
					
						<br> 
						Doctor ID:  
						<input id="doctorID" name="doctorID" type="text" class="form-control form-control-sm">  
					
						<br>
					 	Patient ID:  
					 	<input id="patientID" name="patientID" type="text" class="form-control form-control-sm">  
					 
					 	<br> 
					 	Description:  
					 	<input id="description" name="description" type="text" class="form-control form-control-sm">  
					 
					 	<br> 
						Date:  
					 	<input id="datetime" name="datetime" type="text" class="form-control form-control-sm">  
					 	<br>  
					 
					 	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 	<input type="hidden" id="hidAppoiIDSave" name="hidAppoiIDSave" value=""> 
					</form>
					
					<div id="alertSuccess" class="alert alert-success">
						
						<% out.print(session.getAttribute("statusMsg")); %> 
						
					</div>  
					<div id="alertError" class = "alert alert-danger"></div> 
					
					<br>
					
						<%    
						Appointment appObj = new Appointment();
						out.print(appObj.readAppointment());   
						%>   
					
					
			</div>
		</div>
	</div>

</body>
</html>