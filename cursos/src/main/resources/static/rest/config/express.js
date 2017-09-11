var express = require('express');
var consign = require('consign');
var bodyParser = require('body-parser');
var expressValidator = require('express-validator');
var resourceNotFoundHandler = require('./resource-not-found-handler');
var errorHandler = require('./error-handler');

module.exports = () => {
    var app = express();
    
    app.use(bodyParser.urlencoded({
        extended: true
    }));
    app.use(bodyParser.json());
    app.use(expressValidator());

    consign({cwd: 'app'})
        .include('services')
        .then('controllers')
        .then('routes')
        .into(app);


    app.use(resourceNotFoundHandler());
    app.use(errorHandler());
    
    return app;
};