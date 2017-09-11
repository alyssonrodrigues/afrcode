var app = require('./config/express')();

var hostname = 'localhost';
var port = 3000;
app.listen(port, hostname, () => {
    console.log(`Server running at http://${hostname}:${port}...`);
});