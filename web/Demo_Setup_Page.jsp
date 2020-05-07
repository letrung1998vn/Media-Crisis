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
                            <a href="Demo_Setup_Page.jsp">
                                <i class="pe-7s-graph"></i>
                                <p>Demo setting</p>
                            </a>
                        </li>
                        <li class="">
                            <a href="Demo_Data_Page.jsp">
                                <i class="pe-7s-graph"></i>
                                <p>Check data</p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="main-panel">
                <div class="content">
                    <div class="card margin-top">
                        <div class="row">
                            <div class="col-md-12 margin-top">
                                <div class="col-md-3">
                                    Start crawl server: 
                                </div>
                                <div class="col-md-6">
                                    <a href="http://127.0.0.1:8000/about/?csrfmiddlewaretoken=lhuQpebL5prrlNySD9OhsGY51dOnB2pT20cSxEAvvR7QR54blX1vFsieAzzLNMLk"><button class="btn btn-success">Start</button></a>
                                </div>
                            </div>
                            <div class="col-md-12 margin-top">
                                <div class="col-md-3">
                                    Start detect crisis server: 
                                </div>
                                <div class="col-md-6">
                                    <a href="http://localhost:8181/checkMeaning/check"><button class="btn btn-success">Start</button></a>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 margin-top">
                                <div class="col-md-3">
                                    Import demo data from script file:
                                </div>
                            </div>
                            <div class="col-md-12" style="margin-bottom: 10px">
                                <div class="col-md-1"></div>
                                <div class="col-md-5">Case no.1: Abnormal value of the react, replies and share number in negative post</div>
                                <div class="col-md-2 nopadding">
                                    <a href=""><button class="btn btn-default">Run script 1</button></a>
                                </div>
                            </div>
                            <div class="col-md-12" style="margin-bottom: 10px">
                                <div class="col-md-1"></div>
                                <div class="col-md-5">Case no.2: Higher ratio of negative post</div>
                                <div class="col-md-2 nopadding">
                                    <a href=""><button class="btn btn-primary">Run script 2</button></a>
                                </div>
                            </div>
                            <div class="col-md-12" style="margin-bottom: 10px">
                                <div class="col-md-1"></div>
                                <div class="col-md-5">Case no.3: Abnormal increased value of the react, replies and share number in negative post</div>
                                <div class="col-md-2 nopadding">
                                    <a href=""><button class="btn btn-danger">Run script 3</button></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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
