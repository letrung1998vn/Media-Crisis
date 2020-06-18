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
                        <li class="">
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
                        <li class="active">
                            <a href="Demo_Sentiment_Page.jsp">
                                <i class="pe-7s-graph"></i>
                                <p>Demo Sentiment</p>
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
                                    Demo English Sentiment 
                                </div>
                                <div class="col-md-6">
                                    <form class="login100-form validate-form-login" action="EngSentimentController" method="post">
                                        <input type="text" name="txtText">
                                        <button  type="submit" class="btn btn-success">Sentiment Analyze</button>
                                    </form>
                                    
                                </div>
                            </div>
                            <div class="col-md-12 margin-top">
                                <div class="col-md-3">
                                    Demo Vietnamese Sentiment 
                                </div>
                                <div class="col-md-6">
                                    <form action="VieSentimentController">
                                        <input type="text" name="txtText">
                                        <button  type="submit"class="btn btn-success">Sentiment Analyze</button>
                                    </form>
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
