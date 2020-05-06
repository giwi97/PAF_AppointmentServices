package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Servlet implementation class AppointmentAPI
 */
@WebServlet("/AppointmentAPI")
public class AppointmentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    Appointment appObj = new Appointment();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String output = appObj.setAppointment(request.getParameter("hospitalID"),
				request.getParameter("doctorID"),
				request.getParameter("patientID"),
				request.getParameter("description"),
				request.getParameter("datetime"));
		
		response.getWriter().write(output);
		
	}

	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		try
		{
			
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params)
			{
				
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		 }
		  catch (Exception e)
		 {
			  
		 }
		
		return map;
		
	}
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		
		String output = appObj.updateAppointment(paras.get("hidAppoiIDSave").toString(),
				paras.get("hospitalID").toString(),
				paras.get("doctorID").toString(),
				paras.get("patientID").toString(),
				paras.get("description").toString(),
				paras.get("datetime").toString());
		
		response.getWriter().write(output);
		
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		
		String output = appObj.deleteAppointment(paras.get("appointmentID").toString());
		
		response.getWriter().write(output);
		
	}

}
