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
<script type="text/javascript">
    var theButton = document.getElementById("Edit");

    theButton.onclick = function() { 
        document.getElementById("form").style.visibility="hidden";   
    }
    
    $(function(){
   $('.Container.row.col-md-4.Edit').click(function(){
      $(this).hide();
      $('.Container .form').show();
      return false;
   });
});

</script>

<script src="editEmployeeView.jsp"></script>

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
            <th>Edit</th>
            
        </tr>
        <c:forEach items="${employees}" var="employee">
            <tr>
            
                <td>${employee.securitycode}</td>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td>${employee.phone}</td>
                <td>${employee.country}</td>
                <td>${employee.address}</td>
                <td>
                <!--<a class="Edit" href="http://localhost:8080/oms/doEditEmployee?securitycode=${employee.securitycode}">Edit</a>-->
                <a class="Edit" href="http://localhost:8080/oms/editEmployee?securitycode=${employee.securitycode}">Edit</a>
             </td>
             
             
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
            <th>Edit</th>
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
  <div class="form">	
       <form id = "changeEmployee" method="POST" action="doEditEmployee">
          <input type="hidden" name="securitycode" value="${employee.securitycode}" />
          <table border="0">
             <tr>
                <td>Code</td>
                <td style="color:red;">${employee.securitycode}</td>
             </tr>
             <tr>
                <td>First Name</td>
                <td><input type="text" name="firstname" value="${employee.firstName}" /></td>
             </tr>
             <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastname" value="${employee.lastName}" /></td>
             </tr>
             <tr>
                <td>Phone</td>
                <td><input type="text" name="phone" value="${employee.phone}" /></td>
             </tr>
             <tr>
                <td>Country</td>
                <td><input type="text" name="country" value="${employee.country}" /></td>
             </tr>
             <tr>
                <td>Address</td>
                <td><input type="text" name="address" value="${employee.address}" /></td>
             </tr>
             <tr>
                <td colspan = "2">
                    <input type="submit" value="Change" />
                    <a href="${pageContext.request.contextPath}/employee">Cancel</a>
                </td>
             </tr>
          </table>
       </form>
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