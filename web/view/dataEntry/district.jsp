<%-- 
    Document   : district
    Created on : Aug 14, 2012, 11:01:10 AM
    Author     : Shruti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Data Entry: District Table</title>
        <link href="style/style.css" type="text/css" rel="stylesheet" media="Screen"/>
        <link href="style/Table_content.css" type="text/css" rel="stylesheet" media="Screen"/>
        <script type="text/javascript" src="JS/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="JS/jquery.autocomplete.js"></script>
        <script type="text/javascript" language="javascript">
            jQuery(function(){
                $("#state_name").autocomplete("stateNameCont");
                $("#state_name").result(function(event, data, formatted){
                    document.getElementById("district_name").value = "";
                    $("#district_name").flushCache();
                });
                $("#district_name").autocomplete("districtNameCont", {
                    extraParams: {
                        state_name: function() { return document.getElementById("state_name").value;},
                        caller: function() { return "district";}
                    }
                });
            });

            function makeEditable(id) {
                document.getElementById("district_id").disabled = false;
                document.getElementById("district_name").disabled = false;
                document.getElementById("state_name").disabled = false;
                if(id == 'new') {
                    document.getElementById("message").innerHTML = "";      // Remove message
                    document.getElementById("edit").disabled = true;
                    document.getElementById("delete").disabled = true;
                    document.getElementById("save_As").disabled = true;
                    setDefaultColor(document.getElementById("noOfRowsTraversed").value, 4);
                    document.getElementById("state_name").focus();
                }
                if(id == 'edit') {
                    document.getElementById("save_As").disabled = false;
                    document.getElementById("delete").disabled = false;
                }
                document.getElementById("save").disabled = false;
            }
            function setDefaultColor(noOfRowsTraversed, noOfColumns) {
                for(var i = 0; i < noOfRowsTraversed; i++) {
                    for(var j = 1; j <= noOfColumns; j++) {
                        document.getElementById("t1c" + (i * noOfColumns + j)).bgColor = "";     // set the default color.
                    }
                }
            }
            function fillColumns(id) {
                var noOfRowsTraversed = document.getElementById("noOfRowsTraversed").value;
                var noOfColumns = 4;
                var columnId = id;                              <%-- holds the id of the column being clicked, excluding the prefix t1c e.g. t1c3 (column 3 of table 1). --%>
                columnId = columnId.substring(3, id.length);    <%-- for e.g. suppose id is t1c3 we want characters after t1c i.e beginIndex = 3. --%>
                var lowerLimit, higherLimit;
                for(var i = 0; i < noOfRowsTraversed; i++) {
                    lowerLimit = i * noOfColumns + 1;       // e.g. 11 = (1 * 10 + 1)
                    higherLimit = (i + 1) * noOfColumns;    // e.g. 20 = ((1 + 1) * 10)
                    if((columnId >= lowerLimit) && (columnId <= higherLimit)) break;
                }
                setDefaultColor(noOfRowsTraversed, noOfColumns);        // set default color of rows (i.e. of multiple coloumns).
                var t1id = "t1c";       // particular column id of table 1 e.g. t1c3.
                for(var i = 0; i < noOfColumns; i++) {
                    // set the background color of clicked/selected row to yellow.
                    document.getElementById(t1id + (lowerLimit + i)).bgColor = "yellowgreen";
                }
                // Now get clicked row data, and set these into the below edit table.
                document.getElementById("district_id").value = document.getElementById(t1id + (lowerLimit + 0)).innerHTML;
                document.getElementById("district_name").value = document.getElementById(t1id + (lowerLimit + 2)).innerHTML;
                document.getElementById("state_name").value = document.getElementById(t1id + (lowerLimit + 3)).innerHTML;
                // Now enable/disable various buttons.
                document.getElementById("edit").disabled = false;
                if(!document.getElementById("save").disabled) {
                    // if save button is already enabled, then make edit, and delete button enabled too.
                    document.getElementById("delete").disabled = false;
                    document.getElementById("save_As").disabled = true;
                }
                document.getElementById("message").innerHTML = "";      // Remove message
            }
            function setStatus(id) {
                if(id == 'save') {
                    document.getElementById("clickedButton").value = "Save";
                }
                else if(id == 'save_As'){
                    document.getElementById("clickedButton").value = "Save AS New";
                }
                else {
                    document.getElementById("clickedButton").value = "Delete";;
                }
            }
            function myLeftTrim(str) {
                var beginIndex = 0;
                for(var i = 0; i < str.length; i++) {
                    if(str.charAt(i) == ' ')
                        beginIndex++;
                    else break;
                }
                return str.substring(beginIndex, str.length);
            }
            function verify() {
                var result;
                if(document.getElementById("clickedButton").value == 'Save' || document.getElementById("clickedButton").value == 'Save AS New') {
                    var state_name = document.getElementById("state_name").value;
                    if(myLeftTrim(state_name).length == 0) {
                        document.getElementById("message").innerHTML = "<td colspan='5' bgcolor='coral'><b>State Name is required...</b></td>";
                        document.getElementById("state_name").focus();
                        return false; // code to stop from submitting the form2.
                    }
                    var district_name = document.getElementById("district_name").value;
                    if(myLeftTrim(district_name).length == 0) {
                        document.getElementById("message").innerHTML = "<td colspan='5' bgcolor='coral'><b>District Name is required...</b></td>";
                        document.getElementById("district_name").focus();
                        return false; // code to stop from submitting the form2.
                    }
                    if(result == false) {
                        // if result has value false do nothing, so result will remain contain value false.
                    } else {
                        result = true;
                    }

                } else {
                    result = confirm("Are you sure you want to delete this record?");
                }
                return result;
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
                        <table align="center" width="100%"border="0" cellpadding="0" cellspacing="0" >
                            <tr><td>
                                    <table border="4" class="header_table" width="100%">
                                        <tr style="font-size:larger ;font-weight: 700;" align="center">
                                            <td>
                                                District Table
                                            </td>
                                        </tr>
                                    </table>
                                </td> </tr>

                            <tr>
                                <td align="center">
                                    <form name="form1" method="POST" action="districtCont">
                                        <DIV STYLE="overflow: auto; width: 500px; max-height: 410px; padding:0px; margin-bottom: 20px" >
                                            <table  border="1" id="table1" align="center" class="reference">
                                                <tr>
                                                    <th class="heading" style="visibility: hidden"><!-- District ID--></th>
                                                    <th class="heading">S.No.</th>
                                                    <th class="heading" colspan="2">District Name</th>
                                                    <th class="heading">State Name</th>
                                                </tr>
                                                <c:forEach var="district" items="${requestScope['districtList']}" varStatus="loopCounter">
                                                    <tr class="row" onMouseOver=this.style.backgroundColor='#E3ECF3' onmouseout=this.style.backgroundColor='white'>
                                                        <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" style="visibility: hidden">${district.district_id}</td>
                                                        <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" align="center">${lowerLimit - noOfRowsTraversed + loopCounter.count}</td>
                                                        <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)" colspan="2">${district.district_name}</td>
                                                        <td id="t1c${IDGenerator.uniqueID}"  onclick="fillColumns(id)">${district.state_name}</td>
                                                    </tr>
                                                </c:forEach>
                                                <tr>
                                                    <td align='center' colspan="6">
                                                        <c:choose>
                                                            <c:when test="${showFirst eq 'false'}">
                                                                <input class="button" type='submit' name='buttonAction' value='First' disabled>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input class="button" type='submit' name='buttonAction' value='First'>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when test="${showPrevious == 'false'}">
                                                                <input class="button" type='submit' name='buttonAction' value='Previous' disabled>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input class="button" type='submit' name='buttonAction' value='Previous'>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when test="${showNext eq 'false'}">
                                                                <input class="button" type='submit' name='buttonAction' value='Next' disabled>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input class="button" type='submit' name='buttonAction' value='Next'>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when test="${showLast == 'false'}">
                                                                <input class="button" type='submit' name='buttonAction' value='Last' disabled>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input class="button" type='submit' name='buttonAction' value='Last'>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td> </tr>
                                                    <%-- These hidden fields "lowerLimit", and "noOfRowsTraversed" belong to form1 of table1. --%>
                                                <input type="hidden" name="lowerLimit" value="${lowerLimit}">
                                                <input type="hidden" id="noOfRowsTraversed" name="noOfRowsTraversed" value="${noOfRowsTraversed}">
                                            </table>
                                        </DIV>
                                    </form>
                                </td>
                            <tr>
                                <td align="center">
                                    <form name="form2" method="POST" action="districtCont" onsubmit="return verify()">
                                        <DIV STYLE="overflow: auto; width: 380px; padding:0px; margin-bottom: 20px" >
                                            <table  border="1" id="table2" align="center" class="reference" >
                                                <tr id="message">
                                                    <c:if test="${not empty message}">
                                                        <td colspan="8" bgcolor="${msgBgColor}"><b>Result: ${message}</b></td>
                                                    </c:if>
                                                </tr>

                                                <tr>

                                                    <th class="heading">State Name</th>
                                                    <td><input class="input" type="text" id="state_name" name="state_name" size="30" value="" disabled></td>
                                                </tr><tr>
                                                    <th class="heading">District Name</th>
                                                    <td><input class="input" type="text" id="district_name" name="district_name" size="30" value="" disabled></td>
                                                </tr>


                                                <tr>
                                                    <td align='center' colspan="2">
                                                        <input class="button" type="button" name="edit" id="edit" value="Edit" onclick="makeEditable(id)" disabled>
                                                        <input class="button" type="submit" name="task" id="save" value="Save" onclick="setStatus(id)" disabled>
                                                        <input class="button" type="submit" name="task" id="save_As" value="Save AS New" onclick="setStatus(id)" disabled>  
                                                        <input class="button" type="reset" name="new" id="new" value="New" onclick="makeEditable(id)"> 
                                                        <input class="button" type="submit" name="task" id="delete" value="Delete" onclick="setStatus(id)" disabled>
                                                    </td>
                                                </tr>
                                                <%-- These hidden fields "lowerLimit", "noOfRowsTraversed", and "clickedButton" belong to form2 of table2. --%>
                                                <input  type="hidden" id="district_id" name="district_id" value="" disabled>
                                                <input type="hidden" name="lowerLimit" value="${lowerLimit}">
                                                <input type="hidden" name="noOfRowsTraversed" value="${noOfRowsTraversed}">
                                                <input type="hidden" id="clickedButton" value="">
                                            </table>
                                        </DIV>
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

