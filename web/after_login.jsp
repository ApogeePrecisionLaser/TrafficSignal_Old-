<%-- 
    Document   : index
    Created on : Jun 6, 2012, 2:34:35 PM
    Author     : Tarun
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <link rel="stylesheet" href="style/style.css" media="screen">
        <link rel="stylesheet" href="style/Table_content.css" media="screen">
        <title>Traffic Signals Monitoring</title>
    </head>
    <body>
         <table align="center" cellpadding="0" cellspacing="0" class="main" >
            <!--DWLayoutDefaultTable-->
            <tr><td><%@include file="/layout/header.jsp" %></td></tr>
            <tr><td><%@include file="/layout/menu.jsp" %></td></tr>
            <tr>
                <td>
                    <DIV id="body" class="maindiv">

                    </DIV>
                </td>
            </tr>
            <tr><td><%@include file="/layout/footer.jsp" %></td> </tr>
        </table>
<!--                         <h2 align="center">Welcome To Traffic Signals Monitoring</h2>
        <table align="center">
            <tr><td><a href="loggedInJunctionCont">View Signal Status</a></td></tr><br>
            <tr><td><a href="junctionCont">Junction Details</a></td></tr><br>
            <tr><td><a href="CityCont">City Details</a></td></tr><br>
            <tr><td><a href="stateCont">State Details</a></td></tr><br>
            <tr><td><a href="districtCont">District Details</a></td></tr>
            <tr><td><a href="JunSunriseSunsetCont">Sunrise Sunset Details</a></td></tr>
            <tr><td><a href="LoggerHistoryCont">Logger History Details</a></td></tr>
            <tr><td><a href="ts_statusShowerCont">view refresh</a></td></tr>
            <tr><td><a href="riseSetCont">List of riseset time</a></td></tr>
        </table>-->
       
    </body>
</html>
