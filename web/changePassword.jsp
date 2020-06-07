<%@page import="MediaCrisis.Model.User"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <link rel="icon" type="image/png" href="assets/img/favicon.ico">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

        <title>Light Bootstrap Dashboard by Creative Tim</title>

        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
        <meta name="viewport" content="width=device-width" />


        <!-- Bootstrap core CSS     -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" />

        <!-- Animation library for notifications   -->
        <link href="assets/css/animate.min.css" rel="stylesheet"/>

        <!--  Light Bootstrap Table core CSS    -->
        <link href="assets/css/light-bootstrap-dashboard.css?v=1.4.0" rel="stylesheet"/>


        <!--  CSS for Demo Purpose, don't include it in your project     -->
        <link href="assets/css/demo.css" rel="stylesheet" />


        <!--     Fonts and icons     -->
        <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
        <link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet" />
    </head>
    <body>

        <div class="wrapper">
            <div class="sidebar" data-color="purple" data-image="assets/img/sidebar-5.jpg">

                <!--   you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple" -->


                <div class="sidebar-wrapper">
                    <div class="logo">
                        <a href="#" class="simple-text">
                            Media Crisis Detect Application
                        </a>
                    </div>

                    <ul class="nav">
                        <li>
                            <a href="mainPage_JSP.jsp">
                                <i class="pe-7s-graph"></i>
                                <p>Dashboard</p>
                            </a>
                        </li>
                        <li class="active">
                            <a href="userProfile.jsp">
                                <i class="pe-7s-user"></i>
                                <p>User Profile</p>
                            </a>
                        </li>
                        <li>
                            <a href="Keyword_JSP.jsp">
                                <i class="pe-7s-note2"></i>
                                <p>Keyword</p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="main-panel">
                <nav class="navbar navbar-default navbar-fixed">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="#">User Profile</a>
                        </div>
                        <div class="collapse navbar-collapse">

                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="webhook.jsp">
                                        <p>Webhook</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <p>Change password</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="userProfile.jsp">
                                        <p>Account</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="MainController?btnAction=LogOut">
                                        <p>Log out</p>
                                    </a>
                                </li>
                                <li class="separator hidden-lg hidden-md"></li>
                            </ul>
                        </div>
                    </div>
                </nav>


                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="header">
                                        <h4 class="title">Change Password</h4>
                                    </div>
                                    <div class="content">
                                        <% String pwd = (String) session.getAttribute("PWD");%>
                                        <form action="MainController" method="post" class="validate-form-change-password">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <div class="col-md-12">
                                                            <div class="col-md-4"><label>Old Password</label></div>
                                                            <div class="col-md-8">
                                                                <label  class="old-password-error"></label>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-9">
                                                            <div class="col-md-8">
                                                                <input id="pwd1" type="password" name="txtOldPassword" class="form-control input100" placeholder="Old Password" value="">
                                                                <input name="old-password-hidden" type="hidden" value="<%= pwd%>">
                                                            </div>
                                                            <div class="col-md-4">
                                                                <input type="checkbox" onclick="showPassword1()"> Show Password
                                                            </div>

                                                        </div>

                                                    </div>
                                                </div>

                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <div class="col-md-12">
                                                            <div class="col-md-4"><label>New Password</label></div>
                                                            <div class="col-md-8">
                                                                <label  class="new-password-error"></label>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-9">
                                                            <div class="col-md-8">
                                                                <input id="pwd2" type="password" name="txtPassword" class="form-control input100" placeholder="New Password" value="">
                                                            </div>
                                                            <div class="col-md-4">
                                                                <input type="checkbox" onclick="showPassword2()"> Show Password
                                                            </div>

                                                        </div>

                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <div class="col-md-12">
                                                            <div class="col-md-4"><label>Confirm Password</label></div>
                                                            <div class="col-md-8">
                                                                <label class="confirm-password-error"></label>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-9">
                                                            <div class="col-md-8">
                                                                <input id="pwd" type="password" name="txtConfirmPassword" class="form-control input100" placeholder="Confirm Password" value="">
                                                            </div>
                                                            <div class="col-md-4">
                                                                <input type="checkbox" onclick="showPassword()"> Show Password
                                                            </div>

                                                        </div>

                                                    </div>
                                                </div>
                                            </div>

                                            <button type="submit" class="btn btn-info btn-fill pull-left" value="ChangePassword" name="btnAction">Update Password</button>
                                            <div class="clearfix"></div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>


                <footer class="footer">
                    <div class="container-fluid">
                        <nav class="pull-left">
                            <ul>
                                <li>
                                    <a href="mainPage_JSP.jsp">
                                        Home
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        Company
                                    </a>
                                </li>
                            </ul>
                        </nav>
                        <p class="copyright pull-right">
                            &copy; <script>document.write(new Date().getFullYear())</script> <a href="http://www.creative-tim.com">Media Crisis</a>, for your better business 
                        </p>
                    </div>
                </footer>

            </div>
        </div>


    </body>

    <!--   Core JS Files   -->
    <script src="assets/js/jquery.3.2.1.min.js" type="text/javascript"></script>
    <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

    <!--  Charts Plugin -->
    <script src="assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="assets/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>

    <!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
    <script src="assets/js/light-bootstrap-dashboard.js?v=1.4.0"></script>

    <!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
    <script src="assets/js/demo.js"></script>

</html>

<script src="js/main.js"></script>
<script>
                                function showPassword() {
                                    var x = document.getElementById("pwd");
                                    if (x.type === "password") {
                                        x.type = "text";
                                    } else {
                                        x.type = "password";
                                    }
                                }
                                function showPassword1() {
                                    var x = document.getElementById("pwd1");
                                    if (x.type === "password") {
                                        x.type = "text";
                                    } else {
                                        x.type = "password";
                                    }
                                }
                                function showPassword2() {
                                    var x = document.getElementById("pwd2");
                                    if (x.type === "password") {
                                        x.type = "text";
                                    } else {
                                        x.type = "password";
                                    }
                                }
</script>
<script type="text/javascript">
    $(document).ready(function () {
        if (<%=session.getAttribute("SEND")%>) {
            $.notify({
                icon: "pe-7s-bell",
                message: '<%=session.getAttribute("CREATE_MESSAGE")%>'

            }, {
                type: type[<%=session.getAttribute("RESULT")%>],
                timer: 4000,
                placement: {
                    from: 'top',
                    align: 'left'
                }
            });
    <% session.removeAttribute("SEND"); %>
    <% session.removeAttribute("CREATE_MESSAGE"); %>
    <% session.removeAttribute("RESULT");%>
        }
    });
</script>
