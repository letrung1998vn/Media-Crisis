<%-- 
    Document   : login_JSP
    Created on : Feb 2, 2020, 5:36:04 PM
    Author     : Administrator
--%>

<%@page import="MediaCrisis.Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Sign up JSP</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!--===============================================================================================-->
    </head>
    <body>

        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <form class="login100-form validate-form-signup" action="MainController" method="post">
                        <span class="login100-form-title p-b-26">
                            Sign Up
                        </span>
                        <% User user = (User) request.getAttribute("INPUT_USER"); %>
                        <div class="wrap-input100 validate-input validate-username-exist" data-validate = "Enter username">
                            <input class="input100" type="text" name="txtUsername" value="">
                            <span class="focus-input100" data-placeholder="Username"></span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Enter name">
                            <input class="input100" type="text" name="txtName" value="<% if (user != null) {%><%= user.getName()%><% } %>">
                            <span class="focus-input100" data-placeholder="Name"></span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Valid email is: a@b.c">
                            <input class="input100" type="text" name="txtEmail" value="<% if (user != null) {%><%= user.getEmail()%><% }%>">
                            <span class="focus-input100" data-placeholder="Email"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="8chars min, atleast 1 char and num">
                            <span class="btn-show-pass">
                                <i class="zmdi zmdi-eye"></i>
                            </span>
                            <input class="input100" type="password" name="txtPassword">
                            <span class="focus-input100" data-placeholder="Password"></span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Not match">
                            <span class="btn-show-pass">
                                <i class="zmdi zmdi-eye"></i>
                            </span>
                            <input class="input100" type="password" name="txtPasswordConf">
                            <span class="focus-input100" data-placeholder="Password"></span>
                        </div>
                        <div class="container-login100-form-btn">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="login100-form-btn" type="submit" value="SignUp" name="btnAction">
                                    Sign up
                                </button>
                            </div>
                        </div>

                        <div class="text-center">
                            <span class="txt1">
                                Already have an account?
                            </span>

                            <a class="txt2" href="login_JSP.jsp">
                                Login
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <div id="dropDownSelect1"></div>

        <!--===============================================================================================-->
        <!--===============================================================================================-->
        <script src="vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/daterangepicker/moment.min.js"></script>
        <script src="vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->
        <script src="js/main.js"></script>
        <!--   Core JS Files   -->
        <script src="assets/js/jquery.3.2.1.min.js" type="text/javascript"></script>
        <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

        <!--  Charts Plugin -->
        <script src="assets/js/chartist.min.js"></script>

        <!--===============================================================================================-->
        <!--===============================================================================================-->
        <script src="vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/daterangepicker/moment.min.js"></script>
        <script src="vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->
        <script src="js/main.js"></script>
        <!--  Notifications Plugin    -->
        <script src="assets/js/bootstrap-notify.js"></script>

        <!--  Google Maps Plugin    -->
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>

        <!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
        <script src="assets/js/light-bootstrap-dashboard.js?v=1.4.0"></script>

        <!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
        <script src="assets/js/demo.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                if (<%=request.getAttribute("SEND")%>) {
                    $.notify({
                        icon: "pe-7s-bell",
                        message: '<%=request.getAttribute("CREATE_MESSAGE")%>'

                    }, {
                        type: type[<%=request.getAttribute("RESULT")%>],
                        timer: 4000,
                        placement: {
                            from: 'top',
                            align: 'left'
                        }
                    });
                }
                if (<%= request.getAttribute("INPUT_USER") != null%>) {
                    $('.input100').each(function () {
                        if ($(this).attr("name") != "txtUsername") {
                            $(this).focus();
                        }
                    });
                    var validateUsername = $('.validate-username-exist');
                    validateUsername.attr('data-validate', 'Existed username, please pick another!');
                    validateUsername.addClass('alert-validate');
                    $("[name='txtPasswordConf']").blur();
                }

            });
        </script>

    </body>
</html>
