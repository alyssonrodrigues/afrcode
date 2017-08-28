module.exports = (app) => {
    app.get('/', (request, response) => {
        var mysql = require('mysql');
        var connection = mysql.createConnection({
            host: 'localhost',
            user: 'root',
            password: 'M03y08s14',
            database: 'nodejsapp'
        });
        connection.query('select * from produtos', (error, result) => {
            if (error) {
                console.log(error);
            }
            console.log(result);
        })
        response.render('index.ejs');
    });
};