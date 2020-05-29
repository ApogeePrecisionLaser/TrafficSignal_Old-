<%-- 
    Document   : logged_in_junction_details
    Created on : Oct 15, 2012, 11:44:45 AM
    Author     : Shruti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Junction Detail</title>
        <link rel="stylesheet" href="style/style.css" media="screen">
        <link rel="stylesheet" href="style/Table_content.css" media="screen">
        <script type="text/javascript" src="JS/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="JS/jquery.autocomplete.js"></script>
        <script type="text/javascript">
           
            function enableMainDiv() {
                document.getElementById("div_animationLoading").style.visibility = 'visible';
                document.getElementById("signalContent").style.visibility = 'hidden';
            }

            function viewJunctionCurrentStatus(junctionId1,b,c,d) {
                //var junctionId = document.getElementById("junctionId").value;
                var junctionId = junctionId1;
                //var junctionName = document.getElementById("junctionName").value;
                var junctionName = b;
                //var program_version_no = document.getElementById("program_version_no").value;
                var program_version_no = c;
                //var fileNo = document.getElementById("fileNo").value;
                var fileNo = d;
                //alert(junctionId+"   "+junctionName);
                var str = "Request for viewing <b>"+junctionName+"</b> signal status sent Successfully";
                document.getElementById("junMsg").innerHTML = str;
                enableMainDiv();
                var queryString = "task=viewJunctionCurrentStatus&junctionId=" + junctionId + "&junctionName=" +junctionName;
                document.forms['form1'].action = "modemResReadSaveCont?task=viewJunctionCurrentStatus&junctionId=" + junctionId + "&junctionName=" +junctionName + "&program_version_no=" +program_version_no+ "&fileNo=" +fileNo;
                document.forms['form1'].submit();
                //                $.ajax({url: "modemResReadSaveCont", data: queryString, success: function(response_data) {
                //                        if(response_data != 'null'){
                ////                            alert(1);
                //                            document.getElementById("message").innerHTML = response_data;
                //                            document.getElementById("message").style.backgroundColor = "red";
                //                            document.getElementById("div_animationLoading").style.visibility = 'hidden';
                //                            document.getElementById("signalContent").style.visibility = 'visible';
                //                        }
                //                    }});
                //                setTimeout("readClientResponse()", 3 * 1000);
            }

            function openCurrentMap(longitude, lattitude) {
            var x = lattitude;//$.trim(doc.getElementById(lattitude).value);
            var y = longitude;//$.trim(doc.getElementById(logitude).value);
            var url="ts_statusShowerCont?task=showAllStatus&logitude="+y+"&lattitude="+x;
            window.open(url);
        //popupwin = openPopUp(url, "",  580, 620);
          }

//            function getCurrentStatus(){
//                var val = document.getElementById("junctionID").value;
//                if(val != ''){
//                    var data = "task=getCurrentStatus&junctionID=" +val;
//                    $.ajax({url: "loggedInJunctionCont", async: true, data: data, success: function(response_data) {
//                            if(response_data == 1){
//                                document.forms['form1'].action = "loggedInJunctionCont";
//                                document.forms['form1'].submit();
//                            }
//                        }
//                    });
//                }
//            }

////            $(document).ready(function() {
//                setTimeout("getCurrentStatus()", 600000);
//            });

            
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
                        <div id="signalContent" style="overflow: auto;max-height: 550px">
                            <table id="table0"  align="center" >
                                <tr><td>
                                        <table border="4" width="100%">
                                            <tr style="font-size:larger ;font-weight: 700;" align="center" class="header_table"><td>Logged In Junction Details</td></tr>
                                        </table>
                                    </td></tr>
                                <tr>
                                    <td>
                                        <input type="button" value="All Junction Status Map" onclick="openCurrentMap()">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <form name="form1" action="loggedInJunctionCont" method="post">
                                            <table id="table1" border="1" width="100%" align="center" class="reference">
                                                <tr>
                                                    <th class="heading">S.No.</th>
                                                    <th class="heading">Junction Name</th>
                                                    <th class="heading" style="white-space: normal">Program Version</th>
                                                    <th class="heading">City Name</th>
                                                    <th class="heading">IP Address</th>
                                                    <th class="heading">Port</th>
                                                    <th class="heading" style="white-space: normal">Last Updated Time</th>
                                                    <th class="heading" style="white-space: normal">Junction Time Set</th>
                                                    <th class="heading" style="white-space: normal">Synchronization<br>Status</th>
                                                    <th class="heading"></th>
                                                </tr>
                                                <c:forEach var="list" items="${requestScope['junction']}" varStatus="loopCounter">
                                                    <tr onMouseOver=this.style.backgroundColor='#E3ECF3' onmouseout=this.style.backgroundColor='white'>
                                                        <td>
                                                            ${loopCounter.count}
                                                        </td>
                                                        <td id="j_name">${list.junction_name}</td>
                                                        <td id="p_version">${list.program_version_no}</td>
                                                        <td id="c_name">${list.city_name}</td>
                                                        <td id="ip_add">${list.ip_address}</td>
                                                        <td id="port">${list.port}</td>
                                                        <td id="a_last_time">${list.application_last_time_set}</td>
                                                        <td id="j_last_time">${list.junction_last_time_set}</td>
                                                        <td id="j_time_status">${list.time_synchronization_status}</td>
                                                        <td><input type="hidden" id="junctionId" name="junctionId" value="${list.junction_id}">
                                                            <input type="hidden" id="junctionName" name="junctionName" value="${list.junction_name}">
                                                            <input type="hidden" id="program_version_no" name="program_version_no" value="${list.program_version_no}">
                                                            <input type="hidden" id="fileNo" name="fileNo" value="${list.fileNo}">
                                                            <input type="button"  onclick="viewJunctionCurrentStatus('${list.junction_id}','${list.junction_name}','${list.program_version_no}','${list.fileNo}');" value="View Junction Current Status">
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                <tr>
                                                    <td colspan="6" id="message"></td>
                                                </tr>
                                                <%-- These hidden fields "lowerLimit", and "noOfRowsTraversed" belong to form1 of table1. --%>

                                                <input type="hidden" name="junctionID" id="junctionID" value="${junctionID}">
                                            </table>

                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </DIV>
                </td>
            </tr>
            <tr><td><%@include file="/layout/footer.jsp" %></td> </tr>
        </table>

        <div id="div_animationLoading" style="height: 0px; display: none; vertical-align: middle;visibility: hidden">
            <table align="center" style="vertical-align: middle;">
                <tr>
                    <td align="center">
                        <img src="./images/animated.gif" >
                    </td>
                </tr>
                <tr>
                    <td id="junMsg">
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
