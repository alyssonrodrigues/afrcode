module.exports = (app) => {
    var produtosController = new app.controllers.ProdutosController(app);
    app.get('/', produtosController.recuperarTodos.bind(produtosController));
    app.get('/inserir', produtosController.inserir.bind(produtosController));
    app.post('/', produtosController.salvar.bind(produtosController));
    app.get('/editar/:produtoId', produtosController.editar.bind(produtosController));
    app.get('/remover/:produtoId', produtosController.remover.bind(produtosController));
};