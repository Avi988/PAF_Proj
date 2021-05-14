package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/ItemsAPI")
public class ItemsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Item itemObj = new Item();

	private HttpServletRequest request; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsAPI() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String output = itemObj.insertIncompleteResearch(request.getParameter("ID"),
				 request.getParameter("researcher_name"),
				 request.getParameter("email"),
				request.getParameter("start_date"),
				request.getParameter("research_category"),
				request.getParameter("price"));
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
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
		String output = itemObj.insertIncompleteResearch(request.getParameter("ID"),
				 request.getParameter("researcher_name"),
				request.getParameter("email"),
				request.getParameter("start_date"),
				request.getParameter("research_category"),
				request.getParameter("price"));
				response.getWriter().write(output); 
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	Map paras = getParasMap(request);
	String output = itemObj.updateIncompleteResearch(paras.get("hidIncompleteResearchIDSave").toString(),
	paras.get("researcher_name").toString(),
	paras.get("email").toString(),
	paras.get("start_date").toString(),
	paras.get("research_category").toString(),
	paras.get("price").toString()); 
	response.getWriter().write(output);
	
	 
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		
		Map paras = getParasMap(request);
		 String output = itemObj.deleteIncompleteResearch(paras.get("ID").toString());
		response.getWriter().write(output); 
		
		
	}

}
