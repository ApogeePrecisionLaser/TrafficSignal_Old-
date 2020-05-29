<%--
    Document   : city
    Created on : Aug 14, 2012, 9:47:05 AM
    Author     : Shruti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Data Entry: City Table</title>
        <link href="style/style.css" type="text/css" rel="stylesheet" media="Screen"/>
        <link href="style/Table_content.css" type="text/css" rel="stylesheet" media="Screen"/>
        <%-- <link rel="stylesheet" type="text/css" href="css/jquery-ui.css"/> --%>

        <script type="text/javascript" src="JS/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="JS/jquery.autocomplete.js"></script>
        <%-- <script type="text/javascript" src="JS/jquery-ui.min.js"></script> --%>
        <script type="text/javascript" language="javascript">
            jQuery(function(){
                $("#city_name").autocomplete("cityNameCont", {
                    extraParams: {
                        state_name: function() { return document.getElementById("state_name").value;},
                        district_name: function() { return document.getElementById("district_name").value;},
                        desiredOutput: function() { return "filteredCity";}
                    }
                });
                $("#state_name").autocomplete("stateNameCont");
                $("#district_name").autocomplete("districtNameCont", {
                    extraParams: {
                        state_name: function() { return document.getElementById("state_name").value;}
                    }
                });
                $("#state_name").result(function(event, data, formatted){
                    document.getElementById("district_name").value = "";
                    document.getElementById("city_name").value = "";
                    $("#district_name").flushCache();
                    $("#city_name").flushCache();
                });
                $("#district_name").result(function(event, data, formatted){
                    document.getElementById("city_name").value = "";
                    $("#city_name").flushCache();
                });
            });

            function makeEditable(id) {
                document.getElementById("city_id").disabled = false;
                document.getElementById("state_name").disabled = false;
                document.getElementById("district_name").disabled = false;
                document.getElementById("city_name").disabled = false;
                document.getElementById("std_code").disabled = false;
                document.getElementById("pin_code").disabled = false;
                if(id == 'new') {
                    $("#message").html('');
                    //                    document.getElementById("message").innerHTML = "";      // Remove message
                    document.getElementById("edit").disabled = true;
                    document.getElementById("delete").disabled = true;
                    document.getElementById("save_As").disabled = true;
                    setDefaultColor(document.getElementById("noOfRowsTraversed").value, 7);
                    document.getElementById("state_name").focus();
                }
                if(id == 'edit') {
                    document.getElementById("save_As").disabled = false;
                    document.getElementById("delete").disabled = false;
                    document.getElementById("state_name").focus();
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
                var noOfColumns = 7;
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
                document.getElementById("city_id").value = document.getElementById(t1id + (lowerLimit + 0)).innerHTML;
                document.getElementById("city_name").value = document.getElementById(t1id + (lowerLimit + 2)).innerHTML;
                document.getElementById("district_name").value = document.getElementById(t1id + (lowerLimit + 3)).innerHTML;
                document.getElementById("state_name").value = document.getElementById(t1id + (lowerLimit + 4)).innerHTML;
                document.getElementById("pin_code").value = document.getElementById(t1id + (lowerLimit + 5)).innerHTML;
                document.getElementById("std_code").value = document.getElementById(t1id + (lowerLimit + 6)).innerHTML;
                // Now enable/disable various buttons.
                document.getElementById("edit").disabled = false;
                if(!document.getElementById("save").disabled) {
                    // if save button is already enabled, then make edit, and delete button enabled too.
                    document.getElementById("delete").disabled = false;
                    document.getElementById("save_As").disabled = true;
                }
                $("#message").html('');      // Remove message
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
                    if(str.charAt(i) == ' ') {
                        beginIndex++;
                    } else {
                        break;
                    }
                }
                return str.substring(beginIndex, str.length);
            }
            function verify() {
                var result;
                if(document.getElementById("clickedButton").value == 'Save' || document.getElementById("clickedButton").value == 'Save AS New') {
                    var state_name = document.getElementById("state_name").value;
                    if(myLeftTrim(state_name).length == 0) {
                        document.getElementById("message").innerHTML = "<td colspan='6' bgcolor='coral'><b>State Name is required...</b></td>";
                        document.getElementById("state_name").focus();
                        return false; // code to stop from submitting the form2.
                    }
                    var district_name = document.getElementById("district_name").value;
                    if(myLeftTrim(district_name).length == 0) {
                        document.getElementById("message").innerHTML = "<td colspan='6' bgcolor='coral'><b>District Name is required...</b></td>";
                        document.getElementById("district_name").focus();
                        return false; // code to stop from submitting the form2.
                    }
                    var city_name = document.getElementById("city_name").value;
                    if(myLeftTrim(city_name).length == 0) {
                        document.getElementById("message").innerHTML = "<td colspan='6' bgcolor='coral'><b>City Name is required...</b></td>";
                        document.getElementById("city_name").focus();
                        return false; // code to stop from submitting the form2.
                    }
                    var pin_code = parseInt(document.getElementById("pin_code").value, 10);
                    if(isNaN(pin_code)) {
                        document.getElementById("message").innerHTML = "<td colspan='6' bgcolor='coral'><b>Invalid Pin Code...</b></td>";
                        document.getElementById("pin_code").focus();
                        return false; // code to stop from submitting the form2.
                    } else {
                        document.getElementById("pin_code").value = pin_code;
                        pin_code = document.getElementById("pin_code").value;
                        if(pin_code.length < 6) {
                            document.getElementById("message").innerHTML = "<td colspan='6' bgcolor='coral'><b>PIN Code must be of 6 characters.</b></td>";
                            document.getElementById("pin_code").focus();
                            return false; // code to stop from submitting the form2.
                        } else {
                            document.getElementById("pin_code").value = pin_code;
                        }
                    }
                    var std_code = parseInt(document.getElementById("std_code").value, 10);
                    if(isNaN(std_code)) {
                        document.getElementById("message").innerHTML = "<td colspan='6' bgcolor='coral'><b>Invalid STD Code...</b></td>";
                        document.getElementById("std_code").focus();
                        return false; // code to stop from submitting the form2.
                    } else {
                        document.getElementById("std_code").value = "0" + std_code;
                    }
                    if(result == false) {
                        // if result has value false do nothing, so result will remain contain value false.
                    } else {
                        result = true;
                    }
                    if(document.getElementById("clickedButton").value == 'Save AS New'){
                        result = confirm("Are you sure you want to save it as New record?")
                        return result;
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
                        <table align="center" width="1000" border="0" cellpadding="0" cellspacing="0" >
                            <tr><td><table border="4" class="header_table" width="100%">
                                        <tr style="font-size:larger ;font-weight: 700;" align="center">
                                            <td>
                                                City Table
                                            </td>
                                        </tr>
                                    </table> </td> </tr>

                            <tr>
                                <td>
                                    <table id="table0"  align="center" width="500">
                                        <tr>
                                            <td>
                                                <form name="form1" method="POST" action="CityCont">
                                                    <DIV STYLE="overflow: auto;  max-height: 410px; padding:0px; margin-bottom: 20px">
                                                        <table border="1" id="table1" align="center" class="reference">
                                                            <tr>
                                                                <th class="heading" style="visibility: hidden"><!-- City ID --></th>
                                                                <th class="heading" >S.No.</th>
                                                                <th class="heading" >City Name</th>
                                                                <th class="heading" >District</th>
                                                                <th class="heading" >State</th>
                                                                <th class="heading" >PIN Code</th>
                                                                <th class="heading" >STD Code</th>
                                                            </tr>
                                                            <c:forEach var="city" items="${requestScope['cityList']}" varStatus="loopCounter">
                                                                <tr class="row" onMouseOver=this.style.backgroundColor='#E3ECF3' onmouseout=this.style.backgroundColor='white'>
                                                                    <td id="t1c${IDGenerator.uniqueID}" style="visibility: hidden" onclick="fillColumns(id)">${city.city_id}</td>
                                                                    <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)" align="center">${lowerLimit - noOfRowsTraversed + loopCounter.count}</td>
                                                                    <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)">${city.city_name}</td>
                                                                    <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)">${city.district_name}</td>
                                                                    <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)">${city.state_name}</td>
                                                                    <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)">${city.pin_code}</td>
                                                                    <td id="t1c${IDGenerator.uniqueID}" onclick="fillColumns(id)">${city.std_code}</td>
                                                                </tr>
                                                            </c:forEach>
                                                            <tr>
                                                                <td align='center' colspan="8">
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
                                                                </td>
                                                            </tr>
                                                            <%-- These hidden fields "lowerLimit", and "noOfRowsTraversed" belong to form1 of table1. --%>
                                                            <input type="hidden" name="lowerLimit" value="${lowerLimit}">
                                                            <input type="hidden" id="noOfRowsTraversed" name="noOfRowsTraversed" value="${noOfRowsTraversed}">
                                                        </table>
                                                    </DIV>
                                                </form>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <form name="form2" method="POST" action="CityCont" onsubmit="return verify()">

                                                    <table border="1"  id="table2" align="center"  class="reference">
                                                        <tr id="message">
                                                            <c:if test="${not empty message}">
                                                                <td colspan="8" bgcolor="${msgBgColor}"><b>Result: ${message}</b></td>
                                                            </c:if>
                                                        </tr>

                                                        <tr>
                                                            <th class="heading" >State</th><td><input class="input" type="text" id="state_name" name="state_name" value="" size="20" disabled></td>
                                                            <th class="heading" >District</th><td><input class="input" type="text" id="district_name" name="district_name" value="" size="25" disabled></td>
                                                            <th class="heading" >City_Name</th><td><input class="input" type="text" id="city_name" name="city_name" value="" size="20" disabled></td>
                                                        </tr>
                                                        <tr>
                                                            <th class="heading" >PIN Code</th><td><input class="input" type="text" id="pin_code" name="pin_code" value="" maxlength="6" size="20" disabled></td>
                                                            <th class="heading" >STD_Code</th><td colspan="3"><input class="input" type="text" id="std_code" name="std_code" value="" maxlength="5" size="20" disabled></td>
                                                        </tr>


                                                        <tr>
                                                            <td align='center' colspan="6">
                                                                <input class="button" type="button" name="edit" id="edit" value="Edit" onclick="makeEditable(id)" disabled> &nbsp;&nbsp;&nbsp;&nbsp;
                                                                <input class="button" type="submit" name="task" id="save" value="Save" onclick="setStatus(id)" disabled>&nbsp;&nbsp;&nbsp;&nbsp;
                                                                <input class="button" type="submit" name="task" id="save_As" value="Save AS New" onclick="setStatus(id)" disabled>  &nbsp;&nbsp;&nbsp;&nbsp;
                                                                <input class="button" type="reset" name="new" id="new" value="New" onclick="makeEditable(id)">&nbsp;&nbsp;&nbsp;&nbsp;
                                                                <input class="button" type="submit" name="task" id="delete" value="Delete" onclick="setStatus(id)" disabled>
                                                            </td>
                                                        </tr>
                                                        <%-- These hidden fields "lowerLimit", "noOfRowsTraversed", and "clickedButton" belong to form2 of table2. --%>
                                                        <input type="hidden" id="city_id" name="city_id" value="" disabled>
                                                        <input type="hidden" name="lowerLimit" value="${lowerLimit}">
                                                        <input type="hidden" name="noOfRowsTraversed" value="${noOfRowsTraversed}">
                                                        <input type="hidden" id="clickedButton" value="">
                                                    </table>

                                                </form>
                                            </td>
                                        </tr>
                                    </table>
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

