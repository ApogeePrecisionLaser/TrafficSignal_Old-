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
        <script src = "supermap-libs/classic/libs/SuperMap.Include.js"></script>
        <script type="text/javascript">
            var map, layer, markerlayer, marker, points,osmLayer,junction_id,
                    //host = document.location.toString().match(/file:\/\//) ? "http://localhost:8090" : 'http://' + document.location.host;
                    //url = host + "/iserver/services/map-world/rest/maps/World";
                    url = "http://45.114.142.35:8090/iserver/services/map-world/rest/maps/World";

            function init()
            {
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
                //var position1= (new SuperMap.LonLat(23.1657228,79.9505182).transform(new SuperMap.Projection("EPSG:4326")));
                map.setCenter(new SuperMap.LonLat(79.9505182, 23.1657228).transform(new SuperMap.Projection("EPSG:4326"),map.getProjectionObject()), 13);
                //map.setCenter(position1, 8);
                addData();
            }

            var infowin = null;
        //Define the mouseClickHandler function. This function will be called when trigger this click event
        function mouseClickHandler(event){
            closeInfoWin(); 
            var latlon = (marker.getLonLat());
            var location = this.getLonLat();
            //alert(location);
           var lon = location.lon;
           var lat = location.lat;
           var point = new SuperMap.Geometry.Point(lon, lat);
                  var convert_point = point.transform(new SuperMap.Projection("EPSG:3857"),new SuperMap.Projection("EPSG:4326"));
           //alert(location.lon+","+location.lat);
           var con_str = convert_point.toString();
           var point_1 = con_str.split("(");
           var point_1_1= point_1[0];
           var point_1_2 = point_1[1];
           var point_1_1_1 = point_1_2.split(" ");
           var point_first = point_1_1_1[0];
           var point_112 = point_1_1_1[1];
           var point_211 = point_112.split(")");
           var point_second = point_211[0];
           //var latlon1 = (new SuperMap.LonLat(8900050.9734435, 2652072.3475235).transform(new SuperMap.Projection("EPSG:3857"),map.getProjectionObject())); 
           //alert(latlon1);
           //var latlonArray = latlon.split(",");
          // var lon = latlonArray[1];
           //var lat = latlonArray[0];
           //var latlon1 = lon+","+lat;
           //alert(lat+" "+lon);
          var url = "ts_statusShowerCont?task=showSingleJunctionStatus&latlon="+location+"&latitude="+point_second+"&longitude="+point_first;
          window.open(url);         
        }
        function mouseClickHandler1(event){
            closeInfoWin();  
            var junction_name,no_of_sides,no_of_plans;
           //var latlon = (marker.getLonLat()); // lon=8900050.9734435,lat=2652072.3475235  
           var location = this.getLonLat();
           var lon = location.lon;
           var lat = location.lat;
           var point = new SuperMap.Geometry.Point(lon, lat);
                  var convert_point = point.transform(new SuperMap.Projection("EPSG:3857"),new SuperMap.Projection("EPSG:4326"));
           //alert(location.lon+","+location.lat);
           var con_str = convert_point.toString();
           var point_1 = con_str.split("(");
           var point_1_1= point_1[0];
           var point_1_2 = point_1[1];
           var point_1_1_1 = point_1_2.split(" ");
           var point_first = point_1_1_1[0];
           var point_112 = point_1_1_1[1];
           var point_211 = point_112.split(")");
           var point_second = point_211[0];
           //alert(convert_point+","+point_1+","+point_1_1+","+point_1_2+" :: "+point_1_1_1+" uy "+point_111+" tyu"+point_112);
           //alert(point_111+","+point_211_1);
           $.ajax({url: "ts_statusShowerCont", async: true, data: "task=getJunctionIdByLatLong&longitude="+point_first+"&latitude="+point_second, dataType:'json', success: function(response_data) {
                                                                        
                                     if(response_data == ''){
                        }else{ 
                            junction_name = response_data.junction_name;
                            no_of_sides = response_data.no_of_sides;
                            no_of_plans = response_data.no_of_plans;
                        
           var print = "Junction Info ";
           popup = new SuperMap.Popup(
                    "chicken",
                    marker.getLonLat(),
                    new SuperMap.Size(350,250),
                    "<div>"+print+"</div>"+
                    "<div>"+junction_name+"</div>"+                            
                    "<div>"+no_of_sides+"</div>"+                            
                    "<div>"+no_of_plans+"</div>",                            
                    true,
                    null
            );
            infowin = popup;
            //Add the popup to the map layer
            map.addPopup(popup);  
            }
                    }
                });        
        }

        function closeInfoWin(){
            if(infowin){
                        try {
                            infowin.hide();
                            infowin.destroy();
                        } catch (e) {
                        }
                    }
                }


//            function addLayer() {
//
//                map.addLayers([layer, markerlayer]);
//                //Display the map extent
//                map.setCenter(new SuperMap.LonLat(0, 0), 17);
//
//            }
            //Add data
            function addData()
            {                              
                var coordinateCount = $("#count").val();
                
                        for (var i=1; i <= coordinateCount; i++) {                           
                            var size = new SuperMap.Size(44, 33);
                            var offset = new SuperMap.Pixel(-(size.w / 2), -size.h);
                            //junction_id = $("#junction_id"+i).val();
                            var icon = new SuperMap.Icon('supermap-libs/classic/theme/images/marker.png', size, offset);
                            marker = new SuperMap.Marker(new SuperMap.LonLat($("#longi"+i).val(),$("#lati"+i).val()).transform(
                            new SuperMap.Projection("EPSG:4326"),
                            map.getProjectionObject()),icon);
                            //marker = new SuperMap.Marker(new SuperMap.LonLat($("#longi"+i).val(),$("#lati"+i).val()),icon);
                            markerlayer.addMarker(marker);
                            marker.events.on({"mouseover":mouseClickHandler1,
                                              "touchstart":mouseClickHandler1,
                                                    "scope":marker});
                            marker.events.on({"click":mouseClickHandler});
                        }                             

            }

        </script>
    </head>
    <body onload="init()" >
        <div id="map"></div>
        <input type="hidden" id="longi" value="${longi}" >
        <input type="hidden" id="latti" value="${latti}" >
        <c:forEach var="Coordinates" items="${requestScope['CoordinatesList']}" varStatus="loopCounter">
            <c:set var="cordinateLength"  value="${loopCounter.count}"></c:set>
            <input type="hidden" id="junction_id${loopCounter.count}" value="${Coordinates.junction_id}">
            <input type="hidden" id="lati${loopCounter.count}" value="${Coordinates.latitude}">
            <input type="hidden" id="longi${loopCounter.count}" value="${Coordinates.longitude}">
        </c:forEach>
        <input type="hidden" id="count" value="${cordinateLength}">
    </body>
</html>
