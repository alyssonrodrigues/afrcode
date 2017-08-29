class ProdutosController {
    constructor(app) {
        this._app = app;
    }

    _handleError(error) {
        if (error) {
            console.log(error);
            throw new Error(JSON.stringify(error));
        }
    }

    inserir(request, response) { 
        response.render('cadastrar-produto', 
            {
                id: '',
                titulo: '',
                descricao: '',
                preco: ''
            }
        );
    }

    recuperarTodos(request, response) {
        var connection = this._app.services.connectionFactory();
        var produtosService = new this._app.services.ProdutosService(connection);
        produtosService.recuperarTodos((error, result) => {
            this._handleError(error);
            response.format({
                html: () => response.render('produtos', {
                    produtos: result
                }),
                json: () => response.json(result)
            });
        });
        connection.end();
    }

    salvar(request, response) {
        var connection = this._app.services.connectionFactory();
        var produtosService = new this._app.services.ProdutosService(connection);
        var produto = request.body;
        produtosService.salvar(produto, (error, result) => {
            this._handleError(error);
            response.redirect('/');
        });
        connection.end();
    }

    editar(request, response) {
        var connection = this._app.services.connectionFactory();
        var produtosService = new this._app.services.ProdutosService(connection);
        produtosService.recuperarPorId(request.params.produtoId, (error, result) => {
            this._handleError(error);
            response.render('cadastrar-produto', {
                id: result[0].id,
                titulo: result[0].titulo,
                descricao: result[0].descricao,
                preco: result[0].preco
            });
        });
        connection.end();
    }

    remover(request, response) {
        var connection = this._app.services.connectionFactory();
        var produtosService = new this._app.services.ProdutosService(connection);
        produtosService.remover(request.params.produtoId, (error, result) => {
            this._handleError(error);
            response.redirect('/');
        });
        connection.end();
    }
}

module.exports = () => ProdutosController;