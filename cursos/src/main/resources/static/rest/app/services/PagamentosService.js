class PagamentosService {
    constructor(connection) {
        this._connection = connection;
    }

    recuperarTodos(callback) {
        this._connection.query('select * from pagamentos', callback);
    }

    salvar(pagamento, callback) {
        if (pagamento.id) {
            this._connection.query('update pagamentos set ? where id = ?', 
                [{
                    valor: pagamento.valor,
                    data: pagamento.data,
                    descricao: pagamento.descricao,
                    status: pagamento.status
                }, 
                pagamento.id], 
                callback);
        } else {
            this._connection.query('insert into pagamentos set ?', 
            {
                valor: pagamento.valor,
                data: pagamento.data,
                descricao: pagamento.descricao,
                status: 0
            }, 
            callback);
        }
    }

    recuperarPorId(pagamentoId, callback) {
        this._connection.query('select * from pagamentos where id = ?', pagamentoId, callback);
    }

    remover(pagamentoId, callback) {
        this._connection.query('delete from pagamentos where id = ?', pagamentoId, callback);
    }
}

module.exports = () => PagamentosService;