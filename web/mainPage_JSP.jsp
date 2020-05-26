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
                                    <span>Keyword:</span><br>
                                    <input list="my-list" type="text" class="form-control search-keyword" placeholder="Enter Keyword">
                                    <% List<String> listUserKeywords = (List<String>) session.getAttribute("KEYWORDLIIST");
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
                                <th>Crisis content</th>
                                <th>Keyword</th>
                                <th>Detect date</th>
                                <th>Chart</th>
                                <th>Description</th>
                                </thead>
                                <% List<Crisis> listUserCrisis = (List<Crisis>) session.getAttribute("USERALLCRISIS");
                                    size = listUserCrisis.size();
                                    if (listUserCrisis != null) {%>
                                <% for (int i = 0; i < listUserCrisis.size(); i++) {
                                %>
                                <tbody>
                                <td>
                                    <%= i%>
                                </td>
                                <td>
                                    <%= listUserCrisis.get(i).getContent()%>
                                </td>
                                <td class="keywords">
                                    <%= listUserCrisis.get(i).getKeyword()%>
                                </td>
                                <td>
                                    <%= listUserCrisis.get(i).getDetectDate()%>
                                </td>
                                <% double std = 0.0;
                                    if (listUserCrisis.get(i).getPercentage() == 93.3) {
                                        std = 1.5;
                                    } else if (listUserCrisis.get(i).getPercentage() == 97.7) {
                                        std = 2;
                                    } else if (listUserCrisis.get(i).getPercentage() == 99.4) {
                                        std = 2.5;
                                    } else if (listUserCrisis.get(i).getPercentage() == 99.9) {
                                        std = 3;
                                    };%>
                                <td>
                                    <input type="button" id="drawChart<%=i%>" value="Show the reason of crisis" class="btn btn-info btn-fill col-md-4 pull-left hidden" onclick="drawVisualization(<%=std%>, '<%=i%><%= listUserCrisis.get(i).getType()%>')"/>
                                    <p id="<%=i%><%= listUserCrisis.get(i).getType()%>"></p>
                                </td>
                                <td>
                                    <%= listUserCrisis.get(i).getType()%> has reached <%= listUserCrisis.get(i).getPercentage()%>% of the top posts with the highest <%= listUserCrisis.get(i).getDetectType()%>
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
        function NormalDensityZx(x, Mean, StdDev) {

            var a = x - Mean;
            return Math.exp(-(a * a) / (2 * StdDev * StdDev)) / (Math.sqrt(2 * Math.PI) * StdDev);
        }
        function drawVisualization(std, loadChart) {
            var data = new google.visualization.DataTable();
            data.addColumn('number', 'X Value');
            data.addColumn('number', 'Blue');
            data.addColumn('number', 'Red');
            var chartData = new Array([]);
            var index = 0;
            for (var i = -3; i < (std + 0.1); i += 0.1) {

                chartData[index] = new Array(3);
                chartData[index][0] = i;
                chartData[index][1] = NormalDensityZx(i, 0, 1);
                index++;
            }

            index--;
            chartData[index][2] = chartData[index][1];
            index++;
            for (var i = (std - 0.1); i < 3.1; i += 0.1) {

                chartData[index] = new Array(3);
                chartData[index][0] = i;

                chartData[index][2] = NormalDensityZx(i, 0, 1);
                index++;
            }
            data.addRows(chartData);
            var options;
            options = {
                height: 200,
                width: 500,
                legend: 'none',
                isStacked: false,
                series: {
                    0: {color: 'blue', visibleInLegend: false},
                    1: {color: 'red', visibleInLegend: false}
                },
                hAxis: {
                    ticks: [{v: -3, f: '0.1%'}, {v: -2.5, f: '1%'}, {v: -2, f: '2.3%'}, {v: -1.5, f: '5%'}, {v: -1, f: '15.9%'}, {v: -0.5, f: '30%'}, {v: 0, f: '50%'}, {v: 0.5, f: '70%'}, {v: 1, f: '84.1%'}, {v: 1.5, f: '95%'}, {v: 2, f: '97.7%'}, {v: 2.5, f: '99.4'}, {v: 3, f: '99.9%'}]
                }
            };
            var chart = new google.visualization.AreaChart(document.getElementById(loadChart));
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
