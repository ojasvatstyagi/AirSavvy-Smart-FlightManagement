document.addEventListener("DOMContentLoaded", () => {
    restoreChatHistory();
});

function toggleChat() {
    const chatBox = document.getElementById("chat-box");
    chatBox.classList.toggle("visible");
    chatBox.style.display = chatBox.classList.contains("visible") ? "flex" : "none";
}

/**
 * Convert markdown-style text to HTML tags for simple formatting.
 * Supports **bold**, *italic*, and # headings.
 */
function markdownToHTML(text) {
    return text
        // Escape HTML special characters to prevent injection
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")

        // Headers
        .replace(/^### (.*)$/gm, '<h3>$1</h3>')
        .replace(/^## (.*)$/gm, '<h2>$1</h2>')
        .replace(/^# (.*)$/gm, '<h1>$1</h1>')

        // Bold **bold**
        .replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')

        // Italic *italic*
        .replace(/\*(.*?)\*/g, '<em>$1</em>')

        // Line breaks
        .replace(/\n/g, '<br/>');
}

function createMessageElement(type, text, isTyping = false) {
    const msg = document.createElement("div");
    msg.className = `message ${type}-message`;

    if (isTyping) {
        msg.innerHTML = `<span class="typing-dots"><span>.</span><span>.</span><span>.</span></span>`;
    } else {
        // Convert markdown response to HTML
        msg.innerHTML = markdownToHTML(text);
    }

    return msg;
}

function sendMessage() {
    const userInput = document.getElementById("userInput");
    const chatBody = document.getElementById("chat-body");
    const query = userInput.value.trim();
    if (!query) return;

    // Add user message
    const userMsg = createMessageElement("user", query);
    chatBody.appendChild(userMsg);
    saveToHistory("user", query);
    userInput.value = "";
    chatBody.scrollTop = chatBody.scrollHeight;

    // Show typing indicator
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
