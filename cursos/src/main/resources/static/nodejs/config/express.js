var express = require('express');
var load = require('express-load');
var bodyParser = require('body-parser');

module.exports = () => {
    var app = express();

    app.set('view engine', 'ejs');
    app.set('views', './app/views');
    app.use(bodyParser.urlencoded({
        extended: true
    }));

    load('services', {cwd: 'app'})
        .then('controllers')
        .then('routes')
        .into(app);
    
    return app;
};