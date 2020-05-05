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
	var status = validateItemForm();
	
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
// If valid------------------------
	$("#formAppointment").submit();
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