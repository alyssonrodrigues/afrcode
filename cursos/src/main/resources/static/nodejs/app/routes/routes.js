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

    app.get('/inserir', (request, response) => response.render('cadastrar-produto'));

    app.post('/', (request, response) => {
        var connection = app.services.connectionFactory();
        var produtosService = new app.services.ProdutosService(connection);
        var produto = request.body;
        produtosService.salvar(produto, (error, result) => {
            if (error) {
                console.log(error);
            }
            console.log(result);
            response.redirect('/');
        });
        connection.end();
    });
};