<%--
    Document   : classicpageForMarker
    Created on : Jan 16, 2019, 12:04:01 PM
    Author     : rituk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <script type="text/javascript" src="JS/jquery-1.4.2.min.js"></script>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>Marker Layer</title>
        <style type="text/css">
            body{
                margin: 0;
                overflow: hidden;
                background: #fff;
            }
            #map{
                position: relative;
                height: 553px;
                border:1px solid #3473b7;
            }
        </style>
<!--        <script src = "http://localhost:8084/supermap-libs/classic/libs/SuperMap.Include.js"></script>-->
        <script src = "supermap-libs/classic/libs/SuperMap.Include.js"></script>
        <script type="text/javascript">
            var doc = document;
            var map, layer, markerlayer, marker, points,osmLayer,junction_id,
                    //host = document.location.toString().match(/file:\/\//) ? "http://localhost:8090" : 'http://' + document.location.host;
                    //url = host + "/iserver/services/map-world/rest/maps/World";
                    url = "http://45.114.142.35:8090/iserver/services/map-world/rest/maps/World";

            function init()
            {
                var latlon = ($("#latlon").val()); 
                            var latlon1 = latlon.split(",");
                            var lat = latlon1[1];
                            var lon = latlon1[0];
                            var lat1 = lat.split("=");
                            var lon1 = lon.split("=");
                            var lat2 = lat1[1];
                            var lon2 = lon1[1];
                map = new SuperMap.Map("map", {controls: [
                        new SuperMap.Control.Zoom(),
                        new SuperMap.Control.Navigation(),
                        new SuperMap.Control.LayerSwitcher()
                    ]});
//                layer = new SuperMap.Layer.TiledDynamicRESTLayer("World", url, null, {maxResolution: "auto"});
//                layer.events.on({"layerInitialized": addLayer});
                osmLayer = new SuperMap.Layer.OSM();
                markerlayer = new SuperMap.Layer.Markers("markerLayer");
                map.addLayers([osmLayer, markerlayer]);
                //map.setCenter(new SuperMap.LonLat(79.9505182, 23.1657228).transform(new SuperMap.Projection("EPSG:4326"),map.getProjectionObject()), 16);
                map.setCenter(new SuperMap.LonLat(lon2, lat2), 19);
                //anotherFunction();
                addData();                
            }

            var infowin = null;
        //Define the mouseClickHandler function. This function will be called when trigger this click event
        
        function closeInfoWin(){
            if(infowin){
                        try {
                            infowin.hide();
                            infowin.destroy();
                        } catch (e) {
                        }
                    }
                }

            //Add data
            function addData()
            {
                 //anotherFunction();
                var icon1;
                //var icon1,icon2,icon3,icon4,side1LeftStatus,side1RightStatus,side1UpStatus,side1DownStatus; 
                            //var side2LeftStatus,side2RightStatus,side2UpStatus,side2DownStatus;
                            //var side3LeftStatus,side3RightStatus,side3UpStatus,side3DownStatus;
                           //var side4LeftStatus,side4RightStatus,side4UpStatus,side4DownStatus;
                            var size = new SuperMap.Size(44, 33);
                            var offset = new SuperMap.Pixel(-(size.w / 2), -size.h);                            
                            var icon = new SuperMap.Icon('img/traffic_signal.png', size, offset);
                            //var icon = new SuperMap.Icon(chec_no, size, offset);
                            var latlon = ($("#latlon").val()); 
                            //alert(latlon);
                            var junction_Id = ($("#junction_Id").val()); 
                            var latlon1 = latlon.split(",");
                            var lat = latlon1[1];
                            var lon = latlon1[0];
                            var lat1 = lat.split("=");
                            var lon1 = lon.split("=");
                            var lat2 = lat1[1];
                            var lon2 = lon1[1];
                            //alert(lon2+" "+lat2);
                            //alert(latlon);
                            //marker = new SuperMap.Marker(new SuperMap.LonLat(8897996.2160188, 2652098.8887744),icon);
                            marker = new SuperMap.Marker(new SuperMap.LonLat(lon2, lat2),icon);
                            //marker = new SuperMap.Marker(new SuperMap.LonLat(latlon),icon);
                            markerlayer.addMarker(marker);
                           // console.log(marker);
                            
                            
                            
                           


                            
                            //marker.events.on({"click":mouseClickHandler});
                            
                    
                    $.ajax({url: "ts_statusShowerCont", async: true, data: "task=getLatestStatus&current_junction_id="+junction_Id, dataType:'json', success: function(response_data) {
                                     //icon1 = new SuperMap.Icon('http://localhost:8084/supermap-libs/classic/theme/images/marker.png', size, offset);
                                     
                                     if(response_data == ''){
                        }else{                                                                                                                                                                
//                            side1LeftStatus  =  response_data.side1LeftStatus;
//                            side1RightStatus =  response_data.side1RightStatus;
//                             side1UpStatus    =  response_data.side1UpStatus;
//                             side1DownStatus  =  response_data.side1DownStatus;
//                             side2LeftStatus  =  response_data.side2LeftStatus;
//                             side2RightStatus =  response_data.side2RightStatus;
//                             side2UpStatus    =  response_data.side2UpStatus;
//                             side2DownStatus  =  response_data.side2DownStatus;
//                             side3LeftStatus  =  response_data.side3LeftStatus;
//                             side3RightStatus =  response_data.side3RightStatus;
//                             side3UpStatus    =  response_data.side3UpStatus;
//                             side3DownStatus  =  response_data.side3DownStatus;
//                             side4LeftStatus  =  response_data.side4LeftStatus;
//                             side4RightStatus =  response_data.side4RightStatus;
//                             side4UpStatus    =  response_data.side4UpStatus;
//                             side4DownStatus  =  response_data.side4DownStatus;  
                            $("#side1LeftStatus").val(response_data.side1LeftStatus);
                            $("#side1RightStatus").val(response_data.side1RightStatus);
                            $("#side1UpStatus").val(response_data.side1UpStatus);
                            $("#side1DownStatus").val(response_data.side1DownStatus);
                            $("#side2LeftStatus").val(response_data.side2LeftStatus);
                            $("#side2RightStatus").val(response_data.side2RightStatus);
                            $("#side2UpStatus").val(response_data.side2UpStatus);
                            $("#side2DownStatus").val(response_data.side2DownStatus);
                            $("#side3LeftStatus").val(response_data.side3LeftStatus);
                            $("#side3RightStatus").val(response_data.side3RightStatus);
                            $("#side3UpStatus").val(response_data.side3UpStatus);
                            $("#side3DownStatus").val(response_data.side3DownStatus);
                            $("#side4LeftStatus").val(response_data.side4LeftStatus);
                            $("#side4RightStatus").val(response_data.side4RightStatus);
                            $("#side4UpStatus").val(response_data.side4UpStatus);
                            $("#side4DownStatus").val(response_data.side4DownStatus);
                            $("#side1Time").val(response_data.side1Time);
                            $("#side2Time").val(response_data.side2Time);
                            $("#side3Time").val(response_data.side3Time);
                            $("#side4Time").val(response_data.side4Time);
                  
                    var side1LeftStatus = doc.getElementById("side1LeftStatus").value;                             
                    var side1RightStatus = doc.getElementById("side1RightStatus").value;
                    var side1UpStatus = doc.getElementById("side1UpStatus").value;
                    var side1DownStatus = doc.getElementById("side1DownStatus").value;
                    var side2LeftStatus = doc.getElementById("side2LeftStatus").value;
                    var side2RightStatus = doc.getElementById("side2RightStatus").value;
                    var side2UpStatus = doc.getElementById("side2UpStatus").value;
                    var side2DownStatus = doc.getElementById("side2DownStatus").value;
                    var side3LeftStatus = doc.getElementById("side3LeftStatus").value;
                    var side3RightStatus = doc.getElementById("side3RightStatus").value;
                    var side3UpStatus = doc.getElementById("side3UpStatus").value;
                    var side3DownStatus = doc.getElementById("side3DownStatus").value;
                    var side4LeftStatus = doc.getElementById("side4LeftStatus").value;
                    var side4RightStatus = doc.getElementById("side4RightStatus").value;
                    var side4UpStatus = doc.getElementById("side4UpStatus").value;
                    var side4DownStatus = doc.getElementById("side4DownStatus").value;
                    var side1Time = doc.getElementById("side1Time").value;
                    var side2Time = doc.getElementById("side2Time").value;
                    var side3Time = doc.getElementById("side3Time").value;
                    var side4Time = doc.getElementById("side4Time").value;
                    //alert(side1RightStatus+side1UpStatus+side1DownStatus+side2RightStatus+side3UpStatus+side4RightStatus+side4DownStatus);        
                            var coordinateCount = $("#count").val();                                                                                
                             for (var i=1; i <= coordinateCount; i++) {                                                      
                    //console.log(side1RightStatus+side1UpStatus+side1DownStatus+side2RightStatus+side3UpStatus+side4RightStatus+side4DownStatus);
                       //if(i==0){
                        ///alert(side1RightStatus+side1UpStatus+side1DownStatus+side2RightStatus+side3UpStatus+side4RightStatus+side4DownStatus);
                        // }
                            if(i==1){                               
                                //alert(side1RightStatus+side1UpStatus+side1DownStatus+side2RightStatus+side3UpStatus+side4RightStatus+side4DownStatus);
                                if(side1LeftStatus == 1){
                                     icon1 = new SuperMap.Icon('images/red_light.png', size, offset);                                                                      
                                 } else{
                                       if(side1RightStatus == 1){
                                         icon1 = new SuperMap.Icon('images/yellow_light.png', size, offset); 
                                       }
                                       else{
                                         icon1 = new SuperMap.Icon('images/up.png', size, offset); 
                                       }
                                   } 
                                   marker = new SuperMap.Marker(new SuperMap.LonLat($("#longi"+i).val(),$("#lati"+i).val()).transform(
                            new SuperMap.Projection("EPSG:4326"),
                            map.getProjectionObject()),icon1);
                            markerlayer.addMarker(marker);
                            
                           var latForTimer1 =   $("#lati"+i).val();
                           var longForTimer1 =   $("#longi"+i).val();
                           var pointForTimer = new SuperMap.Geometry.Point(longForTimer1, latForTimer1);
                           var convert_point = pointForTimer.transform(new SuperMap.Projection("EPSG:4326"),new SuperMap.Projection("EPSG:3857"));
                           var con_str = convert_point.toString();
                           var point_1 = con_str.split("(");
                          var point_1_1= point_1[0];
                           var point_1_2 = point_1[1];
                          var point_1_1_1 = point_1_2.split(" ");
                            var point_first = point_1_1_1[0];
                        var point_112 = point_1_1_1[1];
                        var point_211 = point_112.split(")");
                          var point_second = point_211[0];
                           var geoText = new SuperMap.Geometry.GeoText(point_first,point_second, side1Time);
                           var geotextFeature = new SuperMap.Feature.Vector(geoText);

                             //New a policy and used to vectorfeaturesLayer (vector)
                            var strategy = new SuperMap.Strategy.GeoText();
                               strategy.style = {
                                          fontColor:"#FF7F00",
                                          fontWeight:"bolder",
                                           fontSize:"14px",
                                           fill: true,
                                           fillColor: "#FFFFFF",
                                           fillOpacity: 1,
                                           stroke: true,
                                           strokeColor:"#8B7B8B"
                                             };
                          var vectorLayer = new SuperMap.Layer.Vector("Label",{strategies: [strategy]});
                           map.addLayers([vectorLayer]);
                           vectorLayer.addFeatures([geotextFeature]);
                            //console.log(vectorLayer);
                            }
                            if(i==2){  
                               // alert(side1RightStatus+side1UpStatus+side1DownStatus+side2RightStatus+side3UpStatus+side4RightStatus+side4DownStatus);
                                if(side2LeftStatus == 1){
                                     icon1 = new SuperMap.Icon('images/red_light.png', size, offset);                                                                      
                                 } else{
                                       if(side2RightStatus == 1){
                                         icon1 = new SuperMap.Icon('images/yellow_light.png', size, offset); 
                                       }
                                       else{
                                         icon1 = new SuperMap.Icon('images/up.png', size, offset); 
                                       }
                                   }
                                   marker = new SuperMap.Marker(new SuperMap.LonLat($("#longi"+i).val(),$("#lati"+i).val()).transform(
                            new SuperMap.Projection("EPSG:4326"),
                            map.getProjectionObject()),icon1);
                            markerlayer.addMarker(marker);
                            
                            var latForTimer1 =   $("#lati"+i).val();
                           var longForTimer1 =   $("#longi"+i).val();
                           var pointForTimer = new SuperMap.Geometry.Point(longForTimer1, latForTimer1);
                           var convert_point = pointForTimer.transform(new SuperMap.Projection("EPSG:4326"),new SuperMap.Projection("EPSG:3857"));
                           var con_str = convert_point.toString();
                           var point_1 = con_str.split("(");
                          var point_1_1= point_1[0];
                           var point_1_2 = point_1[1];
                          var point_1_1_1 = point_1_2.split(" ");
                            var point_first = point_1_1_1[0];
                        var point_112 = point_1_1_1[1];
                        var point_211 = point_112.split(")");
                          var point_second = point_211[0];
                           var geoText = new SuperMap.Geometry.GeoText(point_first,point_second, side2Time);
                           var geotextFeature = new SuperMap.Feature.Vector(geoText);

                             //New a policy and used to vectorfeaturesLayer (vector)
                            var strategy = new SuperMap.Strategy.GeoText();
                               strategy.style = {
                                          fontColor:"#FF7F00",
                                          fontWeight:"bolder",
                                           fontSize:"14px",
                                           fill: true,
                                           fillColor: "#FFFFFF",
                                           fillOpacity: 1,
                                           stroke: true,
                                           strokeColor:"#8B7B8B"
                                             };
                          var vectorLayer = new SuperMap.Layer.Vector("Label",{strategies: [strategy]});
                           map.addLayers([vectorLayer]);
                           vectorLayer.addFeatures([geotextFeature]);
                            }
                            if(i==3){  
                               // alert(side1RightStatus+side1UpStatus+side1DownStatus+side2RightStatus+side3UpStatus+side4RightStatus+side4DownStatus);
                                if(side3LeftStatus == 1){
                                     icon1 = new SuperMap.Icon('images/red_light.png', size, offset);                                                                      
                                 } else{
                                       if(side3RightStatus == 1){
                                         icon1 = new SuperMap.Icon('images/yellow_light.png', size, offset); 
                                       }
                                       else{
                                         icon1 = new SuperMap.Icon('images/up.png', size, offset); 
                                       }
                                   }
                                   marker = new SuperMap.Marker(new SuperMap.LonLat($("#longi"+i).val(),$("#lati"+i).val()).transform(
                            new SuperMap.Projection("EPSG:4326"),
                            map.getProjectionObject()),icon1);
                            markerlayer.addMarker(marker);
                            
                            var latForTimer1 =   $("#lati"+i).val();
                           var longForTimer1 =   $("#longi"+i).val();
                           var pointForTimer = new SuperMap.Geometry.Point(longForTimer1, latForTimer1);
                           var convert_point = pointForTimer.transform(new SuperMap.Projection("EPSG:4326"),new SuperMap.Projection("EPSG:3857"));
                           var con_str = convert_point.toString();
                           var point_1 = con_str.split("(");
                          var point_1_1= point_1[0];
                           var point_1_2 = point_1[1];
                          var point_1_1_1 = point_1_2.split(" ");
                            var point_first = point_1_1_1[0];
                        var point_112 = point_1_1_1[1];
                        var point_211 = point_112.split(")");
                          var point_second = point_211[0];
                           var geoText = new SuperMap.Geometry.GeoText(point_first,point_second, side3Time);
                           var geotextFeature = new SuperMap.Feature.Vector(geoText);

                             //New a policy and used to vectorfeaturesLayer (vector)
                            var strategy = new SuperMap.Strategy.GeoText();
                               strategy.style = {
                                          fontColor:"#FF7F00",
                                          fontWeight:"bolder",
                                           fontSize:"14px",
                                           fill: true,
                                           fillColor: "#FFFFFF",
                                           fillOpacity: 1,
                                           stroke: true,
                                           strokeColor:"#8B7B8B"
                                             };
                          var vectorLayer = new SuperMap.Layer.Vector("Label",{strategies: [strategy]});
                           map.addLayers([vectorLayer]);
                           vectorLayer.addFeatures([geotextFeature]);
                            }
                            if(i==4){  
                               // alert(side1RightStatus+side1UpStatus+side1DownStatus+side2RightStatus+side3UpStatus+side4RightStatus+side4DownStatus);
                                if(side4LeftStatus == 1){
                                     icon1 = new SuperMap.Icon('images/red_light.png', size, offset);                                                                      
                                 } else{
                                       if(side4RightStatus == 1){
                                         icon1 = new SuperMap.Icon('images/yellow_light.png', size, offset); 
                                       }
                                       else{
                                         icon1 = new SuperMap.Icon('images/up.png', size, offset); 
                                       }
                                   }
                                   marker = new SuperMap.Marker(new SuperMap.LonLat($("#longi"+i).val(),$("#lati"+i).val()).transform(
                            new SuperMap.Projection("EPSG:4326"),
                            map.getProjectionObject()),icon1);
                            markerlayer.addMarker(marker);
                            
                            var latForTimer1 =   $("#lati"+i).val();
                           var longForTimer1 =   $("#longi"+i).val();
                           var pointForTimer = new SuperMap.Geometry.Point(longForTimer1, latForTimer1);
                           var convert_point = pointForTimer.transform(new SuperMap.Projection("EPSG:4326"),new SuperMap.Projection("EPSG:3857"));
                           var con_str = convert_point.toString();
                           var point_1 = con_str.split("(");
                          var point_1_1= point_1[0];
                           var point_1_2 = point_1[1];
                          var point_1_1_1 = point_1_2.split(" ");
                            var point_first = point_1_1_1[0];
                        var point_112 = point_1_1_1[1];
                        var point_211 = point_112.split(")");
                          var point_second = point_211[0];
                           var geoText = new SuperMap.Geometry.GeoText(point_first,point_second, side4Time);
                           var geotextFeature = new SuperMap.Feature.Vector(geoText);

                             //New a policy and used to vectorfeaturesLayer (vector)
                            var strategy = new SuperMap.Strategy.GeoText();
                               strategy.style = {
                                          fontColor:"#FF7F00",
                                          fontWeight:"bolder",
                                           fontSize:"14px",
                                           fill: true,
                                           fillColor: "#FFFFFF",
                                           fillOpacity: 1,
                                           stroke: true,
                                           strokeColor:"#8B7B8B"
                                             };
                          var vectorLayer = new SuperMap.Layer.Vector("Label",{strategies: [strategy]});
                           map.addLayers([vectorLayer]);
                           vectorLayer.addFeatures([geotextFeature]);
                            }                                                      
                           }
                              }
                          }
                           });
                                                    
                    setTimeout("addData()", 500);
            }
            
            function checkDelay(){
                var b = 2;
                var c = 2;
                var d = b+c;
            }
            
            function anotherFunction(){
                 $.ajax({url: "ts_statusShowerCont", async: true, data: "task=onlyTesting&current_junction_id="+12, dataType:'json', success: function(response_data) {
                                     //icon1 = new SuperMap.Icon('http://localhost:8084/supermap-libs/classic/theme/images/marker.png', size, offset);
                                     
                                     if(response_data == ''){
                        }else{                                                                                                                                                                
//                            side1LeftStatus  =  response_data.side1LeftStatus;
//                            side1RightStatus =  response_data.side1RightStatus;
//                             side1UpStatus    =  response_data.side1UpStatus;
//                             side1DownStatus  =  response_data.side1DownStatus;
//                             side2LeftStatus  =  response_data.side2LeftStatus;
//                             side2RightStatus =  response_data.side2RightStatus;
//                             side2UpStatus    =  response_data.side2UpStatus;
//                             side2DownStatus  =  response_data.side2DownStatus;
//                             side3LeftStatus  =  response_data.side3LeftStatus;
//                             side3RightStatus =  response_data.side3RightStatus;
//                             side3UpStatus    =  response_data.side3UpStatus;
//                             side3DownStatus  =  response_data.side3DownStatus;
//                             side4LeftStatus  =  response_data.side4LeftStatus;
//                             side4RightStatus =  response_data.side4RightStatus;
//                             side4UpStatus    =  response_data.side4UpStatus;
//                             side4DownStatus  =  response_data.side4DownStatus;  
                            $("#side1LeftStatus").val(response_data.side1LeftStatus);
                            $("#side1RightStatus").val(response_data.side1RightStatus);
                            $("#side1UpStatus").val(response_data.side1UpStatus);
                            $("#side1DownStatus").val(response_data.side1DownStatus);
                            $("#side2LeftStatus").val(response_data.side2LeftStatus);
                            $("#side2RightStatus").val(response_data.side2RightStatus);
                            $("#side2UpStatus").val(response_data.side2UpStatus);
                            $("#side2DownStatus").val(response_data.side2DownStatus);
                            $("#side3LeftStatus").val(response_data.side3LeftStatus);
                            $("#side3RightStatus").val(response_data.side3RightStatus);
                            $("#side3UpStatus").val(response_data.side3UpStatus);
                            $("#side3DownStatus").val(response_data.side3DownStatus);
                            $("#side4LeftStatus").val(response_data.side4LeftStatus);
                            $("#side4RightStatus").val(response_data.side4RightStatus);
                            $("#side4UpStatus").val(response_data.side4UpStatus);
                            $("#side4DownStatus").val(response_data.side4DownStatus);
                        }
                              }
                           });  
                
            }

        </script>
    </head>
    <body onload="init()" >
        <div id="map"></div>
        <input type="hidden" id="longi" value="${longi}" >
        <input type="hidden" id="latti" value="${latti}" >
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
        
        <c:forEach var="Coordinates" items="${requestScope['CoordinatesList']}" varStatus="loopCounter">
            <c:set var="cordinateLength"  value="${loopCounter.count}"></c:set>
            <input type="hidden" id="junction_id${loopCounter.count}" value="${Coordinates.junction_id}">
            <input type="hidden" id="lati${loopCounter.count}" value="${Coordinates.latitude}">
            <input type="hidden" id="longi${loopCounter.count}" value="${Coordinates.longitude}">
        </c:forEach>
        <input type="hidden" id="count" value="${cordinateLength}">
        <input type="hidden" id="latlon" value="${latlon}">
        <input type="hidden" id="lat1" value="${lat1}">
        <input type="hidden" id="lon1" value="${lon1}">
        <input type="hidden" id="junction_Id" value="${junction_Id}">
              
    </body>
</html>
