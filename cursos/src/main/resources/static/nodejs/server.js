var app = require('./config/express')();

var serverPort = 3000;
var serverHostname = 'localhost';
app.listen(serverPort, serverHostname, () => {
    console.log(`Server running at "http://${serverHostname}:${serverPort}"...`);
});