<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			</div>
		</div>
	</div>

</body>
</html>