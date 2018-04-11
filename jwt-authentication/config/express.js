const express = require('express')
const cors = require('cors')
const consign = require('consign')
const bodyParser = require('body-parser')
const resourceNotFoundHandler = require('./resource-not-found-handler')
const errorHandler = require('./error-handler')

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
