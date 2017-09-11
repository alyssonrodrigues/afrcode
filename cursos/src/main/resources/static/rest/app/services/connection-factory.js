var mysql = require('mysql');

const connectionFactory = () => {
    var env = process.env.NODE_ENV || '';
    var connection = mysql.createConnection({
        host: 'localhost',
        user: 'root',
        password: 'M03y08s14',
        database: `restapp${env}`
    });
    return connection;
};

module.exports = () => connectionFactory;