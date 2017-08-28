module.exports = (app) => {
    app.get('/', (request, response) => {
        var connection = app.services.connectionFactory();
        var produtosService = new app.services.ProdutosService(connection);
        produtosService.recuperarTodos((error, result) => {
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