﻿(function () {
    var r = new RegExp("(^|(.*?\\/))(include-openlayers\.js)(\\?|$)"),
        s = document.getElementsByTagName('script'), targetScript;
    for (var i = 0; i < s.length; i++) {
        var src = s[i].getAttribute('src');
        if (src) {
            var m = src.match(r);
            if (m) {
                targetScript = s[i];
                break;
            }
        }
    }

    function inputScript(url) {
        var script = '<script type="text/javascript" src="' + url + '"><' + '/script>';
        document.writeln(script);
    }

    function inputCSS(url) {
        var css = '<link rel="stylesheet" href="' + url + '">';
        document.writeln(css);
    }

    function inArray(arr, item) {
        for (i in arr) {
            if (arr[i] == item) {
                return true;
            }
        }
        return false;
    }

    function supportES6() {
        var code = "'use strict'; class Foo {}; class Bar extends Foo {};";
        try {
            (new Function(code))();
        } catch (err) {
            return false;
        }
        if (!Array.from) {
            return false;
        }
        return true;
    }


    //加载类库资源文件
    function load() {
        var includes = (targetScript.getAttribute('include') || "").split(",");
        var excludes = (targetScript.getAttribute('exclude') || "").split(",");
        if (!inArray(excludes, 'ol') && !inArray(includes, 'ol-debug')) {
            inputCSS("../../web/libs/openlayers/4.6.5/ol.css");
            inputScript("../../web/libs/openlayers/4.6.5/ol.js");
        }
        if (inArray(includes, 'ol-debug')) {
            inputCSS("../../web/libs/openlayers/4.6.5/ol-debug.css");
            inputScript("../../web/libs/openlayers/4.6.5/ol-debug.js");
        }
        if (inArray(includes, 'mapv')) {
            inputScript("../../web/libs/mapv/2.0.20/mapv.min.js");
        }
        if (inArray(includes, 'turf')) {
            inputScript("../../web/libs/turf/5.1.6/turf.min.js");
        }
        if (inArray(includes, 'deck')) {
            inputScript("../../web/libs/deck.gl/5.1.3/deck.gl.min.js");
        }
        if (inArray(includes, 'ol-mapbox-style')) {
            inputScript("../../web/libs/openlayers/plugins/ol-mapbox-style/2.11.2/olms.js");
        }
        if (!inArray(excludes, 'iclient9-openlayers')) {
            if (supportES6()) {
                inputScript("../../dist/openlayers/iclient9-openlayers-es6.min.js");
            } else {
                inputScript("../../dist/openlayers/iclient9-openlayers.min.js");
            }
        }
        if (!inArray(excludes, 'iclient9-openlayers-css')) {
            inputCSS("../../dist/openlayers/iclient9-openlayers.min.css");
        }
        if (inArray(includes, 'echarts')) {
            inputScript("../../web/libs/echarts/4.1.0/echarts.min.js");
        }
        if (inArray(includes, 'ol3-echarts')) {
            inputScript("../../web/libs/openlayers/ol3-echarts/1.3.4/ol3Echarts.min.js");
        }
        if (inArray(includes, 'osmbuildings')) {
            inputScript("../../web/libs/osmbuildings/OSMBuildings-OL3.js");
        }
        if (inArray(includes, 'animatedclusterlayer')) {
            inputScript("../../web/libs/openlayers/plugins/animatedclusterlayer/animatedclusterlayer.js");
        }
        if (inArray(includes, 'layerswitcher')) {
            inputCSS("../../web/libs/openlayers/plugins/ol-layerswitcher/2.0.0/ol-layerswitcher.css");
            inputScript("../../web/libs/openlayers/plugins/ol-layerswitcher/2.0.0/ol-layerswitcher.js");
        }
    }

    load();
    window.isLocal = true;
    window.server = document.location.toString().match(/file:\/\//) ? "http://localhost:8090" : document.location.protocol + "//" + document.location.host;
})();
