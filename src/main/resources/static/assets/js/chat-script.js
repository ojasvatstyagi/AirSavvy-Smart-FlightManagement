document.addEventListener("DOMContentLoaded", () => {
    restoreChatHistory();
});

function toggleChat() {
    const chatBox = document.getElementById("chat-box");
    chatBox.classList.toggle("visible");
    chatBox.style.display = chatBox.classList.contains("visible") ? "flex" : "none";
}

function createMessageElement(type, text, isTyping = false) {
    const msg = document.createElement("div");
    msg.className = `message ${type}-message`;

    if (isTyping) {
        msg.innerHTML = `<span class="typing-dots"><span>.</span><span>.</span><span>.</span></span>`;
    } else {
        msg.innerHTML = '<span>' + text.replace(/\n/g, '<br/>') + '</span>';
    }

    return msg;
}

function sendMessage() {
    const userInput = document.getElementById("userInput");
    const chatBody = document.getElementById("chat-body");
    const query = userInput.value.trim();
    if (!query) return;

    const userMsg = createMessageElement("user", query);
    chatBody.appendChild(userMsg);
    saveToHistory("user", query);
    userInput.value = "";
    chatBody.scrollTop = chatBody.scrollHeight;

    const typingMsg = createMessageElement("ai", "", true);
    chatBody.appendChild(typingMsg);
    chatBody.scrollTop = chatBody.scrollHeight;

    fetch("/ai/ask?query=" + encodeURIComponent(query))
        .then(response => response.json())
        .then(data => {
            chatBody.removeChild(typingMsg);
            const aiMsg = createMessageElement("ai", data.response);
            chatBody.appendChild(aiMsg);
            saveToHistory("ai", data.response);
            chatBody.scrollTop = chatBody.scrollHeight;
        })
        .catch(() => {
            chatBody.removeChild(typingMsg);
            const errorMsg = createMessageElement("ai", "Sorry, something went wrong.");
            chatBody.appendChild(errorMsg);
            saveToHistory("ai", "Sorry, something went wrong.");
            chatBody.scrollTop = chatBody.scrollHeight;
        });
}

function saveToHistory(role, message) {
    const history = JSON.parse(sessionStorage.getItem("chatHistory") || "[]");
    history.push({ role, message });
    sessionStorage.setItem("chatHistory", JSON.stringify(history));
}

function restoreChatHistory() {
    const history = JSON.parse(sessionStorage.getItem("chatHistory") || "[]");
    const chatBody = document.getElementById("chat-body");

    history.forEach(msg => {
        const msgElem = createMessageElement(msg.role, msg.message);
        chatBody.appendChild(msgElem);
    });

    chatBody.scrollTop = chatBody.scrollHeight;
}