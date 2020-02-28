
<%@page import="MediaCrisis.Model.Keyword"%>
<%@page import="java.util.List"%>
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

        <script>
        </script>
    </head>
    <body>

        <div class="wrapper">
            <div class="sidebar" data-color="purple" data-image="assets/img/sidebar-5.jpg">

                <!--   you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple" -->


                <div class="sidebar-wrapper">
                    <div class="logo">
                        <a href="http://www.creative-tim.com" class="simple-text">
                            Media Crisis Detect Application
                        </a>
                    </div>

                    <ul class="nav">
                        <li>
                            <a href="adminPage_JSP.jsp">
                                <i class="pe-7s-graph"></i>
                                <p>Dashboard</p>
                            </a>
                        </li>
                        <li>
                            <a href="user.html">
                                <i class="pe-7s-user"></i>
                                <p>User Profile</p>
                            </a>
                        </li>
                        <li class="active">
                            <a href="MainController?btnAction=SearchKeyword&page=1&userId=&searchValue=">
                                <i class="pe-7s-note2"></i>
                                <p>Keyword Admin</p>
                            </a>
                        </li>
                        <li>
                            <a href="MainController?btnAction=SearchUser&page=1&searchUser=">
                                <i class="pe-7s-news-paper"></i>
                                <p>User manager</p>
                            </a>
                        </li>
                        <li>
                            <a href="icons.html">
                                <i class="pe-7s-science"></i>
                                <p>Icons</p>
                            </a>
                        </li>
                        <li>
                            <a href="maps.html">
                                <i class="pe-7s-map-marker"></i>
                                <p>Maps</p>
                            </a>
                        </li>
                        <li>
                            <a href="notifications.html">
                                <i class="pe-7s-bell"></i>
                                <p>Notifications</p>
                            </a>
                        </li>
                        <li class="active-pro">
                            <a href="upgrade.html">
                                <i class="pe-7s-rocket"></i>
                                <p>Upgrade to PRO</p>
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
                            <a class="navbar-brand" href="#">Keyword List</a>
                        </div>
                        <div class="collapse navbar-collapse">
                            <ul class="nav navbar-nav navbar-left">
                                <!--                                <li>
                                                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                                        <i class="fa fa-dashboard"></i>
                                                                        <p class="hidden-lg hidden-md">Dashboard</p>
                                                                    </a>
                                                                </li>-->
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <i class="fa fa-globe"></i>
                                        <b class="caret hidden-sm hidden-xs"></b>
                                        <span class="notification hidden-sm hidden-xs">5</span>
                                        <p class="hidden-lg hidden-md">
                                            5 Notifications
                                            <b class="caret"></b>
                                        </p>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Notification 1</a></li>
                                        <li><a href="#">Notification 2</a></li>
                                        <li><a href="#">Notification 3</a></li>
                                        <li><a href="#">Notification 4</a></li>
                                        <li><a href="#">Another notification</a></li>
                                    </ul>
                                </li>
                            </ul>

                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="user.html">
                                        <p>Account</p>
                                    </a>
                                </li>
                                <!--                                <li class="dropdown">
                                                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                                        <p>
                                                                            Dropdown
                                                                            <b class="caret"></b>
                                                                        </p>
                                
                                                                    </a>
                                                                    <ul class="dropdown-menu">
                                                                        <li><a href="#">Action</a></li>
                                                                        <li><a href="#">Another action</a></li>
                                                                        <li><a href="#">Something</a></li>
                                                                        <li><a href="#">Another action</a></li>
                                                                        <li><a href="#">Something</a></li>
                                                                        <li class="divider"></li>
                                                                        <li><a href="#">Separated link</a></li>
                                                                    </ul>
                                                                </li>-->
                                <li>
                                    <a href="#">
                                        <p>Log out</p>
                                    </a>
                                </li>
                                <li class="separator hidden-lg hidden-md"></li>
                            </ul>
                        </div>
                    </div>

                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button class="addKeywordDropdown">Add new keyword</button>
                        </div>
                    </div>
                </nav>

                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="col-md-12 dropdown-create-keyword hide">
                                        <div class="header">
                                            <h4 class="title">Create New Keyword</h4>
                                        </div>
                                        <div class="content">
                                            <form class="login100-form validate-form-add-keyword-admin" action="MainController" method="POST">
                                                <div class="row">
                                                    <div class="col-md-5">

                                                        <div class="wrap-input100 validate-input" data-validate = "">
                                                            <input class="input100" type="text" name="txtKeyword">
                                                            <span class="focus-input100"></span>
                                                        </div>
                                                        <button class="login100-form-btn btn-add-new-keyword" type="submit" value="CreateKeywordAdmin" name="btnAction">
                                                            Add
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="header col-md-12">
                                            <h4 class="title">Search Keyword</h4>
                                        </div>
                                        <div class="content col-md-12">
                                            <div class="row">
                                                <form class="login100-form col-md-12 validate-form-search-keyword-admin" action="MainController" method="post">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <span>Keyword:</span><br>
                                                            <input type="text" class="form-control search-keyword-admin" placeholder="Enter Keyword" name="searchValue" value="<%= session.getAttribute("SEARCHINGKEYWORD")%>">
                                                            <input type="hidden" class="form-control search-keyword-admin" name="page" value="1">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12" style="padding-left: 15px">
                                                        <div class="form-group">
                                                            <span>Username:</span><br>
                                                            <input list="my-list" type="text" class="form-control validate-input input100 userId" placeholder="Enter Username" name="userId" value="<%= session.getAttribute("SEARCHINGUSERNAMEOFKEYWORD")%>">
                                                            <% String[] listUser = (String[]) session.getAttribute("USERSKEYWORDADMIN");
                                                                if (listUser != null) {%>
                                                            <datalist id="my-list">
                                                                <% for (int i = 0; i < listUser.length; i++) {
                                                                %>
                                                                <option class="username-details" value="<%= listUser[i]%>"></option>
                                                                <% } %>
                                                            </datalist>
                                                            <% } %>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <div class="col-md-3 pull-right" style="padding-left: 15px">
                                                            <button id="btn-search" class="btn btn-info btn-fill col-md-1 form-control"  type="submit" value="SearchKeyword" name="btnAction">Search</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="header col-md-12">
                                            <h4 class="title">Keyword List</h4>
                                            <div class="col-md-12">
                                                <div class="col-md-3"></div>
                                                <div class="col-md-3">
                                                    <% if (((int) session.getAttribute("KEYWORDADMINTHISPAGE") != 0)) {%>
                                                    <% if (((int) session.getAttribute("KEYWORDADMINTHISPAGE") > 1)) {%>
                                                    <a class="" href="MainController?btnAction=SearchKeyword&page=<%= ((int) session.getAttribute("KEYWORDADMINTHISPAGE")) - 1%>&userId=&searchValue=<%= session.getAttribute("SEARCHINGKEYWORD")%>"><button><i class="pe-7s-left-arrow" style="width: 20px; height: 20px"></i></button></a>
                                                                <% }%>
                                                    <span style="padding-left: 25px; padding-right: 25px">
                                                        Page <%= session.getAttribute("KEYWORDADMINTHISPAGE")%>/<%= session.getAttribute("KEYWORDADMINMAXPAGE")%>
                                                    </span>
                                                    <% if (((int) session.getAttribute("KEYWORDADMINTHISPAGE")) != (int) (session.getAttribute("KEYWORDADMINMAXPAGE"))) {%>
                                                    <a class="" href="MainController?btnAction=SearchKeyword&page=<%= ((int) session.getAttribute("KEYWORDADMINTHISPAGE")) + 1%>&userId=&searchValue=<%= session.getAttribute("SEARCHINGKEYWORD")%>"><button><i class="pe-7s-right-arrow" style="width: 20px; height: 20px"></i></button></a>

                                                    <% }
                                                        }%>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="content table-responsive table-full-width">
                                            <table id="myTable" class="table table-hover table-striped">
                                                <thead>
                                                <th>NO</th>
                                                <th>Keyword</th>
                                                <th>Username</th>
                                                <th></th>
                                                </thead>
                                                <tbody>
                                                    <% List<Keyword> list = (List) session.getAttribute("LISTKEYWORD");
                                                        if (list != null) {
                                                    %>
                                                    <% for (int i = 0; i < list.size(); i++) {%>
                                                    <% Keyword keywordDTO = list.get(i);%>

                                                    <tr>
                                                        <td><%= (((int) session.getAttribute("KEYWORDADMINTHISPAGE")) - 1) * 10 + (i + 1)%></td>
                                                        <td class="keywords">
                                                            <%= keywordDTO.getKeyword()%>
                                                        </td>
                                                        <td><%= keywordDTO.getUserId()%></td>
                                                        <td><a href="MainController?btnAction=DeleteKeywordAdmin&id=<%= keywordDTO.getId()%>&no=<%= i%>" onclick="return confirm('Are you sure you want to delete this item?');"><button><i class="pe-7s-trash" style="width: 20px; height: 20px"></i></button></a></td>
                                                    </tr>
                                                    <% } %>
                                                    <% }%>
                                                </tbody>
                                            </table>
                                        </div>
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

    <!--===============================================================================================-->
    <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
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
                                        <% session.removeAttribute("RESULT"); %>
                                    }
                                });

    </script>

</html>
