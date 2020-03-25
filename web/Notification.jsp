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
        <form id="form-update-token" action="UpdateNotiBrowserTokenController" method="post" class="">
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <div class="col-md-12">
                            <div class="col-md-4"><label>Your noti token:</label></div>
                        </div>
                        <div class="col-md-12">
                            <div class="col-md-9">
                                <input id="tokenString" type="text" name="txtToken" class="form-control" placeholder="Token link">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <div id="token"></div>
        <div id="msg"></div>
        <div id="notis"></div>
        <div id="err"></div>
        <script>
            MsgElem = document.getElementById("msg")
            TokenElem = document.getElementById("token")
            NotisElem = document.getElementById("notis")
            ErrElem = document.getElementById("err")
        </script>
        <script src="https://www.gstatic.com/firebasejs/7.12.0/firebase-app.js"></script>
        <script src="https://www.gstatic.com/firebasejs/7.12.0/firebase-messaging.js"></script>
        <script>
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
                    navigator.serviceWorker.register('./js/firebase-messaging-sw.js')
                    .then((registration) = > {
                    messaging.useServiceWorker(registration);
                    // Request permission and get token.....
                    messaging
                            .requestPermission()
                            .then(function () {
                                MsgElem.innerHTML = "Notification permission granted."
                                console.log("Notification permission granted.");
                                // get the token in the form of promise
                                tokenString.value(messaging.getToken());
                                tokenForm.submit();
                                return messaging.getToken()
                            })
                            .then(function (token) {
                                // print the token on the HTML page
                                TokenElem.innerHTML = "token is : " + token
                            })
                            .catch(function (err) {
                                console.log("Unable to get permission to notify.", err);
                            });
                    messaging.onMessage(function (payload) {
                        console.log("Message received. ", payload);
                    });
                    });
        </script>
    </body>
</html>
