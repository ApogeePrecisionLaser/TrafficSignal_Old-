<%-- 
    Document   : history_details
    Created on : Sep 28, 2012, 6:05:06 PM
    Author     : Shruti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table cellspacing="10" border="8.0" style="background-color:lightslategrey" id="table0"  align="center" width="500">
            <tr style="font-size:larger ;font-weight: 700;" align="center">
                <td>
                    Junction Logger History
                </td>
            </tr>
            <tr>
                <td>
                    <form name="form1" action="LoggerHistoryCont" method="post">
                        <table name="table1" border="1" width="100%" align="center" style="background-color:white">
                            <tr>
                                <th bgcolor='#febb80'>IP Address</th>
                                <th bgcolor='#febb80'>Port</th>
                                <th bgcolor='#febb80'>Status</th>
                                <th bgcolor='#febb80'>Login Timestamp Date</th>
                                <th bgcolor='#febb80'>Login Timestamp Time</th>
                                <th bgcolor='#febb80'>Logout Timestamp Date</th>
                                <th bgcolor='#febb80'>Logout Timestamp Time</th>
                            </tr>
                            <c:forEach var="detailList" items="${requestScope['detailList']}" varStatus="loopCounter">
                                <tr>
                                    <td>${detailList.ip_address}</td>
                                    <td>${detailList.port}</td>
                                    <td>${detailList.status eq true ? 'Logged In' : 'Logged Out'}</td>
                                    <td>${detailList.login_timestamp_date}</td>
                                    <td>${detailList.login_timestamp_time}</td>
                                    <td>${detailList.logout_timestamp_date}</td>
                                    <td>${detailList.logout_timestamp_time}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td style="visibility: hidden"></td>
                            <c:choose>
                                <c:when test="${showFirst eq 'false'}">
                                    <td align='center'><input type='submit' name='buttonAction' value='First' disabled></td>
                                </c:when>
                                <c:otherwise>
                                    <td align='center'><input type='submit' name='buttonAction' value='First'></td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${showPrevious == 'false'}">
                                    <td align='center'><input type='submit' name='buttonAction' value='Previous' disabled></td>
                                </c:when>
                                <c:otherwise>
                                    <td align='center'><input type='submit' name='buttonAction' value='Previous'></td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${showNext eq 'false'}">
                                    <td align='center'><input type='submit' name='buttonAction' value='Next' disabled></td>
                                </c:when>
                                <c:otherwise>
                                    <td align='center'><input type='submit' name='buttonAction' value='Next'></td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${showLast == 'false'}">
                                    <td align='center'><input type='submit' name='buttonAction' value='Last' disabled></td>
                                </c:when>
                                <c:otherwise>
                                    <td align='center'><input type='submit' name='buttonAction' value='Last'></td>
                                </c:otherwise>
                            </c:choose>
                            </tr>
                        </table>
                        <%-- These hidden fields "lowerLimit", and "noOfRowsTraversed" belong to form1 of table1. --%>
                        <input type="hidden" name="lowerLimit" value="${lowerLimit}">
                        <input type="hidden" id="noOfRowsTraversed" name="noOfRowsTraversed" value="${noOfRowsTraversed}">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
