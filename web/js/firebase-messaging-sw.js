importScripts('https://www.gstatic.com/firebasejs/7.12.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.12.0/firebase-messaging.js');

// Initialize the Firebase app in the service worker by passing in the
// messagingSenderId.
firebase.initializeApp({
    'messagingSenderId': '721141867711',
    'apiKey': 'AIzaSyDu0alR16fuDysOrsWMWF9bm-IkscLH4Zw',
    'projectId': 'media-crisis',
    'appId': '1:721141867711:web:b2ea06e0e59157b6ae520a',
});

// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
const messaging = firebase.messaging();

messaging.setBackgroundMessageHandler(function (payload) {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);
    // Customize notification here
    const notificationTitle = 'Background Message Title';
    const notificationOptions = {
        body: 'Background Message body.',
        icon: '/itwonders-web-logo.png'
    };

    return self.registration.showNotification(notificationTitle,
            notificationOptions);
});
self.addEventListener('push', function (event) {
    var apiPath = 'browser_push_notification?endpoint=';

    event.waitUntil(
            registration.pushManager.getSubscription()
            .then(function (subscription) {
                if (!subscription || !subscription.endpoint) {
                    throw new Error();
                }

                apiPath = apiPath + encodeURI(subscription.endpoint);

                return fetch(apiPath)
                        .then(function (response) {
                            if (response.status !== 200) {
                                console.log("Problem Occurred:" + response.status);
                                throw new Error();
                            }

                            return response.json();
                        })
                        .then(function (data) {
                            if (data.status == 0) {
                                console.error('The API returned an error.', data.error.message);
                                throw new Error();
                            }
                            var data = data.data;
                            var title = data.notification.title;
                            var message = data.notification.message;
                            var icon = data.notification.icon;
                            var data = {
                                url: data.notification.url
                            };

                            return self.registration.showNotification(title, {
                                body: message,
                                icon: icon,
                                data: data
                            });
                        })
                        .catch(function (err) {
                            return self.registration.showNotification('Notification', {
                                body: 'Có một sự kiện sắp diễn ra',
                                icon: 'image/pn_logo.png',
                                data: {
                                    url: "/"
                                }
                            });
                        });
            })
            );
});
self.addEventListener('notificationclick', function (event) {
    event.notification.close();
    var url = event.notification.data.url;
    event.waitUntil(
            clients.matchAll({
                type: 'window'
            })
            .then(function (windowClients) {
                for (var i = 0; i < windowClients.length; i++) {
                    var client = windowClients[i];
                    if (client.url === url && 'focus' in client) {
                        return client.focus();
                    }
                }
                if (clients.openWindow) {
                    return clients.openWindow(url);
                }
            })
            );
});