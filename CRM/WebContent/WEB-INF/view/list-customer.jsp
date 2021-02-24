<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE>

<html>

<head>
<title>List Customers</title>
<link type="text/css" 
	rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>


	<div id="container">
		<div id="content">
		
		<!-- A button to insert new customer -->
		<input type="button" 
			   value="Add Customer" 
			   onclick="window.location.href='showFormForAdd'; return false;" 
			   class="add-button" 
	   />
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>

				<!-- Loop over and print our customers information to the table -->
				<!-- ${customers} refers to -> theModel.addAttribute("customer", theCustomers) in the CustomerController -->
				<c:forEach var="tempCustomer" items="${customers}">
				
					<!-- A link that is embedded with the customer's ID -->
					<c:url var="updateLink" value="/customer/showFormForUpdate"> 
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
					
					<!-- A link that is embedded with the customer's ID -->
					<c:url var="deleteLink" value="/customer/delete"> 
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
				
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td>
							<a href="${updateLink}">Update</a>
							| 
							<a href="${deleteLink}" 
							   onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>				
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>

</body>


</html>