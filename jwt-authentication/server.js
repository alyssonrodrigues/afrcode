var app = require('./config/express')()
var http = require('http').Server(app)

var serverPort = 8080
var serverHostname = 'localhost'
http.listen(serverPort, serverHostname, () => {
  console.log(`Server running at "http://${serverHostname}:${serverPort}"...`)
})
