var connectionFactory = require('../services/connection-factory.js');
module.exports = (app) => {
    app.get('/', (request, response) => {
        var connection = connectionFactory();
        connection.query('select * from produtos', (error, result) => {
            if (error) {
                console.log(error);
            }
            console.log(result);
            response.render('index.ejs', {
                produtos: result
            });
        });
        connection.end();
    });
};