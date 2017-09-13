module.exports = (app) => {
    var pagamentosController = new app.controllers.PagamentosController(app);
    app.get('/api/pagamentos', pagamentosController.recuperarTodos.bind(pagamentosController));
    app.post('/api/pagamentos', pagamentosController.salvar.bind(pagamentosController));
    app.get('/api/pagamento/:pagamentoId', pagamentosController.recuperarPorId.bind(pagamentosController));
    app.get('/api/pagamento/remover/:pagamentoId', pagamentosController.remover.bind(pagamentosController));
};