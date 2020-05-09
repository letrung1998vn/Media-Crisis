<%@page import="MediaCrisis.Model.Crisis"%>
<%@page import="MediaCrisis.Model.UserCrisis"%>
<%@page import="java.util.List"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <link rel="icon" type="image/png" href="assets/img/favicon.ico">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

        <title>Dashboard</title>

        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
        <meta name="viewport" content="width=device-width" />


        <!-- Bootstrap core CSS     -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" />

        <!-- Animation library for notifications   -->
        <link href="assets/css/animate.min.css" rel="stylesheet"/>

        <!--  Light Bootstrap Table core CSS    -->
        <link href="assets/css/light-bootstrap-dashboard.css?v=1.4.0" rel="stylesheet"/>

        <!--     Fonts and icons     -->
        <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
        <link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet" />

    </head>
    <body>

        <div class="wrapper">
            <div class="sidebar" data-color="purple" data-image="assets/img/sidebar-5.jpg">

                <!--
            
                    Tip 1: you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple"
                    Tip 2: you can also add an image using data-image tag
            
                -->

                <div class="sidebar-wrapper">
                    <div class="logo">
                        <a href="#" class="simple-text">
                            Media Crisis Detect Application
                        </a>
                    </div>

                    <ul class="nav">
                        <li class="active">
                            <a href="mainPage_JSP.jsp">
                                <i class="pe-7s-graph"></i>
                                <p>Dashboard</p>
                            </a>
                        </li>
                        <li>
                            <a href="userProfile.jsp">
                                <i class="pe-7s-user"></i>
                                <p>User Profile</p>
                            </a>
                        </li>
                        <li>
                            <a href="MainController?btnAction=SearchKeywordUser">
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
                            <a class="navbar-brand" href="mainPage_JSP.jsp">Dashboard</a>
                        </div>
                        <div class="collapse navbar-collapse">
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="">
                                        <p>Account</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="MainController?btnAction=LogOut">
                                        <p>Log out</p>
                                    </a>
                                </li>
                                <li class="separator hidden-lg"></li>
                            </ul>
                        </div>
                    </div>
                </nav>
                <body>
                    <div class="content">
                        <div class="col-md-12">
                            <div class="col-md-12" style="padding-left: 15px">
                                <div class="form-group">
                                    <span>Keywords: </span><br>
                                    <% List<UserCrisis> listUserCrisis = (List<UserCrisis>) session.getAttribute("USERALLCRISIS"); %>
                                    <select onchange="changeCrisisDetails(this.value)" class="form-control">
                                        <option value="3">Blabla</option>
                                        <% if (listUserCrisis != null) {%>
                                        <% for (int i = 0; i < listUserCrisis.size(); i++) {
                                        %>
                                        <option value="<%= i%>"><%= listUserCrisis.get(i).getKeyword()%></option>
                                        <% } %>
                                        <% }%>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="card margin-top">
                            <table id="table-crisis-3" class="table table-hover table-striped">
                                <tbody>
                                <th>blabla</th>
                                </tbody>
                            </table>
                            <% if (listUserCrisis != null) {%>
                            <% for (int i = 0; i < listUserCrisis.size(); i++) {
                            %>
                            <table id="table-crisis-<%= i%>" class="table table-hover table-striped <% if(i!=3) { %>hidden<%}%>">
                                <thead>
                                <th>NO</th>
                                <th>Crisis content</th>
                                <th>Type</th>
                                <th>Chart</th>
                                <th>Description</th>
                                </thead>
                                <% List<Crisis> crisis = listUserCrisis.get(i).getCrisisList();
                                    for (int j = 0; j < crisis.size(); j++) {
                                %>
                                <tbody>  
                                <th>
                                    <%= j%>
                                </th>
                                <th>
                                    <%= crisis.get(j).getContent()%>
                                </th>
                                <th>
                                    <%= crisis.get(j).getType()%>
                                </th>
                                <th>
                                    Chart
                                </th>
                                <th>
                                    Why crisis?
                                </th>
                                </tbody>

                                <% } %>
                            </table>
                            <% } %>
                            <% }%>
                        </div>
                    </div>
                </body>
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
                                <!--                                <li>
                                                                    <a href="#">
                                                                        Portfolio
                                                                    </a>
                                                                </li>-->
                                <!--                                <li>
                                                                    <a href="#">
                                                                        Blog
                                                                    </a>
                                                                </li>-->
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

    <!--Light Bootstrap Table DEMO methods, don't include it in your project!--> 
    <script src="assets/js/demo.js"></script>

    <script type="text/javascript">
                                $(document).ready(function () {
                                    var dataPreferences = {
                                        labels: ['20%', '40%', '20%', '20%'],
                                        series: [20, 40, 20, 20]
                                    };
                                    demo.initChartist(dataPreferences);
                                });
    </script>
    <script type="text/javascript">
        var currentPointer = 3;
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

            function changeCrisisDetails(i) {
                var tableHide = "table-crisis-" + currentPointer;
                var tableShow = "table-crisis-" + i;
                document.getElementById(tableHide).classList.add("hidden");
                document.getElementById(tableShow).classList.remove("hidden");
                currentPointer = i;
            }
    </script>
</html>
