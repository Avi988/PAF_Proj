
$(document).ready(function(){ 

if ($("#alertSuccess").text().trim() == "null" ) { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 



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
var type = ($("#hidIncompleteResearchIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "ItemsAPI",
type : type,
data : $("#formItem").serialize(),
dataType : "text",
complete : function(response, status)
{
onItemSaveComplete(response.responseText, status);
}
});
});

function onItemSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
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
14
 $("#hidItemIDSave").val("");
 $("#formItem")[0].reset();
}

$(document).on("click", ".btnUpdate", function(event)
		{
		$("#hidIncompleteResearchIDSave").val($(this).data("itemid"));
		 $("#researcher_name").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#email").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#start_date").val($(this).closest("tr").find('td:eq(2)').text());
		 $("#research_category").val($(this).closest("tr").find('td:eq(3)').text());
		 $("#Price").val($(this).closest("tr").find('td:eq(4)').text());
		 
		});

$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "ItemsAPI",
		 type : "DELETE",
		 data : "itemID=" + $(this).data("itemid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onItemDeleteComplete(response.responseText, status);
		 }
		 });
		});


function onItemDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
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



// SAVE ============================================
$(document).on("click", "#btnSave", function(event) { 
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
 $("#formItem").submit(); 
}); 
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	 $("#hidIncompleteResearchIDSave").val($(this).closest("tr").find('#hidIncompleteResearchIDIDUpdate').val()); 
	 $("#researcher_name").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#email").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#start_date").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#research_category").val($(this).closest("tr").find('td:eq(3)').text()); 
	 $("#price").val($(this).closest("tr").find('td:eq(4)').text());
}); 
// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// RESEARCHER_NAME
if ($("#researcher_name").val().trim() == "") 
{ 
return "Insert IncompleteResearch researcher_name."; 
}
// EMAIL
if ($("#email").val().trim() == "") 
{ 
return "Insert IncompleteResearch email."; 
}
//STRAT_DATE-------------------------------
if ($("#start_date").val().trim() == "") 
{ 
return "Insert IncompleteResearch start_date."; 
}
//RESEARCH_CATEGORY-------------------------------
if ($("#research_category").val().trim() == "") 
{ 
return "Insert IncompleteResearch research_category."; 
}
//PRICE-------------------------------
if ($("#price").val().trim() == "") 
{ 
return "Insert IncompleteResearch Price."; 
}
//is numerical value
var tmpPrice = $("#price").val().trim(); 
if (!$.isNumeric(tmpPrice)) 
 { 
 return "Insert a numerical value for Item Price."; 
 } 
// convert to decimal price
 $("#price").val(parseFloat(tmpPrice).toFixed(2)); 
 
return true; 
}
