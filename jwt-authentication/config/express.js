var express = require('express')
var cors = require('cors')
var consign = require('consign')
var bodyParser = require('body-parser')
var resourceNotFoundHandler = require('./resource-not-found-handler')
var errorHandler = require('./error-handler')

module.exports = () => {
  var app = express()

  app.set('view engine', 'ejs')
  app.set('views', './src/views')

  app.use(cors({origin: '*'}))

  app.use(bodyParser.urlencoded({
    extended: true
  }))
  app.use(bodyParser.json())

  consign({cwd: 'src'})
    .include('services')
    .then('routes')
    .into(app)

  app.use(resourceNotFoundHandler())
  app.use(errorHandler())

  return app
}
