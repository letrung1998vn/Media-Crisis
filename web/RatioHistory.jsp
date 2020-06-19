<%@page import="MediaCrisis.Model.HistoryRatioModel"%>
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
        <%
            String url = "";
            if (session.getAttribute("USERID") == null) {
                url = "login_JSP.jsp";
                session.setAttribute("CREATE_MESSAGE", "Your session has been time out");
                session.setAttribute("RESULT", 3);
                session.setAttribute("SEND", true);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
        %>
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
                                    <a href="mainPage_JSP.jsp">
                                        <p>View Crisis Of All Keyword</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="RatioHistory.jsp">
                                        <p>View Ratio Of All Keyword</p>
                                    </a>
                                </li>
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
                                    <span>Keyword:</span><br>
                                    <input list="my-list" type="text" class="form-control search-keyword" placeholder="Enter Keyword">
                                    <% List<String> listUserKeywords = (List<String>) session.getAttribute("RATIOKEYWORDLIST");
                                        int size = listUserKeywords.size(); %>
                                    <datalist id="my-list">
                                        <% for (int i = 0; i < size; i++) {
                                        %>
                                        <option class="keywords-details" value="<%= listUserKeywords.get(i)%>"></option>
                                        <% } %>
                                    </datalist>
                                </div>
                            </div>
                        </div>
                        <div class="card margin-top">
                            <table id="myTable" class="table table-hover table-striped">
                                <thead>
                                <th>NO</th>
                                <th>Keyword</th>
                                <th>Type</th>
                                <th>Chart</th>
                                <th>Detail Data</th>
                                </thead>
                                <% List<HistoryRatioModel> listUserRatio = (List<HistoryRatioModel>) session.getAttribute("listRatioHistory");
                                    size = listUserRatio.size();
                                    if (listUserRatio != null) {%>
                                <% for (int i = 0; i < listUserRatio.size(); i++) {
                                        HistoryRatioModel hrm = listUserRatio.get(i);
                                        String loadChartName = i + hrm.getType();
                                %>
                                <tbody>
                                <td>
                                    <%= i%>
                                </td>
                                <td class="keywords">
                                    <%=hrm.getKeyword()%>
                                </td>
                                <td>
                                    <%=hrm.getType()%>
                                </td>
                                <td>
                                    <input type="button" id="drawChart<%=i%>" value="Show the reason of crisis" class="btn btn-info btn-fill col-md-4 pull-left" onclick="
                                            var listDate = [];
                                           <% for (int x = 0; x < hrm.getListDateStr().size(); x++) {
                                                   String date = hrm.getListDateStr().get(x);
                                           %>
                                            listDate.push('<%=date%>');
                                           <%
                                               }
                                           %>
                                            drawVisualization(<%=hrm.getListRatioStr()%>, listDate, '<%=loadChartName%>')
                                           " style="visibility: hidden"/>
                                    <p id="<%=loadChartName%>"></p>
                                </td>
                                <td>
                                    <%
                                        if (hrm.getType().equals("post")) {
                                    %>
                                    <a href="MainController?btnAction=getNegativePost&keyword=<%=hrm.getKeyword()%>" target='_blank'>
                                        <p>Show Negative Post of this Keyword</p>
                                    </a>
                                    <%
                                    } else if (hrm.getType().equals("comment")) {
                                    %>
                                    <a href="MainController?btnAction=getNegativeComment&keyword=<%=hrm.getKeyword()%>" target='_blank'>
                                        <p>Show Negative Comment of this Keyword</p>
                                    </a>
                                    <%
                                        }
                                    %>

                                </td>
                                </tbody>
                                <% } %>
                                <% }%>
                            </table>
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
        $('.search-keyword').on('input', function () {
            var keywordsinput = $('.search-keyword').val().toLowerCase();
            var keyword;
            var keywordDecode;
            $('#myTable td.keywords').each(function () {
                keyword = $(this).html().toLowerCase();
                keywordDecode = $('<textarea />').html(keyword).text();
                $(this).parent().removeClass("hide");
                if (keywordDecode.indexOf(keywordsinput) == -1) {
                    $(this).parent().addClass("hide");
                }
            });
        });
    </script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages': ['corechart']});
        google.setOnLoadCallback(drawChart);
        function drawVisualization(listRatio, listDate, chartId) {
            var len = listRatio.length;
            var i;
            var formatDate = new google.visualization.DateFormat({
                pattern: 'yyyy-MM-dd hh:mm:ss'
            });
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Time');
            data.addColumn('number', 'Percentage of Negative');
            for (i = 0; i < len; i++) {
                console.log(listDate[i]);
                var rowDate = new Date(listDate[i]);
                data.addRow([formatDate.formatValue(rowDate), listRatio[i] * 100]);
            }
            var options = {chartArea: {left: 20, top: 10, width: '80%', height: '80%'}};
            var chart = new google.visualization.ColumnChart(document.getElementById(chartId));
            chart.draw(data, options);
        }
        function drawChart() {
            var size =<%= size%>;
            var i;
            for (i = 0; i < size; i++) {
                var idButton = "drawChart" + i;
                document.getElementById(idButton).click();
            }
        }
    </script>
</html>
