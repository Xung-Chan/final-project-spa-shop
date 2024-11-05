function toggleChat() {
    const chatContainer = document.getElementById("chat-container");
    // Kiểm tra trạng thái hiển thị và thay đổi nó
    if (chatContainer.style.display === "none" || chatContainer.style.display === "") {
        chatContainer.style.display = "block"; // Hiện khung chat
    } else {
        chatContainer.style.display = "none"; // Ẩn khung chat
    }
}

function sendMessage() {
    const userInput = document.getElementById('userInput').value;
    if (userInput) {
        const chatbox = document.getElementById('chatbox');

        // Tin nhắn của người dùng
        const userMessage = document.createElement('div');
        userMessage.className = 'message user';
        userMessage.textContent = userInput;
        chatbox.appendChild(userMessage);

        document.getElementById('userInput').value = ''; // Xóa nội dung input sau khi gửi

        // Gửi tin nhắn đến API chatbot
        fetch('http://127.0.0.1:5000/chat', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ message: userInput })
        })
            .then(response => response.json())
            .then(data => {
                const botMessage = document.createElement('div');
                botMessage.className = 'message bot';

                // Thêm biểu tượng trước nội dung tin nhắn bot
                const profileIcon = document.createElement('div');
                profileIcon.className = '/img/chat.png';

                botMessage.prepend(profileIcon);
                botMessage.appendChild(document.createTextNode(data.response));
                chatbox.appendChild(botMessage);

                // Cuộn xuống cuối khi nhận được phản hồi mới
                chatbox.scrollTop = chatbox.scrollHeight;
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
}

// Lắng nghe sự kiện click của nút gửi và phím Enter
document.getElementById('sendBtn').addEventListener('click', sendMessage);
document.getElementById('userInput').addEventListener('keypress', function (event) {
    if (event.key === 'Enter') {
        sendMessage();
    }
});
