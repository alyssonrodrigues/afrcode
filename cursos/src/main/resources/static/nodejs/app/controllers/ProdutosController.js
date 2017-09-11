class ProdutosController {
    constructor(app) {
        this._app = app;
    }

    _handleError(error, next) {
        if (error) {
            console.log(error);
            next(error);
        }
        return error;
    }

    inserir(request, response) { 
        response.render('cadastrar-produto', 
            {
                id: '',
                titulo: '',
                descricao: '',
                preco: '',
                errors: ''
            }
        );
    }

    recuperarTodos(request, response, next) {
        let connection = this._app.services.connectionFactory();
        let produtosService = new this._app.services.ProdutosService(connection);
        produtosService.recuperarTodos((error, result) => {
            if (this._handleError(error, next)) return;
            response.format(
                {
                    html: () => response.render('produtos', {
                        produtos: result
                    }),
                    json: () => response.json(result)
                }
            );
        });
        connection.end();
    }

    _validar(request) {
        request.assert('titulo', 'Título é obrigatório!').notEmpty();
        request.assert('preco', 'Preço não é um número decimal válido (999.99)!')
            .isFloat();
        request.assert('descricao', 'Descrição é obrigatória!').notEmpty();
        return request.validationErrors();
    }

    salvar(request, response, next) {
        let produto = request.body;
        let errors = this._validar(request);
        if (errors) {
            response.format(
                {
                    html: () => 
                        response.status(400).render('cadastrar-produto',
                        {
                            id: produto.id,
                            titulo: produto.titulo,
                            descricao: produto.descricao,
                            preco: produto.preco,
                            errors: errors
                        }),
                    json: () => response.status(400).json(errors)
                }
            );
            return;
        }
        
        let connection = this._app.services.connectionFactory();
        let produtosService = new this._app.services.ProdutosService(connection);
        produtosService.salvar(produto, (error, result) => {
            if (this._handleError(error, next)) return;
            this._app.get('io').emit('reload', produto);
            response.redirect('/');
        });
        connection.end();
    }

    editar(request, response, next) {
        let connection = this._app.services.connectionFactory();
        let produtosService = new this._app.services.ProdutosService(connection);
        produtosService.recuperarPorId(request.params.produtoId, (error, result) => {
            if (this._handleError(error, next)) return;
            response.render('cadastrar-produto', {
                id: result[0].id,
                titulo: result[0].titulo,
                descricao: result[0].descricao,
                preco: result[0].preco,
                errors: ''
            });
        });
        connection.end();
    }

    remover(request, response, next) {
        let connection = this._app.services.connectionFactory();
        let produtosService = new this._app.services.ProdutosService(connection);
        produtosService.remover(request.params.produtoId, (error, result) => {
            if (this._handleError(error, next)) return;
            response.redirect('/');
        });
        connection.end();
    }
}

module.exports = () => ProdutosController;