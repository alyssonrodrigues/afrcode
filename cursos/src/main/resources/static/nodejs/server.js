var app = require('express')();

app.set('view engine', 'ejs');

app.get('/', (request, response) => {
    response.render('index.ejs');
});

var serverPort = 4000;
var serverHostname = 'localhost';
app.listen(serverPort, serverHostname, () => {
    console.log(`Server running at "http://${serverHostname}:${serverPort}"...`);
});