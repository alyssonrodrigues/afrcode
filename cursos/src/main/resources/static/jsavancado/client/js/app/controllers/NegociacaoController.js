class NegociacaoController {
    constructor() {
        let $ = document.querySelector.bind(document);
        
        this._inputData = $("#data");
        this._inputQuantidade = $("#quantidade");
        this._inputValor = $("#valor");
         
        this._negociacoesList = new Bind(
            new NegociacoesList(), 
            new NegociacoesView($("#negociacoesView")), 
            "adiciona", "esvazia", "ordena", "inverteOrdem");
       
        this._mensagem = new Bind(
            new Mensagem(), new MensagemView($("#mensagemView")),
            "texto"); 
        
        this._negociacoesService = new NegociacoesService();
        this._ordemAtual = "";
        this._constants = new Constants();
        this._connectionFactory = new ConnectionFactory(this._constants.dbName, 
        		this._constants.dbVersion, 
        		this._constants.negociacaoStoreName);
        this._listaTodos();
    }
    
    _listaTodos() {
        this._connectionFactory.getConnection()
    		.then(connection => new NegociacaoDao(connection, this._constants))
    		.then(negociacaoDao => negociacaoDao.listaTodos())
    		.then(negociacoes => negociacoes.forEach(
    			negociacao => this._negociacoesList.adiciona(negociacao)));
    }
    
    adiciona(event) {
        event.preventDefault();
        let negociacao = new Negociacao(
				DateHelper.textoParaData(this._inputData.value),
				this._inputQuantidade.value,
	            this._inputValor.value);
    	this._connectionFactory.getConnection()
    		.then(connection => new NegociacaoDao(connection, this._constants))
    		.then(negociacaoDao => negociacaoDao.adiciona(negociacao))
            .then(() => {
            	this._negociacoesList.adiciona(negociacao);
            	this._mensagem.texto = "Negociação incluída com sucesso!";
            	this._limpaFormulario();
            }).catch(error => this._mensagem.texto = error);
    }
    
    apaga() {
    	this._connectionFactory.getConnection()
			.then(connection => new NegociacaoDao(connection, this._constants))
			.then(negociacaoDao => negociacaoDao.listaTodos()
				.then(negociacoes => negociacaoDao.removeNegociacoes(negociacoes)
					.then(() => {
						this._negociacoesList.esvazia();
						this._mensagem.texto = "Negociações apagadas com sucesso!";
					}).catch(error => this._mensagem.texto = error)));
    }
    
    importaNegociacoes() {
    	this._connectionFactory.getConnection()
    		.then(connection => new NegociacaoDao(connection, this._constants))
    		.then(negociacaoDao => 
    			this._negociacoesService.getTodasNegociacoes()
    				.then(negociacoes => 
    					negociacaoDao.adicionaNegociacoes(negociacoes)
    						.then(() => {
    							negociacoes.forEach(negociacao => 
    								this._negociacoesList.adiciona(negociacao));
    							this._mensagem.texto = "Negociações importadas com sucesso!";
    						})
    						.catch(error => this._mensagem.texto = error))); 
    }
    
    ordena(coluna) {
        if(this._ordemAtual == coluna) {
            this._negociacoesList.inverteOrdem();
        } else {
            this._negociacoesList.ordena((a, b) => a[coluna] - b[coluna]);    
        }
        this._ordemAtual = coluna;
    }
    
    _limpaFormulario() {
        this._inputData.value = "";
        this._inputQuantidade.value = 1;
        this._inputValor.value = 0.0;
        this._inputData.focus();   
    }
}