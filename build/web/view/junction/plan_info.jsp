<%--
    Document   : plan_info
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
        var result;
        var no_of_plans = document.getElementById("no_of_plans").value;
        //   alert(no_of_plans);
        if(document.getElementById("SAVE").value == 'SAVE') {
            for(var i = 1; i <= no_of_plans; i++) {
                var mode = document.getElementById("mode" + i).value;
                //                alert(mode);
                if(mode == 'Signal' || mode == 'Blinker'){
                    //                    alert(mode == 'Signal');
                    var no_of_sides = document.getElementById("no_of_sides").value;

                    if(no_of_sides == 2){
                        var side1_green_time = document.getElementById("side1_green_time" + i).value;
                        //                        alert(document.getElementById("side1_green_time" + i).value);
                        if($.trim(side1_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side1 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side1_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side2_green_time = document.getElementById("side2_green_time" + i).value;
                        //                        alert( document.getElementById("side2_green_time" + i).value);
                        if($.trim(side2_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side2 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side2_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }

                        var side1_amber_time = document.getElementById("side1_amber_time" + i).value;
                        //                        alert(document.getElementById("side1_green_time" + i).value);
                        if($.trim(side1_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side1 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side1_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side2_amber_time = document.getElementById("side2_amber_time" + i).value;
                        //                        alert( document.getElementById("side2_green_time" + i).value);
                        if($.trim(side2_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side2 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side2_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }

                    }else if(no_of_sides == 3){
                        var side1_green_time = document.getElementById("side1_green_time" + i).value;
                        //                        alert(document.getElementById("side1_green_time" + i).value);
                        if($.trim(side1_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side1 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side1_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side2_green_time = document.getElementById("side2_green_time" + i).value;
                        //                        alert( document.getElementById("side2_green_time" + i).value);
                        if($.trim(side2_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side2 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side2_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side3_green_time = document.getElementById("side3_green_time" + i).value;
                        //                        alert(document.getElementById("side3_green_time" + i).value);
                        if($.trim(side3_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side3 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side3_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }

                        var side1_amber_time = document.getElementById("side1_amber_time" + i).value;
                        //                        alert(document.getElementById("side1_green_time" + i).value);
                        if($.trim(side1_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side1 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side1_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side2_amber_time = document.getElementById("side2_amber_time" + i).value;
                        //                        alert( document.getElementById("side2_green_time" + i).value);
                        if($.trim(side2_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side2 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side2_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side3_amber_time = document.getElementById("side3_amber_time" + i).value;
                        //                        alert(document.getElementById("side3_green_time" + i).value);
                        if($.trim(side3_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side3 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side3_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                    }else if(no_of_sides == 4){
                        var side1_green_time = document.getElementById("side1_green_time" + i).value;
                        //                        alert(document.getElementById("side1_green_time" + i).value);
                        if($.trim(side1_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side1 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side1_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side2_green_time = document.getElementById("side2_green_time" + i).value;
                        //                        alert( document.getElementById("side2_green_time" + i).value);
                        if($.trim(side2_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side2 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side2_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side3_green_time = document.getElementById("side3_green_time" + i).value;
                        //                        alert(document.getElementById("side3_green_time" + i).value);
                        if($.trim(side3_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side3 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side3_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side4_green_time = document.getElementById("side4_green_time" + i).value;
                        //                        alert(document.getElementById("side4_green_time" + i).value);
                        if($.trim(side4_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side4 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side4_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }

                        var side1_amber_time = document.getElementById("side1_amber_time" + i).value;
                        //                        alert(document.getElementById("side1_green_time" + i).value);
                        if($.trim(side1_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side1 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side1_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side2_amber_time = document.getElementById("side2_amber_time" + i).value;
                        //                        alert( document.getElementById("side2_green_time" + i).value);
                        if($.trim(side2_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side2 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side2_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side3_amber_time = document.getElementById("side3_amber_time" + i).value;
                        //                        alert(document.getElementById("side3_green_time" + i).value);
                        if($.trim(side3_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side3 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side3_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side4_amber_time = document.getElementById("side4_amber_time" + i).value;
                        //                        alert(document.getElementById("side4_green_time" + i).value);
                        if($.trim(side4_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side4 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side4_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                    }else{
                        var side1_green_time = document.getElementById("side1_green_time" + i).value;
                        //                        alert(document.getElementById("side1_green_time" + i).value);
                        if($.trim(side1_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side1 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side1_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side2_green_time = document.getElementById("side2_green_time" + i).value;
                        //                        alert( document.getElementById("side2_green_time" + i).value);
                        if($.trim(side2_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side2 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side2_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side3_green_time = document.getElementById("side3_green_time" + i).value;
                        //                        alert(document.getElementById("side3_green_time" + i).value);
                        if($.trim(side3_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side3 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side3_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side4_green_time = document.getElementById("side4_green_time" + i).value;
                        //                        alert(document.getElementById("side4_green_time" + i).value);
                        if($.trim(side4_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side4 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side4_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side5_green_time = document.getElementById("side5_green_time" + i).value;
                        //                        alert(document.getElementById("side5_green_time" + i).value);
                        if($.trim(side5_green_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side5 Green Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side5_green_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }

                        var side1_amber_time = document.getElementById("side1_amber_time" + i).value;
                        //                        alert(document.getElementById("side1_green_time" + i).value);
                        if($.trim(side1_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side1 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side1_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side2_amber_time = document.getElementById("side2_amber_time" + i).value;
                        //                        alert( document.getElementById("side2_green_time" + i).value);
                        if($.trim(side2_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side2 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side2_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side3_amber_time = document.getElementById("side3_amber_time" + i).value;
                        //                        alert(document.getElementById("side3_green_time" + i).value);
                        if($.trim(side3_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side3 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side3_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                        var side4_amber_time = document.getElementById("side4_amber_time" + i).value;
                        //                        alert(document.getElementById("side4_green_time" + i).value);
                        if($.trim(side4_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side4 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side4_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }

                        var side5_amber_time = document.getElementById("side5_amber_time" + i).value;
                        //                        alert(document.getElementById("side4_green_time" + i).value);
                        if($.trim(side5_amber_time).length == 0) {
                            var message = "<td colspan='6' bgcolor='coral'><b>Side5 Amber Time is required...</b></td>";
                            $("#message").html(message);
                            document.getElementById("side5_amber_time" + i).focus();
                            return false; // code to stop from submitting the form2.
                        }
                    }
                }
                var on_time_hour = document.getElementById("on_time_hour" + (i)).value;
                //alert(document.getElementById("on_time_hour" + (i+1)).value);
                if($.trim(on_time_hour).length == 0) {
                    // alert($.trim(on_time_hour).length == 0);
                    var message = "<td colspan='6' bgcolor='coral'><b>ON Time Hour is required...</b></td>";
                    // alert(message);
                    $("#message").html(message);
                    document.getElementById("on_time_hour" + (i)).focus();
                    return false;
                    // code to stop from submitting the form2.
                }
                var on_time_min = document.getElementById("on_time_min" + (i)).value;
                // alert(document.getElementById("on_time_min" + (i+1)).value);
                if($.trim(on_time_min).length == 0) {
                    var message = "<td colspan='6' bgcolor='coral'><b>ON Time Min is required...</b></td>";
                    $("#message").html(message);
                    document.getElementById("on_time_min" + (i)).focus();
                    return false; // code to stop from submitting the form2.
                }
                var off_time_hour = document.getElementById("off_time_hour" + (i)).value;
                //                alert(document.getElementById("off_time_hour" + (i+1)).value);
                if($.trim(off_time_hour).length == 0) {
                    var message = "<td colspan='6' bgcolor='coral'><b>OFF Time Hour is required...</b></td>";
                    $("#message").html(message);
                    document.getElementById("off_time_hour" + (i)).focus();
                    return false; // code to stop from submitting the form2.
                }
                var off_time_min = document.getElementById("off_time_min" + (i)).value;
                //  alert(document.getElementById("off_time_min" + (i+1)).value);
                if($.trim(off_time_min).length == 0) {
                    var message = "<td colspan='6' bgcolor='coral'><b>OFF Time Min is required...</b></td>";
                    $("#message").html(message);
                    document.getElementById("off_time_min" + (i)).focus();
                    return false; // code to stop from submitting the form2.
                }
                //  alert(no_of_plans);
                //  alert(i);
                if(i<no_of_plans && off_time_hour ==23 && off_time_min==59) {
                    var message = "<td colspan='6' bgcolor='coral'><b>Plan"+i+" Off Time  23 Hrs and Off Time 59 minuets  can not be allow  for this row. This time is allow  only for last row ...</b></td>";
                    $("#message").html(message);
                    document.getElementById("off_time_hour" + i).focus();
                    return false;
                }

                if(i== no_of_plans) {
                    var last_off_time_hour = document.getElementById("off_time_hour" + i).value;
                    var last_off_time_min = document.getElementById("off_time_min" + i).value;
                    // alert(i+"  "+last_off_time_hour+"  "+last_off_time_min);
                    if(last_off_time_hour != 23) {
                        var message = "<td colspan='6' bgcolor='coral'><b>Plan"+i+" Off Time hr must be equal to 23 hrs...</b></td>";
                        $("#message").html(message);
                        document.getElementById("off_time_hour" + i).focus();
                        return false; // code to stop from submitting the form2
                    }
                    if(last_off_time_min != 59) {
                        var message = "<td colspan='6' bgcolor='coral'><b>Plan"+i+" Off Time min must  be equal to 59 minutes...</b></td>";
                        //  var message = "<td colspan='6' bgcolor='coral'><b>Plan"+(i+1)+" Off Time min must not be greater than 59 minutes...</b></td>";
                        $("#message").html(message);
                        document.getElementById("off_time_min" + i).focus();
                        return false; // code to stop from submitting the form2
                    }
                    /*  if(last_off_time_hour == 23 && last_off_time_min == 59){
                      document.getElementById("add_plan").disabled = true;
                  } */
                }

            }
        }

        //        alert(result);
        return result;
    }

    function setDropdownVisibility(id){
        var no_of_sides = $("#no_of_sides").val();
        //        alert(no_of_sides);
        //        alert(id);
        //        //        alert(id.length);
        //        var rowNum = id.substr(4, id.length);
        //        alert(rowNum);
        //        alert(document.getElementById("mode" + id).value);
        var mode = document.getElementById("mode" + id).value;
        //       alert(mode);
        if(mode == 'Signal' || mode == 'Blinker'){
            document.getElementById("side1_green_time" + id).disabled = false;
            document.getElementById("side2_green_time" + id).disabled = false;
            document.getElementById("side1_green_time" + id).style.backgroundColor = "";
            document.getElementById("side2_green_time" + id).style.backgroundColor = "";
            document.getElementById("side1_amber_time" + id).disabled = false;
            document.getElementById("side2_amber_time" + id).disabled = false;
            document.getElementById("side1_amber_time" + id).style.backgroundColor = "";
            document.getElementById("side2_amber_time" + id).style.backgroundColor = "";
            if(no_of_sides == '2'){
                document.getElementById("side3_green_time" + id).value = "";
                document.getElementById("side4_green_time" + id).value = "";
                document.getElementById("side5_green_time" + id).value = "";
                document.getElementById("side3_green_time" + id).disabled = true;
                document.getElementById("side4_green_time" + id).disabled = true;
                document.getElementById("side5_green_time" + id).disabled = true;
                document.getElementById("side3_green_time" + id).style.backgroundColor = "lightgrey";
                document.getElementById("side4_green_time" + id).style.backgroundColor = "lightgrey";
                document.getElementById("side5_green_time" + id).style.backgroundColor = "lightgrey";
                document.getElementById("side3_amber_time" + id).value = "";
                document.getElementById("side4_amber_time" + id).value = "";
                document.getElementById("side5_amber_time" + id).value = "";
                document.getElementById("side3_amber_time" + id).disabled = true;
                document.getElementById("side4_amber_time" + id).disabled = true;
                document.getElementById("side5_amber_time" + id).disabled = true;
                document.getElementById("side3_amber_time" + id).style.backgroundColor = "lightgrey";
                document.getElementById("side4_amber_time" + id).style.backgroundColor = "lightgrey";
                document.getElementById("side5_amber_time" + id).style.backgroundColor = "lightgrey";
            }else if(no_of_sides == '3'){
                document.getElementById("side4_green_time" + id).value = "";
                document.getElementById("side5_green_time" + id).value = "";
                document.getElementById("side3_green_time" + id).disabled = false;
                document.getElementById("side4_green_time" + id).disabled = true;
                document.getElementById("side5_green_time" + id).disabled = true;
                document.getElementById("side3_green_time" + id).style.backgroundColor = "";
                document.getElementById("side4_green_time" + id).style.backgroundColor = "lightgrey";
                document.getElementById("side5_green_time" + id).style.backgroundColor = "lightgrey";
                document.getElementById("side4_amber_time" + id).value = "";
                document.getElementById("side5_amber_time" + id).value = "";
                document.getElementById("side3_amber_time" + id).disabled = false;
                document.getElementById("side4_amber_time" + id).disabled = true;
                document.getElementById("side5_amber_time" + id).disabled = true;
                document.getElementById("side3_amber_time" + id).style.backgroundColor = "";
                document.getElementById("side4_amber_time" + id).style.backgroundColor = "lightgrey";
                document.getElementById("side5_amber_time" + id).style.backgroundColor = "lightgrey";
            }else if(no_of_sides == '4'){
                document.getElementById("side5_green_time" + id).value = "";
                document.getElementById("side3_green_time" + id).disabled = false;
                document.getElementById("side4_green_time" + id).disabled = false;
                document.getElementById("side5_green_time" + id).disabled = true;
                document.getElementById("side3_green_time" + id).style.backgroundColor = "";
                document.getElementById("side4_green_time" + id).style.backgroundColor = "";
                document.getElementById("side5_green_time" + id).style.backgroundColor = "lightgrey";
                document.getElementById("side5_amber_time" + id).value = "";
                document.getElementById("side3_amber_time" + id).disabled = false;
                document.getElementById("side4_amber_time" + id).disabled = false;
                document.getElementById("side5_amber_time" + id).disabled = true;
                document.getElementById("side3_amber_time" + id).style.backgroundColor = "";
                document.getElementById("side4_amber_time" + id).style.backgroundColor = "";
                document.getElementById("side5_amber_time" + id).style.backgroundColor = "lightgrey";
            }else{
                document.getElementById("side3_green_time" + id).disabled = false;
                document.getElementById("side4_green_time" + id).disabled = false;
                document.getElementById("side5_green_time" + id).disabled = false;
                document.getElementById("side3_green_time" + id).style.backgroundColor = "";
                document.getElementById("side4_green_time" + id).style.backgroundColor = "";
                document.getElementById("side5_green_time" + id).style.backgroundColor = "";
                document.getElementById("side3_amber_time" + id).disabled = false;
                document.getElementById("side4_amber_time" + id).disabled = false;
                document.getElementById("side5_amber_time" + id).disabled = false;
                document.getElementById("side3_amber_time" + id).style.backgroundColor = "";
                document.getElementById("side4_amber_time" + id).style.backgroundColor = "";
                document.getElementById("side5_amber_time" + id).style.backgroundColor = "";
            }
        }
        else{
            document.getElementById("side1_green_time" + id).value = "";
            document.getElementById("side2_green_time" + id).value = "";
            document.getElementById("side3_green_time" + id).value = "";
            document.getElementById("side4_green_time" + id).value = "";
            document.getElementById("side5_green_time" + id).value = "";
            document.getElementById("side1_green_time" + id).disabled = true;
            document.getElementById("side2_green_time" + id).disabled = true;
            document.getElementById("side3_green_time" + id).disabled = true;
            document.getElementById("side4_green_time" + id).disabled = true;
            document.getElementById("side5_green_time" + id).disabled = true;
            document.getElementById("side1_green_time" + id).style.backgroundColor = "lightgrey";
            document.getElementById("side2_green_time" + id).style.backgroundColor = "lightgrey";
            document.getElementById("side3_green_time" + id).style.backgroundColor = "lightgrey";
            document.getElementById("side4_green_time" + id).style.backgroundColor = "lightgrey";
            document.getElementById("side5_green_time" + id).style.backgroundColor = "lightgrey";

            document.getElementById("side1_amber_time" + id).value = "";
            document.getElementById("side2_amber_time" + id).value = "";
            document.getElementById("side3_amber_time" + id).value = "";
            document.getElementById("side4_amber_time" + id).value = "";
            document.getElementById("side5_amber_time" + id).value = "";
            document.getElementById("side1_amber_time" + id).disabled = true;
            document.getElementById("side2_amber_time" + id).disabled = true;
            document.getElementById("side3_amber_time" + id).disabled = true;
            document.getElementById("side4_amber_time" + id).disabled = true;
            document.getElementById("side5_amber_time" + id).disabled = true;
            document.getElementById("side1_amber_time" + id).style.backgroundColor = "lightgrey";
            document.getElementById("side2_amber_time" + id).style.backgroundColor = "lightgrey";
            document.getElementById("side3_amber_time" + id).style.backgroundColor = "lightgrey";
            document.getElementById("side4_amber_time" + id).style.backgroundColor = "lightgrey";
            document.getElementById("side5_amber_time" + id).style.backgroundColor = "lightgrey";
        }
    }

    function addRow(tableID) {
        $("#message").html("");
        var firstVal = "00";
        var onTimeHour = "";
        var onTimeMinute = "";
        var  onTimePreviousHour="";
        var  onTimePreviousMinute="";
        var table = document.getElementById(tableID);

        var rowCount = table.rows.length;
        var previousRow = rowCount-1;
        //  alert(rowCount);
        if(rowCount > 1) {
            onTimeHour = document.getElementById("off_time_hour"+previousRow).value;
            onTimeMinute = document.getElementById("off_time_min"+previousRow).value;
            onTimePreviousHour = document.getElementById("on_time_hour"+previousRow).value;
            onTimePreviousMinute = document.getElementById("on_time_min"+previousRow).value;
        }
        //  alert(onTimeMinute);
        // alert(rowCount);
        // alert(onTimePreviousHour + "  "+ onTimePreviousMinute);
        //  alert(onTimeHour + "  "+ onTimeMinute);

        if(rowCount > 1 && (onTimeHour == "" || onTimeMinute == "")) {
            var message = "<td colspan='6' bgcolor='coral'><b>Plan"+previousRow+" Off Time is required...</b></td>";
            $("#message").html(message);
            if(onTimeHour == "") {
                document.getElementById("off_time_hour" + previousRow).focus();
            }else {
                document.getElementById("off_time_min" + previousRow).focus();
            }

        }else if(onTimeHour == "23" && onTimeMinute == "59"){
            var message = "<td colspan='6' bgcolor='coral'><b>Sorry , You can not add more row .You have completed  plan ...</b></td>";
            $("#message").html(message);
            //document.getElementById("add_plan").disabled = true;
        }
        //  alert("fgfgfdfdfdf");
      
        else if(onTimeHour>23){
            
            var message = "<td colspan='6' bgcolor='coral'><b>Plan"+previousRow+" Off Time hr must not be greater than 23 hrs......</b></td>";
            $("#message").html(message);
            document.getElementById("off_time_hour" + previousRow).focus();
        }
        else if(onTimeMinute>59){

            var message = "<td colspan='6' bgcolor='coral'><b>Plan"+previousRow+"  Off Time min must not be greater than 59 minutes......</b></td>";
            $("#message").html(message);
            document.getElementById("off_time_min" + previousRow).focus();
        }//  else if(rowCount > 1 && parseInt(onTimeHour)<=parseInt(onTimePreviousHour) &&  parseInt(onTimeMinute)<=parseInt(onTimePreviousMinute)){
        else if(rowCount > 1&& parseInt(onTimeHour)<parseInt(onTimePreviousHour)){
     
            var message = "<td colspan='6' bgcolor='coral'><b>Plan"+previousRow+" Off Time hr  and Off Time min must be greater than  On Time  "+onTimePreviousHour+":"+onTimePreviousMinute+"...</b></td>";
            $("#message").html(message);
            document.getElementById("off_time_hour" + previousRow).focus();
         
        } else if(rowCount > 1&& parseInt(onTimeHour)==parseInt(onTimePreviousHour) && parseInt(onTimeMinute)<=parseInt(onTimePreviousMinute)){
         
            var message = "<td colspan='6' bgcolor='coral'><b>Plan"+previousRow+" Off Time hr  and Off Time min must be greater than  On Time  "+onTimePreviousHour+":"+onTimePreviousMinute+"...</b></td>";
            $("#message").html(message);
            document.getElementById("off_time_hour" + previousRow).focus();

        }
        else {
            var row = table.insertRow(rowCount);
            var cell1 = row.insertCell(0);
            var element1 = document.createElement("input");
            element1.type = "checkbox";
            element1.disabled =false;
            //element1.style.display = "none";

            cell1.appendChild(element1);

            var cell2 = row.insertCell(1);
            var element1 = document.createElement("input");
            element1.type = "text";
            element1.id = "s_no"+rowCount;
            element1.name = "s_no"+rowCount;
            element1.value = rowCount;
            element1.size = 3;
            element1.readOnly = true;
            cell2.appendChild(element1);

            var cell3 = row.insertCell(2);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "on_time_hour"+rowCount;
            element2.id = "on_time_hour"+rowCount;
            element2.size = 3;
            element2.maxLength =2;
            element2.value = rowCount == 1 ? firstVal : onTimeHour;
            element2.readonly =true;
            element2.setAttribute("onchange", 'validateOnTime(id,'+rowCount+')');
            element2.setAttribute("onkeyup", 'validateOnTime(id,'+rowCount+')');
            cell3.appendChild(element2);

            var cell4 = row.insertCell(3);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "on_time_min"+rowCount;
            element2.id = "on_time_min"+rowCount;
            element2.size = 3;
            element2.maxLength = 2;
            element2.value =  rowCount == 1 ? firstVal : onTimeMinute;
            element2.readonly = true;
            element2.setAttribute("onchange", 'validateOnTime(id,'+rowCount+')');
            element2.setAttribute("onkeyup", 'validateOnTime(id,'+rowCount+')');
            cell4.appendChild(element2);

            var cell5 = row.insertCell(4);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "off_time_hour"+rowCount;
            element2.id = "off_time_hour"+rowCount;
            element2.size = 3;
            element2.maxLength = 2;
            element2.value = "";
            element2.setAttribute("onchange", 'validateOffTime(id,'+rowCount+')');
            element2.setAttribute("onkeyup", 'validateOffTime(id,'+rowCount+')');
            cell5.appendChild(element2);

            var cell6 = row.insertCell(5);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "off_time_min"+rowCount;
            element2.id = "off_time_min"+rowCount;
            element2.size = 3;
            element2.maxLength = 2;
            element2.value = "";
            element2.setAttribute("onchange", 'validateOffTime(id,'+rowCount+')');
            element2.setAttribute("onkeyup", 'validateOffTime(id,'+rowCount+')');
            cell6.appendChild(element2);

            var cell7 = row.insertCell(6);
            var modeElement = document.createElement("select");
            var optionArray = new Array('Blinker', 'Signal', 'Off');
            for(var i = 0; i < optionArray.length; i++) {
                var opt = document.createElement("option");
                opt.text = optionArray[i];
                opt.value = optionArray[i];
                modeElement.options.add(opt);
            }
            modeElement.name = "mode"+rowCount;
            modeElement.id = "mode"+rowCount;   //setDropdownVisibility
            modeElement.onchange = function xyz() {
                setDropdownVisibility(rowCount);
            };
            modeElement.value = "";
            cell7.appendChild(modeElement);

            var cell8 = row.insertCell(7);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "side1_green_time"+rowCount;
            element2.id = "side1_green_time"+rowCount;
            element2.size = 3;
            element2.maxLength = 2;
            element2.value = "";
            cell8.appendChild(element2);

            var cell9 = row.insertCell(8);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "side2_green_time"+rowCount;
            element2.id = "side2_green_time"+rowCount;
            element2.size = 3;
            element2.maxLength = 2;
            element2.value = "";
            cell9.appendChild(element2);

            var cell10 = row.insertCell(9);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "side3_green_time"+rowCount;
            element2.id = "side3_green_time"+rowCount;
            element2.size = 3;
            element2.maxLength = 2;
            element2.value = "";
            cell10.appendChild(element2);

            var cell11 = row.insertCell(10);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "side4_green_time"+rowCount;
            element2.id = "side4_green_time"+rowCount;
            element2.size = 3;
            element2.maxLength = 2;
            element2.value = "";
            cell11.appendChild(element2);

            var cell12 = row.insertCell(11);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "side5_green_time"+rowCount;
            element2.id = "side5_green_time"+rowCount;
            element2.size = 3;
            element2.maxLength = 2;
            element2.value = "";
            cell12.appendChild(element2);

            var cell13 = row.insertCell(12);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "side1_amber_time"+rowCount;
            element2.id = "side1_amber_time"+rowCount;
            element2.size = 3;
            element2.maxLength = 2;
            element2.value = "";
            cell13.appendChild(element2);

            var cell14 = row.insertCell(13);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "side2_amber_time"+rowCount;
            element2.id = "side2_amber_time"+rowCount;
            element2.size = 3;
            element2.maxLength = 2;
            element2.value = "";
            cell14.appendChild(element2);

            var cell15 = row.insertCell(14);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "side3_amber_time"+rowCount;
            element2.id = "side3_amber_time"+rowCount;
            element2.size = 3;
            element2.maxLength = 2;
            element2.value = "";
            cell15.appendChild(element2);

            var cell16 = row.insertCell(15);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "side4_amber_time"+rowCount;
            element2.id = "side4_amber_time"+rowCount;
            element2.size = 3;
            element2.maxLength = 2;
            element2.value = "";
            cell16.appendChild(element2);

            var cell17 = row.insertCell(16);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "side5_amber_time"+rowCount;
            element2.id = "side5_amber_time"+rowCount;
            element2.size = 3;
            element2.maxLength = 2;
            element2.value = "";
            cell17.appendChild(element2);
            document.getElementById("no_of_plans").value = rowCount;
            setDropdownVisibility(rowCount);
        }
    }

    function isExists(items, item){
        var position_index = -1;
        for(var i=0; i<items.length; i++){
            //            alert(items[i] + ' item: ' + item);
            if(items[i] == item){
                position_index = i + 1;
                break;
            }
        }
        return position_index;
    }

    function setSequential(prev_no_of_plans, deletedRowNo){
        for(var i = deletedRowNo; i < prev_no_of_plans; i++){
            var j = i + 1;
            document.getElementById("plan_no" + j).id = "plan_no" + i;
            document.getElementById("plan_no" + i).value = i;

            document.getElementById("on_time_hour" + j).id = "on_time_hour" + i;
            document.getElementById("on_time_min" + j).id = "on_time_min" + i;
            document.getElementById("off_time_hour" + j).id = "off_time_hour" + i;
            document.getElementById("off_time_min" + j).id = "off_time_min" + i;
            document.getElementById("mode" + j).id = "mode" + i;
            document.getElementById("side1_green_time" + j).id = "side1_green_time" + i;
            document.getElementById("side2_green_time" + j).id = "side2_green_time" + i;
            document.getElementById("side3_green_time" + j).id = "side3_green_time" + i;
            document.getElementById("side4_green_time" + j).id = "side4_green_time" + i;
            document.getElementById("side5_green_time" + j).id = "side5_green_time" + i;
            document.getElementById("side1_amber_time" + j).id = "side1_amber_time" + i;
            document.getElementById("side2_amber_time" + j).id = "side2_amber_time" + i;
            document.getElementById("side3_amber_time" + j).id = "side3_amber_time" + i;
            document.getElementById("side4_amber_time" + j).id = "side4_amber_time" + i;
            document.getElementById("side5_amber_time" + j).id = "side5_amber_time" + i;

            document.getElementById("plan_no" + i).name = "plan_no" + i;
            document.getElementById("on_time_hour" + i).name = "on_time_hour" + i;
            document.getElementById("on_time_min" + i).name = "on_time_min" + i;
            document.getElementById("off_time_hour" + i).name = "off_time_hour" + i;
            document.getElementById("off_time_min" + i).name = "off_time_min" + i;
            document.getElementById("mode" + i).name = "mode" + i;
            document.getElementById("side1_green_time" + i).name = "side1_green_time" + i;
            document.getElementById("side2_green_time" + i).name = "side2_green_time" + i;
            document.getElementById("side3_green_time" + i).name = "side3_green_time" + i;
            document.getElementById("side4_green_time" + i).name = "side4_green_time" + i;
            document.getElementById("side5_green_time" + i).name = "side5_green_time" + i;
            document.getElementById("side1_amber_time" + i).name = "side1_amber_time" + i;
            document.getElementById("side2_amber_time" + i).name = "side2_amber_time" + i;
            document.getElementById("side3_amber_time" + i).name = "side3_amber_time" + i;
            document.getElementById("side4_amber_time" + i).name = "side4_amber_time" + i;
            document.getElementById("side5_amber_time" + i).name = "side5_amber_time" + i;
        }
    }

    function deleteRow(tableID) {
        try {
            // alert(tableID);
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;        
            var delete_palan_row=0;
            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                //  alert(row);
                var chkbox = row.cells[0].childNodes[0];
                //     alert(chkbox);
                if(null != chkbox && true == chkbox.checked ||k<rowCount) {
                    // alert(i);
                    var  k=i;
                    for(k=i;k<rowCount;k++){
                        table.deleteRow(i);
                        delete_palan_row++;
                        //  alert(delete_palan_row);
                        // --rowCount;
                        //setSequential(--rowCount, i);
                        // i--;
                        document.getElementById("no_of_plans").value=  document.getElementById("no_of_plans").value-1;
                    }
                }
            }
            // alert("fdfdfd");
            //document.getElementById("no_of_plans").value =(parseInt(rowCount)-parseInt(delete_palan_row));
        }catch(e) {
            // alert(e);
        }
    }

    function setDefaults() {
        var no_of_plans = document.getElementById("no_of_plans").value;
        for(var i = 1; i <= no_of_plans; i++) {
            //  document.getElementById("checkbox"+1).disabled = true;
            document.getElementById("on_time_hour"+1).readOnly = true;
            document.getElementById("on_time_min"+1).readOnly = true;
            document.getElementById("off_time_hour"+1).readOnly = true;
            document.getElementById("off_time_min"+1).readOnly = true;
            document.getElementById("on_time_hour"+2).readOnly = true;
            setDropdownVisibility(i);
        }
    }

    function validateOnTime(id,rowNo) {
        //alert(id+"  "+rowNo);
        $("#message").html("");
        var stringRowNo = rowNo+"";
        var name = id.substr(0,(id.length-stringRowNo.length));
        var name1 = name.substr(2, name.length);
        name1 = "off"+name1;
        //alert(name1);
        var val = parseInt($("#"+id).val());
        // alert(val+" "+name);
        if(rowNo == 1) {
            if(val!= 0){
                var message = "<td colspan='6' bgcolor='coral'><b>Plan1 On Time must be equal to 00...</b></td>";
                $("#message").html(message);
                document.getElementById(id).focus();
            }
        }else{
            var val1 = $("#"+id).val() ;
            var val2 = $("#"+name1+(rowNo-1)).val() ;
            //alert(val1+" "+val2);
            if((val1!=val2)){
                var message = "<td colspan='6' bgcolor='coral'><b>Plan"+rowNo+" On Time must be equal to Plan"+(rowNo-1)+" Off time...</b></td>";
                $("#message").html(message);
                document.getElementById(id).focus();
            }
            if(name == "on_time_hour") {
                if(val >23) {
                    var message = "<td colspan='6' bgcolor='coral'><b>Plan"+rowNo+" On Time hr must not be greater than 23 hrs...</b></td>";
                    $("#message").html(message);
                    document.getElementById(id).focus();
                }
            }else if(name == "on_time_min") {
                if(val >59) {
                    var message = "<td colspan='6' bgcolor='coral'><b>Plan"+rowNo+" On Time min must not be greater than 59 minutes...</b></td>";
                    $("#message").html(message);
                    document.getElementById(id).focus();
                }
            }
        }

    }

    /*function changeOnTimeHr(id,rowNo) {
      // alert("sdfdfd");


    } */

    function validateOffTime(id,rowNo) {
        //  alert(id+"  "+rowNo);
        var table = document.getElementById("dataTable");
        var rowCount = table.rows.length;
        //  alert(rowCount);
        var off_time=parseInt(rowNo)+1;
        // alert(a);
        for(var i=parseInt(rowNo)+1; i<rowCount; i++) {
            //}
            if(i==parseInt(off_time)){
                document.getElementById("on_time_hour" + i).value = document.getElementById("off_time_hour" + rowNo).value;
                document.getElementById("on_time_min" + i).value = document.getElementById("off_time_min" + rowNo).value;
                // document.getElementById("add_plan").disabled = true;
            }
            else{
                document.getElementById("on_time_hour" + i).value = '';
                document.getElementById("on_time_min" + i).value = '';

            }

            document.getElementById("off_time_hour" + i).value = '';
            document.getElementById("off_time_min" + i).value = '';

        }


        $("#message").html("");
        var stringRowNo = rowNo+"";
        var name = id.substr(0,(id.length-stringRowNo.length));
        //alert(name);
        var value = parseInt($("#"+id).val());
        var onTimeHr = parseInt($("#on_time_hour"+rowNo).val());
        var onTimeMin = parseInt($("#on_time_min"+rowNo).val());
        var no_of_plans = parseInt($("#no_of_plans").val());
        var off_time_hr = parseInt($("#off_time_hour"+rowNo).val());
        var off_time_min = parseInt($("#off_time_min"+rowNo).val());
        // alert(rowNo);
        //alert(value+" "+name);
        // alert(no_of_plans+" "+rowNo);
        /* if(off_time_hr !=23 && off_time_min!=59) {
               alert("gfgfg");
                document.getElementById("add_plan").disabled =false;

            } */
        if(name == "off_time_hour") {
            if(value > 23) {
                var message = "<td colspan='6' bgcolor='coral'><b>Plan"+rowNo+" Off Time hr must not be greater than 23 hrs...</b></td>";
                $("#message").html(message);
                document.getElementById(id).focus();
            }
            if(value < onTimeHr) {
                var message = "<td colspan='6' bgcolor='coral'><b>Plan"+rowNo+" Off Time hr must be greater than or equal to On Time hrs...</b></td>";
                $("#message").html(message);
                document.getElementById(id).focus();
            }
        }else if(name == "off_time_min") {
            // var off_time_hr = parseInt($("#off_time_hour"+rowNo).val());
            if(value >59) {
                var message = "<td colspan='6' bgcolor='coral'><b>Plan"+rowNo+" Off Time min must not be greater than 59 minutes...</b></td>";
                $("#message").html(message);
                document.getElementById(id).focus();
            }
            if(off_time_hr == onTimeHr && (value < onTimeMin || value==onTimeMin)) {
                var message = "<td colspan='6' bgcolor='coral'><b>Plan"+rowNo+" Off Time min must be greater than On Time min...</b></td>";
                $("#message").html(message);
                document.getElementById(id).focus();
            }
        } if(no_of_plans != rowNo && off_time_hr ==23 && off_time_min==59) {
            // alert("fgfg");
            var message = "<td colspan='6' bgcolor='coral'><b>Plan"+rowNo+" Off Time  23 Hrs and Off Time 59 minutes  can not be allow  for this row. This time is allow for last row ...</b></td>";
            $("#message").html(message);
            // document.getElementById("add_plan").disabled = true;
            document.getElementById(id).focus();
        }
        // alert(off_time_hr);
        //   alert(off_time_min);
           

    }

</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table cellspacing="0" border="0" id="table0"  align="center" width="500">
            <tr style="font-size:larger ;font-weight: 700;" align="center">
                <td>
                    ${plan_info_name} Plan Info Details
                </td>
            </tr>
            <tr align="center">
                <td>
                    <input value="Add Plan" id="add_plan" onclick="addRow('dataTable')" type="button">

                    <input value="Delete Plan" onclick="deleteRow('dataTable')" type="button">
                </td>
            </tr>
            <tr id="message">
                <c:if test="${not empty message}">
                    <td colspan="8" bgcolor="${msgBgColor}"><b>Result: ${message}</b></td>
                </c:if>
            </tr>
            <tr>
                <td>
                    <form name="form1" action="PlanInfoCont" method="post" onsubmit="return verify()">
                        <table id="dataTable" style="border-collapse: collapse;" border="1" width="100%" align="center">
                            <tbody>
                                <tr>
                                    <th class="heading" >Select.</th>
                                    <th  class="heading">S.No</th>
                                    <th  class="heading">ON TimeHr</th>
                                    <th  class="heading">ON TimeMin</th>
                                    <th class="heading">OFF TimeHr</th>
                                    <th class="heading">OFF TimeMin</th>
                                    <th  class="heading">Mode</th>
                                    <th  class="heading">Side1 GreenTime</th>
                                    <th  class="heading">Side2 GreenTime</th>
                                    <th  class="heading">Side3 GreenTime</th>
                                    <th  class="heading">Side4 GreenTime</th>
                                    <th  class="heading">Side5 GreenTime</th>
                                    <th class="heading">Side1 AmberTime</th>
                                    <th  class="heading">Side2 AmberTime</th>
                                    <th  class="heading">Side3 AmberTime</th>
                                    <th  class="heading">Side4 AmberTime</th>
                                    <th  class="heading">Side5 AmberTime</th>
                                </tr>
                                <c:forEach var="list" items="${requestScope['planinfo']}" varStatus="loopCounter">
                                    <tr>
                                        <td><input class="input" type="checkbox"  name="chk" id="checkbox${loopCounter.count}"  ></td>
                                        <td><input class="input" type="text" name="s_no${loopCounter.count}" id="s_no${loopCounter.count}" size="3" value="${loopCounter.count}" readonly>
                                            <input type="hidden" name="plan_no${loopCounter.count}" id="plan_no${loopCounter.count}" size="3" value="${list.plan_no}">
                                            <input type="hidden" name="plan_revision_no${loopCounter.count}" id="plan_revision_no${loopCounter.count}" value="${list.plan_revision_no}">
                                        </td>
                                        <td><input class="input" type="text" name="on_time_hour${loopCounter.count}" size="3" id="on_time_hour${loopCounter.count}" maxlength="2" value="${list.on_time_hour}" onchange="validateOnTime(id,'${loopCounter.count}')" onkeyup="validateOnTime(id,'${loopCounter.count}')" readonly></td>
                                        <td><input class="input" type="text" name="on_time_min${loopCounter.count}" id="on_time_min${loopCounter.count}" size="3" maxlength="2" value="${list.on_time_min}" onchange="validateOnTime(id,'${loopCounter.count}')" onkeyup="validateOnTime(id,'${loopCounter.count}')" readonly></td>
                                        <td><input class="input" type="text" name="off_time_hour${loopCounter.count}" id="off_time_hour${loopCounter.count}" size="3" maxlength="2" value="${list.off_time_hour}" onchange="validateOffTime(id,'${loopCounter.count}')" onkeyup="validateOffTime(id,'${loopCounter.count}')" ></td>
                                        <td><input class="input" type="text" name="off_time_min${loopCounter.count}" id="off_time_min${loopCounter.count}" size="3" maxlength="2" value="${list.off_time_min}" onchange="validateOffTime(id,'${loopCounter.count}')" onkeyup="validateOffTime(id,'${loopCounter.count}')" ></td>
                                        <td  style="height: 21px;width: 99px">
                                            <select class="select" id="mode${loopCounter.count}" name="mode${loopCounter.count}" onchange="setDropdownVisibility(${loopCounter.count});" style="height: 20px;width: 80px">
                                                <option value="Signal" ${list.mode eq 'Signal'? 'SELECTED': ''}>Signal</option>
                                                <option value="Blinker" ${list.mode eq 'Blinker'? 'SELECTED': ''}>Blinker</option>
                                                <option value="Off" ${list.mode eq 'Off'? 'SELECTED': ''}>Off</option>
                                            </select>
                                        </td>
                                        <td><input class="input"  type="text" name="side1_green_time${loopCounter.count}" maxlength="2" size="3" id="side1_green_time${loopCounter.count}" value="${list.side1_green_time}"></td>
                                        <td><input class="input" type="text" name="side2_green_time${loopCounter.count}" maxlength="2" size="3" id="side2_green_time${loopCounter.count}" value="${list.side2_green_time}" ></td>
                                        <td><input class="input" type="text" name="side3_green_time${loopCounter.count}" maxlength="2" size="3" id="side3_green_time${loopCounter.count}" value="${list.side3_green_time}" ></td>
                                        <td><input class="input" type="text" name="side4_green_time${loopCounter.count}" maxlength="2" size="3" id="side4_green_time${loopCounter.count}" value="${list.side4_green_time}" ></td>
                                        <td><input class="input" type="text" name="side5_green_time${loopCounter.count}" maxlength="2" size="3" id="side5_green_time${loopCounter.count}" value="${list.side5_green_time}" ></td>
                                        <td><input class="input" type="text" name="side1_amber_time${loopCounter.count}" maxlength="2" size="3" id="side1_amber_time${loopCounter.count}" value="${list.side1_amber_time}" ></td>
                                        <td><input class="input" type="text" name="side2_amber_time${loopCounter.count}" maxlength="2" size="3" id="side2_amber_time${loopCounter.count}" value="${list.side2_amber_time}" ></td>
                                        <td><input class="input" type="text" name="side3_amber_time${loopCounter.count}" maxlength="2" size="3" id="side3_amber_time${loopCounter.count}" value="${list.side3_amber_time}" ></td>
                                        <td><input class="input" type="text" name="side4_amber_time${loopCounter.count}" maxlength="2" size="3" id="side4_amber_time${loopCounter.count}" value="${list.side4_amber_time}" ></td>
                                        <td><input class="input" type="text" name="side5_amber_time${loopCounter.count}" maxlength="2" size="3" id="side5_amber_time${loopCounter.count}" value="${list.side5_amber_time}" ></td>
                                    </tr>
                                </c:forEach>
                            <input class="button" type="submit" id="SAVE" name="task" value="SAVE" >
                            </tbody>
                        </table>
                        <input type="hidden" id="junction_id" name="junction_id" value="${param.junction_id}">
                        <%-- <input type="text" id="program_version_no" name="program_version_no" value="${param.program_version_no}">  --%>
                        <input type="hidden" id="program_version_no" name="program_version_no" value="${program_version_no}">
                        <input type="hidden" id="no_of_plans" name="no_of_plans" value="${no_of_plans}">
                        <input type="hidden" id="no_of_sides" name="no_of_sides" value="${param.no_of_sides}">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>

