class ProdutosService {
    constructor(connection) {
        this._connection = connection;
    }

    recuperarTodos(callback) {
        this._connection.query('select * from produtos', callback);
    }

    salvar(produto, callback) {
        if (produto.id) {
            this._connection.query('update produtos set ? where id = ?', 
                [{
                    titulo: produto.titulo,
                    descricao: produto.descricao,
                    preco: produto.preco
                }, 
                produto.id], 
                callback);
        } else {
            this._connection.query('insert into produtos set ?', 
            {
                titulo: produto.titulo,
                descricao: produto.descricao,
                preco: produto.preco
            }, 
            callback);
        }
    }

    recuperarPorId(produtoId, callback) {
        this._connection.query('select * from produtos where id = ?', produtoId, callback);
    }

    remover(produtoId, callback) {
        this._connection.query('delete from produtos where id = ?', produtoId, callback);
    }
}

module.exports = () => ProdutosService;