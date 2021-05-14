<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.Item"%>
<%
//Save---------------------------------
if (request.getParameter("ID") != null) { 
Item itemObj = new Item(); 
String stsMsg = ""; 
	//Insert--------------------------
	if (request.getParameter("hidItemIDSave") == "") 
		if (request.getParameter("hidIncompleteResearchIDSave") == "") 
		{ 
		stsMsg = itemObj.insertIncompleteResearch(request.getParameter("ID"), 
		request.getParameter("researcher_name"),
		request.getParameter("email"), 
		request.getParameter("start_date"), 
		request.getParameter("research_category"),
		request.getParameter("price")); 
		} 
	else//Update----------------------
	{ 
	stsMsg = itemObj.updateIncompleteResearch(request.getParameter("hidIncompleteResearchIDSave"), 
	request.getParameter("researcher_name"), 
	request.getParameter("email"), 
	request.getParameter("start_date"), 
	request.getParameter("research_category"),
	request.getParameter("price")); 
	} 
	session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidIncompleteResearchIDDelete") != null) 
{ 
Item itemObj = new Item(); 
String stsMsg = 
itemObj.removeItem(request.getParameter("hidIncompleteResearchIDDelete")); 
session.setAttribute("statusMsg", stsMsg); 
}
%>

<html>
<head>
<meta charset=ISO-8859-1">
<title>Incomplete Research Management</title>
</head>
<link href="Views/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="Components/jquery.js"></script>
<script src="Components/items.js"></script>
<body>
<!--  
<script type="text/javascript">

if(typeof alertSuccess == "undefined"){
	alert ("not working");
}else{
	alert("working");
}
</script>
-->


<div class="container">
<h1 >Incomplete Research Management</h1>
<form method="post" action="items.jsp" id ="formItem">
	 <div class="mb-1 col-6">
   		 <label for="researcher_name" class="form-label">Researcher Name</label>
    	<input type="text"name="researcher_name"  class="form-control" id="researcher_name">
    	<input  type="hidden" name="hidIncompleteResearchIDSave" id ="hidIncompleteResearchIDSave" >
    </div>
    <div class="mb-1 col-6">
   		 <label for="email" class="form-label">Email</label>
    	<input type="text" name="email"  class="form-control" id="email">
    </div>
    
    <div class="mb-4 col-6">
   		 <label for="start_date" class="form-label">Start Date</label>
    	<input type="text"name="start_date"  class="form-control" id="start_date">
    </div>
    
    <div class="mb-4 col-6">
   		 <label for="research_category" class="form-label">Research Category</label>
    	<input type="text"name="research_category"  class="form-control" id="research_category">
    </div>
    
    <div class="mb-1 col-6">
   		 <label for="Price" class="form-label"> Price</label>
    	<input type="text"name="itemPrice"  class="form-control" id="price" placeholder="ooo.oo/-">
    </div>
    

 <input name="btnSubmit" class="btn btn-primary mb-3" id="btnSave" type="button" value="Save">
 
</form>
<br>
<div class="alert alert-primary"  id="alertSuccess">
	<%
 		out.print(session.getAttribute("statusMsg"));
	%>
</div>
<div class="alert alert-danger"  id="alertError"> </div>


<%
 Item itemObj = new Item();
 out.print(itemObj.readItems());
%>
</div>
</body>

</html>
