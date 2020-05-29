<%-- 
    Document   : menu_before_login
    Created on : Nov 1, 2012, 6:10:00 PM
    Author     : Neha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>
        <title>Traffic Signal</title>
        <link rel="stylesheet" href="css/style.css" media="screen">
        <link rel="stylesheet" href="style/menu.css" media="screen">
        <link rel="stylesheet" href="style/login.css" media="screen">
    </head>
    <script>
        function ShowHide(wrapper)
        {
            if(document.getElementById(wrapper).style.display == 'none' || document.getElementById(wrapper).style.display == '')
            {
                document.getElementById(wrapper).style.display='block';
            }
            else
            {
                document.getElementById(wrapper).style.display = 'none';
            }
        }

       </script>

    <body>
        <ul class="menu">

            <li><a href="beforeHomeCont" class="home">Home</a></li>
            <li><a href="#" class="about">About us</a></li>
            <li><a href="#" class="rules">Traffic Rules</a></li>
            <li><a href="#" class="contact">Contact Us</a></li>

            <li style="margin-left: 400px" > <a href="#" onclick="ShowHide('wrapper')" style="border: none;position: absolute;float: left"><div style="height: 30px;width: 100px">Login<input  type="image" src="img/User-icon.png" class="button_signout" align="right"/></div></a>

                <div id="wrapper">
                    <div id="login">
                        <form name="form1" method="post" action="loginCont">
                            <h1>Log in</h1>
                            <div>
                                <div> <span>Your email or username</span>
                                    <input id="username" name="username"  required="required"type="text"/></div>
                            </div>
                            <div>
                                <div> <span>Your password </span></div>
                                <div><input id="password" name="password" required="required" type="password"  /> </div>
                            </div>
                            <div class="keeplogin">
                                <div ><input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" />
                                    Keep me logged in</div>
                                <div><a>Forget password</a></div>
                            </div>
                            <div class="login button">
                                <input type="submit" value="Login" name="task"/>
                            </div>
                            <div id="message">
                                <c:if test="${not empty message}">
                                    <div  bgcolor="${msgBgColor}"><b>${message}</b></div>
                                </c:if>
                            </div>
                        </form>
                    </div>


                </div>

            </li>



        </ul> <!-- end .menu -->

    </body>

</html>