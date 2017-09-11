module.exports = (app) => {
    var pagamentosController = new app.controllers.PagamentosController(app);
    app.get('/', pagamentosController.recuperarTodos.bind(pagamentosController));
    app.post('/', pagamentosController.salvar.bind(pagamentosController));
    app.get('/remover/:pagamentoId', pagamentosController.remover.bind(pagamentosController));
};