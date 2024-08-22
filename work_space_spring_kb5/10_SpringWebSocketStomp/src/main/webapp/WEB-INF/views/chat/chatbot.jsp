<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bank Chatbot</title>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet">
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="${path}/resources/js/sockjs-0.3.4.js"></script>
    <script type="text/javascript" src="${path}/resources/js/stomp.js"></script>
</head>
<body onload="connect();">
<br>
<br>
<img src="${path}/resources/image/chat1.png" width="100" height="100">
<img src="${path}/resources/image/chat4.png" width="400" height="100">
<div class="alert alert-primary" style="width: 500px;">
    <div>챗 봇>> 1) 은행거래 2) 대출거래</div>
    <br>
    <div id="response">

    </div>
    <div class="form-floating mb-3 mt-3" id="conversationDiv">
        <table>
            <tr>
                <td>
                    <input id="text" type="text"  class="form-control"  style="width: 400px;"></td>
                <td>
                    <button id="sendMessage" onclick="sendMessage();" class="btn btn-primary">Send</button>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>


<script type="text/javascript">
    var stompClient = null;

    function setConnected(connected) {
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        document.getElementById('response').innerHTML = '';
    }

    function connect() {
        // scoket 연결
        var socket = new SockJS('${path}/chatbot');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            setConnected(true);
            alert('연결됨. ' + frame);
            stompClient.subscribe('/topic/chatbot_messages', function (messageOutput) {
                showMessageOutput(JSON.parse(messageOutput.body));
            });
        });
    }

    function sendMessage() {
        var from = "${sessionScope.member.memberId}";
        var text = document.getElementById('text').value;
        // 특정 토픽에 보내기
        stompClient.send("/app/chatbot", {}, JSON.stringify({
            'from': from,
            'text': text
        }));
    }

    function showMessageOutput(messageOutput) {
        var response = document.getElementById('response');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(messageOutput.menu));
        response.appendChild(p);
    }

    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

</script>