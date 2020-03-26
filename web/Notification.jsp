<%-- 
    Document   : Notification
    Created on : Mar 25, 2020, 4:09:34 PM
    Author     : letru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-10">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Update Web Notification</h4>
                            </div>
                            <div class="content">
                                <form action="MainController" method="post" class="validate-form-update-profile" id="form-update-token">
                                    <input id="tokenString" type="hidden" name="txtToken">
                                    <input type="hidden" name="btnAction" value="UpdateNotiBrowser">
                                    <button id="Notibtn" type="submit" class="btn btn-info btn-fill pull-left" onclick="getToken(event)">Enable Notification</button>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://www.gstatic.com/firebasejs/7.12.0/firebase-app.js"></script>
        <script src="https://www.gstatic.com/firebasejs/7.12.0/firebase-messaging.js"></script>
        <script>
                                        function getToken(event) {
                                            event.preventDefault();
                                            var tokenForm = document.getElementById("form-update-token");
                                            var tokenString = document.getElementById("tokenString");
                                            var firebaseConfig = {
                                                apiKey: "AIzaSyDu0alR16fuDysOrsWMWF9bm-IkscLH4Zw",
                                                authDomain: "media-crisis.firebaseapp.com",
                                                databaseURL: "https://media-crisis.firebaseio.com",
                                                projectId: "media-crisis",
                                                storageBucket: "media-crisis.appspot.com",
                                                messagingSenderId: "721141867711",
                                                appId: "1:721141867711:web:b2ea06e0e59157b6ae520a",
                                                measurementId: "G-YBT6NGHPTL"
                                            };
                                            // Initialize Firebase
                                            firebase.initializeApp(firebaseConfig);
                                            const messaging = firebase.messaging();
                                            navigator.serviceWorker.register('./firebase-messaging-sw.js')
                                                    .then((registration) => {
                                                        messaging.useServiceWorker(registration);
                                                        // Request permission and get token.....
                                                        messaging
                                                                .requestPermission()
                                                                .then(function () {
                                                                    console.log("Notification permission granted.");
                                                                    // get the token in the form of promise
                                                                    return messaging.getToken()
                                                                })
                                                                .then(function (token) {
                                                                    // print the token on the HTML page
                                                                    tokenString.value = token;
                                                                    tokenForm.submit();
                                                                    console.log("Token: " + token);
                                                                })
                                                                .catch(function (err) {
                                                                    console.log("Unable to get permission to notify.", err);
                                                                });
                                                        messaging.onMessage(function (payload) {
                                                            console.log("Message received. ", payload);
                                                        });
                                                    }
                                                    );
                                        }
        </script>
    </body>
</html>
