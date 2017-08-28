var mysql = require('mysql');
module.exports = () => {
    var connection = mysql.createConnection({
        host: 'localhost',
        user: 'root',
        password: 'M03y08s14',
        database: 'nodejsapp'
    });
    return connection;
};