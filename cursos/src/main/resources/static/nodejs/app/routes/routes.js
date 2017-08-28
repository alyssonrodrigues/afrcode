module.exports = (app) => {
    app.get('/', (request, response) => {
        var connection = app.services.connectionFactory();
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