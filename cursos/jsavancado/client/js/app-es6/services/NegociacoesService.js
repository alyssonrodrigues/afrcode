class NegociacoesService {
	constructor() {
		this._httpService = new HttpService();
        this._constants = new Constants();
        this._connectionFactory = new ConnectionFactory(this._constants.dbName, 
        		this._constants.dbVersion, 
        		this._constants.negociacaoStoreName);
	}
	
	adiciona(negociacao, negociacoesList) {
		return new Promise((resolve, reject) => {
	    	this._connectionFactory.getConnection()
    			.then(connection => new NegociacaoDao(connection, this._constants))
    			.then(negociacaoDao => negociacaoDao.adiciona(negociacao))
    			.then(() => {
    				negociacoesList.adiciona(negociacao);
    				resolve();
    			}).catch(error => reject(error));
		});
	}
	
	removeTodos(negociacoesList) {
		return new Promise((resolve, reject) => {
	    	this._connectionFactory.getConnection()
				.then(connection => new NegociacaoDao(connection, this._constants))
				.then(negociacaoDao => negociacaoDao.removeTodos())
				.then(() => {
					negociacoesList.esvazia();
					resolve();
				}).catch(error => reject(error));
		});
	}
	
	importaNegociacoes(negociacoesList) {
		return new Promise((resolve, reject) => {
	    	this._connectionFactory.getConnection()
    			.then(connection => new NegociacaoDao(connection, this._constants))
    			.then(negociacaoDao => this.getTodasNegociacoes()
    				.then(negociacoes =>  negociacoes.filter(negociacao => 
    					!negociacoesList.contains(negociacao)))
    				.then(negociacoes => 
    					negociacaoDao.adicionaNegociacoes(negociacoes)
    						.then(() => {
    							negociacoes.forEach(negociacao => 
    								negociacoesList.adiciona(negociacao));
    							resolve();
    						})
    				).catch(error => reject(error))
    			).catch(error => reject(error)); 
		});
	}
	
	listaTodos(negociacoesList) {
		return new Promise((resolve, reject) => {
	        this._connectionFactory.getConnection()
	        	.then(connection => new NegociacaoDao(connection, this._constants))
	        	.then(negociacaoDao => negociacaoDao.listaTodos())
	        	.then(negociacoes => { 
	        		negociacoes.forEach(negociacao => 
	        			negociacoesList.adiciona(negociacao));
	        		resolve();
	        	}).catch(error => reject(error));
		});
	}
	
	getNegociacoesSemanaRetrasada() {
    	return this._getNegociacoes("negociacoes/retrasada");
	}

	getNegociacoesSemanaAnterior() {
    	return this._getNegociacoes("negociacoes/anterior");
	}

	getNegociacoesSemana() {
    	return this._getNegociacoes("negociacoes/semana");
	}
	
	getTodasNegociacoes() {
    	let promises = [this.getNegociacoesSemana(),
    		this.getNegociacoesSemanaAnterior(),
    		this.getNegociacoesSemanaRetrasada()];
    	
    	return new Promise((resolve, reject) => 
    		Promise.all(promises)
    		.then(negociacoesArrayDeArrays => 
    			resolve(negociacoesArrayDeArrays.reduce(
    				(negociacoes, umNegociacoesArray) => 
    					negociacoes.concat(umNegociacoesArray), []))
    		).catch(error => reject(error)));
	}
	
	_getNegociacoes(url) {
		return new Promise((resolve, reject) => 
			this._httpService.get(url)
			.then(negociacoesStringArray =>
				resolve(negociacoesStringArray.map(negociacaoStr => 
					new Negociacao(
						new Date(negociacaoStr.data), 
						negociacaoStr.quantidade, 
						negociacaoStr.valor)))
			).catch(error => reject(error)));
	}
}