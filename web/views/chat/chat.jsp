<%--
  Created by IntelliJ IDEA.
  User: Владелец
  Date: 29.07.2019
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src='/socket.io/socket.io.js'></script>
    <script>
        var socket = io();

        socket.on('welcome', function(data) {
            addMessage(data.message);

            // Respond with a message including this clients' id sent from the server
            socket.emit('i am client', {data: 'foo!', id: data.id});
        });
        socket.on('time', function(data) {
            addMessage(data.time);
        });
        socket.on('error', console.error.bind(console));
        socket.on('message', console.log.bind(console));

        function addMessage(message) {
            var text = document.createTextNode(message),
                el = document.createElement('li'),
                messages = document.getElementById('messages');

            el.appendChild(text);
            messages.appendChild(el);
        }
    </script>
</head>
<body>
<ul id='messages'></ul>
</body>
</html>
