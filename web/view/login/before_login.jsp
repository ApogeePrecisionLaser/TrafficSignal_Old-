<%-- 
    Document   : after_login
    Created on : Oct 29, 2012, 10:34:03 AM
    Author     : Shruti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <link rel="stylesheet" href="style/style.css" media="screen">
        <title>JSP Page</title>
    </head>
    <body>
        <table align="center" cellpadding="0" cellspacing="0" class="main" >
            <!--DWLayoutDefaultTable-->
            <tr><td><%@include file="/layout/header.jsp" %></td></tr>
            <tr><td><%@include file="/layout/menu_before_login.jsp" %></td></tr>
            <tr>
                <td>
                    <DIV id="body" class="maindiv">
                        <%--                        <form name="form1" method="post" action="loginCont">
            <table id="table1" border="1" align="center">
                <tr>
                    <th>Username</th>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <th>Password</th>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" name="task" value="Login"></td>
                </tr>
                <tr id="message">
                <c:if test="${not empty message}">
                    <td colspan="8" bgcolor="${msgBgColor}"><b>${message}</b></td>
                </c:if>
                </tr>
            </table>
        </form> --%>
                    </DIV>
                </td>
            </tr>
            <tr><td><%@include file="/layout/footer.jsp" %></td> </tr>
        </table>

      
       
    </body>
</html>
