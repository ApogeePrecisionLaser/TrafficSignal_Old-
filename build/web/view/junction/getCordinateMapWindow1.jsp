<%--
    Document   : mapWindow
    Created on : Sep 29, 2014, 11:36:35 AM
    Author     : Robin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <!--        <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="JS/jquery-1.4.2.min.js"></script>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>Map View</title>
        <style type="text/css">
            #map {
                height: 350px;
                border: 1px solid #000;
            }
        </style>
        <script type="text/javascript" language="javascript">
            var data= [];
            window.onload = function()
            {
                        map();
            };
            function map()
            {
                var lat = ${latti};
                if(lat == "")
                    lat = "23.1657228";
                var lng = ${longi};
                if(lng == "")
                    lng = "79.9505182";
                var latlng = new google.maps.LatLng(lat, lng);//(23.1657228,79.9505182);
                var map = new google.maps.Map(document.getElementById('map'), {
                    center: latlng,
                    zoom: 10,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                });
                var marker = new google.maps.Marker({
                    position: latlng,
                    map: map,
                    title: 'Set lat/lon values for this property',
                    draggable: true
                });
//                var marker1 = [];
//                for (i = 0; i < data.length; i++) {
//                    marker1[i] = new google.maps.Marker({
//                        position: new google.maps.LatLng(data[i].latitude, data[i].longitude),
//                        map: map,
////                        icon: 'http://google.com/mapfiles/kml/paddle/g',
//                        label: ""+(i+1)
//                    });
//                }
                google.maps.event.addListener(marker, 'dragend', function(a) {
                    opener.document.getElementById("latitude").value = a.latLng.lat();
                    opener.document.getElementById("longitude").value = a.latLng.lng();
                    opener.document.getElementById("cordinate_change").value = "Y";
                    if(${latti} != 0 && ${longi} != 0){
                        $.ajax({url: "generalCont.do?task=GetDistance",
                            data: "source="+ ${latti} + "," + ${longi} + "&destination="+ a.latLng.lat() + "," + a.latLng.lng(),
                            success: function(response_data) {
                                if(response_data > 50){
                                    var dst = response_data/1000;
                                    dst = dst + parseFloat(opener.distance);//parseFloat(opener.document.getElementById("distance").value);
                                    dst = dst.toFixed(2);
                                    opener.document.getElementById("distance").value = dst;
                                    opener. calculateAmount();
                                    opener.document.getElementById("add_distance").value = dst;
                                }else{
                                    dst = parseFloat(opener.distance);//parseFloat(opener.document.getElementById("distance").value);
                                    opener.document.getElementById("distance").value = dst;
                                    opener. calculateAmount();
                                    opener.document.getElementById("add_distance").value = dst;
                                }
                            }
                        });
                    }
                });
            }
        </script>
    </head>
    <body>
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyDOT5yBi-LAmh9P2X0jQmm4y7zOUaWRXI0"></script>
        <input type="button" value="Close" onclick="window.close();">
        <div id="map" style="width:600px;height:550px;"></div>
        <input type="hidden" id="longi" value="${longi}" >
        <input type="hidden" id="latti" value="${latti}" >
    </body>
</html>
