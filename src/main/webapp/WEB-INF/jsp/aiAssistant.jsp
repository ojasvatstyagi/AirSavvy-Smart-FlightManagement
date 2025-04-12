<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>AI Assistant</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="/assets/css/chat-style.css">
</head>

<body>
    <div class="floating-btn" onclick="toggleChat()">
        <i class="fas fa-robot"></i>
    </div>
    <div class="chat-container" id="chat-box">
        <div class="chat-header">
            AI Assistant
            <span onclick="toggleChat()" style="float: right; cursor: pointer;">✖</span>
        </div>
        <div class="chat-body" id="chat-body"></div>
        <div class="chat-footer">
            <input type="text" id="userInput" placeholder="Ask something..." onkeypress="if(event.key === 'Enter') sendMessage()" />
            <button onclick="sendMessage()">Send</button>
        </div>
    </div>
<script src="/assets/js/chat-script.js"></script>
</body>
</html>