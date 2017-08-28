class ProdutosService {
    constructor(connection) {
        this._connection = connection;
    }

    recuperarTodos(callback) {
        this._connection.query('select * from produtos', callback);
    }
}

module.exports = () => ProdutosService;