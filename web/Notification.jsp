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
        <h1>Hello World!</h1>
    </body>
    <script src="https://www.gstatic.com/firebasejs/7.12.0/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/7.12.0/firebase-messaging.js"></script>
    <script>
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
                                MsgElem.innerHTML = "Notification permission granted."
                                console.log("Notification permission granted.");
                                // get the token in the form of promise
                                return messaging.getToken()
                            })
                            .then(function (token) {
                                // print the token on the HTML page

                            })
                            .catch(function (err) {
                                console.log("Unable to get permission to notify.", err);
                            });
                    messaging.onMessage(function (payload) {
                        console.log("Message received. ", payload);
                    });
                });
    </script>
</html>
