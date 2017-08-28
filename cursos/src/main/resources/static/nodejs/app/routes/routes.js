module.exports = (app) => {
    app.get('/', (request, response) => {
        var connection = app.services.connectionFactory();
        var produtosService = new app.services.ProdutosService(connection);
        produtosService.recuperarTodos((error, result) => {
            if (error) {
                console.log(error);
            }
            console.log(result);
            response.render('produtos', {
                produtos: result
            });
        });
        connection.end();
    });

    app.get('/produtos/inserir', (request, response) => response.render('cadastrar-produto'));

    app.post('/produtos/salvar', (request, response) => {
        var connection = app.services.connectionFactory();
        var produtosService = new app.services.ProdutosService(connection);
        var produto = request.body;
        console.log(produto);
        produtosService.recuperarTodos((error, result) => {
            if (error) {
                console.log(error);
            }
            console.log(result);
            response.render('produtos', {
                produtos: result
            });
        });
        connection.end();
    });
};