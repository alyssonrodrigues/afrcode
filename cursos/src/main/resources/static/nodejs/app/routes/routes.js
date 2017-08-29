module.exports = (app) => {
    function _handleError(error) {
        if (error) {
            console.log(error);
            throw new Error(JSON.stringify(error));
        }
    }
    app.get('/', (request, response) => {
        var connection = app.services.connectionFactory();
        var produtosService = new app.services.ProdutosService(connection);
        produtosService.recuperarTodos((error, result) => {
            _handleError(error);
            response.format({
                html: () => response.render('produtos', {
                    produtos: result
                }),
                json: () => response.json(result)
            });
        });
        connection.end();
    });

    app.get('/inserir', (request, response) => 
        response.render('cadastrar-produto', {
            id: '',
            titulo: '',
            descricao: '',
            preco: ''
        })
    );

    app.post('/', (request, response) => {
        var connection = app.services.connectionFactory();
        var produtosService = new app.services.ProdutosService(connection);
        var produto = request.body;
        produtosService.salvar(produto, (error, result) => {
            _handleError(error);
            response.redirect('/');
        });
        connection.end();
    });

    app.get('/editar/:produtoId', (request, response) => {
        var connection = app.services.connectionFactory();
        var produtosService = new app.services.ProdutosService(connection);
        produtosService.recuperarPorId(request.params.produtoId, (error, result) => {
            _handleError(error);
            response.render('cadastrar-produto', {
                id: result[0].id,
                titulo: result[0].titulo,
                descricao: result[0].descricao,
                preco: result[0].preco
            });
        });
        connection.end();
    });

    app.get('/remover/:produtoId', (request, response) => {
        var connection = app.services.connectionFactory();
        var produtosService = new app.services.ProdutosService(connection);
        produtosService.remover(request.params.produtoId, (error, result) => {
            _handleError(error);
            response.redirect('/');
        });
        connection.end();
    });
};