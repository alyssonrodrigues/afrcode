var mysql = require('mysql');

const connectionFactory = () => {
    var connection = mysql.createConnection({
        host: 'localhost',
        user: 'root',
        password: 'M03y08s14',
        database: 'nodejsapp'
    });
    return connection;
};

module.exports = () => connectionFactory;