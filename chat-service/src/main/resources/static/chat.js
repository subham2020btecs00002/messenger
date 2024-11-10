// Establish a WebSocket connection using SockJS and STOMP
const socket = new SockJS('http://localhost:8081/chat');
const stompClient = Stomp.over(socket);

// Connect to the WebSocket server
stompClient.connect({}, (frame) => {
    console.log('Connected as: ' + frame);

    // Subscribe to the topic to receive messages
    stompClient.subscribe('/topic/messages', (messageOutput) => {
        const message = JSON.parse(messageOutput.body);
        document.getElementById('messages').innerHTML += `<p><strong>${message.sender}:</strong> ${message.content}</p>`;
    });
});

// Send a message function
function sendMessage() {
    const sender = document.getElementById('sender').value;
    const content = document.getElementById('messageInput').value;
    const message = {
        sender: sender,
        content: content,
        timestamp: new Date().toISOString()
    };
    
    stompClient.send("/app/sendMessage", {}, JSON.stringify(message));
}
