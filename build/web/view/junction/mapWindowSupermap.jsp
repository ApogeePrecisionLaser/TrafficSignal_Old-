<%-- 
    Document   : mapWindowSupermap
    Created on : Jan 25, 2019, 10:22:21 AM
    Author     : Administrator
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
        <script src = "http://localhost:8084/supermap-libs/classic/libs/SuperMap.Include.js"></script>
        <script type="text/javascript">
            var map, layer, markerlayer, marker,points,vector,pointVector,osmLayer,
                    //host = document.location.toString().match(/file:\/\//) ? "http://localhost:8090" : 'http://' + document.location.host;
                    //url = host + "/iserver/services/map-world/rest/maps/World";
                    url = "http://localhost:8090/iserver/services/map-world/rest/maps/World";

            function init()
            {
                map = new SuperMap.Map("map", {controls: [
                        new SuperMap.Control.Zoom(),
                        new SuperMap.Control.Navigation(),
                        new SuperMap.Control.LayerSwitcher()
                    ]});               
               
                osmLayer = new SuperMap.Layer.OSM();
                //vector = new SuperMap.Layer.Vector("vector");
                markerlayer = new SuperMap.Layer.Markers("markerLayer");
                map.addLayers([osmLayer, markerlayer]);
                //Display the map extent
                map.setCenter(new SuperMap.LonLat(8117383.7784246, 2574751.9467887), 8);

                addData();
            }          
            //Add data
            function addData()
            {

              var x = $.trim(document.getElementById("latti").value);
              var y = $.trim(document.getElementById("longi").value);
                    
                var size = new SuperMap.Size(44, 33);
                var offset = new SuperMap.Pixel(-(size.w / 2), -size.h);
                var icon = new SuperMap.Icon('http://localhost:8084/supermap-libs/classic/theme/images/marker.png', size, offset);            
                markerlayer.addMarker(new SuperMap.Marker(new SuperMap.LonLat(y,x).transform(
                      new SuperMap.Projection("EPSG:4326"),
                       map.getProjectionObject()),icon));

            }

        </script>
    </head>
    <body onload="init()" >
        <div id="map"></div>
        <input type="hidden" id="longi" value="${longi}" >
        <input type="hidden" id="latti" value="${latti}" >      
    </body>
</html>

