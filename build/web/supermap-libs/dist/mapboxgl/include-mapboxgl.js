(function () {
    var r = new RegExp("(^|(.*?\\/))(include-mapboxgl\.js)(\\?|$)"),
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
        if (!inArray(excludes, 'mapbox-gl')) {
            inputCSS("../../web/libs/mapbox-gl-js/0.48.0/mapbox-gl.css");
            inputScript("../../web/libs/mapbox-gl-js/0.48.0/mapbox-gl.js");
        }
        if (inArray(includes, 'draw')) {
            inputCSS("../../web/libs/mapbox-gl-js/plugins/mapbox-gl-draw/1.0.9/mapbox-gl-draw.css");
            inputScript("../../web/libs/mapbox-gl-js/plugins/mapbox-gl-draw/1.0.9/mapbox-gl-draw.js");
        }
        if (inArray(includes, 'compare')) {
            inputCSS("../../web/libs/mapbox-gl-js/plugins/mapbox-gl-compare/mapbox-gl-compare.css");
            inputScript("../../web/libs/mapbox-gl-js/plugins/mapbox-gl-compare/mapbox-gl-compare.js");
        }
        if (inArray(includes, 'mapv')) {
            inputScript("../../web/libs/mapv/2.0.20/mapv.min.js");
        }
        if (inArray(includes, 'echarts')) {
            inputScript("../../web/libs/echarts/4.1.0/echarts.min.js");
            inputScript("../../web/libs/echartsLayer/EchartsLayer.js");
        }
        if (inArray(includes, 'three')) {
            inputScript("../../web/libs/three/92/three.min.js");
        }
        if (inArray(includes, 'deck')) {
            inputScript("../../web/libs/deck.gl/5.1.3/deck.gl.min.js");
        }
        if (!inArray(excludes, 'iclient9-mapboxgl')) {
            if (supportES6()) {
                inputScript("../../dist/mapboxgl/iclient9-mapboxgl-es6.min.js");
            } else {
                inputScript("../../dist/mapboxgl/iclient9-mapboxgl.min.js");
            }
        }
        if (inArray(includes, 'LoaderSupport')) {
            inputScript("../../web/libs/three/plugins/loaders/LoaderCommons.js");
            inputScript("../../web/libs/three/plugins/loaders/LoaderBuilder.js");
            inputScript("../../web/libs/three/plugins/loaders/LoaderWorkerSupport.js");
        }
        if (inArray(includes, 'OBJLoader')) {
            inputScript("../../web/libs/three/plugins/loaders/OBJLoader.js");
        }
        if (inArray(includes, 'OBJLoader2')) {
            inputScript("../../web/libs/three/plugins/loaders/OBJLoader2.js");
        }
        if (inArray(includes, 'MTLLoader')) {
            inputScript("../../web/libs/three/plugins/loaders/MTLLoader.js");
        }
        if (inArray(includes, 'GLTFLoader')) {
            inputScript("../../web/libs/three/plugins/loaders/GLTFLoader.js");
        }
        if (inArray(includes, 'proj4')) {
            inputScript("../../web/libs/proj4/2.4.4/proj4.js");
        }
        if (inArray(includes, 'echarts-gl')) {
            inputScript("../../web/libs/echarts-gl/1.1.1/echarts-gl.min.js");
        }
        if (inArray(includes, 'shapefile')) {
            inputScript("../../web/libs/shapefile/shapefile.js");
        }
    }

    load();
    window.isLocal = true;
    window.server = document.location.toString().match(/file:\/\//) ? "http://localhost:8090" : document.location.protocol + "//" + document.location.host;
})();
