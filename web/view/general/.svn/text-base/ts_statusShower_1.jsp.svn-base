<%--
    Document   : timeShower
    Created on : Jun 5, 2012, 12:28:18 PM
    Author     : Tarun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modem Current Time</title>
        <script type="text/javascript" src="JS/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="JS/jquery.autocomplete.js"></script>
        <script type="text/javascript" language="javascript">
            $( document ).ready(function() {
                setActivity();
            });
            var globalVar = 0;
            var refreshSide1GlobalVar = 0;
            var refreshSide2GlobalVar = 0;
            var refreshSide3GlobalVar = 0;
            var refreshSide4GlobalVar = 0;
            var temGreenTime =0;
            var delay = 0;
            var sys_time = '';
            var time = 0;
            var side1Time = 0, side2Time = 0, side3Time = 0, side4Time = 0;
            var current_min = 0;
            function loadContents() {

                //                greenTime = document.getElementById("side1_green_time").value;// in minutes
                //                if(greenTime != 0 ){
                //                    time = (+(hours*60*60)) + (+(mins*60)) + (+secs); //time is 3:50
                //                    current_min = (+time) + (+(greenTime*60));
                //                    document.getElementById("timeToDisplay").value = (greenTime*60);
                //                    //                     alert("time+currentTime ==" + time + " , " + current_min +"sys_time " +sys_time);
                //                }else{
                //                    time = (+(onTimeHr%12)*60*60) + (+(onTimeMin*60)) + (+onTimeSec);
                //                    current_min = (+(OffTimeHr%12)*60*60) + (+(OffTimeMin*60)) + (+offTimeSec);
                //                    var calculatedTime = current_min - time;
                //                    document.getElementById("timeToDisplay").value = calculatedTime;
                //                    //                    alert("time" +time + "cuttent_min" +current_min + "timetodisplay" + document.getElementById("timeToDisplay").value);
                //                }

                //                alert("current min" +current_min + "time to display" +timeToDisplay);
                //                alert("systime" +sys_time + "current min" +current_min +"clearance time" +clearanceTime);

                //                 alert("real hour:" + real_hour + " , " + "Hour:" +hour + " , " + sys_time +" , "+ current_min);
                //                alert(document.getElementById("side1_green_time").value);
                //                alert(delay);

                //$.ajax({url: "ts_statusShowerCont", async: true, data: "task=jQueryRequest", success: function(response_data) {
                //        $("#currentTime").html(response_data);
                //    }
                //});
                $.ajax({url: "ts_statusShowerCont", async: true, data: "task=getLatestStatus", success: function(response_data) {
                        if(response_data == ''){
                        }else{
                            var response_data_split = response_data.split("#$", response_data.length);
                            //alert(response_data_split);
                            for(var j=0; j <= response_data_split.length; j++){
                                //alert(response_data_split[j]);
                                var response1 = response_data_split[j].split("=", response_data_split[j].length);
                                if(response1[0] == 'functionNo'){
                                    $("#functionNo").val(response1[1]);
                                }else if(response1[0] == 'junction_id'){
                                    $("#junction_id").val(response1[1]);
                                }else if(response1[0] == 'program_version_no'){
                                    $("#program_version_no").val(response1[1]);
                                }else if(response1[0] == 'fileNo'){
                                    $("#fileNo").val(response1[1]);
                                }else if(response1[0] == 'activity'){
                                    $("#selectedActivity").val(response1[1]);
                                }else if(response1[0] == 'side_no'){
                                    $("#side_no").val(response1[1]);
                                }else if(response1[0] == 'junctionName'){
                                    $("#junctionName").val(response1[1]);
                                }else if(response1[0] == 'sideName'){
                                    $("#sideName").val(response1[1]);
                                    /* }else if(response1[0] == 'onTimeHr'){
                                    $("#onTimeHr").val(response1[1]);
                                }else if(response1[0] == 'onTimeMin'){
                                    $("#onTimeMin").val(response1[1]);
                                }else if(response1[0] == 'onTimeSec'){
                                    $("#onTimeSec").val(response1[1]);
                                }else if(response1[0] == 'OffTimeHr'){
                                    $("#OffTimeHr").val(response1[1]);
                                }else if(response1[0] == 'OffTimeMin'){
                                    $("#OffTimeMin").val(response1[1]);
                                }else if(response1[0] == 'offTimeSec'){
                                    $("#offTimeSec").val(response1[1]);
                                }else if(response1[0] == 'side1Name'){
                                    $("#side1Name").val(response1[1]);*/
                                }else if(response1[0] == 'juncHr'){
                                    $("#juncHr").val(response1[1]);
                                }else if(response1[0] == 'juncMin'){
                                    $("#juncMin").val(response1[1]);
                                }else if(response1[0] == 'juncDat'){
                                    $("#juncDat").val(response1[1]);
                                }else if(response1[0] == 'juncMonth'){
                                    $("#juncMonth").val(response1[1]);
                                }else if(response1[0] == 'juncYear'){
                                    $("#juncYear").val(response1[1]);
                                }else if(response1[0] == 'onTime'){
                                    $("#onTime").val(response1[1]);
                                }else if(response1[0] == 'offTime'){
                                    $("#offTime").val(response1[1]);
                                }else if(response1[0] == 'mode'){
                                    $("#mode").val(response1[1]);
                                }else if(response1[0] == 'plan_no'){
                                    $("#plan_no").val(response1[1]);
                                }else if(response1[0] == 'side2Name'){
                                    $("#side2Name").val(response1[1]);
                                }else if(response1[0] == 'side3Name'){
                                    $("#side3Name").val(response1[1]);
                                }else if(response1[0] == 'side4Name'){
                                    $("#side4Name").val(response1[1]);
                                }else if(response1[0] == 'side5Name'){
                                    $("#side5Name").val(response1[1]);
                                }else if(response1[0] == 'side1Time'){
                                    $("#side1Time").val(response1[1]);
                                }else if(response1[0] == 'side2Time'){
                                    $("#side2Time").val(response1[1]);
                                }else if(response1[0] == 'side3Time'){
                                    $("#side3Time").val(response1[1]);
                                }else if(response1[0] == 'side4Time'){
                                    $("#side4Time").val(response1[1]);
                                }else if(response1[0] == 'side1LeftStatus'){
                                    $("#side1LeftStatus").val(response1[1]);
                                }else if(response1[0] == 'side1RightStatus'){
                                    $("#side1RightStatus").val(response1[1]);
                                }else if(response1[0] == 'side1UpStatus'){
                                    $("#side1UpStatus").val(response1[1]);
                                }else if(response1[0] == 'side1DownStatus'){
                                    $("#side1DownStatus").val(response1[1]);
                                }else if(response1[0] == 'side2LeftStatus'){
                                    $("#side2LeftStatus").val(response1[1]);
                                }else if(response1[0] == 'side2RightStatus'){
                                    $("#side2RightStatus").val(response1[1]);
                                }else if(response1[0] == 'side2UpStatus'){
                                    $("#side2UpStatus").val(response1[1]);
                                }else if(response1[0] == 'side2DownStatus'){
                                    $("#side2DownStatus").val(response1[1]);
                                }else if(response1[0] == 'side3LeftStatus'){
                                    $("#side3LeftStatus").val(response1[1]);
                                }else if(response1[0] == 'side3RightStatus'){
                                    $("#side3RightStatus").val(response1[1]);
                                }else if(response1[0] == 'side3UpStatus'){
                                    $("#side3UpStatus").val(response1[1]);
                                }else if(response1[0] == 'side3DownStatus'){
                                    $("#side3DownStatus").val(response1[1]);
                                }else if(response1[0] == 'side4LeftStatus'){
                                    $("#side4LeftStatus").val(response1[1]);
                                }else if(response1[0] == 'side4RightStatus'){
                                    $("#side4RightStatus").val(response1[1]);
                                }else if(response1[0] == 'side4UpStatus'){
                                    $("#side4UpStatus").val(response1[1]);
                                }else if(response1[0] == 'side4DownStatus'){
                                    $("#side4DownStatus").val(response1[1]);
                                }
                            }
                        }
                    }
                });
                ChangeStatus();
                setActivity();
                setTimeout("loadContents()", 1 * 50);
            }

            function ChangeStatus(){
                var d = new Date();
                var hour = d.getHours();
                var min = d.getMinutes();
                var  sec = d.getSeconds();
                var real_hour = hour%12;
                sys_time = (real_hour*60*60) + (min*60) + sec;
                //var onTimeHr = document.getElementById("onTimeHr").value;
                //var onTimeMin = document.getElementById("onTimeMin").value;
                //var onTimeSec = document.getElementById("onTimeSec").value;
                //var OffTimeHr = document.getElementById("OffTimeHr").value;
                //var OffTimeMin = document.getElementById("OffTimeMin").value;
                //var offTimeSec = document.getElementById("offTimeSec").value;
                //                var hours =document.getElementById("hours").value;
                //                var mins =document.getElementById("mins").value;
                //                var secs =document.getElementById("secs").value;
                var functionNo = document.getElementById("functionNo").value;
                //                alert("functionNo -" +functionNo + " " );
                //                alert("functionNo" +functionNo);
                $("#plan_no_td").html(document.getElementById("plan_no").value);
                $("#mode_td").html(document.getElementById("mode").value);
                $("#onTime_td").html(document.getElementById("onTime").value);
                $("#offTime_td").html(document.getElementById("offTime").value);
                $("#date_time_td").html(document.getElementById("juncHr").value+":"+document.getElementById("juncMin").value+"  "+document.getElementById("juncDat").value+"-"+document.getElementById("juncMonth").value);
                if(functionNo == 8){
                    var side1LeftStatus = document.getElementById("side1LeftStatus").value;
                    var side1RightStatus = document.getElementById("side1RightStatus").value;
                    var side1UpStatus = document.getElementById("side1UpStatus").value;
                    var side1DownStatus = document.getElementById("side1DownStatus").value;
                    var side2LeftStatus = document.getElementById("side2LeftStatus").value;
                    var side2RightStatus = document.getElementById("side2RightStatus").value;
                    var side2UpStatus = document.getElementById("side2UpStatus").value;
                    var side2DownStatus = document.getElementById("side2DownStatus").value;
                    var side3LeftStatus = document.getElementById("side3LeftStatus").value;
                    var side3RightStatus = document.getElementById("side3RightStatus").value;
                    var side3UpStatus = document.getElementById("side3UpStatus").value;
                    var side3DownStatus = document.getElementById("side3DownStatus").value;
                    var side4LeftStatus = document.getElementById("side4LeftStatus").value;
                    var side4RightStatus = document.getElementById("side4RightStatus").value;
                    var side4UpStatus = document.getElementById("side4UpStatus").value;
                    var side4DownStatus = document.getElementById("side4DownStatus").value;
                    if(side1LeftStatus == 1){
                        $("#signal_upper_down").html(document.getElementById("div_redSignal").innerHTML);
                    }else{
                        $("#signal_upper_down").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side1RightStatus == 1){
                        $("#signal_upper_left").html(document.getElementById("div_yellowSignal").innerHTML);
                    }else{
                        $("#signal_upper_left").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side1UpStatus == 1){
                        $("#signal_upper_up").html(document.getElementById("green_down").innerHTML);
                    }else{
                        $("#signal_upper_up").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side1DownStatus == 1){
                        $("#signal_upper_right").html(document.getElementById("green_left").innerHTML);
                    }else{
                        $("#signal_upper_right").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side1LeftStatus == 1  || side1UpStatus == 1 || side1DownStatus == 1){
                        //                    refreshCounterUpper();
                        //                    refreshSide1GlobalVar = 1;
                        $("#side1").html(document.getElementById("side1Name").value);
                        $("#side1_span").html(document.getElementById("side1Name").value);
                        $("#label_upper").html(document.getElementById("side1Time").value);
                    }else if(side1RightStatus == 1){
                        $("#side1").html(document.getElementById("side1Name").value);
                        $("#side1_span").html(document.getElementById("side1Name").value);
                        //                    refreshSide1GlobalVar = 0;
                        //                    side1Time = 0;
                    }
                    if(side2LeftStatus == 1){
                        $("#signal_left_down").html(document.getElementById("div_redSignal").innerHTML);
                    }else{
                        $("#signal_left_down").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side2RightStatus == 1){
                        $("#signal_left_left").html(document.getElementById("div_yellowSignal").innerHTML);
                    }else{
                        $("#signal_left_left").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side2UpStatus == 1){
                        $("#signal_left_up").html(document.getElementById("green_right").innerHTML);
                    }else{
                        $("#signal_left_up").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side2DownStatus == 1){
                        $("#signal_left_right").html(document.getElementById("green_down").innerHTML);
                    }else{
                        $("#signal_left_right").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side2LeftStatus == 1  || side2UpStatus == 1 || side2DownStatus == 1){
                        //                    refreshCounterLeft();
                        //                    refreshSide2GlobalVar = 1;
                        $("#side2").html(document.getElementById("side2Name").value);
                        $("#side2_span").html(document.getElementById("side2Name").value);
                        $("#label_left").html(document.getElementById("side2Time").value);
                    }else if(side2RightStatus == 1){
                        //                    refreshSide2GlobalVar = 0;
                        //                    side2Time = 0;
                        $("#side2").html(document.getElementById("side2Name").value);
                        $("#side2_span").html(document.getElementById("side2Name").value);
                    }
                    if(side3LeftStatus == 1){
                        $("#signal_right_down").html(document.getElementById("div_redSignal").innerHTML);
                    }else{
                        $("#signal_right_down").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side3RightStatus == 1){
                        $("#signal_right_left").html(document.getElementById("div_yellowSignal").innerHTML);
                    }else{
                        $("#signal_right_left").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side3UpStatus == 1){
                        $("#signal_right_up").html(document.getElementById("green_left").innerHTML);
                    }else{
                        $("#signal_right_up").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side3DownStatus == 1){
                        $("#signal_right_right").html(document.getElementById("green_up").innerHTML);
                    }else{
                        $("#signal_right_right").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side3LeftStatus == 1  || side3UpStatus == 1 || side3DownStatus == 1){
                        //                    refreshCounterRight();
                        //                    refreshSide3GlobalVar = 1;
                        $("#side3").html(document.getElementById("side3Name").value);
                        $("#side3_span").html(document.getElementById("side3Name").value);
                        $("#label_right").html(document.getElementById("side3Time").value);
                    }else if(side3RightStatus == 1){
                        //  alert("globalVar -ELSE--"+globalVar);
                        //                    side3Time = 0;
                        //                    refreshSide3GlobalVar = 0;
                        $("#side3").html(document.getElementById("side3Name").value);
                        $("#side3_span").html(document.getElementById("side3Name").value);
                    }
                    if(side4LeftStatus == 1){
                        $("#signal_lower_down").html(document.getElementById("div_redSignal").innerHTML);
                    }else{
                        $("#signal_lower_down").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side4RightStatus == 1){
                        $("#signal_lower_left").html(document.getElementById("div_yellowSignal").innerHTML);
                    }else{
                        $("#signal_lower_left").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side4UpStatus == 1){
                        $("#signal_lower_up").html(document.getElementById("green_up").innerHTML);
                    }else{
                        $("#signal_lower_up").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side4DownStatus == 1){
                        $("#signal_lower_right").html(document.getElementById("green_down").innerHTML);
                    }else{
                        $("#signal_lower_right").html(document.getElementById("div_greySignal").innerHTML);
                    }if(side4LeftStatus == 1  || side4UpStatus == 1 || side4DownStatus == 1){
                        //                    refreshCounterDown();
                        //                    refreshSide4GlobalVar = 1;
                        $("#side4").html(document.getElementById("side4Name").value);
                        $("#side4_span").html(document.getElementById("side4Name").value);
                        $("#label_lower").html(document.getElementById("side4Time").value);
                    }else if(side4RightStatus == 1){
                        //   alert("globalVar -2nd ELSE--"+globalVar);
                        //                    refreshSide4GlobalVar = 0;
                        //                    side4Time = 0;
                        $("#side4").html(document.getElementById("side4Name").value);
                        $("#side4_span").html(document.getElementById("side4Name").value);
                    }
                }
                if(functionNo == 6){
                    var sideNo = document.getElementById("sideNo").value;
                    //                    alert(sideNo);
                    if(sideNo == 1){
                        $("#signal_upper_right").html(document.getElementById("green_left").innerHTML);
                        $("#signal_upper_left").html(document.getElementById("green_right").innerHTML);
                        $("#signal_upper_up").html(document.getElementById("green_down").innerHTML);
                        $("#signal_upper_down").html(document.getElementById("div_redSignal").innerHTML);
                    }else{
                        $("#signal_upper_right").html(document.getElementById("div_greySignal").innerHTML);
                        $("#signal_upper_left").html(document.getElementById("div_greySignal").innerHTML);
                        $("#signal_upper_up").html(document.getElementById("div_greySignal").innerHTML);
                        $("#signal_upper_down").html(document.getElementById("div_redSignal").innerHTML);
                    }
                    if(sideNo == 2){
                        $("#signal_left_left").html(document.getElementById("green_up").innerHTML);
                        $("#signal_left_right").html(document.getElementById("green_down").innerHTML);
                        $("#signal_left_up").html(document.getElementById("green_right").innerHTML);
                        $("#signal_left_down").html(document.getElementById("div_redSignal").innerHTML);
                    }else {
                        $("#signal_left_left").html(document.getElementById("div_greySignal").innerHTML);
                        $("#signal_left_right").html(document.getElementById("div_greySignal").innerHTML);
                        $("#signal_left_up").html(document.getElementById("div_greySignal").innerHTML);
                        $("#signal_left_down").html(document.getElementById("div_redSignal").innerHTML);
                    }
                    if(sideNo == 3){
                        $("#signal_right_left").html(document.getElementById("green_down").innerHTML);
                        $("#signal_right_right").html(document.getElementById("green_up").innerHTML);
                        $("#signal_right_up").html(document.getElementById("green_left").innerHTML);
                        $("#signal_right_down").html(document.getElementById("div_redSignal").innerHTML);
                    } else{
                        $("#signal_right_left").html(document.getElementById("div_greySignal").innerHTML);
                        $("#signal_right_right").html(document.getElementById("div_greySignal").innerHTML);
                        $("#signal_right_up").html(document.getElementById("div_greySignal").innerHTML);
                        $("#signal_right_down").html(document.getElementById("div_redSignal").innerHTML);
                    }
                    if(sideNo == 4){
                        $("#signal_lower_left").html(document.getElementById("green_left").innerHTML);
                        $("#signal_lower_right").html(document.getElementById("green_right").innerHTML);
                        $("#signal_lower_up").html(document.getElementById("green_up").innerHTML);
                        $("#signal_lower_down").html(document.getElementById("div_redSignal").innerHTML);
                    }else{
                        $("#signal_lower_left").html(document.getElementById("div_greySignal").innerHTML);
                        $("#signal_lower_right").html(document.getElementById("div_greySignal").innerHTML);
                        $("#signal_lower_up").html(document.getElementById("div_greySignal").innerHTML);
                        $("#signal_lower_down").html(document.getElementById("div_redSignal").innerHTML);
                    }
                    $("#side1").html(document.getElementById("side1Name").value);
                    $("#side1_span").html(document.getElementById("side1Name").value);
                    $("#side2").html(document.getElementById("side2Name").value);
                    $("#side2_span").html(document.getElementById("side2Name").value);
                    $("#side3").html(document.getElementById("side3Name").value);
                    $("#side3_span").html(document.getElementById("side3Name").value);
                    $("#side4").html(document.getElementById("side4Name").value);
                    $("#side4_span").html(document.getElementById("side4Name").value);
                }
            }

            function refreshCounterUpper(){
                if(refreshSide1GlobalVar == 0){
                    side1Time = document.getElementById("side1Time").value;
                }
                if(side1Time != 0){
                    side1Time = side1Time - 1;
                    document.getElementById("refreshCounterTime").value = side1Time;
                }
            }
            function refreshCounterLeft(){
                if(refreshSide2GlobalVar == 0){
                    side2Time = document.getElementById("side2Time").value;
                }
                if(side2Time > 0){
                    side2Time = side2Time - 1;
                    document.getElementById("refreshCounterTime1").value = side2Time;
                }
            }
            function refreshCounterRight(){
                if(refreshSide3GlobalVar == 0){
                    side3Time = document.getElementById("side3Time").value;
                }
                if(side3Time > 0){
                    side3Time = side3Time - 1;
                    document.getElementById("refreshCounterTime2").value = side3Time;
                }
            }
            function refreshCounterDown(){
                if(refreshSide4GlobalVar == 0){
                    side4Time = document.getElementById("side4Time").value;
                }
                if(side4Time > 0){
                    side4Time = side4Time - 1;
                    document.getElementById("refreshCounterTime3").value = side4Time;
                }
            }

            function changeGreenTime(time, current_min){
                var delay;
                if(globalVar == 0){
                    //                    hour = d.getHours();
                    //                    min = d.getMinutes();
                    //                    sec = d.getSeconds();
                    //                    real_hour = hour%12;
                    //                    sys_time = (real_hour*60*60) + (min*60) + sec;
                    //                    var current_min = 0;
                    //                    var onTimeHr = document.getElementById("onTimeHr").value;
                    //                    var onTimeMin = document.getElementById("onTimeMin").value;
                    //                    var onTimeSec = document.getElementById("onTimeSec").value;
                    //                    var OffTimeHr = document.getElementById("OffTimeHr").value;
                    //                    var OffTimeMin = document.getElementById("OffTimeMin").value;
                    //                    var offTimeSec = document.getElementById("offTimeSec").value;
                    //                    greenTime = document.getElementById("side1_green_time").value;// in minutes
                    //                    if(greenTime != 0 ){
                    //                        time = 300 + 00; //time is 3:50
                    //                        current_min = (+time) + (+greenTime);
                    //                        document.getElementById("timeToDisplay").value = greenTime;
                    //
                    //                    }else{
                    //                        time = (+(onTimeHr%12)*60*60) + (+(onTimeMin*60)) + (+onTimeSec);
                    //                        current_min = (+(OffTimeHr%12)*60*60) + (+(OffTimeMin*60)) + (+offTimeSec);
                    //                        var calculatedTime = current_min - time;
                    //                        document.getElementById("timeToDisplay").value = calculatedTime;
                    //                        alert("sys_time :" + sys_time + "time: " +time + "current_min: " + current_min);
                    //                    }
                    delay = (sys_time - time);
                    var timeToDisplay =  document.getElementById("timeToDisplay").value// in minutes
                    //                    alert(delay);
                    temGreenTime = (timeToDisplay) - delay;
                    //                    alert("clearance time:" +clearanceTime + "tempGreenTime:" +temGreenTime);

                }

                //                alert("tempgreentime" + tempgreentime + "delay" +delay);
                //                alert("changeGreenTime--"+globalVar + ",greenTime- "+greenTime + " ,temGreenTime- "+temGreenTime);
                //                alert("time+currentTime ==" + time + " , " + current_min);
                if(sys_time == time || sys_time <= current_min){
                    document.getElementById("current_time").value = current_min;
                    document.getElementById("sys_time").value = sys_time;
                    temGreenTime = temGreenTime - 1;
                    document.getElementById("greenTime").value = temGreenTime;
                    // alert(temGreenTime);
                }
            }

            function changeStatus(id) {
                var junctionId = document.getElementById("junctionId").value;
                var junctionName = document.getElementById("junctionName").value;
                var program_version_no = document.getElementById("program_version_no").value;
                var fileNo = document.getElementById("fileNo").value;
                document.forms['form1'].action = "ts_statusUpdaterCont?task=Back To Normal&junctionId=" + junctionId + "&junctionName=" +junctionName+ "&program_version_no=" +program_version_no+ "&fileNo=" +fileNo;
                document.forms['form1'].submit();
            }
            function sendMessage() {
                var customMsg = document.getElementById("customMsg").value;
                var queryString = "task=sendMsg&customMsg=" + customMsg;
                $.ajax({url: "modemsTaskCont", async: true, data: queryString, success: function(response_data) {
                        $("#td_responseMsg").html(response_data);
                    }
                });
            }

            function onChange(id){
                //                alert(id);

                if(id == 'clearnceSide1'){
                    document.getElementById("tdNo").value = "color1";
                    document.getElementById("clickedButton").value = "1";
                    document.getElementById("selectedClearanceSide").value = "1";
                } else if(id == 'clearnceSide2'){
                    document.getElementById("tdNo").value = "color2";
                    document.getElementById("clickedButton").value = "2";
                    document.getElementById("selectedClearanceSide").value = "2";
                } else if(id == 'clearnceSide3'){
                    document.getElementById("tdNo").value = "color3";
                    document.getElementById("clickedButton").value = "3";
                    document.getElementById("selectedClearanceSide").value = "3";
                } else{
                    document.getElementById("tdNo").value = "color4";
                    document.getElementById("clickedButton").value = "4";
                    document.getElementById("selectedClearanceSide").value = "4";
                }
            }

            function readClientResponse() {
                var junctionId = document.getElementById("junctionId").value;
                var junctionName = document.getElementById("junctionName").value;
                var program_version_no = document.getElementById("program_version_no").value;
                var fileNo = document.getElementById("fileNo").value;
                var radioBtnValue = document.getElementById("clickedButton").value;
                //alert(junctionId);
                var queryString = "task=Change To Green&junctionId=" + junctionId + "&junctionName=" +junctionName +"&radioBtnValue=" +radioBtnValue+ "&program_version_no=" +program_version_no+ "&fileNo=" +fileNo;
                document.getElementById("message").innerHTML = "Request for Changing <b>"  + junctionName +  "</b> signal status sent Successfully";
                var tdID = document.getElementById("tdNo").value;
                document.getElementById(tdID).style.backgroundColor = "lightblue";
                document.getElementById("message").style.backgroundColor = "lightyellow";
                $('#image_loading').html(document.getElementById("div_animationLoading").innerHTML);
                document.forms['form1'].action = "ts_statusUpdaterCont?task=Change To Green&junctionId=" + junctionId + "&junctionName=" +junctionName +"&radioBtnValue=" +radioBtnValue+ "&program_version_no=" +program_version_no+ "&fileNo=" +fileNo;
                document.forms['form1'].submit();
            }

            function onSelect(id){
                //                alert(id);

                if(id == 'activity1'){
                    document.getElementById("selectedActivity").value = "1";
                    document.getElementById("activity_no").value = "1";
                    document.getElementById("div_middle").style.display = "none";
                    document.getElementById("changeActivity").style.display = "";
                    document.getElementById("div_middle_pedestrian").style.display = "";
                } else if(id == 'activity2'){
                    document.getElementById("div_middle").style.display = "";
                    document.getElementById("activity_no").value = "2";
                    document.getElementById("changeActivity").style.display = "none";
                    document.getElementById("div_middle_pedestrian").style.display = "none";

                } else if(id == 'activity3'){
                    document.getElementById("selectedActivity").value = "3";
                    document.getElementById("activity_no").value = "3";
                    document.getElementById("div_middle").style.display = "none";
                    document.getElementById("changeActivity").style.display = "";
                    document.getElementById("div_middle_pedestrian").style.display = "";
                } else if(id == 'activity4'){
                    document.getElementById("selectedActivity").value = "4";
                    document.getElementById("activity_no").value = "4";
                    document.getElementById("div_middle").style.display = "none";
                    document.getElementById("changeActivity").style.display = "";
                    document.getElementById("div_middle_pedestrian").style.display = "";
                } else if(id == 'activity5'){
                     document.getElementById("selectedActivity").value = "5";
                    document.getElementById("activity_no").value = "5";
                    document.getElementById("div_middle").style.display = "none";
                    document.getElementById("changeActivity").style.display = "";
                    document.getElementById("div_middle_pedestrian").style.display = "";
                } else{
                    document.getElementById("selectedActivity").value = "6";
                    document.getElementById("activity_no").value = "6";
                    document.getElementById("div_middle").style.display = "none";
                    document.getElementById("changeActivity").style.display = "";
                    document.getElementById("div_middle_pedestrian").style.display = "";
                }
                
            }

            function changeActivityFn() {

                    var side1UpStatus = document.getElementById("side1UpStatus").value;
                    var side1DownStatus = document.getElementById("side1DownStatus").value;

                    var side2UpStatus = document.getElementById("side2UpStatus").value;
                    var side2DownStatus = document.getElementById("side2DownStatus").value;

                    var side3UpStatus = document.getElementById("side3UpStatus").value;
                    var side3DownStatus = document.getElementById("side3DownStatus").value;

                    var side4UpStatus = document.getElementById("side4UpStatus").value;
                    var side4DownStatus = document.getElementById("side4DownStatus").value;

                    var activitySide = side1UpStatus == '1' && side1DownStatus == '1' ? '1' :
                                       side2UpStatus == '1' && side2DownStatus == '1' ? '2' :
                                       side3UpStatus == '1' && side3DownStatus == '1' ? '3' :
                                       side4UpStatus == '1' && side4DownStatus == '1' ? '4' : '1';
                 //   alert(activitySide);
                //document.getElementById("activity_message").innerHTML = "Request has been sent successfully";
               // document.getElementById("activity_message").style.backgroundColor = "lightyellow";
               // $('#activity_image_loading').html(document.getElementById("div_animationLoading").innerHTML);

                document.forms['form3'].action = "ts_statusUpdaterCont?task=changeActivity&activitySide="+activitySide;
                document.forms['form3'].submit();
            }

            function setActivity() {
                var selectedActivity = $("#selectedActivity").val();
                var activity_no = $("#activity_no").val();

                var val= activity_no=="" ? selectedActivity : activity_no;
               // alert("selectedActivity:"+selectedActivity+" activity_no: "+activity_no+" val: "+val);
                if(val != 2) {
                    //  alert("pedestrian");
                    document.getElementById("div_middle").style.display = "none";
                    document.getElementById("changeActivity").style.display = "";
                    document.getElementById("div_middle_pedestrian").style.display = "";
                    $("#activity"+val).attr("checked","checked");
                }else{
                    $("#activity2").attr("checked","checked");
                    var side_no = $("#side_no").val();
                    var selectedSide = $("#selectedClearanceSide").val();
                    var sideVal = selectedSide=="" ? side_no : selectedSide;
                    //alert(side_no);
                    document.getElementById("div_middle").style.display = "";
                    document.getElementById("changeActivity").style.display = "none";
                    document.getElementById("div_middle_pedestrian").style.display = "none";
                    $("#clearnceSide"+sideVal).attr("checked","checked");
                }
            }

        </script>
    </head>
    <body onload="loadContents();changeGreenTime();">
        <table align="center" cellpadding="0" cellspacing="0" class="main" >
            <!--DWLayoutDefaultTable-->
            <tr><td><%@include file="/layout/header.jsp" %></td></tr>
            <tr><td><%@include file="/layout/menu.jsp" %></td></tr>
            <tr>
                <td>
                    <DIV id="body" class="maindiv">
                        <!--        <img src="./images/close-button.jpg" align="right"/>-->
                        <h1 align="center">${junctionName} Signal Status</h1>
                        <!-- <h1 align="center">${val}</h1>
                           <h3 align="center">Through EL: ${applicationScope['currentTime']}</h3>
                        <h3 id="currentTime" align="center"></h3>
                        <div id="div_signalStatus" style="margin: 5px auto 0 auto">-->
                            <table align="center">
                                <tr>
                                    <td>
                                        <table align="center" border="0">
                                            <tr>
                                                <td bgcolor="lightsteelblue" colspan="4"><div id="change_activity_div" style="position: static;  height: 50px;" >
                                                        <form name="form3" method="post">
                                                            <table  width="100%" border="0">
                                                                <tr>
                                                                    <td><input type="radio" name="activityBtn" value="1" id="activity1" onclick="onSelect(id);">Normal&nbsp;&nbsp;&nbsp;</td>
                                                                    <td><input type="radio" name="activityBtn" value="2" id="activity2" onclick="onSelect(id);">Clearance&nbsp;&nbsp;&nbsp;</td>
                                                                    <td><input type="radio" name="activityBtn" value="3" id="activity3" onclick="onSelect(id);">Change Mode&nbsp;&nbsp;&nbsp;</td>
                                                                    <td><input type="radio" name="activityBtn" value="4" id="activity4" onclick="onSelect(id);">Jump&nbsp;&nbsp;&nbsp;</td>
                                                                    <td><input type="radio" name="activityBtn" value="5" id="activity5" onclick="onSelect(id);">Shutdown&nbsp;&nbsp;&nbsp;</td>
                                                                    <td><input type="radio" name="activityBtn" value="6" id="activity6" onclick="onSelect(id);">Extend</td>
                                                                    <td>
                                                                        <input type="button" id="changeActivity" value="Submit" onclick="changeActivityFn()">
                                                                        <input type="hidden" name="junctionId" value="${junctionId}">
                                                                        <input type="hidden" name="program_version_no" value="${program_version_no}">
                                                                        <input type="hidden" name="fileNo" value="${fileNo}">
                                                                        <input type="hidden" name="junctionName" value="${junctionName}">
                                                                        <input type="hidden" id="selectedActivity" name="selectedActivity" value="${activity}">
                                                                        <input type="hidden" id="activity_no" name="activity_no" value="">
                                                                        <input type="hidden" id="side_no" name="side_no" value="${side_no}">
                                                                    </td>
                                                                    <td>
                                                                        <table width="100%" border="0px">
                                                                            <tr>
                                                                                <td id="activity_message" style="color: darkred"></td>
                                                                                <td id="activity_image_loading" style="color: #fef2d8"></td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </form>
                                                    </div>
                                                </td>
                                            </tr>
                                            <!--                                            <caption>Signal Type: Chauraha</caption>-->
                                            <tr>
                                                <td><div style="width: 150px; height: 100px;"></div></td>
                                                <td colspan="2">
                                                    <div id="div_upper" style="width: 150px; height: 150px; border-left-style: ridge; border-right-style: ridge; background-color: grey;">
                                                        <table align="center" border="0" width="100%">
                                                            <tr align="center">
                                                                <td id="side1"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <table border="0" width="50%" align="center">
                                                                        <tr>
                                                                            <td colspan="2" align="center" id="signal_upper_down"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="left" id="signal_upper_right"></td>
                                                                            <td align="right" id="signal_upper_left"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="center" colspan="2" id="signal_upper_up"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="center" colspan="2"><label id="label_upper" style="color: white"></label></td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </td>
                                                <td rowspan="3">
                                                    <div id="plan_info_div" style="position: static; width: 200px;height: 390px">
                                                        <table align="center" style="background-color: lightsteelblue;" border="1" width="100%" height="100%">
                                                            <caption align="top"><b>Plan Details</b></caption>
                                                            <tr>
                                                                <th>Date Time</th>
                                                                <td id="date_time_td"></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Plan No</th>
                                                                <td id="plan_no_td"></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Mode</th>
                                                                <td id="mode_td"></td>
                                                            </tr>
                                                            <tr>
                                                                <th>On Time</th>
                                                                <td id="onTime_td"></td>
                                                            </tr>
                                                            <tr>
                                                                <th>Off Time</th>
                                                                <td id="offTime_td"></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div id="div_left" style="width: 150px; height: 150px; border-top-style: ridge; border-bottom-style: ridge; background-color: grey;">
                                                        <table align="left" border="0" width="100%">

                                                            <tr>
                                                                <td align="center" id="side2"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <table width="50%" align="center">
                                                                        <tr>
                                                                            <td align="center" colspan="2" id="signal_left_left"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="left" id="signal_left_down"></td>
                                                                            <td align="right" id="signal_left_up"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="center" colspan="2" id="signal_left_right"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="center" colspan="2"><label id="label_left" style="color: white"></label></td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </td>

                                                <td bgcolor="lightgrey">
                                                    <div id="div_middle" style="position: static; width: 150px; height: 150px;display:none">
                                                        <form name="form1" method="post">
                                                            <table  width="100%" border="0">
                                                                <tr id="color1">
                                                                    <td width="45%">
                                                                        <input type="radio" name="radiobtn" value="1" id="clearnceSide1" onclick="onChange(id);">${side1Name}
                                                                    </td>
                                                                </tr>
                                                                <tr id="color2">
                                                                    <td>
                                                                        <input type="radio" name="radiobtn" value="2" id="clearnceSide2" onclick="onChange(id);">${side2Name}
                                                                    </td>
                                                                </tr>
                                                                <tr id="color3">
                                                                    <td>
                                                                        <input type="radio" name="radiobtn" value="3" id="clearnceSide3" onclick="onChange(id);">${side3Name}
                                                                    </td>
                                                                </tr>
                                                                <tr id="color4">
                                                                    <td>
                                                                        <input type="radio" name="radiobtn" value="4" id="clearnceSide4" onclick="onChange(id);">${side4Name}
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td colspan="2">
                                                                        <input type="button" name="task" id="submitbtn" value="Change To Green" onclick="readClientResponse();">
                                                                        <input type="hidden" id="junctionId" name="junctionId" value="${junctionId}">
                                                                        <input type="hidden" id="program_version_no" name="program_version_no" value="${program_version_no}">
                                                                        <input type="hidden" id="fileNo" name="fileNo" value="${fileNo}">
                                                                        <input type="hidden" id="junctionName" name="junctionName" value="${junctionName}">
                                                                        <input type="hidden" id="clickedButton" value="">
                                                                        <input type="hidden" id="tdNo" value="">
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                            <table width="100%" border="0px">
                                                                <tr>
                                                                    <td id="message" style="color: darkred"></td>
                                                                    <td id="image_loading" style="color: #fef2d8"></td>
                                                                </tr>
                                                            </table>
                                                        </form>
                                                    </div>
                                                    <div id="div_middle_pedestrian" style="position: static; width: 150px; height: 150px;">
                                                        <table  width="100%" border="0">
                                                            <tr>
                                                                <td>
                                                                    <table width="50%" align="center">
                                                                        <tr>
                                                                            <td align="center" colspan="2" id="pedestrian_up"><div style="position: static;width: 50px; height: 50px"><img src="./img/red_pedestrian.jpg" width="30" height="30"></div></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="left" id="pedestrian_left"><div style="position: static;width: 50px; height: 50px"><img src="./img/red_pedestrian.jpg" width="30" height="30"></div></td>
                                                                            <td align="right" id="pedestrian_down"><div style="position: static;width: 50px; height: 50px"><img src="./img/red_pedestrian.jpg" width="30" height="30"></div></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="center" colspan="2" id="pedestrian_right"><div style="position: static;width: 50px; height: 50px"><img src="./img/red_pedestrian.jpg" width="30" height="30"></div></td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </td>

                                                <td>
                                                    <div id="div_right" style="position: static; width: 150px; height: 150px; border-top-style: ridge; border-bottom-style: ridge; background-color: grey;">
                                                        <table align="right" border="0" width="100%">
                                                            <tr>
                                                                <td align="center" id="side4"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <table width="50%" align="center">
                                                                        <tr>
                                                                            <td align="center" colspan="2" id="signal_lower_up"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="left" id="signal_lower_left"></td>
                                                                            <td align="right" id="signal_lower_right"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="center" colspan="2" id="signal_lower_down"></td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td align="center"><label id="label_lower" style="color: white"></label></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><div style="width: 150px; height: 120px;"></div></td>
                                                <td>
                                                    <div id="div_lower" style="width: 150px; height: 150px; border-left-style: ridge; border-right-style: ridge; background-color: grey;">
                                                        <table align="center" border="0" style="vertical-align: bottom" width="100%">
                                                            <tr>
                                                                <td align="center" colspan="2"><label id="label_right" style="color: white"></label></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="center" id="side3"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <table width="50%" align="center">
                                                                        <tr>
                                                                            <td align="center" colspan="2" id="signal_right_right"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="left" id="signal_right_up"></td>
                                                                            <td align="right" id="signal_right_down"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td align="center" colspan="2" id="signal_right_left"></td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <%-- <td>
                                              <table align="center" style="background-color: lightsteelblue">
                                                  <caption align="top">Send Message To The Client</caption>
                                                  <tr>
                                                      <th>Message</th>
                                                      <td><input type="text" id="customMsg" name="customMsg" value="Hello!" size="30"></td>
                                                  </tr>
                                                  <tr>
                                                      <td colspan="2" align="right">
                                                          <input type="button" id="sendMsgBtn" value="Send Message" onclick="sendMessage();">
                                                      </td>
                                                  </tr>
                                                  <tr>
                                                      <td id="td_responseMsg" colspan="2"></td>
                                                  </tr>
                                              </table>
                                          </td>
                                          <td>
                                              <table align="center" style="background-color: lightsteelblue">
                                                  <caption align="top">Clients Latest Response</caption>
                                                  <tr>
                                                      <td id="client">
                                                          <textarea id="clientResponse" rows="10" cols="20" readonly></textarea>
                                                      </td>
                                                  </tr>
                                              </table>
                                          </td>  --%>
                                </tr>
                            </table>
                            <input type="hidden" id="functionNo" name="functionNo" value="${functionNo}">
                            <input type="hidden" id="sideNo" name="sideNo" value="${sideNo}">
                            <input type="hidden" id="sideName" name="sideName" value="${sideName}">
                            <input type="hidden" id="side1Name" name="side1Name" value="${side1Name}">
                            <input type="hidden" id="side2Name" name="side2Name" value="${side2Name}">
                            <input type="hidden" id="side3Name" name="side3Name" value="${side3Name}">
                            <input type="hidden" id="side4Name" name="side4Name" value="${side4Name}">
                            <input type="hidden" id="side5Name" name="side5Name" value="${side5Name}">
                            <input type="hidden" id="side1_green_time" name="side1_green_time" value="${side1_green_time}">
                            <%--<input type="hidden" id="onTimeHr" name="onTimeHr" value="${onTimeHr}">
                                <input type="hidden" id="onTimeMin" name="onTimeMin" value="${onTimeMin}">
                                <input type="hidden" id="onTimeSec" name="onTimeSec" value="${onTimeSec}">
                                <input type="hidden" id="OffTimeHr" name="OffTimeHr" value="${OffTimeHr}">
                                <input type="hidden" id="OffTimeMin" name="OffTimeMin" value="${OffTimeMin}">
                                <input type="hidden" id="offTimeSec" name="offTimeSec" value="${offTimeSec}">  --%>
                            <input type="hidden" id="juncHr" name="juncHr" value="${juncHr}">
                            <input type="hidden" id="juncMin" name="juncMin" value="${juncMin}">
                            <input type="hidden" id="juncDat" name="juncDat" value="${juncDat}">
                            <input type="hidden" id="juncMonth" name="juncMonth" value="${juncMonth}">
                            <input type="hidden" id="juncYear" name="juncYear" value="${juncYear}">
                            <input type="hidden" id="onTime" name="onTime" value="${onTime}">
                            <input type="hidden" id="offTime" name="offTime" value="${offTime}">
                            <input type="hidden" id="plan_no" name="plan_no" value="${plan_no}">
                            <input type="hidden" id="mode" name="mode" value="${mode}">
                            <input type="hidden" id="timeToDisplay" id="timeToDisplay" value="">
                            <input type="hidden" id="greenTime" name="greenTime" value="">
                            <input type="hidden" id="current_time" name="current_time" value="">
                            <input type="hidden" id="sys_time" name="sys_time" value="">
                            <input type="hidden" id="hours" name="hours" value="${hours}">
                            <input type="hidden" id="mins" name="mins" value="${mins}">
                            <input type="hidden" id="secs" name="secs" value="${secs}">
                            <input type="hidden" id="side1Time" name="side1Time" value="${side1Time}">
                            <input type="hidden" id="side2Time" name="side2Time" value="${side2Time}">
                            <input type="hidden" id="side3Time" name="side3Time" value="${side3Time}">
                            <input type="hidden" id="side4Time" name="side4Time" value="${side4Time}">
                            <div style="visibility: hidden"> side1</div>
                            <input type="hidden" id="side1LeftStatus" name="side1LeftStatus" value="${side1LeftStatus}">
                            <input type="hidden" id="side1RightStatus" name="side1RightStatus" value="${side1RightStatus}">
                            <input type="hidden" id="side1UpStatus" name="side1UpStatus" value="${side1UpStatus}">
                            <input type="hidden" id="side1DownStatus" name="side1DownStatus" value="${side1DownStatus}">
                            <div style="visibility: hidden"> side2</div>
                            <input type="hidden" id="side2LeftStatus" name="side2LeftStatus" value="${side2LeftStatus}">
                            <input type="hidden" id="side2RightStatus" name="side2RightStatus" value="${side2RightStatus}">
                            <input type="hidden" id="side2UpStatus" name="side2UpStatus" value="${side2UpStatus}">
                            <input type="hidden" id="side2DownStatus" name="side2DownStatus" value="${side2DownStatus}">
                            <div style="visibility: hidden">side3 </div>
                            <input type="hidden" id="side3LeftStatus" name="side3LeftStatus" value="${side3LeftStatus}">
                            <input type="hidden" id="side3RightStatus" name="side3RightStatus" value="${side3RightStatus}">
                            <input type="hidden" id="side3UpStatus" name="side3UpStatus" value="${side3UpStatus}">
                            <input type="hidden" id="side3DownStatus" name="side3DownStatus" value="${side3DownStatus}">
                            <div style="visibility: hidden">side4</div>
                            <input type="hidden" id="side4LeftStatus" name="side4LeftStatus" value="${side4LeftStatus}">
                            <input type="hidden" id="side4RightStatus" name="side4RightStatus" value="${side4RightStatus}">
                            <input type="hidden" id="side4UpStatus" name="side4UpStatus" value="${side4UpStatus}">
                            <input type="hidden" id="side4DownStatus" name="side4DownStatus" value="${side4DownStatus}">
                            <div style="visibility: hidden">Counter: </div>
                            <input type="hidden" id="refreshCounterTime" name="refreshCounterTime" value="">
                            <input type="hidden" id="refreshCounterTime1" name="refreshCounterTime1" value="">
                            <input type="hidden" id="refreshCounterTime2" name="refreshCounterTime2" value="">
                            <input type="hidden" id="refreshCounterTime3" name="refreshCounterTime3" value="">
                        <div id="div_signals" style="visibility: hidden;border: 1px solid red">
                            <div id="div_redSignal" style="position: absolute;"> <img src="./images/red_light.png" width="30" height="30"></div>
                            <div id="div_yellowSignal" style="position: absolute;"><img src="./images/yellow_light.png" width="30" height="30"></div>
                            <div id="green_down" style="position: absolute;"><img src="./images/down.png" width="30" height="30"></div>
                            <div id="green_right" style="position: absolute;"><img src="./images/right.png" width="30" height="30"></div>
                            <div id="green_left" style="position: absolute;"><img src="./images/left.png" width="30" height="30"></div>
                            <div id="green_up" style="position: absolute;"><img src="./images/up.png" width="30" height="30"></div>
                            <div id="div_greySignal" style="position: absolute;"><img src="./images/grey.png" width="30" height="25"></div>
                            <div id="div_animationLoading" style="position: absolute;"><img src="./images/animated.gif" width="30" height="30"></div>
                        </div>
                            <input type="hidden" id="selectedClearanceSide" value="">
                    </DIV>
                </td>
            </tr>
            <tr><td><%@include file="/layout/footer.jsp" %></td> </tr>
        </table>

    </body>
</html>
