// Make connection
var socket = io.connect('http://localhost:4000');

//Query DOM
var message = document.getElementById('message'),
handle = document.getElementById('handle'),
btn = document.getElementById('send'),
output = document.getElementById('output');
feedback = document.getElementById('feedback')

//Emit events
btn.addEventListener('click', function(){
    socket.emit('chatting', handle.value, message.value)
});

message.addEventListener('keypress', function(){
    socket.emit('typing',handle.value);
});


//Listen for events
// socket.on('chat',function(data){
//     output.innerHTML += '<p><strong>' + data.handle + ': </strong>' + data.message + '</p>'
// });

socket.on('typing', function(data){
    feedback.innerHTML = '<p><em>' + data + 'is typing a message...</em></p>';
})

socket.on('chatting',function(data){
    output.innerHTML += '<p><strong>' + data.username + ': </strong>' + data.message + '</p>'
});