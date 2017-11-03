var express = require('express');
var load = require('express-load');
var bodyParser = require('body-parser');
var expressValidator = require('express-validator');
var resourceNotFoundHandler = require('./resource-not-found-handler');
var errorHandler = require('./error-handler');

module.exports = () => {
    var app = express();

    app.use('/static', express.static('public'));
    app.set('view engine', 'ejs');
    app.set('views', './app/views');
    app.use(bodyParser.urlencoded({
        extended: true
    }));
    app.use(bodyParser.json());
    app.use(expressValidator());

    load('services', {cwd: 'app'})
        .then('controllers')
        .then('routes')
        .into(app);

    app.use(resourceNotFoundHandler());
    app.use(errorHandler());
    
    return app;
};