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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

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
                        <li class="active">
                            <a href="MainController?btnAction=SearchKeywordUser&userId=<%= session.getAttribute("USERID")%>">
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
                            <a class="navbar-brand" href="#">Keyword List</a>
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
                                            <form class="login100-form validate-form-add-keyword" action="MainController" method="POST">
                                                <div class="row">
                                                    <div class="col-md-12">

                                                        <div class="validate-input col-md-12" data-validate = "">
                                                            <div class="col-md-7"><input class="input100 form-control" type="text" name="txtKeyword" maxlength="50"></div>
                                                            <div class="col-md-1">
                                                                <select id="" class="form-control" name="txtCrisisRate">
                                                                    <!--                                                            <option value="84.1">84.1</option>-->
                                                                    <option value="93.3">93.3</option>
                                                                    <option value="97.7" selected="selected">97.7</option>
                                                                    <option value="99.4">99.4</option>
                                                                    <option value="99.9">99.9</option>
                                                                </select>
                                                            </div>
                                                            <div class="col-md-2">
                                                                <button class="btn btn-add-new-keyword btn-primary" style="width: 150px" type="submit" value="CreateKeyword" name="btnAction">
                                                                    Add
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="header">
                                            <h4 class="title">Search Keyword</h4>
                                        </div>
                                        <div class="content">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <input type="text" class="form-control search-keyword" placeholder="Enter Keyword">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="header">
                                            <h4 class="title">Keyword List</h4>
                                        </div>
                                        <div class="content table-responsive table-full-width">
                                            <table id="myTable" class="table table-hover table-striped">
                                                <thead>
                                                <th>NO</th>
                                                <th>Keyword</th>
                                                <th>Normal rate</th>
                                                <th>Edit</th>
                                                <th>User Id</th>
                                                <th>Delete</th>
                                                </thead>
                                                <tbody>
                                                    <% List<Keyword> list = (List) session.getAttribute("LISTKEYWORD");
                                                        if (list != null) {
                                                    %>
                                                    <% for (int i = 0; i < list.size(); i++) {%>
                                                    <% Keyword keywordDTO = list.get(i);%>

                                                    <tr>
                                                        <td class="keywordsNo"><%= i + 1%></td>
                                                <form class="login100-form validate-form-update-keyword" action="MainController" method="POST">
                                                    <td class="keywords">
                                                        <input id="keyword-value-<%= i%>" type="text" name="txtNewKeyword" class="form-control keyword-value validate-input input100" value="<%= keywordDTO.getKeyword()%>" disabled maxlength="50">    
                                                    </td>
                                                    <td>
                                                        <select id="percentage-<%= i%>" class="form-control" name="txtCrisisRate" disabled>
                                                            <!--                                                            <option value="84.1">84.1</option>-->
                                                            <option value="93.3"<% if(keywordDTO.getNormal_rate() == 93.3){ %>selected="selected"<%}%>>93.3</option>
                                                            <option value="97.7"<% if(keywordDTO.getNormal_rate() == 97.7){ %>selected="selected"<%}%>>97.7</option>
                                                            <option value="99.4"<% if(keywordDTO.getNormal_rate() == 99.4){ %>selected="selected"<%}%>>99.4</option>
                                                            <option value="99.9"<% if(keywordDTO.getNormal_rate() == 99.9){ %>selected="selected"<%}%>>99.9</option>
                                                        </select>
                                                    </td>
                                                    <input type="hidden" name="txtKeywordId" value="<%= keywordDTO.getId()%>">
                                                    <input type="hidden" name="txtLogversion" value="<%= keywordDTO.getLog_version()%>">
                                                    <input type="hidden" name="txtNo" value="<%= i%>">
                                                    <td>
                                                        <a id="btn-edit-keyword-<%= i%>" class="btn btn-edit" onclick="enableInput(<%= i%>)"><i class="pe-7s-pen btn-edit" style="width: 20px; height: 20px"></i></a>
                                                        <button id="btn-update-keyword-<%= i%>" class="btn btn-success hidden" type="submit" value="UpdateKeyword" name="btnAction" onclick="return confirm('Are you sure you want to update this item?');">
                                                            Update
                                                        </button>
                                                    </td>
                                                </form>
                                                <td><%= keywordDTO.getUserId()%></td>
                                                <td><a href="MainController?btnAction=DeleteKeyword&id=<%= keywordDTO.getId()%>&version=<%= keywordDTO.getLog_version()%>" onclick="return confirm('Are you sure you want to delete this item?');"><button class="btn btn-danger"><i class="pe-7s-trash" style="width: 20px; height: 20px"></i></button></a></td>
                                                <td></td>  
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
                                $('.search-keyword').on('input', function () {
                                    var keywordsinput = $('.search-keyword').val().toLowerCase();
                                    var keyword;
                                    var keywordDecode;
                                    $('#myTable td.keywords').each(function () {
                                        keyword = $(this).children().val().toLowerCase();
                                        keywordDecode = $('<textarea />').html(keyword).text();
                                        $(this).parent().removeClass("hide");
                                        if (keywordDecode.indexOf(keywordsinput) == -1) {
                                            $(this).parent().addClass("hide");
                                        }
                                    });
                                });

                                function enableInput(i) {
                                    var idOfInput = "keyword-value-" + i;
                                    var idOfEdit = "btn-edit-keyword-" + i;
                                    var idOfButton = "btn-update-keyword-" + i;
                                    var idOfList = "percentage-" + i;

//                                    $('.keyword-value').each(function () {
//                                        $(this).disabled = true;
//                                    });

                                    document.getElementById(idOfInput).disabled = false;
                                    document.getElementById(idOfList).disabled = false;
                                    document.getElementById(idOfEdit).classList.add("hidden");
                                    document.getElementById(idOfButton).classList.remove("hidden");
                                }

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
                                        var i = <%= session.getAttribute("UPDATINGPOS")%>;
                                        var idOfInput = "keyword-value-" + i;
                                        var idOfEdit = "btn-edit-keyword-" + i;
                                        var idOfButton = "btn-update-keyword-" + i;
                                        document.getElementById(idOfInput).disabled = false;
                                        document.getElementById(idOfInput).focus();
                                        document.getElementById(idOfEdit).classList.add("hidden");
                                        document.getElementById(idOfButton).classList.remove("hidden");
                                        document.getElementById(idOfInput).value = "<%= session.getAttribute("UPDATINGVALUE")%>";

        <% session.removeAttribute("SEND"); %>
        <% session.removeAttribute("CREATE_MESSAGE"); %>
        <% session.removeAttribute("RESULT");%>
        <% session.removeAttribute("UPDATINGPOS");%>
        <% session.removeAttribute("UPDATINGVALUE");%>
                                    }
                                });
    </script>

</html>
