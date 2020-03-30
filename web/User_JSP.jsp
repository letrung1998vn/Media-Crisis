<%@page import="MediaCrisis.Model.User"%>
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
                        <a href="#" class="simple-text">
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
                            <a href="userProfile_Admin.jsp">
                                <i class="pe-7s-user"></i>
                                <p>User Profile</p>
                            </a>
                        </li>
                        <li>
                            <a href="MainController?btnAction=SearchKeyword&page=1&userId=&searchValue=">
                                <i class="pe-7s-note2"></i>
                                <p>Keyword Admin</p>
                            </a>
                        </li>
                        <li class="active">
                            <a href="MainController?btnAction=SearchUser&page=1&searchUser=">
                                <i class="pe-7s-news-paper"></i>
                                <p>User manager</p>
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
                            <a class="navbar-brand" href="MainController?btnAction=SearchUser&page=1&searchUser=">User List</a>
                        </div>
                        <div class="collapse navbar-collapse">
                            <ul class="nav navbar-nav navbar-right">
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
                        <a href="createUser_Admin.jsp"><button><i class="pe-7s-plus" style="width: 20px; height: 20px"></i>Add new user</button></a>
                    </div>
                </nav>

                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="col-md-12">
                                        <div class="header">
                                            <h4 class="title">Search user</h4>
                                        </div>
                                        <div class="content">
                                            <div class="row">
                                                <form class="login100-form col-md-12" action="MainController" method="post">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <span>Username: </span>
                                                            <input type="text" class="form-control search-user-admin" placeholder="Enter Username" name="searchUser" value="<%= session.getAttribute("SEARCHINGUSER")%>">
                                                            <input type="hidden" class="form-control search-keyword-admin" name="page" value="1">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <div class="col-md-3 pull-right" style="padding-left: 15px">
                                                            <button id="btn-search" class="btn btn-info btn-fill col-md-1 form-control"  type="submit" value="SearchUser" name="btnAction">Search</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="header">
                                            <h4 class="title">Users List</h4>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="col-md-4"></div>
                                            <div class="col-md-2">
                                                <% if (((int) session.getAttribute("USERADMINTHISPAGE") != 0)) {%>
                                                <% if (((int) session.getAttribute("USERADMINTHISPAGE") > 1)) {%>
                                                <a class="" href="MainController?btnAction=SearchUser&page=<%= ((int) session.getAttribute("USERADMINTHISPAGE")) - 1%>&searchUser=<%= session.getAttribute("SEARCHINGUSER")%>"><button><i class="pe-7s-left-arrow" style="width: 20px; height: 20px"></i></button></a>
                                                            <% }%>
                                                <span style="padding-left: 25px; padding-right: 25px">
                                                    Page <%= session.getAttribute("USERADMINTHISPAGE")%>/<%= session.getAttribute("USERADMINMAXPAGE")%>
                                                </span>
                                                <% if (((int) session.getAttribute("USERADMINTHISPAGE")) != (int) (session.getAttribute("USERADMINMAXPAGE"))) {%>
                                                <a class="" href="MainController?btnAction=SearchUser&page=<%= ((int) session.getAttribute("USERADMINTHISPAGE")) + 1%>&searchUser=<%= session.getAttribute("SEARCHINGUSER")%>"><button><i class="pe-7s-right-arrow" style="width: 20px; height: 20px"></i></button></a>

                                                <% }
                                                    }%>
                                            </div>
                                        </div>
                                        <div class="content table-responsive table-full-width">
                                            <table id="myTable" class="table table-hover table-striped">
                                                <thead>
                                                <th>NO</th>
                                                <th>Userename</th>
                                                <th>Name</th>
                                                <th>Email</th>
                                                <th>Role</th>
                                                <th>Status</th>
                                                </thead>
                                                <tbody>
                                                    <% List<User> users = (List<User>) session.getAttribute("LISTUSER");
                                                        if (users != null) {
                                                            for (int i = 0; i < users.size(); i++) {

                                                    %>
                                                    <tr>
                                                        <td><%= (((int) session.getAttribute("USERADMINTHISPAGE")) - 1) * 10 + (i + 1)%></td>
                                                        <td class="users"><%= users.get(i).getUsername()%></td>
                                                        <td><%= users.get(i).getName()%></td>
                                                        <td><%= users.get(i).getEmail()%></td>
                                                        <td><%= users.get(i).getRole()%></td>
                                                        <td><a href="MainController?btnAction=ChangeUserStatus&username=<%= users.get(i).getUsername()%>" onclick="return confirm('Are you sure you want to ' + <%
                                                            if (users.get(i).isIsAvailable()) { %>
                                                                'disable'
                                                               <%
                                                               } else { %>
                                                                'enable'
                                                               <%
                                                                   }
                                                               %> + ' this item?'
                                                                        );"><%
                                                                            if (users.get(i).isIsAvailable()) { %>
                                                                <button class="btn btn-success">Enable</button>
                                                                <%
                                                                } else { %>
                                                                <button class="btn btn-danger">Disable</button>
                                                                <%
                                                                    }
                                                                %></a></td>
                                                    </tr>
                                                    <% }
                                                        }%>

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
                                    }
        <% session.removeAttribute("SEND"); %>
        <% session.removeAttribute("CREATE_MESSAGE"); %>
        <% session.removeAttribute("RESULT");%>
                                });
    </script>

</html>
