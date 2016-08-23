<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
 </head>
 <body>
 
 
    <h3>Edit Employee</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <c:if test="${not empty employee}">
       <form id = "changeEmployee" method="POST" action="doEditEmployee">
          <input type="hidden" name="securitycode" value="${employee.securitycode}" />
          <table border="0">
             <tr>
                <td>Code</td>
                <td style="color:red;">${employee.securitycode}</td>
             </tr>
             <tr>
                <td>First Name</td>
                <td><input type="text" name="firstname" value="${employee.firstname}" /></td>
             </tr>
             <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastname" value="${employee.lastname}" /></td>
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
                    <input type="submit" value="Submit" />
                    <a href="${pageContext.request.contextPath}/employeeList">Cancel</a>
                </td>
             </tr>
          </table>
       </form>
    </c:if>
 
    <jsp:include page="footer.jsp"></jsp:include>
 
 </body>
</html>