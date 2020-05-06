$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
// Form validation-------------------
	var status = validateAppointmentForm();
	
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
// If valid------------------------
	var type = ($("#hidAppoiIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
			{
				url : "AppointmentAPI",
				type : t,
				data : $("#formAppointment").serialize(),
				dataType : "text",
				complete : function(response, status)
				{
					onAppointmentSaveComplete(response.responseText, status);
				}
			});
	
});


function onAppointmentSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divAppointmentGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error")
		{
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
			
		}
	} else if (status == "error")
	{
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	
	} else
	{
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	
	}
	
	$("#hidAppoiIDSave").val("");
	$("#formAppointment")[0].reset();

}

$(document).on("click", ".btnRemove", function(event)
{
		 $.ajax(
		 {
			 url : "AppointmentAPI",
			 type : "DELETE",
			 data : "appointmentID=" + $(this).data("appointmentID"),
			 dataType : "text",
			 complete : function(response, status)
			 {
				 
				 onAppointmentDeleteComplete(response.responseText, status);
			 
			 }
		 
		 });
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidAppoiIDSave").val($(this).closest("tr").find('#hidAppoiIDUpdate').val());
	$("#hospitalID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#doctorID").val($(this).closest("tr").find('td:eq(1)').text());
	$("#patientID").val($(this).closest("tr").find('td:eq(2)').text());
	$("#description").val($(this).closest("tr").find('td:eq(3)').text());
	$("#datetime").val($(this).closest("tr").find('td:eq(4)').text());
});


function onAppointmentDeleteComplete(response, status)
{
	if (status == "success")
	{
		
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		
		{
			
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divAppointmentGrid").html(resultSet.data);
		
		} else if (resultSet.status.trim() == "error")
		{
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		
		}
	} else if (status == "error")
	{
		
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	
	} else
	{
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	
	}
}


// CLIENTMODEL=========================================================================
function validateAppointmentForm()
{
	// HOSPITAL_ID-------------------------
	if ($("#hospitalID").val().trim() == "")
	{
		return "Insert Hospital ID.";
	}
	// DOCTOR_ID---------------------------
	if ($("#doctorID").val().trim() == "")
	{
		return "Insert Doctor ID.";
	} 
	// PATIENT_ID-------------------------------
	if ($("#patientID").val().trim() == "")
	{
		return "Insert Patient ID.";
	}
/*	// is numerical value
	var tmpPrice = $("#itemPrice").val().trim();
	if (!$.isNumeric(tmpPrice))
	{
		return "Insert a numerical value for Item Price.";
	}
	// convert to decimal price
	$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));*/
	// DESCRIPTION------------------------
	if ($("#description").val().trim() == "")
	{
		return "Insert Description.";
	}
	//DATE--------------------------------
	if ($("#datetime").val().trim() == "")
	{
		return "Insert Date.";
	}
}