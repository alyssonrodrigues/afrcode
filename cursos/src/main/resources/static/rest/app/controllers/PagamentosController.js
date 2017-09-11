class PagamentosController {
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

    recuperarTodos(request, response, next) {
        let connection = this._app.services.connectionFactory();
        let pagamentosService = new this._app.services.PagamentosService(connection);
        pagamentosService.recuperarTodos((error, result) => {
            if (this._handleError(error, next)) return;
            response.json(result);
        });
        connection.end();
    }

    _validar(request) {
        request.assert('valor', 'Valor não é um número decimal válido (999.99)!')
            .isFloat();
        request.assert('descricao', 'Descrição é obrigatória!').notEmpty();
        request.assert('data', 'Data é obrigatória!').notEmpty();
        return request.validationErrors();
    }

    salvar(request, response, next) {
        let pagamento = request.body;
        let errors = this._validar(request);
        if (errors) {
            response.status(400).json(errors)
            return;
        }
        
        let connection = this._app.services.connectionFactory();
        let pagamentosService = new this._app.services.PagamentosService(connection);
        pagamentosService.salvar(pagamento, (error, result) => {
            if (this._handleError(error, next)) return;
            response.json(pagamento);
        });
        connection.end();
    }

    remover(request, response, next) {
        let connection = this._app.services.connectionFactory();
        let pagamentosService = new this._app.services.PagamentosService(connection);
        pagamentosService.remover(request.params.pagamentoId, (error, result) => {
            if (this._handleError(error, next)) return;
            response.json(request.params.pagamentoId);
        });
        connection.end();
    }
}

module.exports = () => PagamentosController;