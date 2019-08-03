<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Circular Navigation - Demo 1 | Codrops</title>
    <meta name="description" content="Circular Navigation Styles - Building a Circular Navigation with CSS Transforms | Codrops " />
    <meta name="keywords" content="css transforms, circular navigation, round navigation, circular menu, tutorial" />
    <meta name="author" content="Sara Soueidan for Codrops" />
    <link rel="shortcut icon" href="../favicon.ico">
    <link rel="stylesheet" type="text/css" href="css/nav/normalize.css" />
    <link rel="stylesheet" type="text/css" href="css/nav/demo.css" />
    <link rel="stylesheet" type="text/css" href="css/nav/component1.css" />
    <script src="js/nav/modernizr-2.6.2.min.js"></script>

    <script type="text/javascript">
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-7243260-2']);
        _gaq.push(['_trackPageview']);
        (function() {
            var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
        })();
    </script>

</head>
<body style="background: #f06060;">
<div class="container">
    <!-- Top Navigation -->
<%--    <div class="codrops-top clearfix">--%>
<%--        <a class="codrops-icon codrops-icon-prev" href="http://tympanus.net/Development/CreativeLinkEffects/"><span>Previous Demo</span></a>--%>
<%--        <span class="right"><a class="codrops-icon codrops-icon-drop" href="http://tympanus.net/codrops/?p=16114"><span>Back to the Codrops Article</span></a></span>--%>
<%--    </div>--%>
<%--    <header>--%>
<%--        <h1>Circular Navigation <span>Building a Circular Navigation with CSS Transforms</span></h1>--%>
<%--        <nav class="codrops-demos">--%>
<%--            <a class="current-demo" href="index.html">Demo 1</a>--%>
<%--            <a href="index2.html">Demo 2</a>--%>
<%--            <a href="interactivedemo/index.html">Intractive demo</a>--%>
<%--        </nav>--%>
<%--    </header>--%>
<%--    --%>
    <div class="component">
        <!-- Start Nav Structure -->
        <button class="cn-button" id="cn-button">+</button>
        <div class="cn-wrapper" id="cn-wrapper">
            <ul>
                <li><a href="/profile"><span class="icon-home"></span></a></li>

                <li><a href="/news"><span class="fas fa-newspaper"></span></a></li>
                <li><a href="/friend"><span class="fas fa-users"></span></a></li>
                <li><a href="/views/dialog/dialog.jsp"><span class="fas fa-envelope"></span></a></li>
                <li><a href="/login"><span class="icon-exitfas fa-times"></span></a></li>
            </ul>
        </div>
        <div id="cn-overlay" class="cn-overlay"></div>
        <!-- End Nav Structure -->
    </div>
</div><!-- /container -->
<script src="js/nav/polyfills.js"></script>
<script src="js/nav/demo1.js"></script>
<!-- For the demo ad only -->
<script src="http://tympanus.net/codrops/adpacks/demoad.js"></script>
</body>
</html>











