var express = require('express');
var consign = require('consign');

module.exports = () => {
    var app = express();
    consign()
        .include('app/routes')
        .into(app);
    return app;
};