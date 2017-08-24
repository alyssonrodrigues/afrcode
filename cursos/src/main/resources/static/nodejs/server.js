var app = require('express')();

app.get('/', (request, response) => {
    response.sendFile('/home/alysson/cursosweb/nodejs/public/index.html');
});

var serverPort = 4000;
var serverHostname = 'localhost';
app.listen(serverPort, serverHostname, () => {
    console.log(`Server running at "http://${serverHostname}:${serverPort}"...`);
});