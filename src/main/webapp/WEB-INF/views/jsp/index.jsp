<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Order Management System</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">Order Management System</a>
	</div>
  </div>
</nav>

<div class="jumbotron">
  <div class="container">
	<h1>${title}</h1>
	<p>
		<c:if test="${not empty msg}">
			Hello ${msg}
		</c:if>

		<c:if test="${empty msg}">
			Welcome Welcome!
		</c:if>
        </p>
        <p>
		<a class="btn btn-primary btn-lg"
                    href="#" role="button">Learn more</a>
	</p>
	</div>
</div>

<div class="container">

  <div class="row">
	<div class="col-md-4">
		<h2>Client</h2>
	
		
		<table border="1">
        <tr>
            <th>Employee Securitycode</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone</th>
            <th>Country</th>
            <th>Address</th>
        </tr>
        <c:forEach items="${employees}" var="employee">
            <tr>
            
                <td>${employee.securitycode}</td>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td>${employee.phone}</td>
                <td>${employee.country}</td>
                <td>${employee.address}</td>
            </tr>
        </c:forEach>
    </table>
    
      <p>
			<a class="btn btn-default" href="http://localhost:8080/oms/employee" role="button">View details</a>
		</p>
	</div>
	
	
	<div class="col-md-4">
		<h2>Product</h2>
		
		<table border="1">
        <tr>
            <th>Barcode</th>
            <th>Name</th>
            <th>Price (EUR)</th>
            <th>Description </th>
            <th>Date</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
            
                <td>${product.BarCode}</td>
                <td>${product.Name}</td>
                <td>${product.Price}</td>
                <td>${product.Description}</td>
                <td>${product.Date}</td>
            </tr>
        </c:forEach>
    </table>
    
    <p>
			<a class="btn btn-default" href="http://localhost:8080/oms/product" role="button">View details</a>
		</p>
		
	</div>
	<div class="col-md-4">
		<h2>Other</h2>
		<p>ABC</p>
		<p>
			<a class="btn btn-default" href="#" role="button">View details</a>
		</p>
	</div>
  </div>


  <hr>
  <footer>
	<p>© Anastassia 2016</p>
  </footer>
</div>

<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>