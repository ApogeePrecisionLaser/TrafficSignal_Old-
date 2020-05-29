<%-- 
    Document   : connected_ip
    Created on : Mar 11, 2015, 10:19:39 AM
    Author     : Pooja
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
        <title>Connected Ip</title>
        <script type="text/javascript">

            
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
                                                Connected Ip Details
                                            </td>
                                        </tr>
                                    </table> </td> </tr>

                            <tr>
                                <td>
                                    <form name="form1" action="ConnectedIpCont" method="post">
                                        <table name="table1" border="1" width="100%" align="center" class="reference">
                                            <tr>
                                                <th class="heading">IP Address</th>
                                                <th class="heading">Port</th>
                                                <th class="heading">Status</th>
                                                <th class="heading">TimeStamp</th>
                                            </tr>
                                            <c:forEach var="list" items="${applicationScope['connectedIp']}" varStatus="loopCounter">
                                                <tr onMouseOver=this.style.backgroundColor='#E3ECF3' onmouseout=this.style.backgroundColor='white'>
                                                    <td>${list.ipAddress}</td>
                                                    <td>${list.ipPort}</td>
                                                    <td>${list.ipStatus eq false ? 'Connected' : 'Not Connected'}</td>
                                                    <td>${list.ipLoginTimstamp}</td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </DIV>
                </td>
            </tr>
            <tr><td><%@include file="/layout/footer.jsp" %></td></tr>
        </table>

    </body>
</html>
