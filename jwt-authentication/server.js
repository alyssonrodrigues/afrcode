const app = require('./config/express')()
const http = require('http').Server(app)

const serverPort = 8080
const serverHostname = 'localhost'
http.listen(serverPort, serverHostname, () => {
  console.log(`Server running at "http://${serverHostname}:${serverPort}"...`)
})
