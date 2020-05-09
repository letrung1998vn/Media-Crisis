<%@page import="MediaCrisis.Model.EmailContentModel"%>
<%@page import="java.util.StringTokenizer"%>
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
    </head>
    <body>

        <div class="wrapper">
            <div class="sidebar" data-color="purple" data-image="assets/img/sidebar-5.jpg">

                <!--   you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple" -->
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
                            <a class="navbar-brand" href="#">List Crisis</a>
                        </div>

                    </div>
                </nav>

                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="header">
                                        <h4 class="title">Keyword: <%=request.getAttribute("keyword")%></h4>
                                    </div>
                                    <div class="content table-responsive table-full-width">
                                        <table class="table table-hover table-striped">
                                            <thead>
                                            <th>No</th>
                                            <th>Content</th>
                                            <th>Type of Crisis</th>
                                            <th>Crisis Reason</th>
                                            <th>Link Detail</th>
                                            </thead>
                                            <tbody>
                                                <%
                                                    List<EmailContentModel> list = (List<EmailContentModel>) request.getAttribute("list");
                                                    if (list != null) {
                                                        for (int i = 0; i < list.size(); i++) {
                                                            EmailContentModel ecm = list.get(i);
                                                            String content = ecm.getContent();
                                                            String linkDetail = ecm.getLink();
                                                            String type = ecm.getType();
                                                            String std = ecm.getStd();
                                                            String loadChartName = i + type;
                                                            String showType = "";
                                                            if (type.equals("react")) {
                                                                showType = "Abnormal High React";
                                                            } else if (type.equals("retweet")) {
                                                                showType = "Abnormal High Retweet";
                                                            } else if (type.equals("reply")) {
                                                                showType = "Abnormal High Reply";
                                                            } else if (type.equals("increaseReact")) {
                                                                showType = "Abnormal Increase React";
                                                            } else if (type.equals("increaseRetweet")) {
                                                                showType = "Abnormal Increase Retweet";
                                                            } else if (type.equals("increaseReply")) {
                                                                showType = "Abnormal Increase Reply";
                                                            }
                                                %>
                                                <tr>
                                                    <td>
                                                        <%=i + 1%>
                                                    </td>
                                                    <td><p><%=content%></p></td>
                                                    <td><%=showType%></td>
                                                    <td style="width: 700px; height: 500px;">
                                                        <input type="button" id="drawChart<%=i%>" value="Show the reason of crisis" class="btn btn-info btn-fill col-md-4 pull-left" onclick="drawVisualization(<%=std%>, '<%=loadChartName%>')" style="visibility: hidden"/>
                                                        <p id="<%=loadChartName%>"></p>
                                                    </td>
                                                    <td><a href="<%=linkDetail%>" target='_blank'><%=linkDetail%></a></td>
                                                </tr>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </tbody>
                                        </table>

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
                                    <a href="#">
                                        Home
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        Company
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        Portfolio
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        Blog
                                    </a>
                                </li>
                            </ul>
                        </nav>
                        <p class="copyright pull-right">
                            &copy; <script>document.write(new Date().getFullYear())</script> <a href="http://www.creative-tim.com">Creative Tim</a>, made with love for a better web
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
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <%
        int size = (int) request.getAttribute("size");
    %>
    <script>
//                                $(document).ready(function () {

//                                })
    </script>
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
            for (var i = (std-0.1); i < 3.1; i += 0.1) {

                chartData[index] = new Array(3);
                chartData[index][0] = i;

                chartData[index][2] = NormalDensityZx(i, 0, 1);
                index++;
            }
            data.addRows(chartData);
            var options;
            options = {
                height: 300,
                width: 700,
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
