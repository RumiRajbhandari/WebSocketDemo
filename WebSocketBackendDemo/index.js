var express = require('express');
var socket = require('socket.io')

//App Setup
var app = express();
//listen to port no 4000 and call callback function
var server = app.listen(4000, function(){
    console.log("Listening to requests on port 4000");
});

//static files
app.use(express.static('public'));

//Socket setup
var io = socket(server);
io.on('connection', function(socket){
    console.log('made socket connection', socket.id);
    
    // socket.on('chat', function(data){
    //     io.sockets.emit('chat',data)

    // });

    socket.on('typing', function(data){
        socket.broadcast.emit('typing', data)
    });

    socket.on('join', function(username){
        io.sockets.emit('userjoinedthechat', username + " : has joined the chat")

    });

    socket.on('chatting', function(username, message){
        let  msg = {"message":message, "username":username}
        io.sockets.emit('chatting',msg)

    });
});