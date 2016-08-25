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

<script language="javascript" type="text/javascript">

$(function(){
    $('.create-order').on('click', function(e){
        e.preventDefault();
        $(this).next('.order-form').show();
    });
});


function validation(){
   alert("validation");
   String createOrder = (String)request.getAttribute("errorString");
   if(createOrder!=null) {
       windows.location= "http://localhost:8080/oms";
       out.println("<font color=red size=4px>" + createOrder + "</font>");
       return false;
   }
}
</script>

<style>
table, th, td {
    border: 1px solid black;
}
</style>

</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="http://localhost:8080/oms">Order Management System</a>
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
	
		
		<table style="width:100%" border="1">
        <tr>
            <th width="70" height="100" >Client Security code</th>
            <th width="70" height="100" >First Name</th>
            <th width="70" height="100" >Last Name</th>
            <th width="70" height="100" >Phone</th>
            <th width="70" height="100" >Country</th>
            <th width="70" height="100" >Address</th>
            <th width="70" height="100" >Edit</th>
            
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
             
                <a class="Edit" href="http://localhost:8080/oms/editEmployee?securitycode=${employee.securitycode}">Edit</a>
             </td>
             
             
            </tr>
        </c:forEach>
    </table>
    <br><br>
        <p>
			<a class="btn btn-default" href="http://localhost:8080/oms/employee" role="button">View details</a>
		</p>
		<br><br>
		<h3>Edit client form</h3>
		<br><br>
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
             <br><br>
                <td colspan = "2">
                    <input type="submit" value="Change" />
                    <a href="${pageContext.request.contextPath}/employee">Cancel</a>
                </td>
             </tr>
          </table>
       </form>
  </div>
  
    <br><br>
		<h3>Add client form</h3>
		<br><br>
    <div class="form">	
       <form id = "createEmployee" method="POST" action="doCreateEmployee">
          
          <table border="0">
              <tr>
                <td>Code</td>
                <td><input type="text" name="securitycode" value="${employee.securitycode}" required/></td>
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
                <td><input type="text" name="country" value="${employee.country}" required/></td>
             </tr>
             <tr>
                <td>Address</td>
                <td><input type="text" name="address" value="${employee.address}" /></td>
             </tr>
             <tr>
             <br><br>
                <td colspan = "2">
                    <input type="submit" value="Create" />
                    <a href="${pageContext.request.contextPath}/employee">Cancel</a>
                </td>
             </tr>
          </table>
       </form>
  </div>
		
	</div>
	
	
	<div class="col-md-4">
		<h2>Product</h2>
		
		<table style="width:100%" border="1">
        <tr>
            <th width="70" height="100" >Barcode</th>
            <th width="70" height="100" >Name</th>
            <th width="70" height="100" >Price (EUR)</th>
            <th width="70" height="100" >Description </th>
            <th width="70" height="100" >Date</th>
            <th width="70" height="100" >Edit</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
            
                <td>${product.barcode}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.description}</td>
                <td>${product.date}</td>
                <td>
                
                <a class="Edit" href="http://localhost:8080/oms/editProduct?barcode=${product.barcode}">Edit</a>
             </td>
             
            </tr>
        </c:forEach>
    </table>
    <br><br>
        <p>
			<a class="btn btn-default" href="http://localhost:8080/oms/product" role="button">View details</a>
		</p>
		
	
		<br><br>
		<h3>Edit product form</h3>
		<br><br>
  <div class="form">	
       <form id = "changeProduct" method="POST" action="doEditProduct">
          <input type="hidden" name="barcode" value="${product.barcode}" />
          <table border="0">
             <tr>
                <td>Code</td>
                <td style="color:red;">${product.barcode}</td>
             </tr>
             <tr>
                <td>Name</td>
                <td><input type="text" name="name" value="${product.name}" /></td>
             </tr>
             <tr>
                <td>Price</td>
                <td><input type="text" name="price" value="${product.price}" /></td>
             </tr>
             <tr>
                <td>Description</td>
                <td><input type="text" name="description" value="${product.description}" /></td>
             </tr>
             <tr>
                <td>Date</td>
                <td><input type="text" name="date" value="${product.date}" /></td>
             </tr>
            
             <tr>
             <br><br>
                <td colspan = "2">
                    <input type="submit" value="Change" />
                    <a href="${pageContext.request.contextPath}/product">Cancel</a>
                </td>
             </tr>
          </table>
       </form>
  </div>
  <br><br>
		<h3>Add product form</h3>
		<br><br>
    <div class="form">	
       <form id = "createProduct" method="POST" action="doCreateProduct">
          
          <table border="0">
             <tr>
                <td>Barcode</td>
                <td><input type="text" name="barcode" value="${product.barcode}" required/></td>
             </tr>
             <tr>
                <td>Name</td>
                <td><input type="text" name="name" value="${name}" /></td>
             </tr>
             <tr>
                <td>Price</td>
                <td><input type="text" name="price" value="${product.price}" required/></td>
             </tr>
             <tr>
                <td>Description</td>
                <td><input type="text" name="description" value="${product.description}" /></td>
             </tr>
             <tr>
                <td>Date</td>
                <td><input type="text" name="date" value="${product.date}" /></td>
             </tr>
            
             <tr>
             <br><br>
                <td colspan = "2">
                    <input type="submit" value="Create" />
                    <a href="${pageContext.request.contextPath}/product">Cancel</a>
                </td>
             </tr>
          </table>
       </form>
  </div>
		
	</div>
	<div class="col-md-4">
		<h2>Order</h2>
		
		<table style="width:100%" border="1">
        <tr>
            <th width="70" height="100" ><a class="sort" href="http://localhost:8080/oms/sort">Order nr</a></th>
            <th width="70" height="100" >EUR price </th>
            <th width="70" height="100" >Transaction date</th>
            <th width="70" height="100" >Barcode</th>
            <th width="70" height="100" >Client ID</th>
            <th width="70" height="100" >Name</th>
            <th width="70" height="100" >Converted price (Client currency)</th>
            <th width="70" height="100" >Description </th>
            <th width="70" height="100" >Date</th>
           
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
            
                <td>${order.ordernr}</td>
                <td>${order.price}</td>
                <td>${order.trandate}</td>
                <td>${order.barcode}</td>
                <td>${order.client}</td>
                <td>${order.name}</td>
                <td>${order.convprice}</td>
                <td>${order.description}</td>
                <td>${order.date}</td>
               
             
            </tr>
        </c:forEach>
    </table>
		<br><br>
		<p>
			<a class="btn btn-default" href="http://localhost:8080/oms/order" role="button">View details</a>
		</p>
		
		<br><br>
		
	
		<h3>Add order form</h3>
		<br><br>
		
		  <div class="form">	
       <form id = "createOrder" method="POST" action="doCreateOrder">
          
          <table border="0">
             <tr>
                <td>Code</td>
                <td><input type="text" name="ordernr" value="${order.ordernr}" required/></td>
             </tr>
             <tr>
                <td>Eur price</td>
                <td><input type="text" name="price" value="${order.price}" required/></td>
             </tr>
             <tr>
                <td>Transaction date</td>
                <td><input type="text" name="trandate" value="${order.trandate}" /></td>
             </tr>
             <tr>
                <td>Product barcode</td>
                <td><input type="text" name="barcode" value="${order.barcode}" required/></td>
             </tr>
             <tr>
                <td>Client ID</td>
                <td><input type="text" name="client" value="${order.client}" required/></td>
             </tr>
             
             <tr>
             <br><br>
                <td colspan = "2">
                <%
                   try{
                   %>
                    <input type="submit" value="Create" />
                    <% }
                    catch (Exception e){
                         out.println("An exception occurred: " + e.getMessage());
                    }
                    %>
                    
                    
                    
                    <%-- hasActionErrors() method is defined in ActionSupport --%>
                   <%--   <s:if test="hasActionErrors()">
                         <div class="errorDiv">
                                     <s:actionerror/>              
                         </div>
                    </s:if>
                    --%>
                    
                    <a href="${pageContext.request.contextPath}/order">Cancel</a>
                </td>
             </tr>
          </table>
       </form>
  </div>
		
	</div>
  </div>
    <hr>
    

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