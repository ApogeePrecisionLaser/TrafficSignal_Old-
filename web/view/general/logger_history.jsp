<%-- 
    Document   : logger_history
    Created on : Sep 28, 2012, 12:32:39 PM
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
        <link rel="stylesheet" href="style/Table_content.css" media="screen">
        <title>JSP Page</title>
        <script type="text/javascript">
            var popupwin=null;
            function popup(url, window_name,popup_height,popup_width){
                var popup_top_pos = (screen.availHeight / 2) - (popup_height / 2);
                var popup_left_pos = (screen.availWidth / 2) - (popup_width / 2);
                popupwin = window.showModalDialog(url,window_name,"dialogWidth:" +popup_width+"px;" + " dialogHeight:" +popup_height+ "px;"+" dialogLeft:" +popup_left_pos+ "px;"+" dialogTop:" +popup_top_pos+ "px;"+" resizable:no; center:yes");
                popupwin.focus()
            }

            function openPopUp(url, window_name, popup_height, popup_width) {
                var popup_top_pos = (screen.availHeight / 2) - (popup_height / 2);
                var popup_left_pos = (screen.availWidth / 2) - (popup_width / 2);
                               var window_features = "left=" + popup_left_pos + ", top=" + popup_top_pos + ", width=" + popup_width + ", height=" + popup_height + ", resizable=no, scrollbars=yes, status=no, dialog=yes, dependent=yes";
                               popupwin = window.open(url, window_name, window_features);
                //popupwin = window.showModalDialog(url,window_name,"dialogWidth:" +popup_width+"px;" + " dialogHeight:" +popup_height+ "px;"+" dialogLeft:" +popup_left_pos+ "px;"+" dialogTop:" +popup_top_pos+ "px;"+" resizable:no; center:yes");
                window.focus();
                return popupwin;
            }

            function ViewHistoryInfo(ipAddress, port) {
                var queryString = "action1=showAllDetails&ipAddress=" + ipAddress +"&port=" +port;
                var url = "LoggerHistoryCont?" + queryString;
                popupwin = openPopUp(url, "View Details ",580,900);
            }
        </script>
    </head>
    <body>
        <table align="center" cellpadding="0" cellspacing="0" class="main" >
            <!--DWLayoutDefaultTable-->
            <tr><td><%@include file="/layout/header.jsp" %></td></tr>
            <tr><td><%@include file="/layout/menu.jsp" %></td></tr>
            <tr>
                <td>
                    <DIV id="body" class="maindiv">
                        <table cellspacing="0" id="table0"  align="center" >
                            <tr><td><table border="4" class="header_table" width="100%">
                                       <tr style="font-size:larger ;font-weight: 700;" align="center">
                                <td>
                                    Junction Logger History
                                </td>
                            </tr>
                                    </table> </td> </tr>
                           
                            <tr>
                                <td>
                                    <form name="form1" action="LoggerHistoryCont" method="post">
                                        <table name="table1" border="1" width="100%" align="center" class="reference">
                                            <tr>
                                                <th class="heading">IP Address</th>
                                                <th class="heading">Port</th>
                                                <th class="heading">Status</th>
                                                <th class="heading">Login Timestamp Date</th>
                                                <th class="heading">Login Timestamp Time</th>
                                            </tr>
                                            <c:forEach var="list" items="${requestScope['junction']}" varStatus="loopCounter">
                                                <tr onMouseOver=this.style.backgroundColor='#E3ECF3' onmouseout=this.style.backgroundColor='white'>
                                                    <td>${list.ip_address}</td>
                                                    <td>${list.port}</td>
                                                    <td>${list.status eq true ? 'Logged In' : 'Logged Out'}</td>
                                                    <td>${list.login_timestamp_date}</td>
                                                    <td>${list.login_timestamp_time}</td>
                                                    <td><a href="#" onclick="ViewHistoryInfo('${list.ip_address}', ${list.port});">View Details</a></td>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <td align='center' colspan="6">
                                                <c:choose>
                                                    <c:when test="${showFirst eq 'false'}">
                                                       <input type='submit' name='buttonAction' value='First' disabled>
                                                        </c:when>
                                                        <c:otherwise>
                                                       <input type='submit' name='buttonAction' value='First'>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${showPrevious == 'false'}">
                                                        <input type='submit' name='buttonAction' value='Previous' disabled>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <input type='submit' name='buttonAction' value='Previous'>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${showNext eq 'false'}">
                                                       <input type='submit' name='buttonAction' value='Next' disabled>
                                                        </c:when>
                                                        <c:otherwise>
                                                      <input type='submit' name='buttonAction' value='Next'>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${showLast == 'false'}">
                                                       <input type='submit' name='buttonAction' value='Last' disabled>
                                                        </c:when>
                                                        <c:otherwise>
                                                       <input type='submit' name='buttonAction' value='Last'>
                                                        </c:otherwise>
                                                    </c:choose>
                                          </td>  </tr>
                                        </table>
                                        <%-- These hidden fields "lowerLimit", and "noOfRowsTraversed" belong to form1 of table1. --%>
                                        <input type="hidden" name="lowerLimit" value="${lowerLimit}">
                                        <input type="hidden" id="noOfRowsTraversed" name="noOfRowsTraversed" value="${noOfRowsTraversed}">
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </DIV>
                </td>
            </tr>
            <tr><td><%@include file="/layout/footer.jsp" %></td> </tr>
        </table>

    </body>
</html>
