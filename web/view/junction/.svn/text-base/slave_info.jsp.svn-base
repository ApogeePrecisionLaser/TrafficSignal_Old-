<%-- 
    Document   : slave_info
    Created on : Aug 14, 2012, 11:25:00 AM
    Author     : Shruti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="style/style.css" />
<link rel="stylesheet" type="text/css" href="style/Table_content.css" />
<script type="text/javascript" src="JS/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="JS/jquery.autocomplete.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" language="javascript">

    function verify() {
        var no_of_sides = document.getElementById("no_of_sides").value;
       // alert(no_of_sides);
        if(document.getElementById("SAVE").value == 'SAVE') {
            for(var i = 1; i<= parseInt(no_of_sides); i++) {
                var slave_id= $("#slave_id"+i).val();
               // alert(slave_id);
                if($.trim(slave_id).length == 0) {
                    var message = "<td colspan='6' bgcolor='coral'><b>Slave Id is required...</b></td>";
                    $("#message").html(message);
                    document.getElementById("slave_id"+i).focus();
                    return false; // code to stop from submitting the form2.
                }
            }
           // alert(result);
           return confirm("Do you want to save slave ids ?");
        }
    }


  
    
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Slave Detail</title>
    </head>
    <body>
        <table cellspacing="0" border="0" id="table0"  align="center" width="500">
            <tr style="font-size:larger ;font-weight: 700;" align="center">
                <td class="heading">
                    ${slave_info_name} slave info details
                </td>
            </tr>
            <tr><td></td></tr>
            <tr id="message">
                <c:if test="${not empty message}">
                    <td colspan="8" bgcolor="${msgBgColor}"><b>Result: ${message}</b></td>
                </c:if>
            </tr>
            <tr>
                <td align="center">
                    <form name="form1" action="slaveInfoCont" method="post" onsubmit="return verify()">
                        <table id="dataTable" style="border-collapse: collapse;" border="1" width="100%" align="center">
                            <tbody>
                                <tr>
                                    <th  class="heading">Side No</th>
                                    <th  class="heading">Side Name</th>
                                    <th  class="heading">Slave Id</th>
                                </tr>
                                <c:forEach var="list" items="${requestScope['slaveinfo']}" varStatus="loopCounter">
                                    <tr>
                                        <td><input class="input" type="text" name="side_no${loopCounter.count}" id="side_no${loopCounter.count}" size="3" value="${list.side_no}" style="background-color: #c3c3c3" readonly>
                                            <input type="hidden" name="side_revision_no${loopCounter.count}" id="side_revision_no${loopCounter.count}" value="${list.side_revision_no}"></td>
                                        <td><input class="input" type="text" name="side_name${loopCounter.count}" id="side_name${loopCounter.count}" value="${list.sideName}" style="background-color: #c3c3c3" readonly></td>
                                        <td><input class="input" type="text" name="slave_id${loopCounter.count}" id="slave_id${loopCounter.count}" value="${list.slave_id}" maxlength="8"></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <input class="button" type="submit" id="SAVE" name="task" value="SAVE">
                        <input type="hidden" id="junction_id" name="junction_id" value="${param.junction_id}">
                        <input type="hidden" id="program_version_no" name="program_version_no" value="${param.program_version_no}">
                        <input type="hidden" id="no_of_sides" name="no_of_sides" value="${param.no_of_sides}">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>

