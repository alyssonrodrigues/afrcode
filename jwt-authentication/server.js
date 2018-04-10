var app = require('./config/express')();
var http = require('http').Server(app);

var io = require('socket.io')(http);
app.set('io', io);

var serverPort = 8080;
var serverHostname = 'localhost';
http.listen(serverPort, serverHostname, () => {
    console.log(`Server running at "http://${serverHostname}:${serverPort}"...`);
});